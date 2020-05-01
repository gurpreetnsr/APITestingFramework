package com.w2a.APITestingFramework.testcases;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.w2a.APITestingFramework.APIs.CreateCustomerAPI;
import com.w2a.APITestingFramework.APIs.DeleteCustomerAPI;
import com.w2a.APITestingFramework.listeners.ExtentListeners;
import com.w2a.APITestingFramework.setup.BaseTest;
import com.w2a.APITestingFramework.utilities.DataUtil;
import com.w2a.APITestingFramework.utilities.TestUtil;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Hashtable;

public class DeleteCustomerTest extends BaseTest {
	
	@Test(dataProviderClass = DataUtil.class,dataProvider = "getData")
	public void deleteCustomer(Hashtable<String, String> data) {
		
		Response response = DeleteCustomerAPI.sendDeleteRequestToDeleteCustomerAPIWithValidID(data);
	//	ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();
		ExtentListeners.testReport.get().info(data.toString());
	//	ExtentListeners.testReport.get().info(response.prettyPrint());
	//	ExtentListeners.testReport.get().info(String.valueOf(response.getStatusCode()));
		System.out.println(response.getStatusCode());
		
//		String actualId = response.jsonPath().get("id").toString();
//		System.out.println("Getting ID from JSON Path :" + actualId);
//		Assert.assertEquals(response.getStatusCode(), 200);
//		Assert.assertEquals(actualId, data.get("id").trim(), "ID not matching");
		
		Assert.assertTrue(TestUtil.jsonHasKey(response.asString(),"id"),"Id key is not present in the response");
		String actual_ID = TestUtil.getJsonKeyValue(response.asString(),"id");
		
		Assert.assertEquals(actual_ID, data.get("id"), "ID not matching");
		
		System.out.println(TestUtil.getJsonKeyValue(response.asString(),"object"));
		//System.out.println(TestUtil.getJsonKeyValue(response.asString(),"deleted"));
		
	}
	
}
