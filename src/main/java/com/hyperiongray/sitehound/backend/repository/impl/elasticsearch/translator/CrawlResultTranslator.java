package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.translator;

import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.ImageDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.ImageTypeEnum;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumInternal;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.CrawlResultDto;
import org.springframework.stereotype.Service;

/**
 * Created by tomas on 2/11/16.
 */
@Service
public class CrawlResultTranslator{

	public CrawlResultDto translateFromAquariumInternal(AquariumInternal aquariumInternal){

		CrawlResultDto crawlResultDto = new CrawlResultDto(aquariumInternal.getRequestedUrl());

		crawlResultDto.setHtml(aquariumInternal.getHtml());
		crawlResultDto.setTitle(aquariumInternal.getTitle());

		ImageDto image;
		if(aquariumInternal.getJpeg() != null){
			image = new ImageDto(ImageTypeEnum.JPG, aquariumInternal.getJpeg());
		}
		else if(aquariumInternal.getPng() != null){
			image = new ImageDto(ImageTypeEnum.PNG, aquariumInternal.getPng());
		}
		else{
			throw new RuntimeException("UNSUPPORTED IMAGE TYPE");
		}
		crawlResultDto.setImage(image);

		return crawlResultDto;
	}
}
