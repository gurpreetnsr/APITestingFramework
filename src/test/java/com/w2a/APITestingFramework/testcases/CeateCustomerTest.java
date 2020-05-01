package com.w2a.APITestingFramework.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.APITestingFramework.APIs.CreateCustomerAPI;
import com.w2a.APITestingFramework.listeners.ExtentListeners;
import com.w2a.APITestingFramework.setup.BaseTest;
import com.w2a.APITestingFramework.utilities.DataUtil;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Hashtable;

public class CeateCustomerTest extends BaseTest {
	
	@Test(dataProviderClass = DataUtil.class,dataProvider = "getData")
	public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String, String> data) {
		
		Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithValidAuthKey(data);
		ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();
		ExtentListeners.testReport.get().info(response.prettyPrint());
		ExtentListeners.testReport.get().info(String.valueOf(response.getStatusCode()));
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(dataProviderClass = DataUtil.class,dataProvider = "getData")
	public void validateCreateCustomerAPIWithInvalidSecretKey(Hashtable<String, String> data) {
		
		Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInvalidAuthKey(data);
		ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();
		System.out.println(response.getStatusCode());
		ExtentListeners.testReport.get().info(response.prettyPrint());
		ExtentListeners.testReport.get().info(String.valueOf(response.getStatusCode()));
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
}
