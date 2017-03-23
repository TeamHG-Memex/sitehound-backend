package com.hyperiongray.sitehound.backend.service.nlp;

import com.beust.jcommander.internal.Sets;
import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tomas on 9/25/15.
 */
@Service
@Deprecated
public class WebCategoryService{


	private Map<String, List<String>> webCategories = Maps.newHashMap();


	@PostConstruct
	public void init() throws IOException {
		Iterator<File> fileIterator = FileUtils.iterateFiles(new File("config/web-category-word-list"), null, true);
		while(fileIterator.hasNext()){
			File file=fileIterator.next();
			List<String> lines = FileUtils.readLines(file);
			webCategories.put(file.getName().substring(0, file.getName().length()-4), lines);
		}
	}


	/***
	 * Categorization based on finding relevant words in the footer (identifying it by the class or id footer)
	 * @param doc
	 * @return
	 */
	public Set<String> categorizeByFooter(final Document doc){

		Set<String> webCategoriesFound = Sets.newHashSet();

		//either class or id "footer"
		Elements footerElement = doc.body().select("#footer");
		if(footerElement!=null){
			webCategoriesFound.addAll(categorizeByText(footerElement.text()));
		}

		footerElement=doc.body().select(".footer");
		if(footerElement!=null){
			webCategoriesFound.addAll(categorizeByText(footerElement.text()));
		}

		return webCategoriesFound;
	}

	public Set<String> categorizeByText(String text){

		Set<String> webCategoriesFound = Sets.newHashSet();

		if(!StringUtils.isNotBlank(text))
			return webCategoriesFound;

		for(Map.Entry<String, List<String>> category : webCategories.entrySet())
		{
			for(String word: category.getValue())
			{
				if(text.toLowerCase().contains(word))
				{
					webCategoriesFound.add(category.getKey());
					break;
				}
			}
		}
		return webCategoriesFound;
	}



}
