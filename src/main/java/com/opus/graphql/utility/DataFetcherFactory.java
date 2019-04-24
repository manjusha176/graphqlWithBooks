package com.opus.graphql.utility;

import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DataFetcherFactory {

    @Autowired
    Map<String, DataFetcher> dataFetchers;

    <T> DataFetcher<T> get(String dataFetcherName) {
        return dataFetchers.get(dataFetcherName);
    }
}