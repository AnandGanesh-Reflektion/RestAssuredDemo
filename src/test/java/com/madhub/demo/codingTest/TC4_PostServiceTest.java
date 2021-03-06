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

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.madhub.demo.constants.Config;
import com.madhub.demo.constants.Constants;
import com.madhub.demo.core.ResponseValidators;
import com.madhub.demo.core.RestAssuredHelpers;
import com.madhub.demo.helpers.POSTPayloadBuilder;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC4_PostServiceTest
{
    public static Logger log = LogManager.getLogger(TC4_PostServiceTest.class.getName());

    RestAssuredHelpers restHelpers = new RestAssuredHelpers();

    ResponseValidators responseValidator = new ResponseValidators();

    private String contentTypeHeaderKey;

    private String contentTypeHeaderValue;

    private String charsetHeaderKey;

    private String charsetHeaderValue;

    POSTPayloadBuilder payload = new POSTPayloadBuilder();

    /**
     * @author madhub
     * Contructor to build the data used in Test case
     */
    public TC4_PostServiceTest()
    {
        this.contentTypeHeaderKey = "Content-Type";
        this.contentTypeHeaderValue = "application/json";
        this.charsetHeaderKey = "charset";
        this.charsetHeaderValue = "UTF-8";
        this.payload.payload_title = "foo";
        this.payload.payload_body = "bar";
        this.payload.payload_userId = 1;
    }

    /**
     *@author madhub
     *This will validate the POST service of the API
     */
    @Test
    public void POSTServiceTest()
    {
        //build headers used in the test case
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(this.contentTypeHeaderKey, this.contentTypeHeaderValue);
        headers.put(this.charsetHeaderKey, this.charsetHeaderValue);

        RequestSpecification request = this.restHelpers
            .getRequestSpecification(Payload.postPayload(this.payload), headers);
        Response response = this.restHelpers.getResponse(Config.POST, request, Resource.readGetServiceResource(null));
        //System.out.println(respone.asString());
        this.responseValidator
            .validateGetResponse(response, Constants.POST_STATUS_CODE, "JsonSchemaSingleRecord.json", -1);
        // TC4_PostServiceTest.resourceId = response.jsonPath().get("id");

        this.verifyRecordCreated(response.jsonPath().get("id"));
    }

    public void verifyRecordCreated(int resourceId)
    {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(this.contentTypeHeaderKey, this.contentTypeHeaderValue);
        headers.put(this.charsetHeaderKey, this.charsetHeaderValue);

        RequestSpecification request = this.restHelpers.getRequestSpecification(null, headers);

        Response response = this.restHelpers
            .getResponse(Config.GET, request, Resource.readGetServiceResource(String.valueOf(resourceId)));
        //System.out.println("After resource creation=" + response.statusCode());
        ResponseValidators.validateResourceCreated(response, Constants.GET_STATUS_CODE, true);

    }
}
