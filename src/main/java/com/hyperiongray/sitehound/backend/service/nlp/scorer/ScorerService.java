package com.hyperiongray.sitehound.backend.service.nlp.scorer;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.service.crawler.CrawledIndexService;
import com.hyperiongray.sitehound.backend.service.nlp.scorer.cosinesimilarity.IndexerDao;
import com.hyperiongray.sitehound.backend.service.nlp.scorer.cosinesimilarity.RelevanceType;
import com.hyperiongray.sitehound.backend.service.nlp.scorer.cosinesimilarity.SimilarityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tomas on 11/19/15.
 */
@Service
public class ScorerService{

	@Autowired private SimilarityService similarityService;
	@Autowired private CrawledIndexService crawledIndexService;
	@Autowired private IndexerDao indexerDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(ScorerService.class);

	private Map<RelevanceType, Predicate<TrainedCrawledUrl>> relevanceFilters = new HashMap();

	public ScorerService(){

		/** Index All */
		Predicate<TrainedCrawledUrl> allPredicate = new Predicate<TrainedCrawledUrl>(){
			@Override
			public boolean apply(TrainedCrawledUrl input){
				return true;
			}
		};
		relevanceFilters.put(RelevanceType.ALL, allPredicate);

		/** Index Relevant */
		Predicate<TrainedCrawledUrl> relevantPredicate = new Predicate<TrainedCrawledUrl>(){
			@Override
			public boolean apply(TrainedCrawledUrl input){
				return input.getRelevant() != null && input.getRelevant();
			}
		};
		relevanceFilters.put(RelevanceType.RELEVANT, relevantPredicate);

		/** Index Irrelevant */
		Predicate<TrainedCrawledUrl> irrelevantPredicate = new Predicate<TrainedCrawledUrl>(){
			@Override
			public boolean apply(TrainedCrawledUrl input){
				return input.getRelevant() != null && !input.getRelevant();
			}
		};
		relevanceFilters.put(RelevanceType.IRRELEVANT, irrelevantPredicate);

	}

	public void indexTrainedData(String workspaceId, String jobId) throws IOException{
		List<TrainedCrawledUrl> trainedDocuments = crawledIndexService.getTrainedDocuments(workspaceId);
		if(trainedDocuments.size()==0){
			LOGGER.warn("SKIPPING WORKSPACE:" + workspaceId +" JOB: " + jobId + "SINCE THERE WAS NOT TRAINED DATA");
			throw new RuntimeException("NO_TRAINED_DATA");
		}

		for(Map.Entry<RelevanceType, Predicate<TrainedCrawledUrl>> relevance: relevanceFilters.entrySet()){
			ImmutableList<TrainedCrawledUrl> trainedCrawledUrlsFiltered=FluentIterable.from(trainedDocuments).filter(relevance.getValue()).toList();
			indexerDao.bulkIndex(workspaceId, jobId, relevance.getKey(), trainedCrawledUrlsFiltered);
		}
	}

	public double score(String workspaceId, String jobId, String url, String text) throws IOException {

		Map<RelevanceType, List<Double>> relevanceTypeListMap = null;
			relevanceTypeListMap = similarityService.similarityCompare(workspaceId, jobId, url, text);

			double maxRelevantSimilarity = 0;
			for(Double similarity : relevanceTypeListMap.get(RelevanceType.RELEVANT)){
				maxRelevantSimilarity = similarity > maxRelevantSimilarity ? similarity : maxRelevantSimilarity;
			}

			double maxIrrelevantSimilarity = 0;
			for(Double similarity : relevanceTypeListMap.get(RelevanceType.IRRELEVANT)){
				maxIrrelevantSimilarity = similarity > maxIrrelevantSimilarity ? similarity : maxIrrelevantSimilarity;
			}

			LOGGER.info("scoring:" +  url);
			LOGGER.debug("score:" +  relevanceTypeListMap);

			Double score = maxRelevantSimilarity >= maxIrrelevantSimilarity ? maxRelevantSimilarity : -maxIrrelevantSimilarity;
			LOGGER.info("scored: " +  score + " for: " + url);
			return score * 100d;

	}

}
