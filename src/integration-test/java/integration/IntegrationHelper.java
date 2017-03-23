package integration;

import com.google.common.collect.Lists;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.api.dto.crawler.SubscriberInput;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;

import java.util.List;

/**
 * Created by tomas on 2/4/16.
 */
public class IntegrationHelper{

	public static Metadata getMetadata(){
		Metadata metadata = new Metadata();
		metadata.setCrawlType(Constants.CrawlType.BROADCRAWL);
		metadata.setSource("fake-source");
		metadata.setStrTimestamp("123456");
		metadata.setWorkspace("test");
		metadata.setTimestamp(123456L);
		metadata.setCallbackQueue("callback-queue");
		metadata.setJobId("jobId-123");
		metadata.setCrawlEntityType(Constants.CrawlEntityType.BING);
		metadata.setnResults(10);
		return metadata;
	}


	public static SubscriberInput getSubscriberInput(){
		SubscriberInput subscriberInput = new SubscriberInput();
		subscriberInput.setSource("fake-source");
		subscriberInput.setStrTimestamp("2016-01-27 23:44:01");
		subscriberInput.setWorkspace("test");
		subscriberInput.setTimestamp(123456L);
		subscriberInput.setCallbackQueue("callback-queue");
		subscriberInput.setJobId("568a8649132ad21be932d3c2");
		subscriberInput.setnResults(10);
		List<String> crawlSources = Lists.newArrayList("SE");
		subscriberInput.setCrawlSources(crawlSources);
		List<String> excludedList = Lists.newArrayList("playstation");
		subscriberInput.setExcluded(excludedList);
		List<String> includedLists = Lists.newArrayList("guns","ammo");
		subscriberInput.setIncluded(includedLists);
		String crawlProvider ="HH_JOOGLE";
		subscriberInput.setCrawlProvider(crawlProvider);
		List<String> existentUrls = Lists.newArrayList();
		subscriberInput.setExistentUrl(existentUrls);
		List<String> includedUrls = Lists.newArrayList();
		subscriberInput.setIrrelevantUrl(includedUrls);

		return subscriberInput;
	}

}
