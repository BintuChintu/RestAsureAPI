package com.test.RestAsureAPI;

import java.util.HashMap;
import java.util.Map;

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

public class RestPostAPITest {

	RequestSpecification httpRequest;
	Response response;
	int id;
	@Test
	public void testPost(ITestContext context) throws ParseException {
		String accessToken = "e685b9ad1e19944970a695087715f1369bec2c6189a0056dcb44176b4794b215";
		Faker faker = new Faker();
//		JSONObject data = new JSONObject();
		Map<String,String> data = new HashMap<String,String>();
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		System.out.println("data :"+data);
		httpRequest = RestAssured.given();
		httpRequest.auth().oauth2(accessToken);
		httpRequest.header("content-type", "application/json");
		httpRequest.body(data);
		response = httpRequest.post("https://gorest.co.in/public/v2/users");
		System.out.println("Response :" + response.body().asString());
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(response.body().asString());
		id = Integer.parseInt(jsonObj.get("id").toString());
		System.out.println("id:"+id);
		context.setAttribute("user-id", id);
		System.out.println("put responsecode :"+response.getStatusCode());
		System.out.println("put response message :"+response.getStatusLine());
	}

}
