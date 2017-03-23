package com.hyperiongray.sitehound.backend.service.nlp.tika;

import java.util.Set;

/**
 * Created by tomas on 9/25/15.
 */
public class TikaOutput{
	private String title;
	private Set<String> links;
	private String pageMetadata;

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public Set<String> getLinks(){
		return links;
	}

	public void setLinks(Set<String> links){
		this.links=links;
	}

	@Override
	public String toString(){
		return "TikaOutput{" +
				       "title='" + title + '\'' +
				       ", links=" + links +
				       '}';
	}

	public void setPageMetadata(String pageMetadata){
		this.pageMetadata=pageMetadata;
	}

	public String getPageMetadata(){
		return pageMetadata;
	}
}
