package com.hyperiongray.sitehound.backend.service.nlp.scorer;

import com.hyperiongray.sitehound.backend.model.TrainedCrawledUrl;
import com.hyperiongray.sitehound.backend.service.crawler.CrawledIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 18/03/16.
 */
@Service
public class MockCrawledIndexService extends CrawledIndexService {

    @Autowired
    @Qualifier("books")
    private String booksHtml;

    @Autowired
    @Qualifier("arabicCnn")
    private String arabicCnnHtml;

    @Autowired
    @Qualifier("other")
    private String otherHtml;

    public List<TrainedCrawledUrl> getTrainedDocuments(String workspaceId) throws IOException {
        System.out.println("-----getting mock trained documents-----");
        List<TrainedCrawledUrl> trainedDocuments = new LinkedList<>();
        trainedDocuments.add(new TrainedCrawledUrl("http://www.aljazeera.net/knowledgegate/books", booksHtml, "", true));
        trainedDocuments.add(new TrainedCrawledUrl("http://arabic.cnn.com",arabicCnnHtml, "", true));
        trainedDocuments.add(new TrainedCrawledUrl("otherHtml",otherHtml, "", true));
        trainedDocuments.add(new TrainedCrawledUrl("simpleTerms", "a, b, c, d, president", "",true));
//        trainedDocuments.add(new TrainedCrawledUrl("url4","text1", true));
//        trainedDocuments.add(new TrainedCrawledUrl("url5","text1", false));
//        trainedDocuments.add(new TrainedCrawledUrl("url6","text1", false));
//        trainedDocuments.add(new TrainedCrawledUrl("url7","text1", false));
//        trainedDocuments.add(new TrainedCrawledUrl("url8","text1", false));
        return trainedDocuments;
    }

}