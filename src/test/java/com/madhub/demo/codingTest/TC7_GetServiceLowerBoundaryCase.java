/*
 * Class: TC7_GetServiceLowerBoundaryCase
 *
 * Created on May 5, 2019
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

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC7_GetServiceLowerBoundaryCase
{
    public static Logger log = LogManager.getLogger(TC7_GetServiceLowerBoundaryCase.class.getName());

    RestAssuredHelpers restHelpers = new RestAssuredHelpers();

    ResponseValidators responseValidator = new ResponseValidators();

    private String contentTypeHeaderKey;

    private String contentTypeHeaderValue;

    private String charsetHeaderKey;

    private String charsetHeaderValue;

    private String param;

    public TC7_GetServiceLowerBoundaryCase()
    {
        this.contentTypeHeaderKey = "Content-Type";
        this.contentTypeHeaderValue = "application/json";
        this.charsetHeaderKey = "charset";
        this.charsetHeaderValue = "UTF-8";
        this.param = "0";
    }

    @Test
    public void GetServiceLowerBoundaryCase()
    {
        //build headers used in the test case
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(this.contentTypeHeaderKey, this.contentTypeHeaderValue);
        headers.put(this.charsetHeaderKey, this.charsetHeaderValue);

        RequestSpecification request = this.restHelpers.getRequestSpecification(null, headers);
        Response response = this.restHelpers
            .getResponse(Config.GET, request, Resource.readGetServiceResource(this.param));
        //System.out.println(response.getBody().asString());
        this.responseValidator.validateGetResponse(response, Constants.ERROR_STATUS_CODE, null, -1);

    }

    @BeforeTest
    public void initialization()
    {
        RestAssuredConfigurationBase.initBaseURI();

    }
}
