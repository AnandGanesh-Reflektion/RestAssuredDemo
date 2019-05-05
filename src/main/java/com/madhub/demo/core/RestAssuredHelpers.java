/*
 * Class: RestAssuredHelper
 *
 * Created on May 2, 2019
 *
 * (c) Copyright Lam Research Corporation, unpublished work, created 2019
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Lam Research Corporation
 * 4000 N. First Street
 * San Jose, CA
 */
package com.madhub.demo.core;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredHelpers extends RestAssuredConfigurationBase
{
    public static Logger log = LogManager.getLogger(RestAssuredHelpers.class.getName());

    Response response;

    RequestSpecification request;

    /**
     * @author madhub
     * This method will build the request specification for API execution
     * @param Payload
     * @param headers
     * @return
     */
    public RequestSpecification getRequestSpecification(String Payload, HashMap<String, String> headers)
    {

        this.request = RestAssured.given();
        if (headers != null)
        {
            this.request.headers(headers);
        }
        if (Payload != null)
        {
            this.request.body(Payload);
        }
        this.request.log().all();
        return this.request;
    }

    /**
     * @author madhub
     * This method will retrive the response received from API call
     * @param httpRquestType
     * @param request
     * @param resource
     * @return
     */
    public Response getResponse(String httpRquestType, RequestSpecification request, String resource)
    {
        if (httpRquestType.equalsIgnoreCase("GET"))
        {
            this.response = request.get(resource);
            RestAssuredHelpers.log.debug("Response Logging :\n" + this.response.asString());
            this.response.then().log().all();

        }
        else if (httpRquestType.equalsIgnoreCase("POST"))
        {
            this.response = request.post(resource);
            RestAssuredHelpers.log.debug("Response Logging :\n" + this.response.asString());
            this.response.then().log().all();
        }
        else if (httpRquestType.equalsIgnoreCase("PUT"))
        {
            this.response = request.put(resource);
            RestAssuredHelpers.log.debug("Response Logging :\n" + this.response.asString());
            this.response.then().log().all();
        }
        else if (httpRquestType.equalsIgnoreCase("DELETE"))
        {
            this.response = request.delete(resource);
            RestAssuredHelpers.log.debug("Response Logging :\n" + this.response.asString());
            this.response.then().log().all();
        }
        return this.response;

    }

}
