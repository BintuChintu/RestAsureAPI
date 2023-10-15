package com.test.RestAsureAPI;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.Map;

import org.apache.groovy.json.internal.BaseJsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mozilla.javascript.json.JsonParser;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.RestAsureAPI.pojo.Employee;
import com.test.RestAsureAPI.pojo.RestAPIResponsePojo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import utilities.ReadJson;
import io.restassured.response.Response;

public class TestAPI {
	public ReadJson readJson = null;
	public String baseurl = "https://dummy.restapiexample.com";
	ExtentReports extent;
	ExtentSparkReporter spark;
	public ExtentTest logger;

//	@BeforeMethod
//	public void beforeMethod()
//	{
//		System.out.println(".........before method...........");
//	}
	@BeforeTest
	public void beforeTest() {
		System.out.println("............before test...............");
		readJson = new ReadJson();
		// Create an object of Extent Reports
		extent = new ExtentReports();

		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/STMExtentReport.html");
		extent.attachReporter(spark);
		extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
		extent.setSystemInfo("Environment", "Production");
		extent.setSystemInfo("User Name", "Rajkumar SM");
		spark.config().setDocumentTitle("Title of the Report Comes here ");
		// Name of the report
		spark.config().setReportName("Name of the Report Comes here ");
		// Dark Theme
		spark.config().setTheme(Theme.STANDARD);
	}

//	@BeforeSuite
//	public void beforeSuite()
//	{
//		System.out.println(".........before suite............");
//	}
//	@BeforeClass
//	public void beforeClass()
//	{
//		System.out.println(".........before class............");
//	}
//	@Test
//	public void testGetApi() throws ParseException {
//		String url = "https://dummy.restapiexample.com/api/v1/employees";
//		
//		 RequestSpecification httpRequest = RestAssured.given();
//		 httpRequest.header("Content-Type", "application/json");
//		 Response response = httpRequest.get(url);
//		 int statusCode = response.getStatusCode();
//		 System.out.println("Response body.........:"+response.body().asString());
//		 System.out.println("Response code..........:"+response.getStatusCode());
//		 System.out.println("Response message..........:"+response.getStatusLine());
//		 Assert.assertEquals(statusCode, 200, "Correct status code returned");
//		 //validating response body
//		 JsonPath jpath = new JsonPath(response.body().asString());
//		 System.out.println("status...........:"+jpath.getString("status"));
////		 System.out.println("no.of employees data...........:"+jpath.getString("data"));
////		 System.out.println("no.of employees...........:"+jpath.getInt("data.size()"));
////		 Assert.assertEquals(jpath.getString("status"), "success","Correct status  returned");
////		 Assert.assertEquals(jpath.getInt("data.size()"), 24,"Correct employee count  returned");
//		 
//		//Response validating through simple json library
//		 JSONParser parser = new JSONParser();
//		 JSONObject json = (JSONObject) parser.parse(response.body().asString());
//		 System.out.println("Json object :"+json);
//		 System.out.println("status......:"+json.get("status"));
//		 System.out.println("data......:"+json.get("data"));
//		 System.out.println("message......:"+json.get("message"));
//		 JSONArray jarray = (JSONArray) json.get("data");
//		 System.out.println("employee count ......:"+jarray.size());
//		 
//		 for(int i=0;i<jarray.size();i++)
//		 {
//			 System.out.println("employee "+i+".........:"+jarray.get(i));
//		 }
//	}
//	
//	@Test
//	public void testPostAPI1() throws ParseException {
//		extent.createTest("To verify PostAPI1 Title");
//		// String url = "https://dummy.restapiexample.com/api/v1/create";
//		RequestSpecification httpRequest = RestAssured.given();
//		JSONObject requestParams = new JSONObject();
//		requestParams.put("name", "test123");
//		requestParams.put("salary", "123");
//		requestParams.put("age", "23");
//		httpRequest.header("content-type", "application/json");
//		//httpRequest.auth().
//		httpRequest.body(requestParams.toString());
//		Response response = httpRequest.post(baseurl + RestAPIConstants.posturl);
//		System.out.println("Response code...........:" + response.getStatusCode());
//		System.out.println("Response line...........:" + response.getStatusLine());
//		System.out.println("Response body...........:" + response.body().asString());
//		// Validating response
//		JSONParser parser = new JSONParser();
//		JSONObject jsonObject = (JSONObject) parser.parse(response.body().asString());
//		System.out.println("status..............:" + jsonObject.get("status"));
//		Assert.assertEquals(jsonObject.get("status").toString(), "success", "Returned status success...!");
//	}
	@Test
	public void putAPI() throws IOException, ParseException
	{
		String url = "https://dummy.restapiexample.com/api/v1/update/21";
		System.out.println("post data............:"+readJson.readJsonData("putdata"));
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("content-type","application/json");
		httpRequest.body(readJson.readJsonData("putdata"));
		Response response = httpRequest.put("https://dummy.restapiexample.com/api/v1/update/21");
		System.out.println("Response body................:"+response.body().asString());
		System.out.println("Response status code.............:"+response.getStatusCode());
		System.out.println("Response status line.............:"+response.getStatusLine());
	}

//	@Test(dataProvider = "employeeDetails")
//	public void testAPIwithDataProvider(String name, String salary, String age) throws ParseException {
//		String url = "https://dummy.restapiexample.com/api/v1/create";
//		RequestSpecification httpRequest = RestAssured.given();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("test", name);
//		jsonObject.put("salary", salary);
//		jsonObject.put("age", age);
//		System.out.println("input json...............:" + jsonObject);
//		httpRequest.header("content-type", "application/json");
//		httpRequest.body(jsonObject);
//		Response response = httpRequest.post(url);
//		System.out.println("Response boy............:" + response.body().asString());
//		System.out.println("Response code............:" + response.getStatusCode());
//		System.out.println("Response status line............:" + response.getStatusLine());
//		Assert.assertEquals(response.getStatusCode(), 200,"Returned correct response code");
//		Assert.assertEquals(((JSONObject)(new JSONParser().parse(response.body().asString()))).get("status"),"success" ,"Returned status success...!");
//	}
//
	// serialization and deserialization
//	@Test
//	public void testPostAPI() throws ParseException, JsonProcessingException {
//		extent.createTest("To verify PostAPI12 Title");
//		Employee employee = new Employee();
//		employee.setAge("25");
//		employee.setName("Ramu");
//		employee.setSalary("1234");
//		System.out.println("emplyee name..........:" + employee.getName());
//		System.out.println("emplyee age..........:" + employee.getAge());
//		System.out.println("emplyee salary..........:" + employee.getSalary());
//		ObjectMapper objectMapper = new ObjectMapper();
//		String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
//		System.out.println("json converted employee object.............:" + jsonString);
//		RequestSpecification httpRequest = RestAssured.given();
//		httpRequest.header("content-type", "application/json");
//		httpRequest.body(jsonString);
//		Response response = httpRequest.post(baseurl + RestAPIConstants.posturl);
//		System.out.println("Response code...........:" + response.getStatusCode());
//		System.out.println("Response line...........:" + response.getStatusLine());
//		System.out.println("Response body...........:" + response.body().asString());
//		// Validating response
//		// Deserialization
//		Map<String, Object> responsePojoMap = objectMapper.readValue(response.body().asString(), Map.class);
//		System.out.println("response status.......:" + responsePojoMap.get("status"));
//		System.out.println("response data.......:" + responsePojoMap.get("data"));
//		System.out.println("response message.......:" + responsePojoMap.get("message"));
//		Assert.assertEquals(responsePojoMap.get("status"), "success", "Returned incorrect respnse");
//
//	}
	
