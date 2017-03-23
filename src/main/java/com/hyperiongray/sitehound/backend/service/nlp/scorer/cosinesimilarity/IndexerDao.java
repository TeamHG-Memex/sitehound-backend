package com.hyperiongray.sitehound.backend.service.nlp.scorer.cosinesimilarity;

import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.BytesRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tomas on 11/18/15.
 */
@Repository
public class IndexerDao{

	@Value( "${broadcrawler.scorer.index.directory}" ) private String indexDirectory;

	public static final String FIELD_CONTENT = "contents"; // name of the field to index
	public static final String FIELD_UUID = "uuid";

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexerDao.class);


	private String getDirectoryPath(String workspace, String jobId, RelevanceType relevanceType){
		return indexDirectory +"/"+ workspace.toUpperCase() + "_" + jobId + "_" + relevanceType.toString();
	}

	private IndexReader getIndexReader(String indexPath) throws IOException {
//		java.nio.file.Path indexDirectory = new UnixPath(indexPath);
		Path path = Paths.get(indexPath);
		NIOFSDirectory directory = new NIOFSDirectory(path);
		IndexReader indexReader = DirectoryReader.open(directory);
		return indexReader;
	}

	public void indexOnTheFly(String workspace, String jobId, String uuid, String textContent) throws IOException{

		String indexPath = getDirectoryPath(workspace, jobId, RelevanceType.ALL);
		Path indexDirectory = Paths.get(indexPath);
		NIOFSDirectory dir = new NIOFSDirectory(indexDirectory);
		Analyzer analyzer = new StandardAnalyzer(StandardAnalyzer.STOP_WORDS_SET);  // using stop words
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

		LOGGER.info("creating doc: " + uuid);
		IndexWriter indexWriter = new IndexWriter(dir, iwc);
		Document doc = createDocument(uuid, textContent);
		indexWriter.updateDocument(new Term("uuid", uuid), doc);
		indexWriter.commit();
		indexWriter.close();
	}


	public DocVector getDocVectorByDocId(String workspace, String jobId, RelevanceType relevanceType, String uuid, Map<String, Integer> allTerms) throws IOException{

		LOGGER.info("getting: " + uuid);
		DocVector docVector = new DocVector(allTerms);

		int docId = -1;
		TermsEnum termsEnum = null;
		try{

			String indexPath = getDirectoryPath(workspace, jobId, relevanceType);
			IndexReader indexReader = getIndexReader(indexPath);
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			TopDocs results = indexSearcher.search(new TermQuery(new Term("uuid", uuid)), 1);
			docId = results.scoreDocs[0].doc;

			Terms vector = indexReader.getTermVector(docId, IndexerDao.FIELD_CONTENT);
//			termsEnum = vector.iterator(termsEnum);
			if(vector!=null){
				termsEnum = vector.iterator();
				BytesRef text = null;
				while ((text = termsEnum.next()) != null) {
					String term = text.utf8ToString();
					int freq = (int) termsEnum.totalTermFreq();
					docVector.setEntry(term, freq);
				}
				docVector.normalize();
			}

		}
		catch(Exception e ){
			LOGGER.info("docId:" + docId);
			LOGGER.info("termsEnum:" + termsEnum);
			LOGGER.error("failing to get internal doc Id from Lucene", e);
			throw (new RuntimeException(e));
		}
		return docVector;
	}

	public void bulkIndex(String workspace, String jobId, RelevanceType relevanceType, List<TrainedCrawledUrl> trainedCrawledUrls) throws	IOException {
		String indexPath = getDirectoryPath(workspace, jobId, relevanceType);

		Path indexDirectory = Paths.get(indexPath);
		NIOFSDirectory dir = new NIOFSDirectory(indexDirectory);
		Analyzer analyzer = new StandardAnalyzer(StandardAnalyzer.STOP_WORDS_SET);  // using stop words
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

		iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

		IndexWriter indexWriter = new IndexWriter(dir, iwc);
		for(TrainedCrawledUrl trainedCrawledUrl: trainedCrawledUrls){
			Document doc = createDocument(trainedCrawledUrl.getUrl(), trainedCrawledUrl.getContent());
			indexWriter.addDocument(doc);
//			LOGGER.info(trainedCrawledUrl.toString());
		}
		indexWriter.commit();
		indexWriter.close();
	}


	private Document createDocument(String uuid, String textContent){
		Document doc =new Document();
		doc.add(getUuidField(uuid));
		doc.add(getContentField(textContent));
		return doc;
	}

	private Field getContentField(String textContent){
		FieldType fieldType = new FieldType();
//		fieldType.setIndexed(true);
//		fieldType.setIndexOptions(FieldInfo.IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
		fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
		fieldType.setStored(true);
		fieldType.setStoreTermVectors(true);
		fieldType.setTokenized(true);
		Field contentField = new Field(FIELD_CONTENT, textContent, fieldType);
		return contentField;
	}

	private Field getUuidField(String uuid){
//        FieldType fieldType= new FieldType();
//        fieldType.setIndexed(true);
//        fieldType.setStored(true);
//        fieldType.setStoreTermVectors(false);
//        fieldType.setTokenized(false);
//        Field field = new Field("uuid", uuid, fieldType);
//        Field field = new Field("id", id, Store.YES, Index.NOT_ANALYZED);
		Field field = new Field(FIELD_UUID, uuid, Field.Store.YES, Field.Index.ANALYZED);
		return field;
	}

	public DocVector[] getDocumentVectors(String workspace, String jobId, RelevanceType relevanceType, Map<String, Integer> allterms) throws IOException{
		String indexPath = getDirectoryPath(workspace, jobId, relevanceType);
		IndexReader indexReader=this.getIndexReader(indexPath);

		Integer totalNoOfDocumentInIndex = indexReader.maxDoc();
		DocVector[] docVector = new DocVector[totalNoOfDocumentInIndex];

		for (int docId = 0; docId < docVector.length; docId++) {
			docVector[docId] = new DocVector(allterms);
			Terms vector = indexReader.getTermVector(docId, IndexerDao.FIELD_CONTENT);
			if(vector==null){
				LOGGER.info("empty vector for docId: " + docId +". Skipping it...");
				continue;
			}

			TermsEnum termsEnum = null;
//			termsEnum = vector.iterator(termsEnum);
			termsEnum = vector.iterator();
			BytesRef text = null;
			while ((text = termsEnum.next()) != null) {
				String term = text.utf8ToString();
				int freq = (int) termsEnum.totalTermFreq();
				docVector[docId].setEntry(term, freq);
			}
			docVector[docId].normalize();
			LOGGER.debug("after normalization:" + docVector[docId] );
		}
		return docVector;
	}


	public Map<String, Integer> getAllTerms(String workspace, String jobId) throws IOException{
		Map<String, Integer> allTerms=new HashMap<>();
		IndexReader indexReader = null;
		try{
			String indexPath = getDirectoryPath(workspace, jobId, RelevanceType.ALL);
			indexReader = this.getIndexReader(indexPath);

			int totalNoOfDocumentInIndex=indexReader.maxDoc();
			int pos=0;
			for(int docId=0; docId < totalNoOfDocumentInIndex; docId++){

				Terms vector=indexReader.getTermVector(docId, IndexerDao.FIELD_CONTENT);
				if(vector==null)
					continue;
				TermsEnum termsEnum=null;
//				termsEnum=vector.iterator(termsEnum);
				termsEnum=vector.iterator();
				BytesRef text=null;
				while((text=termsEnum.next())!=null){
					String term=text.utf8ToString();
					allTerms.put(term, pos++);
				}
			}

			pos=0; //Update postition
			for(Map.Entry<String, Integer> s : allTerms.entrySet()){
				s.setValue(pos++);
			}

			return allTerms;
		}catch(NullPointerException npe){
			LOGGER.error("npe", npe);
			throw new RuntimeException("indexing failed NPE");
		}
		finally {
			indexReader.close();
		}
	}

}
