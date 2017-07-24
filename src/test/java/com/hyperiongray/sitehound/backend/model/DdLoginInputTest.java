package com.hyperiongray.sitehound.backend.model;

import com.beust.jcommander.internal.Maps;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * Created by tomas on 22/06/17.
 */
public class DdLoginInputTest {

    public static DdLoginInput getOne(){
//        String id = "9d11ff30005";
        String workspaceId = "57ea86a9d11ff300054a3519";
        String jobId = "jobId1223124";
        String url = "http://www.example.com/login";
        String screenshot = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        Map<String, String> keyValues = Maps.newHashMap();
        keyValues.put("user","");
        keyValues.put("password","");
        List<String> keysOrder= Lists.newArrayList("user", "password");

        return new DdLoginInput.Builder()
//                .withId(id)
                .withWorkspaceId(workspaceId)
                .withJobId(jobId)
                .withKeyValues(keyValues)
                .withKeysOrder(keysOrder)
                .withScreenshot(screenshot)
                .withUrl(url)
                .build();

    }
}
