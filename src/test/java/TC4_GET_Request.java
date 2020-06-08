import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC4_GET_Request {

	@Test
	public void getWeatherDetails() {
		
		//specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com"; // in the original lesson is: "https://maps.googleapis.com";
		//request object
		RequestSpecification httpRequest = RestAssured.given();
		//response object
		Response response = httpRequest.request(Method.GET, "/utilities/weather/city/Warsaw");
		
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("response--->"+response);  //e.g. response--->io.restassured.internal.RestAssuredResponseImpl@47a86fbb
		System.out.println("responseBody--->"+responseBody);

		Headers	allheaders = response.headers();  //returns all headers in a key : value hashmap from the response
		
		for(Header header:allheaders) {
			System.out.println("header Name--->"+header.getName()+ "	header Value--->"+ header.getValue());
		}

}
}