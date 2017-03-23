package com.hyperiongray.sitehound.backend.service.nlp;

import com.beust.jcommander.internal.Sets;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;

import java.util.*;

/**
 * Created by tomas on 9/20/15.
 */
@Deprecated
public class NlpOutput{

	private Metadata metadata;
	private String desc;
	private String urlDesc;
	private String htmlAsText;
	private List<String> words;
	private Map<String, Integer> wordsFrequency = new HashMap<>();
	private Integer score;
	private Set<String> categories = new HashSet<>();
	private String language;
	private List<String> links;

	public NlpOutput(Metadata metadata){
		this.metadata = metadata;
	}

	public String getDesc(){
		return desc;
	}

	public void setDesc(String desc){
		this.desc=desc;
	}

	public String getUrlDesc(){
		return urlDesc;
	}

	public void setUrlDesc(String urlDesc){
		this.urlDesc=urlDesc;
	}

	public String getHtmlAsText(){
		return htmlAsText;
	}

	public void setHtmlAsText(String htmlAsText){
		this.htmlAsText=htmlAsText;
	}

	public List<String> getWords(){
		return words;
	}

	public void setWords(List<String> words){
		this.words=words;
	}

	public Map<String, Integer> getWordsFrequency(){
		return wordsFrequency;
	}

	public void setWordsFrequency(Map<String, Integer> wordsFrequency){
		this.wordsFrequency=wordsFrequency;
	}

	@Override
	public String toString(){
		return "NlpOutput{" +
				       "metadata=" + metadata +
				       ", desc='" + desc + '\'' +
				       ", urlDesc='" + urlDesc + '\'' +
				       ", htmlAsText='" + (htmlAsText == null ? null : htmlAsText.substring(0,40))  + '\'' +
				       ", words=" + words +
				       ", wordsFrequency=" + wordsFrequency +
				       '}';
	}

	public Integer getScore(){
		return score;
	}

	public void setScore(Integer score){
		this.score=score;
	}
	public void setScore(Double score){
		if(score != null){
			this.score=score.intValue();
		}
	}

	public Set<String> getCategories(){
		if(categories==null)
			categories = Sets.newHashSet();
		return categories;
	}

	public void setCategories(Set<String> categories){
		this.categories=categories;
	}

	public void addCategories(Set<String> categories){
		this.getCategories().addAll(categories);
	}

	public void setLanguage(String language){
		this.language=language;
	}

	public String getLanguage(){
		return language;
	}

	public List<String> getLinks(){
		return links;
	}

	public void setLinks(List<String> links){
		this.links=links;
	}
}
