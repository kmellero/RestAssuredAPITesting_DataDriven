package datadriventesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class DataDrivenTest_AddUser {

	@Test(dataProvider="userdataprovider")
	//single set of data
	//public void postNewUser() {
	//data driven 
	public void postNewUser(String ename, String ejob)  {	
		//define baseURI
		RestAssured.baseURI = "https://reqres.in/api";  
		
		//request object
		RequestSpecification httpRequest = RestAssured.given();  

//this block is for a POST request with single set of data
		
		// create data to be sent along with POST request.  These data are in the hash (key:value) format.  
		JSONObject requestparams  = new JSONObject();
		requestparams.put("name", ename);  //"BeerT");
		requestparams.put("job", ejob);  //"Hycel");
		
		//specify the format of the data in header: Json
		httpRequest.header("Content-Type","application/json; charset=utf-8");
		//attach above data to the request body, convert hash format to json format
		httpRequest.body(requestparams.toJSONString());
		
		//send request and get the response
		Response response = httpRequest.request(Method.POST, "/users");

		//capture response body to perform validations
		String responseBody = response.getBody().asString();
		System.out.println("response--->"+response);
		System.out.println("respnseBody-->"+responseBody);
		Assert.assertEquals(responseBody.contains(ename),true);
		Assert.assertEquals(responseBody.contains(ejob), true);
		Assert.assertEquals(responseBody.contains("id"), true);
		
		//response status code
		int statusCode = response.getStatusCode();
		System.out.println("statusCode-->"+statusCode);
		Assert.assertEquals(201,statusCode);
		
		
	}
	@DataProvider(name="userdataprovider")  //above @Test will be executed 3 times (# rows)
	//Read data from excel
	 String[][] getUserData() throws IOException{
//		String path="C:/Users/kajetanmellerowicz/eclipse-workspace/RestAssuredAPITesting/src/test/java/datadriventesting/userjobdata.xlsx";
		String path=System.getProperty("user.dir")+"\\src\\test\\java\\datadriventesting\\userjobdata.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "UserJob");
		int colcount=XLUtils.getCellcount(path, "UserJob", rownum);
		XLUtils.getCellData(path, "UserJob", rownum, colcount);
		
		// static string array; when reading from a file
		String user[][]=new String[rownum][colcount];
		for(int i=1; i<=rownum;i++) {		//row 0 contains headers
			for(int j=0; j<colcount;j++) {
				user[i-1][j]=XLUtils.getCellData(path, "UserJob", i, j);
			}
		}
		//dynamic array
//		String user[][] = {{"kAjM","Lavk"},{"MjaK","Lesk"},{"Tesk","Kask"}};  //Three rows and two different columns
		return (user);
	}
	 
	 
	 
}
