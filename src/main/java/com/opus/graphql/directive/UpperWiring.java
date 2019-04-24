package com.opus.graphql.directive;

import graphql.annotations.directives.AnnotationsDirectiveWiring;
import graphql.annotations.directives.AnnotationsWiringEnvironment;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetcherFactories;
import graphql.schema.GraphQLFieldDefinition;

public class UpperWiring implements AnnotationsDirectiveWiring{

	@Override
    public GraphQLFieldDefinition onField(AnnotationsWiringEnvironment environment) {
        GraphQLFieldDefinition field = (GraphQLFieldDefinition) environment.getElement();
        boolean isActive = (boolean) environment.getDirective().getArgument("isActive").getValue();
        DataFetcher dataFetcher = DataFetcherFactories.wrapDataFetcher(field.getDataFetcher(), (((dataFetchingEnvironment, value) -> {
            if (value instanceof String && isActive) {
                return ((String) value).toUpperCase();
            }
            return value;
        })));
        return field.transform(builder -> builder.dataFetcher(dataFetcher));
    }
	
}
