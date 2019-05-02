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

import core.RestAssuredConfigurationBase;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import utils.TestUtils;

public class TC1_GetServiceTest extends RestAssuredConfigurationBase
{
    public static Logger log = LogManager.getLogger(RestAssuredConfigurationBase.class.getName());

    @BeforeTest
    public static void initialization()
    {
        RestAssured.baseURI = RestAssuredConfigurationBase.initBaseURI();
    }

    @Test
    public static void testGetService()
    {
        RequestSpecification request = new RestAssuredConfigurationBase().getRequestSpecification();
        /* request.header("Content-Type", "application/json");
        request.header("charset", "UTF-8");*/
        Response res = request.get(Resource.readGetServiceResource());
        if (res != null)
        {
            //Validate the Response statuscode
            try
            {
                Assert.assertEquals(200, res.getStatusCode());
                System.out.println("Received Status Code = " + res.getStatusCode() + ". Matches the Expected.");
                TC1_GetServiceTest.log
                    .info("Received Status Code = " + res.getStatusCode() + ". Matches the Expected.");
            }
            catch (Throwable t)
            {
                System.out.println("Expected Status Code is 200, Received Status Code is " + res.getStatusCode());
                Assert.assertTrue(false);
            }

            //Validate the Response Schema
            res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchemaFullList.json"));

            //Validate the Response records count
            JsonPath js = TestUtils.rawToJson(res);
            int recordCount = js.get("$.size()");
            if (recordCount < 100)
            {
                System.out.println("Expected Record count is >= 100, but received Record count is " + recordCount);

                Assert.assertTrue(false);
            }
            else
            {
                System.out
                    .println("Received Record count is = " + recordCount + ". Matches the Expectation of >= 100 ");
                Assert.assertTrue(true);
            }

        }

    }
}
