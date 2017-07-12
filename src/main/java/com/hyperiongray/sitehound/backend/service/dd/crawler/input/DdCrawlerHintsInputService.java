package com.hyperiongray.sitehound.backend.service.dd.crawler.input;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.input.DdCrawlerHintsInputDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.event.EventInput;
import com.hyperiongray.sitehound.backend.kafka.producer.dd.crawler.DdCrawlerHintsInputProducer;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.BroadCrawlRepository;
import com.hyperiongray.sitehound.backend.service.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by tomas on 2/10/16.
 */
@Service
public class DdCrawlerHintsInputService {

    @Autowired private BroadCrawlRepository broadCrawlRepository;
    @Autowired private DdCrawlerHintsInputProducer ddCrawlerHintsInputProducer;

    private JsonMapper<DdCrawlerHintsInputStartArgs> jsonMapperDdCrawlerHintsInputStartArgs = new JsonMapper();

    private static class DdCrawlerHintsInputStartArgs{
        private boolean pinned;
        private String id; // the id of the document

        public boolean isPinned() {
            return pinned;
        }

        public void setPinned(boolean pinned) {
            this.pinned = pinned;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public void execute(EventInput eventInput) throws IOException {
        DdCrawlerHintsInputDto ddCrawlerHintsInputDto = new DdCrawlerHintsInputDto();
        ddCrawlerHintsInputDto.setWorkspaceId(eventInput.getWorkspaceId());

        DdCrawlerHintsInputStartArgs ddCrawlerHintsInputStartArgs = jsonMapperDdCrawlerHintsInputStartArgs.toObject(eventInput.getArguments(), DdCrawlerHintsInputStartArgs.class);
        ddCrawlerHintsInputDto.setPinned(ddCrawlerHintsInputStartArgs.isPinned());

        String url = broadCrawlRepository.getUrl(ddCrawlerHintsInputStartArgs.getId());
        ddCrawlerHintsInputDto.setUrl(url);
        ddCrawlerHintsInputProducer.submit(ddCrawlerHintsInputDto);
    }

}
