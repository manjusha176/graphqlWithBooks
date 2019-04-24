package com.opus.graphql.utility;

import com.opus.graphql.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;

public class SpringAutoWireHelper {

	private static DataFetcherFactory dataFetcherFactory;

	public static DataFetcherFactory getDataFetcherFactory() {
		return dataFetcherFactory;
	}

	public static void setDataFetcherFactory(DataFetcherFactory dataFetcherFactory) {
		SpringAutoWireHelper.dataFetcherFactory = dataFetcherFactory;
	}
}