package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch;

import java.io.IOException;


public interface ReadWriteElasticsearchRepository<T> extends ReadElasticsearchRepository {

    void save(String indexName, String type, String id, T source) throws IOException;

    void upsert(String indexName, String type, String id, String script) throws IOException;

    void delete(String id) throws IOException;

}
