import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC3_GET_Request {

	
	@Test
	public void googleMapTest() {
	
		//specify base URI
		RestAssured.baseURI="http://ergast.com"; // in the original lesson is: "https://maps.googleapis.com";
		//request object
		RequestSpecification httpRequest = RestAssured.given();
		//response object
		Response response = httpRequest.request(Method.GET, "/api/f1/drivers.json");
		
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("response--->"+response);  //e.g. response--->io.restassured.internal.RestAssuredResponseImpl@47a86fbb
		System.out.println("responseBody--->"+responseBody);

		//Capture details of headers from the response
		String contentType = response.header("Content-Type");
		System.out.println("contentType --->"+contentType);
		Assert.assertEquals("application/json; charset=utf-8", contentType);
		
		String contentLength = response.header("Content-Length");
		System.out.println("contentLength--->"+contentLength);
		Assert.assertEquals("5509", contentLength);
		
		String xPoweredBy = response.header("X-Powered-By");
		System.out.println("xPoweredBy--->"+xPoweredBy);
		Assert.assertEquals("PHP/5.3.3", xPoweredBy);
		
		
		
	}
}
