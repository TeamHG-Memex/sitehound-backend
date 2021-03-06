package com.hyperiongray.sitehound.backend.service.dd.trainer.input;

import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStart;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.trainer.input.DdTrainerInputStop;
import com.hyperiongray.sitehound.backend.kafka.api.dto.event.EventInput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.ModelerModelDto;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.ElasticsearchModelerModelRepository;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao.TrainerModelRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.crawler.KeywordsRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdTrainerRepository;
import com.hyperiongray.framework.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by tomas on 29/09/16.
 */
@Service
@Deprecated
public class DdTrainerInputService {

    @Autowired private KeywordsRepository keywordsRepository;
    @Autowired private ElasticsearchModelerModelRepository modelerModelRepository;
    @Autowired private DdTrainerRepository ddTrainerRepository;
    @Autowired private TrainerModelRepository trainerModelRepository;

/*

    public DdTrainerInputStart getDdTrainerInputStart(EventInput eventInput) throws IOException {
        DdTrainerInputStart ddTrainerInputStart = new DdTrainerInputStart();

        JsonMapper<DdTrainerInputStartArgs> jsonMapper= new JsonMapper();
        DdTrainerInputStartArgs ddTrainerInputStartArgs = jsonMapper.toObject(eventInput.getArguments(), DdTrainerInputStartArgs.class);
        ddTrainerInputStart.setId(ddTrainerInputStartArgs.getJobId());
        ddTrainerInputStart.setWorkspaceId(eventInput.getWorkspaceId());

        // initialize
        ddTrainerRepository.deleteProgress(eventInput.getWorkspaceId());
        trainerModelRepository.delete(eventInput.getWorkspaceId());

        List<String> seeds = keywordsRepository.getAllUrls(eventInput.getWorkspaceId());
        ddTrainerInputStart.setSeeds(seeds);

        Optional<ModelerModelDto> modelerModelDtoOptional = modelerModelRepository.get(eventInput.getWorkspaceId());
        if(modelerModelDtoOptional.isPresent()){
            ModelerModelDto modelerModelDto = modelerModelDtoOptional.get();
            ddTrainerInputStart.setPageModel(modelerModelDto.getModel());
        }
        else{
            throw new RuntimeException("Workspace's Model not found!");
        }

        return ddTrainerInputStart;

    }
*/


    public DdTrainerInputStop getDdTrainerInputStop(EventInput eventInput) throws IOException {
        DdTrainerInputStop ddTrainerInputStop = new DdTrainerInputStop();

        JsonMapper<DdTrainerInputStopArgs> jsonMapper= new JsonMapper();
        DdTrainerInputStopArgs ddTrainerInputStopArgs = jsonMapper.toObject(eventInput.getArguments(), DdTrainerInputStopArgs.class);
        ddTrainerInputStop.setId(ddTrainerInputStopArgs.getJobId());

        return ddTrainerInputStop;
    }

    private static class DdTrainerInputStartArgs{

        private String jobId;

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }
    }

    private static class DdTrainerInputStopArgs{

        private String jobId;

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }
    }
}
