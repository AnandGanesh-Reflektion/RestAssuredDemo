/*
 * Class: TC1_GetServiceTests
 *
 * Created on May 1, 2019
 *
 * (c) Copyright Lam Research Corporation, unpublished work, created 2019
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Lam Research Corporation
 * 4000 N. First Street
 * San Jose, CA
 */
package com.madhub.demo.codingTest;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.madhub.demo.constants.Config;
import com.madhub.demo.constants.Constants;
import com.madhub.demo.core.ResponseValidators;
import com.madhub.demo.core.RestAssuredConfigurationBase;
import com.madhub.demo.core.RestAssuredHelpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC1_GetServiceTest extends RestAssuredConfigurationBase
{
    public static Logger log = LogManager.getLogger(TC1_GetServiceTest.class.getName());

    RestAssuredHelpers restHelpers = new RestAssuredHelpers();

    ResponseValidators responseValidator = new ResponseValidators();

    private String contentTypeHeaderKey;

    private String contentTypeHeaderValue;

    private String charsetHeaderKey;

    private String charsetHeaderValue;

    public TC1_GetServiceTest()
    {
        this.contentTypeHeaderKey = "Content-Type";
        this.contentTypeHeaderValue = "application/json";
        this.charsetHeaderKey = "charset";
        this.charsetHeaderValue = "UTF-8";
    }

    @Test
    public void GETServiceTest()
    {
        //build headers used in the test case
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(this.contentTypeHeaderKey, this.contentTypeHeaderValue);
        headers.put(this.charsetHeaderKey, this.charsetHeaderValue);

        //log the baseURI used
        TC1_GetServiceTest.log.info("Test Specification:\n BaseURI = " + RestAssured.baseURI);

        //Create request specifications for the GET request
        RequestSpecification request = this.restHelpers.getRequestSpecification(null, headers);

        //Execute the RestAPI to capture the response
        Response response = this.restHelpers.getResponse(Config.GET, request, Resource.readGetServiceResource(null));

        //Validate the response received fro API request
        this.responseValidator.validateGetResponse(response, Constants.GET_STATUS_CODE, "JsonSchemaFullList.json", 100);

    }

    @BeforeTest
    public void initialization()
    {
        //Set the baseURI used in the API request
        RestAssuredConfigurationBase.initBaseURI();

    }
}
