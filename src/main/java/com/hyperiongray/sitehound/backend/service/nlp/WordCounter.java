package com.hyperiongray.sitehound.backend.service.nlp;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.hyperiongray.sitehound.backend.service.crawler.Crawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by tomas on 5/24/15.
 */
@Service
public class WordCounter {

	private static final Logger LOGGER = LoggerFactory.getLogger(WordCounter.class);

	private static final Charset ENCODING= StandardCharsets.UTF_8;
	private List<String> stopWords=new ArrayList<>(600);


	@PostConstruct
	private void postConstruct() throws IOException {
		String stopWordsPath = "config/stop-words/stop-words-en.txt";
		Path path = Paths.get(stopWordsPath);
		try (Scanner scanner =  new Scanner(path, ENCODING.name())){
			while (scanner.hasNextLine()){
				stopWords.add(scanner.nextLine());
			}
		}
	}

	public List<String> getMostFrequentWords(String content){
		return getMostFrequentWords(content, stopWords, Crawler.MAX_NUMBER_OF_WORDS);
	}


	public List<String> getMostFrequentWords(String content, List<String> stopWords, int maxNumberOfResult){

		Multiset<String> multiset= HashMultiset.create();

		String [] splitString = content.split("[\\s+|\\.+|,*]");
		for(int i = 0; i < splitString.length ; i ++) {
			String word = splitString[i].replaceAll("[^\\p{L}\\p{Nd}]+", "").replaceAll("\"","").replaceAll(":","");
			if(word.length() > 2 && !word.startsWith("http") && word.indexOf('/')==-1) {
				multiset.add(word.toLowerCase());
			}
			if (i>10*1000) {
				LOGGER.warn("Page was to long to be fully parsed. Only the first 10k words will be considered");
				break;
			}
		}

		Multiset<String> msSortedByCount = Multisets.copyHighestCountFirst(multiset);
		List<String> frequentWords = new LinkedList<String>();
		Iterator<Multiset.Entry<String>> iterator = msSortedByCount.entrySet().iterator();
		int i = 0;
		while (iterator.hasNext() && i<maxNumberOfResult){
			String word = iterator.next().getElement();
			if(!stopWords.contains(word)) {
				frequentWords.add(word);
				i++;
			}
		}
		return frequentWords;
	}


}
