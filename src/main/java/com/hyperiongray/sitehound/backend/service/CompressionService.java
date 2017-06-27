package com.hyperiongray.sitehound.backend.service;

import com.hyperiongray.sitehound.backend.service.events.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by tomas on 27/06/17.
 */
@Component
public class CompressionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompressionService.class);

    public String b64compress(String str){
        if (str == null || str.length() == 0) {
            return null;
        }
        String compressed = null;
        BufferedWriter writer = null;
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream(str.getBytes("UTF-8").length);
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes("UTF-8"));
            gzip.close();

            byte[] compressedBytes = out.toByteArray();
            byte[] encoded = Base64.getEncoder().encode(compressedBytes);
            compressed = new String(encoded, StandardCharsets.UTF_8);
        }
        catch (Exception e){
            LOGGER.warn("failed to compress", e);
        }
        finally{
            try{
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
//                e.printStackTrace();
                LOGGER.warn("failed to close writer", e);
            }
        }
        return compressed;
    }

    public String b64decompress(String b64compressed) throws IOException {
        if (b64compressed == null || b64compressed.length() == 0) {
            return b64compressed;
        }
//        String decoded = new String(Base64.getDecoder().decode(b64compressed.getBytes("UTF-8")));
        byte[] decoded = Base64.getDecoder().decode(b64compressed.getBytes("UTF-8"));
        System.out.println("Input String length : " + decoded.length);
        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(decoded));
        BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        String outStr = "";
        String line;
        while ((line=bf.readLine())!=null) {
            outStr += line;
        }
        return outStr;
    }

}
