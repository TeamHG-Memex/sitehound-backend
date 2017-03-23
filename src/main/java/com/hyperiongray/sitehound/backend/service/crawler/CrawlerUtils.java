package com.hyperiongray.sitehound.backend.service.crawler;

import com.google.common.collect.Lists;
import com.google.common.net.InternetDomainName;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.SearchEngineCrawlResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tomas on 7/7/15.
 */
public class CrawlerUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerUtils.class);

	private static Pattern patternDomainName;

	private static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)" +
			"+[a-zA-Z]{2,6}";
	static { patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);}




	private static String getDomainName(String url){
		String domainName = "";
		Matcher matcher= patternDomainName.matcher(url);
		if (matcher.find()) {
			domainName = matcher.group(0).toLowerCase().trim();
		}
		return domainName;
	}

	public static String getHostName(String url){
		String subdomain = getDomainName(url);
		String hostname = "";
		if(subdomain.endsWith(".onion")){
			hostname = subdomain;
		}
		else{
			InternetDomainName internetDomainName = InternetDomainName.from(subdomain).topPrivateDomain();
			hostname = internetDomainName.toString();
		}
		return hostname;
	}

	public static Set<String> getHostsFromUrls(List<String> urls) {
		Set<String> hosts = new HashSet<>(urls.size());
		for(String url : urls){
			hosts.add(getHostName(url));
		}
		return hosts;
	}

	public static String implodeArray(String[] inputArray, String glueString) {
		String output = "";
		if (inputArray.length > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(inputArray[0]);
			for (int i=1; i<inputArray.length; i++) {
				sb.append(glueString);
				sb.append(inputArray[i]);
			}
			output = sb.toString();
		}
		return output;
	}

	public static String groupCreator(List<String> included){

		int groupSize = included.size();
//		int remainder = included.size() % groupSize;
//		if (remainder == 1) {
//			groupSize = 4;
//		}
		List<List<String>> partitions = Lists.partition(included, groupSize);
		List<String> ands = Lists.newArrayList();
		for(List<String> sublist : partitions){
			String and = new StringBuilder()
					.append(sublist.size() == 1 ? "" : "(" )
					.append(
							CrawlerUtils.implodeArray(sublist.toArray(new String[]{}), " AND ")
					)
					.append(sublist.size() == 1 ? "" : ")").toString();
			ands.add(and);
		}

		return CrawlerUtils.implodeArray( ands.toArray(new String[]{}), " OR ");
	}

	public static String getUserAgent(){
		List<String> userAgents = new ArrayList<>();

		userAgents.add("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357" +
				".130 Safari/537.36");

		userAgents.add("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0" +
				" Safari/535.21");

		userAgents.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357" +
				".130 Safari/537.36");

		userAgents.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");

		userAgents.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357" +
				".124 Safari/537.36");

		userAgents.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/600.6.3 (KHTML, like Gecko) " +
				"Version/8.0.6 Safari/600.6.3");

		userAgents.add("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357" +
				".130 Safari/537.36");

		userAgents.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) " +
				"Chrome/43.0.2357.130 Safari/537.36");

		userAgents.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357" +
				".132 Safari/537.36");

		userAgents.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) " +
				"Chrome/43.0.2357.124 Safari/537.36");

		Random randomGenerator = new Random();
		int randomIndex = randomGenerator.nextInt(userAgents.size() - 1);
		return userAgents.get(randomIndex);
	}

	public static List<SearchEngineCrawlResult> filterFromExistentResults(Set<String> existingUrls, List<SearchEngineCrawlResult> searchEngineCrawlResults){

		List<SearchEngineCrawlResult> filteredResults = new LinkedList<>();
		for(SearchEngineCrawlResult searchEngineCrawlResult: searchEngineCrawlResults){
			if(!existingUrls.contains(searchEngineCrawlResult.getUrl())){
				filteredResults.add(searchEngineCrawlResult);
			}
			else{
				LOGGER.debug("Excluding existing URL: " + searchEngineCrawlResult.getUrl());
			}
		}
		return filteredResults;
	}


}
