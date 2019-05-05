/*
 * Class: TC3_GetServiceInvalidResource
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

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.madhub.demo.constants.Config;
import com.madhub.demo.core.ResponseValidators;
import com.madhub.demo.core.RestAssuredHelpers;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC3_GetServiceInvalidResource
{
    public static Logger log = LogManager.getLogger(TC3_GetServiceInvalidResource.class.getName());

    RestAssuredHelpers restHelpers = new RestAssuredHelpers();

    ResponseValidators responseValidator = new ResponseValidators();

    private String contentTypeHeaderKey;

    private String contentTypeHeaderValue;

    private String charsetHeaderKey;

    private String charsetHeaderValue;

    /**
     * @author madhub
     * Contructor to build the data used in Test case
     */
    public TC3_GetServiceInvalidResource()
    {
        this.contentTypeHeaderKey = "Content-Type";
        this.contentTypeHeaderValue = "application/json";
        this.charsetHeaderKey = "charset";
        this.charsetHeaderValue = "UTF-8";
    }

    /**
     * @author madhub
     * This will validate the GET service of API for the invalid record
     */
    @Test
    public void GETServiceWithInvalidResource()
    {
        //build headers used in the test case
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(this.contentTypeHeaderKey, this.contentTypeHeaderValue);
        headers.put(this.charsetHeaderKey, this.charsetHeaderValue);

        RequestSpecification request = this.restHelpers.getRequestSpecification(null, headers);
        Response response = this.restHelpers.getResponse(Config.GET, request, Resource.readGetServiceInvalidResource());
        this.responseValidator.validateGetResponse(response, 404, null, -1);
    }

}
