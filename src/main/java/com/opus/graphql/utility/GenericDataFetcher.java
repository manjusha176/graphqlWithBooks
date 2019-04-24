package com.opus.graphql.utility;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class GenericDataFetcher<T> implements DataFetcher<T> {
    String dataFetcherName;

    public GenericDataFetcher(String dataFetcherName) {
        this.dataFetcherName = dataFetcherName;
        System.out.println("GenericDataFetcher created with dataFetcherName : " + dataFetcherName);
    }

    @Override
    public T get(DataFetchingEnvironment dataFetchingEnvironment) {
        DataFetcher<T> dataFetcher = SpringAutoWireHelper.getDataFetcherFactory().get(this.dataFetcherName);
        return dataFetcher.get(dataFetchingEnvironment);
    }
}