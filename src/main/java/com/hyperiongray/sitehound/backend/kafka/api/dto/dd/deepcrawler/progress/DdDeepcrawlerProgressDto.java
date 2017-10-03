package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.deepcrawler.progress;

import com.hyperiongray.framework.kafka.dto.KafkaDto;

/**
 * Created by tomas on 2/10/16.
 */
public class DdDeepcrawlerProgressDto extends KafkaDto {

    private String id;

    private ProgressDto progress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProgressDto getProgress() {
        return progress;
    }

    public void setProgress(ProgressDto progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "DdDeepcrawlerProgress{" +
                "id='" + id + '\'' +
                ", progress='" + progress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DdDeepcrawlerProgressDto that = (DdDeepcrawlerProgressDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return progress != null ? progress.equals(that.progress) : that.progress == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (progress != null ? progress.hashCode() : 0);
        return result;
    }
}