	@Test
	public void performOptions()
	{
		 RequestSpecification httpRequest = RestAssured.given();
		 httpRequest.header("Content-Type", "application/json");
		 Response response = httpRequest.options("https://dummy.restapiexample.com/api/v1/employees");
		 int statusCode = response.getStatusCode();
		 System.out.println("Response body.........:"+response.body().asString());
		 System.out.println("Response code..........:"+response.getStatusCode());
		 System.out.println("Response message..........:"+response.getStatusLine());
		 Assert.assertEquals(statusCode, 200, "Correct status code returned");
		 //validating response body
		 JsonPath jpath = new JsonPath(response.body().asString());
		System.out.println("Status code:"+response.getStatusCode());
		System.out.println("Status message:"+response.getStatusLine());
		System.out.println("Options response :"+response.body().asString());
	}

//	@DataProvider(name = "employeeDetails")
//	public Object[][] getData() {
//		return new Object[][] { { "test", "123", "23" }, { "test2", "1234", "234" } };
//	}
	@AfterTest
	public void afterTest() {
		System.out.println("............after test...............");
		extent.flush();
	}

//	@AfterMethod
	public void aftrMethod(ITestResult result) throws Exception {
		{
			if (result.getStatus() == ITestResult.FAILURE) {
				// MarkupHelper is used to display the output in different colors
				logger.log(Status.FAIL,
						MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
				logger.log(Status.FAIL,
						MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
							} else if (result.getStatus() == ITestResult.SKIP) {
				logger.log(Status.SKIP,
						MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				logger.log(Status.PASS,
						MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
			}
			// driver.quit();
		}
//	@AfterSuite
//	public void afterSuite()
//	{
//		System.out.println(".........after suite............");
//	}
//	@AfterClass
//	public void afterClass()
//	{
//		System.out.println(".........after class............");
//	}
	}
}