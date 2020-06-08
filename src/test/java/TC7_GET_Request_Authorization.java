import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC7_GET_Request_Authorization {

	@Test
	public void AuthorizationTest() {
		
		//specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
		
		//Basic authentication (one of so many authentications)
		//specify userid / passwd before sending out the request
		PreemptiveBasicAuthScheme  authscheme = new PreemptiveBasicAuthScheme();  //Create object PreemptiveBasicAuthScheme
		authscheme.setUserName("ToolsQA");
		authscheme.setPassword("TestPassword");
		RestAssured.authentication = authscheme;  //type of authentication i.e. Basic Authentication
		
		
		//request object
		RequestSpecification httpRequest = RestAssured.given();
		//response object
		Response response = httpRequest.request(Method.GET, "/");  // "/" represents root i.e. home page
		
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("response--->"+response);  //e.g. response--->io.restassured.internal.RestAssuredResponseImpl@47a86fbb
		System.out.println("responseBody--->"+responseBody);
		System.out.println("user.dir" +System.getProperty("user.dir"));
	
		//verify status code and status line
		int statusCode = response.getStatusCode();
		System.out.println("status code--->"+statusCode);
		Assert.assertEquals(200, statusCode);
	
	}
	
	
	
}
