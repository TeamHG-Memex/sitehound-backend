/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hyperiongray.sitehound.backend.service.nlp.scorer.cosinesimilarity;

import org.apache.commons.math.linear.OpenMapRealVector;
import org.apache.commons.math.linear.RealVectorFormat;

import java.util.Map;

/**
 *
 * @author Mubin
 */
public class DocVector {

    public Map<String, Integer> terms;
    public OpenMapRealVector vector;
    
    public DocVector(Map<String, Integer> terms) {
        this.terms = terms;
        this.vector = new OpenMapRealVector(terms.size(), 1.0e-10);
    }

    public boolean isEmpty(){
        for(Map.Entry<String,Integer> entry :terms.entrySet()){
            if(entry.getValue()>0)
                return false;
        }
        return true;
    }

    public void setEntry(String term, int freq) {
        if (terms.containsKey(term)) {
            int pos = terms.get(term);
            vector.setEntry(pos, (double) freq);
        }
        else{
            throw new RuntimeException("trying to add a term that is not in the catalog");
        }
    }

    public void normalize() {
        double sum = vector.getL1Norm();
        vector = vector.mapDivide(sum);
    }

    @Override
    public String toString() {
        RealVectorFormat formatter = new RealVectorFormat();
        return formatter.format(vector);
    }

}
