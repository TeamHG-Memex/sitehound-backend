package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.input;

/**
 * Created by tomas on 28/09/16.
 */

import com.hyperiongray.framework.kafka.dto.KafkaDto;

import java.util.LinkedList;
import java.util.List;

/**
dd-modeler-input
{
  "id": "some id that will be returned in the answer message",
  "pages": [
    {
      "url": "http://example.com",
      "html": "<h1>hi</h1>",
      "relevant": true

    },
    {
      "url": "http://example.com/1",
      "html": "<h1>hi 1</h1>",
      "relevant": false

    },
    {
      "url": "http://example.com/2",
      "html": "<h1>hi 2</h1>",
      "relevant": null
    }
  ]
}

 */
public class DdModelerInput extends KafkaDto{

    private String id;
    private List<Page> pages = new LinkedList<>();

    public DdModelerInput(){}

    public DdModelerInput(String id, List<Page> pages) {
        this.id = id;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }


    public static class Page{

        private String url;
        private String html;
        private Boolean relevant;

        public Page(){};
        public Page(String url, String html, Boolean relevant) {
            this.url = url;
            this.html = html;
            this.relevant = relevant;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public Boolean getRelevant() {
            return relevant;
        }

        public void setRelevant(Boolean relevant) {
            this.relevant = relevant;
        }
    }
}
