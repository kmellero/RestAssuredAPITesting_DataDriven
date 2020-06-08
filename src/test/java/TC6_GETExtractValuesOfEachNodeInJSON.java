import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC6_GETExtractValuesOfEachNodeInJSON {
	
	
	@Test
	public void getWeatherDetails() {
		
		//specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com"; // in the original lesson is: "https://maps.googleapis.com";
		//request object
		RequestSpecification httpRequest = RestAssured.given();
		//response object
		Response response = httpRequest.request(Method.GET, "/utilities/weather/city/Warsaw");
		
/*		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("response--->"+response);  //e.g. response--->io.restassured.internal.RestAssuredResponseImpl@47a86fbb
		System.out.println("responseBody--->"+responseBody);
*/		
		//instead of complete body, each key: value needs to be captured
		//need to use JSONPath class
		
		JsonPath jsonpath = response.jsonPath(); 
		System.out.println("City-->"+jsonpath.get("City"));  //return the value for the key="City"
		System.out.println("Temperature-->"+jsonpath.get("Temperature"));
		System.out.println("Humidity-->"+jsonpath.get("Humidity"));
		System.out.println("WeatherDescription-->"+jsonpath.get("WeatherDescription"));
		System.out.println("WindSpeed-->"+jsonpath.get("WindSpeed"));
		System.out.println("WindDirectionDegree-->"+jsonpath.get("WindDirectionDegree"));
		
		//weather changes, so this test has to be adjusted. It's just an example for a test
		Assert.assertEquals(jsonpath.get("Temperature"), "3.13 Degree celsius");


}


}
