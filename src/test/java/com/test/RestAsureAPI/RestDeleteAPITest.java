package com.test.RestAsureAPI;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestDeleteAPITest {

	RequestSpecification httpRequest;
	Response response;
	int id;
	@Test
	public void testDelete(ITestContext context) throws ParseException {
		String accessToken = "e685b9ad1e19944970a695087715f1369bec2c6189a0056dcb44176b4794b215";
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
//		data.put("name", faker.name().fullName());
//		data.put("gender", "Male");
//		data.put("email", faker.internet().emailAddress());
//		data.put("status", "active");
//		System.out.println("put data............data :"+data);
		httpRequest = RestAssured.given();
		httpRequest.auth().oauth2(accessToken);
		httpRequest.header("content-type", "application/json");
		int id = (Integer) context.getAttribute("user-id");
		httpRequest.pathParam("id", id);
		httpRequest.body(data);
		response = httpRequest.put("https://gorest.co.in/public/v2/users/{id}");
		System.out.println("Response :" + response.body().asString());
		System.out.println("delete responsecode :"+response.getStatusCode());
		System.out.println("delete response message :"+response.getStatusLine());
	}

}
