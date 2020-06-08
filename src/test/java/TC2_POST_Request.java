import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC2_POST_Request {

	@Test
	public void RegistrationSuccessful() {

		//specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		//request object
		RequestSpecification httpRequest = RestAssured.given();
		//response object
		
		
		//request payload sending along with post request
		JSONObject requestParams = new JSONObject();	
		requestParams.put("FirstName","Johnhjn");	
		requestParams.put("LastName","AY9");	
		requestParams.put("UserName","JxM9");	
		requestParams.put("Password","Qaws9987");	
		requestParams.put("Email","Johnhjn.AY9@hotmail.com");	
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString());   //attach above data to the request
		
		
		//send request and get the response
		Response response = httpRequest.request(Method.POST, "/register");
		
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("response--->"+response);  //e.g. response--->io.restassured.internal.RestAssuredResponseImpl@47a86fbb
		System.out.println("responseBody--->"+responseBody);
		
		//verify status code
		int statusCode = response.getStatusCode();
		System.out.println("status code--->"+statusCode);
		Assert.assertEquals(201, statusCode);
		
		//successCode validation
		String successCode = response.jsonPath().get("SuccessCode");
		Assert.assertEquals("OPERATION_SUCCESS", successCode);

	}	
}
