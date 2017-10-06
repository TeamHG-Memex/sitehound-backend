package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao;

import com.google.common.collect.Sets;
import com.hyperiongray.sitehound.backend.Application;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.CrawledIndexHttpRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.ImageDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.ImageTypeEnum;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlResultDto;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * Created by tomas on 2/9/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("integration-test")
public class CrawledIndexHttpRepositoryTest{

	@Autowired private CrawledIndexHttpRepository analyzedCrawlResultDtoCrawledIndexHttpRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CrawledIndexHttpRepositoryTest.class);

	@Test
	@Ignore
	public void testSaveAnalyzedResult() throws Exception{
		AnalyzedCrawlResultDto google = getGoogle();
		analyzedCrawlResultDtoCrawledIndexHttpRepository.save("http://www.google.com", "ws1", Constants.CrawlEntityType.GOOGLE, google);

		AnalyzedCrawlResultDto bing = getBing();
		analyzedCrawlResultDtoCrawledIndexHttpRepository.save("http://www.bing.com", "ws1", Constants.CrawlEntityType.TOR, bing);
	}

	@Test
	public void testWebType() throws IOException{
		AnalyzedCrawlResultDto google = getGoogle();
		String url = "http://www.google.com/" + System.currentTimeMillis();
		analyzedCrawlResultDtoCrawledIndexHttpRepository.save(url, "ws1", Constants.CrawlEntityType.GOOGLE, google);
		AnalyzedCrawlResultDto analyzedCrawlResultDto = analyzedCrawlResultDtoCrawledIndexHttpRepository.get(url);
	}



	@Test
	public void testUpsertAndGet() throws IOException{
		String url = "http://www.google.com.arrrr/" + System.currentTimeMillis();
		AnalyzedCrawlResultDto google = getGoogle();
		String workspace = "workspace-test-get";
		analyzedCrawlResultDtoCrawledIndexHttpRepository.upsert(url, workspace, Constants.CrawlEntityType.GOOGLE, google);

		AnalyzedCrawlResultDto analyzedCrawlResultDto = analyzedCrawlResultDtoCrawledIndexHttpRepository.get(url);
		Assert.notNull(analyzedCrawlResultDto);
		Assert.notNull(analyzedCrawlResultDto.getText());
		Assert.notNull(analyzedCrawlResultDto.getLinks());
		Assert.notNull(analyzedCrawlResultDto.getCrawlResultDto());
		Assert.notNull(analyzedCrawlResultDto.getCrawlResultDto().getTimestamp());
	}

	@Test
	public void testSaveAndGet() throws Exception{
		String url = "http://www.google.com.arr/" + System.currentTimeMillis();
		AnalyzedCrawlResultDto google = getGoogle();
		String workspace = "workspace-test-get";
		analyzedCrawlResultDtoCrawledIndexHttpRepository.save(url, workspace, Constants.CrawlEntityType.GOOGLE, google);

		AnalyzedCrawlResultDto analyzedCrawlResultDto = analyzedCrawlResultDtoCrawledIndexHttpRepository.get(url);
		Assert.notNull(analyzedCrawlResultDto);
		Assert.notNull(analyzedCrawlResultDto.getText());
		Assert.notNull(analyzedCrawlResultDto.getLinks());
	}

	@Test
	public void testDelete() throws Exception{
		AnalyzedCrawlResultDto bingAnalyzedCrawlResultDto = getBing();
		String url = "www.to-be-deleted-id.com";
		String workspace = "workspace-test-delete";
		analyzedCrawlResultDtoCrawledIndexHttpRepository.save(url, workspace, Constants.CrawlEntityType.GOOGLE, bingAnalyzedCrawlResultDto);

		AnalyzedCrawlResultDto analyzedCrawlResultDto = analyzedCrawlResultDtoCrawledIndexHttpRepository.get(url);
		Assert.notNull(analyzedCrawlResultDto);

		analyzedCrawlResultDtoCrawledIndexHttpRepository.delete(url);

		AnalyzedCrawlResultDto analyzedCrawlResultDtoAfterDelete = analyzedCrawlResultDtoCrawledIndexHttpRepository.get(url);
		Assert.isNull(analyzedCrawlResultDtoAfterDelete);

	}

	@Test
	public void testDeleteTrash() throws IOException{
		String url = "http://www.bing.com";
		analyzedCrawlResultDtoCrawledIndexHttpRepository.delete(url);
	}


	private AnalyzedCrawlResultDto getGoogle(){
		String url = "https://www.google.com";
		CrawlResultDto crawlResultDto = new CrawlResultDto(url);
		crawlResultDto.setHtml("<html><body><h1>Hello World!</h1</body></html>");
		crawlResultDto.setTitle("Goooooogle");
		ImageDto image = new ImageDto(ImageTypeEnum.JPG, "afdafaefafbbba3234134134134134134134134fadfadfafdadfadfadfadfafafadffa");
		crawlResultDto.setImage(image);
		AnalyzedCrawlResultDto analyzedCrawlResultDto = new AnalyzedCrawlResultDto(crawlResultDto);
		analyzedCrawlResultDto.setLanguage("EN");
		analyzedCrawlResultDto.setCategories(Sets.newHashSet("news", "chat"));
		analyzedCrawlResultDto.setText("texttexttexttexttexttexttexttexttexttexttexttexttexttexttext");
		analyzedCrawlResultDto.setLinks(Sets.<String>newHashSet("www.twitter.com"));
		return analyzedCrawlResultDto;
	}

	private AnalyzedCrawlResultDto getBing(){
		String url = "https://www.yahoo.com";
		CrawlResultDto crawlResultDto = new CrawlResultDto(url);
		crawlResultDto.setHtml("<html><body><h1>Bing!</h1</body></html>");
		crawlResultDto.setTitle("Hello Bing!");
		ImageDto image = new ImageDto(ImageTypeEnum.JPG, "afdafaefafadfadfafdadfadfadfadfafafadffa");
		crawlResultDto.setImage(image);
		AnalyzedCrawlResultDto analyzedCrawlResultDto = new AnalyzedCrawlResultDto(crawlResultDto);
		analyzedCrawlResultDto.setLanguage("ES");
		analyzedCrawlResultDto.setCategories(Sets.newHashSet("news", "forum"));
		return analyzedCrawlResultDto;
	}

}