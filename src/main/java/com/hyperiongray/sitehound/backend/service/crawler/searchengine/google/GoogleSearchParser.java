package com.hyperiongray.sitehound.backend.service.crawler.searchengine.google;

import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlerUtils;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.SearchEngineCrawlResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 9/20/15.
 */
@Service
public class GoogleSearchParser{

	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleSearchParser.class);

	public List<SearchEngineCrawlResult> parse(String content){


		List<SearchEngineCrawlResult> result = new LinkedList<>();
		if(content.equals("Website crawl ban"))
		{
			LOGGER.error("----------------------------------------------------------Website crawl ban!!!------------------------------------");
			return result;
		}

		Document doc = Jsoup.parse(content);
//		Elements children = doc.body().select("#ires ol .g");
		Elements children = doc.body().select("#ires .g");
		for(Element child : children){
			try{

				Elements links = child.select("a");
//				Elements desc = child.select(">div>span");
				Elements desc = child.select(">div .st");

				if(desc.html().isEmpty()){
					desc = child.select("div>.st");
				}
				if(desc.html().isEmpty()){
					LOGGER.warn("failed to get the desc for:" + child);
				}

				String url = links.first().attr("href").replace("/url?q=","").split("&sa")[0];

				SearchEngineCrawlResult model = new SearchEngineCrawlResult(Constants.CrawlEntityType.GOOGLE);
				model.setHost(CrawlerUtils.getHostName(url));
				model.setUrl(url);
				model.setUrlDesc(links.first().html());
				model.setDesc(desc.html());
				result.add(model);
			}
			catch (Exception e){
				LOGGER.error("Could not parse:" + child, e);
			}
		}
		return result;
	}
}
