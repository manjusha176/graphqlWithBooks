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

	@GetMapping (value = { "/query", "/query/{query}" })
	public ResponseEntity query(@PathVariable (required = false) String query, HttpServletRequest request) {

		ExecutionResult result;
		// if(query == null) {
		// result = graphQL.execute("{findAllBooks { id title author{id}} }");
		// } else {
		// result = graphQL.execute(query);q
		// }

		/*
		 * { "endPt" : { queryNames: ["findAll {}", "findAllWithSorting"] defaultQuery: "findAll", queryColumnList: { findAll: "id title author{id}" }
		 * } if query is empty then for project find one get the default queryName retrieve List of column
		 */

		try{
			System.out.println("servlet path--->" + request.getServletPath().substring(request.getServletPath().lastIndexOf("/") + 1));
			// get query provided by api
			String givenQuery = request.getServletPath().substring(request.getServletPath().lastIndexOf("/") + 1).trim();
			// remove side curly braces
			givenQuery = givenQuery.substring(1, givenQuery.length() - 1);
			System.out.println("chaged query -- > " + givenQuery);

			String[] arrOfStr = givenQuery.split("\\{", 2);
			String queryName = arrOfStr[0].trim();

			JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("C:\\Users\\manjusha.dhamdhere\\git\\graphqlWithBooks\\src\\main\\resources\\ext.json");
			
			if (query == null){
				System.out.println("default -- > " + ((JSONObject) jsonObject.get(request.getRequestURI())).get("default"));
				query = (String) ((JSONObject) jsonObject.get(request.getServletPath())).get("default");
			} else {
				if(!((JSONObject) jsonObject.get("/query")).get("queryName").toString().contains(queryName))
					return null;
			}
			result = graphQL.execute(query);

			System.out.println("errors: " + result.getErrors());
			return ResponseEntity.ok(result.getData());
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static Object readJsonSimpleDemo(String filename) throws Exception {
		FileReader reader = new FileReader(filename);
		JSONParser jsonParser = new JSONParser();
		return jsonParser.parse(reader);
	}

}
