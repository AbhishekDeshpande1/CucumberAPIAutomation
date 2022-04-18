package com.ciox.cf.stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import util.ConfigReader;

public class ChartFInder_APIStepDef {

	private static Response response;
	private static Response upload;
	private static  String token;
	
	@Given("User will generate token using valid credentials")
	public void user_will_generate_token_using_valid_credentials() {
		
		RestAssured.baseURI = ConfigReader.getProperties("Host");
		response = given().header("Content-Type", "application/x-www-form-urlencoded")
				.formParam("username", ConfigReader.getProperties("username"))
				.formParam("password", ConfigReader.getProperties("password"))
				.formParam("client_id", ConfigReader.getProperties("client_id"))
				.formParam("client_secret", ConfigReader.getProperties("client_secret"))
				.formParam("grant_type", "password")
				.when().post("/api/authserver.api/oauth2/token")
				.then().assertThat()
				.statusCode(200).and().body("userName", equalTo(ConfigReader.getProperties("username"))).extract()
				.response();
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		token = js.get("access_token");
		//System.out.println("Token One" + token);
	}

	

	@When("User created request and upload PDF and Non PDF file")
	public void user_created_request_and_upload_pdf_and_non_pdf_file() {		
		 RestAssured.baseURI = ConfigReader.getProperties("Host");
		 upload = given().header("Authorization", "Bearer " + token)
	        .queryParam("ChartId", ConfigReader.getProperties("ChartId"))
				.queryParam("ChartStatus",ConfigReader.getProperties("ChartStatus"))
				.multiPart("file", new File("files/Test4.pdf"))
				.multiPart("file", new File("files/Test8.docx"))
				.when()
				.post("/api/chartfinder.api/api/chart/uploadattach")
				.then()
				.log().all().extract().response();
			
		System.out.println("Token 2" + token);
	}


	@Then("Non PDF an PDF file should upload successfully")
	public void non_pdf_an_pdf_file_should_upload_successfully() {
		upload.then().and().statusCode(200);
	}

	@When("User created request and upload one PDF and multiple Non PDF file")
	public void user_created_request_and_upload_one_pdf_and_multiple_non_pdf_file() {
		
		 RestAssured.baseURI = ConfigReader.getProperties("Host");
		 upload = given().header("Authorization", "Bearer " + token)
	        .queryParam("ChartId", ConfigReader.getProperties("ChartId"))
				.queryParam("ChartStatus",ConfigReader.getProperties("ChartStatus"))
				.multiPart("file", new File("files/Test1.pdf"))
				.multiPart("file", new File("files/Test8.docx"))
				.when()
				.post("/api/chartfinder.api/api/chart/uploadattach")
				.then()
				.log().all().extract().response();

	}

	@Then("multiple Non PDF and PDF file should upload successfully")
	public void multiple_non_pdf_an_pdf_file_should_upload_successfully() {
		upload.then().and().statusCode(200);
	}

	@When("User created request and upload one PDF file only")
	public void user_created_request_and_upload_one_pdf_file_only() {
		 RestAssured.baseURI = ConfigReader.getProperties("Host");
		 upload = given().header("Authorization", "Bearer " + token)
	        .queryParam("ChartId", ConfigReader.getProperties("ChartId"))
				.queryParam("ChartStatus",ConfigReader.getProperties("ChartStatus"))
				.multiPart("file", new File("files/Test1.pdf"))
				.when()
				.post("/api/chartfinder.api/api/chart/uploadattach")
				.then()
				.log().all().extract().response();

	}

	@Then("It should  accept only PDF file")
	public void it_should_not_accept_only_pdf_file_error_message_should_display() {
		upload.then().and().statusCode(200);
	}

	@When("User created request and upload files and enter invalid chartId")
	public void user_created_request_and_upload_files_and_enter_invalid_chart_id() {

		 RestAssured.baseURI = ConfigReader.getProperties("Host");
		 upload = given().header("Authorization", "Bearer " + token)
	        .queryParam("ChartId", ConfigReader.getProperties("InvalidChartId"))
				.queryParam("ChartStatus",ConfigReader.getProperties("ChartStatus"))
				.multiPart("file", new File("files/Test1.pdf"))
				.multiPart("file", new File("files/Test8.docx"))
				.when()
				.post("/api/chartfinder.api/api/chart/uploadattach")
				.then()
				.log().all().extract().response();
	}

	@Then("Error message should display for invalid chartid")
	public void error_message_should_display_for_invalid_chartid() {
		upload.then().and().statusCode(400);
	
	}

	@When("User created request and upload files and enter invalid chart status")
	public void user_created_request_and_upload_files_and_enter_invalid_chart_status() {
		
		 RestAssured.baseURI = ConfigReader.getProperties("Host");
		 upload = given().header("Authorization", "Bearer " + token)
	        .queryParam("ChartId", ConfigReader.getProperties("ChartId"))
				.queryParam("ChartStatus",ConfigReader.getProperties("InvalidChartStatus"))
				.multiPart("file", new File("files/Test1.pdf"))
				.multiPart("file", new File("files/Test8.docx"))
				.when()
				.post("/api/chartfinder.api/api/chart/uploadattach")
				.then()
				.log().all().extract().response();

	}

	@Then("Error message should display for invalid chart status")
	public void error_message_should_display_for_invalid_chart_status() {
		upload.then().and().statusCode(400);
	}

	@When("User created request and upload files and enter different chart id")
	public void user_created_request_and_upload_files_and_enter_different_chart_id() {
		
		 RestAssured.baseURI = ConfigReader.getProperties("Host");
		 upload = given().header("Authorization", "Bearer " + token)
	        .queryParam("ChartId", ConfigReader.getProperties("ChartId2"))
				.queryParam("ChartStatus",ConfigReader.getProperties("ChartStatus"))
				.multiPart("file", new File("files/Test1.pdf"))
				.multiPart("file", new File("files/Test8.docx"))
				.when()
				.post("/api/chartfinder.api/api/chart/uploadattach")
				.then()
				.log().all().extract().response();
	}

	@Then("Success message should display and file should upload successfully")
	public void success_message_should_display_and_file_should_upload_successfully() {
		upload.then().and().statusCode(200);

	}
	
}
