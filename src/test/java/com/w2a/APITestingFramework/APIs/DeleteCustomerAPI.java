package com.w2a.APITestingFramework.APIs;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.APITestingFramework.APIs.CreateCustomerAPI;
import com.w2a.APITestingFramework.listeners.ExtentListeners;
import com.w2a.APITestingFramework.setup.BaseTest;
import com.w2a.APITestingFramework.utilities.DataUtil;

import io.restassured.response.Response;

public class DeleteCustomerAPI extends BaseTest {

	public static Response sendDeleteRequestToDeleteCustomerAPIWithValidID(Hashtable<String, String> data) {
		Response response = given().auth().basic(config.getProperty("validSecretKey"), "")
				.when().delete(config.getProperty("customerAPIEndPoint")+"/"+data.get("id"));
		
		return response;
	}

}
