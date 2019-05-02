/*
 * Class: TC5_PutServiceTest
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

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import constants.Config;
import core.ResponseValidators;
import core.RestAssuredConfigurationBase;
import core.RestAssuredHelpers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC5_PutServiceTest
{
    RestAssuredHelpers restHelpers = new RestAssuredHelpers();

    ResponseValidators responseValidator = new ResponseValidators();

    @BeforeTest
    public void initialization()
    {
        RestAssuredConfigurationBase.initBaseURI();

    }

    @Test
    public void PUTServiceTest()
    {
        String input = "1";
        RequestSpecification request = this.restHelpers.getRequestSpecification(Config.PUT, Payload.putPayload());
        Response response = this.restHelpers.getResponse(Config.PUT, request, Resource.readGetServiceResource(input));
        System.out.println(response.asString());
        /* this.responseValidator
            .validateGetResponse(response, Constants.GET_STATUS_CODE, "JsonSchemaSingleRecord.json", -1);*/
    }

}
