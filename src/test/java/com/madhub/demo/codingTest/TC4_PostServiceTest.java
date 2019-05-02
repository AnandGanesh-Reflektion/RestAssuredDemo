/*
 * Class: TC4_PostServiceTest
 *
 * Created on May 3, 2019
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import constants.Config;
import constants.Constants;
import core.ResponseValidators;
import core.RestAssuredConfigurationBase;
import core.RestAssuredHelpers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC4_PostServiceTest
{
    public static Logger log = LogManager.getLogger(TC4_PostServiceTest.class.getName());

    public static int resourceId = -1;

    RestAssuredHelpers restHelpers = new RestAssuredHelpers();

    ResponseValidators responseValidator = new ResponseValidators();

    @BeforeTest
    public void initialization()
    {
        RestAssuredConfigurationBase.initBaseURI();

    }

    @Test
    public void POSTServiceTest()
    {
        RequestSpecification request = this.restHelpers.getRequestSpecification(Config.POST, Payload.postPayload());
        Response response = this.restHelpers.getResponse(Config.POST, request, Resource.readGetServiceResource(null));
        //System.out.println(respone.asString());
        this.responseValidator
            .validateGetResponse(response, Constants.POST_STATUS_CODE, "JsonSchemaSingleRecord.json", -1);
        TC4_PostServiceTest.resourceId = response.jsonPath().get("id");
    }

    @Test
    public void verifyRecordCreated()
    {
        RequestSpecification request = this.restHelpers.getRequestSpecification(Config.GET, null);
        Response response = this.restHelpers.getResponse(
            Config.GET,
            request,
            Resource.readGetServiceResource(String.valueOf(TC4_PostServiceTest.resourceId)));
        System.out.println("After resource creation=" + response.statusCode());
        if (response.getStatusCode() == Constants.GET_STATUS_CODE)
        {
            TC4_PostServiceTest.log
                .info("[Validating the resource created by POST request]: Resource created successfully");
            Assert.assertTrue(Constants.BOOLEAN_TRUE);
        }
        else if (response.getStatusCode() == Constants.ERROR_STATUS_CODE)
        {
            TC4_PostServiceTest.log.error("[Validating the resource created by POST request]: Resource not created");
            Assert.assertTrue(Constants.BOOLEAN_FALSE);
        }
    }
}
