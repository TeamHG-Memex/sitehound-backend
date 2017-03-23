package com.hyperiongray.sitehound.backend.service.crawler.searchengine;

import com.beust.jcommander.internal.Lists;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.GenericCrawlMongoRepository;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tomas on 2/4/16.
 */

@Service
public class UrlDeduplicator{

	@Autowired private GenericCrawlMongoRepository genericCrawlMongoRepository;

	public List<SearchEngineCrawlResult> filterFromExistentResults(String workspaceId, Constants.CrawlType crawlType, List<SearchEngineCrawlResult> searchEngineCrawlResults){
		List<String> urls = Lists.newArrayList();
		for(SearchEngineCrawlResult searchEngineCrawlResult: searchEngineCrawlResults){
			urls.add(searchEngineCrawlResult.getUrl());
		}

		final List<String> existentUrls;

		existentUrls = genericCrawlMongoRepository.filterByUrl(workspaceId, crawlType, urls);

		/*
		Predicate<SearchEngineCrawlResult> predicate = new Predicate<SearchEngineCrawlResult>(){
			@Override
			public boolean test(SearchEngineCrawlResult searchEngineCrawlResult){
				return existentUrls.contains(searchEngineCrawlResult.getUrl());
			}
		};
		searchEngineCrawlResults.removeIf(predicate);
		return searchEngineCrawlResults;
	*/

		ImmutableList<SearchEngineCrawlResult> filtered= FluentIterable.from(searchEngineCrawlResults).filter(new Predicate<SearchEngineCrawlResult>(){
			@Override
			public boolean apply(SearchEngineCrawlResult input){
				return !existentUrls.contains(input.getUrl());
			}
		}).toList();
		return filtered;
	}

}
