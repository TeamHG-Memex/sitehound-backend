package com.hyperiongray.sitehound.backend.service.nlp.scorer.cosinesimilarity;

import com.beust.jcommander.internal.Maps;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tomas on 11/18/15.
 */
@Service
public class SimilarityService{

	@Autowired private IndexerDao indexerDao;
	private final Lock lock = new ReentrantLock();

	private static final Logger LOGGER = LoggerFactory.getLogger(SimilarityService.class);

	public Map<RelevanceType, List<Double>> similarityCompare(String workspace, String jobId, String url, String text) throws IOException{

		String uuid = UUID.randomUUID().toString().replace("-","");
		LOGGER.info("scoring: " + url);

		if(StringUtils.isBlank(text)){
			LOGGER.info("no content for: " + url);
			return getDummyRelevanceTypeListMap();
		}

		Map<String, Integer> allTerms;
		Map<RelevanceType, List<Double>> cosineSimilarity;

		try{
			lock.lock();
			LOGGER.info("indexing using temporary key: " + uuid);
			indexerDao.indexOnTheFly(workspace, jobId, uuid, text);

			LOGGER.info("getting all terms: " + uuid);
			allTerms = indexerDao.getAllTerms(workspace, jobId);

			LOGGER.info("getting docVectoryByDocId: " + uuid);
			DocVector docVector = indexerDao.getDocVectorByDocId(workspace, jobId, RelevanceType.ALL, uuid, allTerms);

			LOGGER.info("getting cosineSimilarity: " + uuid);
			cosineSimilarity = getCosineSimilarity(workspace, jobId, docVector, allTerms);

			LOGGER.info("returning cosineSimilarity: " + cosineSimilarity);
		}
		finally {
			lock.unlock();
		}

		return cosineSimilarity;

	}

	@NotNull
	private Map<RelevanceType, List<Double>> getDummyRelevanceTypeListMap() {
		Map<RelevanceType, List<Double>> dummyCosineSimilarity = new HashMap<>();
		List<Double> values = new ArrayList<>();
		values.add(0d);
		dummyCosineSimilarity.put(RelevanceType.RELEVANT, values);
		dummyCosineSimilarity.put(RelevanceType.IRRELEVANT, values);
		return dummyCosineSimilarity;
	}


	private Map<RelevanceType, List<Double>> getCosineSimilarity(String workspace, String jobId, DocVector docVector, Map<String, Integer> allTerms) throws IOException{

//		Map<String, Integer> allTerms = indexerDao.getAllTerms(workspace, jobId);
		List<Double> relevantSimilarity = compareAgainstSet(workspace, jobId, RelevanceType.RELEVANT, docVector, allTerms);
		List<Double> irrelevantSimilarity = compareAgainstSet(workspace, jobId, RelevanceType.IRRELEVANT, docVector, allTerms);

		Map<RelevanceType, List<Double>> relevanceTypeListMap = Maps.newHashMap();
		relevanceTypeListMap.put(RelevanceType.RELEVANT, relevantSimilarity);
		relevanceTypeListMap.put(RelevanceType.IRRELEVANT, irrelevantSimilarity);
		return relevanceTypeListMap;
	}


	private List<Double> compareAgainstSet(String workspace, String jobId, RelevanceType relevanceType, DocVector docVector, Map<String, Integer> allTerms) throws IOException{

		DocVector[] docVectors = indexerDao.getDocumentVectors(workspace, jobId, relevanceType, allTerms); // getting document vectors
		List<Double> results = new LinkedList<>();
		for(int i = 0; i < docVectors.length; i++){
			double cosineSimilarity = CosineSimilarity.CosineSimilarity(docVector, docVectors[i]);
			results.add(cosineSimilarity);
			LOGGER.info("Score for "+ relevanceType +" between currentDoc and "+i+"  = " + cosineSimilarity);
		}
		return results;
	}

}
