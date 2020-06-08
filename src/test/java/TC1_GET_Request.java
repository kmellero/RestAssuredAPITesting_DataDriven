import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
public class TC1_GET_Request {

	// Steps to verify API
	//1. define BaseURI
	//2. Request object
	//3. Response
	//4. ResponseBody
	//Validations:
	//5. Status Code
	//6. Status Line
	//7. headers(e.g. Content-Type
	//8. responseTime
	
	@Test
	public void getweatherDetails() {
	
		//specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		//request object
		RequestSpecification httpRequest = RestAssured.given();
		//response object
		Response response = httpRequest.request(Method.GET, "/Warsaw");
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("response--->"+response);  //e.g. response--->io.restassured.internal.RestAssuredResponseImpl@47a86fbb
		System.out.println("responseBody--->"+responseBody);
		//verify status code and status line
		int statusCode = response.getStatusCode();
		System.out.println("status code--->"+statusCode);
		Assert.assertEquals(200, statusCode);
		//status line validation e.g. HTTP/1.1 200 OK
		String statusLine = response.getStatusLine();
		System.out.println("status line --->"+statusLine);
		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
		
		
	}
}
