package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_DataDriven {
 
 @Test(dataProvider="empDataprovider")
 void createUsersSuccessful(String ename,String salary,String age)
 {
  
  //Specify base URI
  RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
  
  //Request object
  RequestSpecification httpRequest=RestAssured.given();
  
   
  //Request paylaod sending along with post request
  JSONObject requestParams=new JSONObject();
  
//	requestParams.put("name", "sachin"); // Cast
//	requestParams.put("salary", "70000");
//	requestParams.put("age", "40");
	
	requestParams.put("name", ename); // Cast
	requestParams.put("salary", salary);
	requestParams.put("age", age);
  
  httpRequest.header("Content-Type","application/json");
  
  httpRequest.body(requestParams.toJSONString()); // attach above data to the request
  
  //Response object
  Response response=httpRequest.request(Method.POST,"/create");
    
  
  //print response in console window
  
  String responseBody=response.getBody().asString();
  System.out.println("Response Body is:" +responseBody);
  
  //status code validation
  int statusCode=response.getStatusCode();
  System.out.println("Status code is: "+statusCode);
  Assert.assertEquals(statusCode, 200);
  
  
 }
 
 @DataProvider(name="empDataprovider")
 String[][] getEmpData()
 {
	 String empData[][]={ {"mura1","1000","10"},{"mura2","2000","20"},{"mura3","3000","30"} };
	 return (empData);
 }
 
}

