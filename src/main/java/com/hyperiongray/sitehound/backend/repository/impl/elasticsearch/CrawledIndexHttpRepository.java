package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch;

import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.repository.CrawledIndexRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.AnalyzedCrawlResultWrapperDto;
import com.hyperiongray.sitehound.backend.service.utils.Encoders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Optional;

@Repository
public class CrawledIndexHttpRepository implements CrawledIndexRepository{
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawledIndexHttpRepository.class);

	public final static String CRAWLED_INDEX_NAME ="crawled-open";
	public final static String CRAWLED_TYPE_NAME ="analyzed";

	@Autowired private ElasticsearchDatabaseClient elasticsearchDatabaseClient;

	private final JsonMapper<AnalyzedCrawlResultWrapperDto> analyzedCrawlResultWrapperDtoJsonMapper = new JsonMapper();

	public void save(String url, AnalyzedCrawlResultDto analyzedCrawlResultDto) throws IOException{
		String id = Encoders.encodeUrl(url);
		LOGGER.info("saving: " + url + "->" + id);

		AnalyzedCrawlResultWrapperDto analyzedCrawlResultWrapperDto = new AnalyzedCrawlResultWrapperDto();
		analyzedCrawlResultWrapperDto.setAnalyzedCrawlResultDto(analyzedCrawlResultDto);
		String analyzedCrawlResultWrapperDtoAsString = analyzedCrawlResultWrapperDtoJsonMapper.toJson(analyzedCrawlResultWrapperDto);
		elasticsearchDatabaseClient.save(CRAWLED_INDEX_NAME, CRAWLED_TYPE_NAME, id, analyzedCrawlResultWrapperDtoAsString);
	}

	public AnalyzedCrawlResultDto get(String url) throws IOException{
		String id = Encoders.encodeUrl(url);
		LOGGER.info("getting: " + url + "->" + id);

		Optional<String> stringOptional = elasticsearchDatabaseClient.get(CRAWLED_INDEX_NAME, CRAWLED_TYPE_NAME, id);
		if(stringOptional.isPresent()){
			String result = stringOptional.get();
			AnalyzedCrawlResultWrapperDto analyzedCrawlResultWrapperDto = analyzedCrawlResultWrapperDtoJsonMapper.toObject(result, AnalyzedCrawlResultWrapperDto.class);
			return analyzedCrawlResultWrapperDto == null ? null : analyzedCrawlResultWrapperDto.getAnalyzedCrawlResultDto();
		}
		else{
			return null;
		}

	}

	public void delete(String url) throws IOException{
		String id = Encoders.encodeUrl(url);
		LOGGER.info("delete: " + url + "->" + id);
		elasticsearchDatabaseClient.delete(CRAWLED_INDEX_NAME, CRAWLED_TYPE_NAME, id);
	}

}
