package com.hyperiongray.sitehound.backend.service.nlp.scorer.cosinesimilarity;

/**
 * Class to calculate cosine similarity
 * @author Mubin Shrestha
 */
public class CosineSimilarity {

    public static double CosineSimilarity(DocVector d1, DocVector d2) {
        return (d1.vector.dotProduct(d2.vector)) / (d1.vector.getNorm() * d2.vector.getNorm());
    }

}