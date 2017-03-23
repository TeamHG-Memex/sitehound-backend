package com.hyperiongray.sitehound.backend.kafka.submitter;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.service.crawler.CrawlResult;

import java.io.IOException;

/**
 * Created by tomas on 20/03/16.
 */
public interface TaskSubmitter {

    void submit(Metadata metadata, CrawlResult crawlResult) throws IOException;
}
