package com.hyperiongray.sitehound.backend.service.dd.modeler.input;

import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.service.crawledindex.CrawledIndexService;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.DdModelerInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomas on 29/09/16.
 */
@Service
public class DdModelerInputService {

    @Autowired
    private CrawledIndexService crawledIndexService;

    public DdModelerInput getDdModelerInput(String workspaceId) throws IOException {
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
            page.setHtml(trainedCrawledUrl.getContent());
            page.setUrl(trainedCrawledUrl.getUrl());
            page.setRelevant(trainedCrawledUrl.getRelevant());
            pages.add(page);
        }
        ddModelerInput.setPages(pages);
        return ddModelerInput;

    }


}
