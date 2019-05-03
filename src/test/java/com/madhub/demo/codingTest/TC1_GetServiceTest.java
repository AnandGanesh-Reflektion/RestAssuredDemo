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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import constants.Config;
import constants.Constants;
import core.ResponseValidators;
import core.RestAssuredConfigurationBase;
import core.RestAssuredHelpers;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC1_GetServiceTest extends RestAssuredConfigurationBase
{
    public static Logger log = LogManager.getLogger(TC1_GetServiceTest.class.getName());

    RestAssuredHelpers restHelpers = new RestAssuredHelpers();

    ResponseValidators responseValidator = new ResponseValidators();

    @Test
    public void GETServiceTest()
    {
        TC1_GetServiceTest.log.info("Test Specification:\n BaseURI = " + RestAssured.baseURI);

        RequestSpecification request = this.restHelpers.getRequestSpecification(Config.GET, null);
        Response response = this.restHelpers.getResponse(Config.GET, request, Resource.readGetServiceResource(null));

        this.responseValidator
            .validateGetResponse(response, Constants.GET_STATUS_CODE, "JsonSchemaSingleRecord.json", 100);

    }

    @BeforeTest
    public void initialization()
    {
        RestAssuredConfigurationBase.initBaseURI();

    }
}
