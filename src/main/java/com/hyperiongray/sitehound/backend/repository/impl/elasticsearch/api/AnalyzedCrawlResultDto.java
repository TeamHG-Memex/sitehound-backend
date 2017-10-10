package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api;

import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tomas on 2/11/16.
 */
public class AnalyzedCrawlResultDto{


	private CrawlResultDto crawlResultDto;
	private String text;
	private Set<String> links = Sets.newHashSet();
	private List<String> words = Lists.newLinkedList();
	private Map<String, Integer> wordsFrequency = Maps.newHashMap();
	private Set<String> categories = Sets.newHashSet();
	private String language;

	public AnalyzedCrawlResultDto(CrawlResultDto crawlResultDto){
		this.crawlResultDto = crawlResultDto;
	}

	public AnalyzedCrawlResultDto(){}

	public CrawlResultDto getCrawlResultDto(){
		return crawlResultDto;
	}

	public void setCrawlResultDto(CrawlResultDto crawlResultDto) {
		this.crawlResultDto = crawlResultDto;
	}

	public String getText(){
		return text;
	}

	public void setText(String text){
		this.text = text;
	}

	public List<String> getWords(){
		return words;
	}

	public void setWords(List<String> words){
		this.words = words;
	}

	public Map<String, Integer> getWordsFrequency(){
		return wordsFrequency;
	}

	public void setWordsFrequency(Map<String, Integer> wordsFrequency){
		this.wordsFrequency = wordsFrequency;
	}

	public Set<String> getCategories(){
		return categories;
	}

	public void setCategories(Set<String> categories){
		this.categories = categories;
	}

	public void addCategory(String category){
		this.categories.add(category);
	}

	public void addCategories(Set<String> categories){
		this.categories.addAll(categories);
	}

	public String getLanguage(){
		return language;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public Set<String> getLinks(){
		return links;
	}

	public void setLinks(Set<String> links){
		this.links = links;
	}


	@Override
	public String toString(){
		return "AnalyzedCrawlResultDto{" +
				       "crawlResultDto=" + crawlResultDto +
				       ", text='" + text + '\'' +
				       ", links=" + links +
				       ", words=" + words +
				       ", wordsFrequency=" + wordsFrequency +
				       ", categories=" + categories +
				       ", language='" + language + '\'' +
				       '}';
	}
}
