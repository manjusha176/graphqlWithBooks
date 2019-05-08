package com.opus.graphql.controller;

import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.opus.graphql.utility.GraphQlUtility;

import graphql.ExecutionResult;
import graphql.GraphQL;

@RestController
public class MainController {

	private GraphQL graphQL;
	private GraphQlUtility graphQlUtility;

	@Autowired
	MainController(GraphQlUtility graphQlUtility) throws IOException {
		this.graphQlUtility = graphQlUtility;
		graphQL = graphQlUtility.createGraphQlObject();
	}

	@PostMapping("/query")
	public ResponseEntity query(@RequestBody String query) {

		ExecutionResult result;

		result = graphQL.execute(query);

		System.out.println("errors: " + result.getErrors());
		return ResponseEntity.ok(result.getData());

	}

}
