package com.hyperiongray.sitehound.backend.service.dd.modeler.input;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.input.DdModelerInput;
import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.ModelerModelRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdModelerProgressRepository;
import com.hyperiongray.sitehound.backend.service.CompressionService;
import com.hyperiongray.sitehound.backend.service.crawler.CrawledIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomas on 29/09/16.
 */
@Service
public class DdModelerInputService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdModelerInputService.class);

    @Value( "${dd.modeler.compress.html}" ) private Boolean compressHtml;

    @Autowired private CrawledIndexService crawledIndexService;
    @Autowired private CompressionService compressionService;
    @Autowired private DdModelerProgressRepository ddModelerRepository;
    @Autowired private ModelerModelRepository modelerModelRepository;

    public DdModelerInput getDdModelerInput(String workspaceId) throws IOException {
        LOGGER.info("about the build DdModelerInput for workspace: " + workspaceId);
        LOGGER.info("compression is: " + compressHtml);
        LOGGER.info("deleting existing model: " + compressHtml);

//        ddModelerRepository.deletePageModel(workspaceId); // deletes the existing page_model and their stats (quality, progress)
        ddModelerRepository.deleteProgress(workspaceId); // deletes the existing page_model and their stats (quality, progress)
        modelerModelRepository.delete(workspaceId);

        List<TrainedCrawledUrl> trainedDocuments = crawledIndexService.getTrainedDocuments(workspaceId);
        DdModelerInput ddModelerInput = translate(workspaceId, trainedDocuments);
        return ddModelerInput;
    }

    private DdModelerInput translate(String workspaceId, List<TrainedCrawledUrl> trainedCrawledUrls){
        DdModelerInput ddModelerInput = new DdModelerInput();
        ddModelerInput.setId(workspaceId);

        List<DdModelerInput.Page> pages = new ArrayList<>();

        for(TrainedCrawledUrl trainedCrawledUrl : trainedCrawledUrls){
            DdModelerInput.Page page = new DdModelerInput.Page();
            if(compressHtml) {
                page.setHtml(compressionService.b64compress(trainedCrawledUrl.getHtml()));
            }
            else{
                page.setHtml(trainedCrawledUrl.getHtml());
            }
            page.setUrl(trainedCrawledUrl.getUrl());
            page.setRelevant(trainedCrawledUrl.getRelevant());
            pages.add(page);
        }
        ddModelerInput.setPages(pages);
        return ddModelerInput;

    }


}
