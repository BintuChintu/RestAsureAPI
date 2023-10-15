package com.test.RestAsureAPI;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestGetAPITest {
	
	RequestSpecification httpRequest;
	Response response;
	int id;
	@Test
	public void testGet(ITestContext context) throws ParseException {
		String accessToken = "e685b9ad1e19944970a695087715f1369bec2c6189a0056dcb44176b4794b215";
		httpRequest = RestAssured.given();
		httpRequest.auth().oauth2(accessToken);
		int id = (Integer) context.getAttribute("user-id");
		System.out.println("id...............:"+id);
		httpRequest.pathParams("id",id);
		response = httpRequest.get("https://gorest.co.in/public/v2/users/{id}");
		System.out.println("Response :" + response.body().asString());
		System.out.println("put responsecode :"+response.getStatusCode());
		System.out.println("put response message :"+response.getStatusLine());
//		JSONParser parser = new JSONParser();
//		JSONObject jsonObj = (JSONObject) parser.parse(response.body().asString());
//		id = Integer.parseInt(jsonObj.get("id").toString());
//		System.out.println("id:"+id);
//		context.setAttribute("user-id", jsonObj);
	}
}
