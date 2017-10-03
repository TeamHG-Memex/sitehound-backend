package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch;

import java.io.IOException;


public interface ReadElasticsearchRepository<T> {

    T get(String indexName, String typeName, String id, Class<T> typeParameterClass) throws IOException;

}
