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

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import core.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC1_GetServiceTest extends TestBase
{
    @BeforeTest
    public static void initialization()
    {
        RestAssured.baseURI = TestBase.initBaseURI();
    }

    @Test
    public static void testGetService()
    {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("charset", "UTF-8");
        Response res = request.get(Resource.readGetServiceResource());
        int statusCode = res.getStatusCode();
        String body = res.getBody().asString();
        Assert.assertEquals(200, statusCode);

    }
}
