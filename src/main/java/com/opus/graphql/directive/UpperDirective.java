package com.opus.graphql.directive;

import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLName;
import graphql.annotations.directives.creation.DirectiveLocations;
import graphql.introspection.Introspection;

@GraphQLName("upperCase")
@DirectiveLocations(Introspection.DirectiveLocation.FIELD_DEFINITION)
public class UpperDirective {
    boolean isActive;
}
