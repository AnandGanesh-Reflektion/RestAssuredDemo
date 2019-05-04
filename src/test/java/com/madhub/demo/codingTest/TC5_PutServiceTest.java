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

import java.util.HashMap;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import constants.Config;
import constants.Constants;
import core.ResponseValidators;
import core.RestAssuredConfigurationBase;
import core.RestAssuredHelpers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.PayloadBuilder;

public class TC5_PutServiceTest
{
    RestAssuredHelpers restHelpers = new RestAssuredHelpers();

    ResponseValidators responseValidator = new ResponseValidators();

    private String contentTypeHeaderKey;

    private String contentTypeHeaderValue;

    private String charsetHeaderKey;

    private String charsetHeaderValue;

    private String param;

    private int payload_id;

    private String payload_title;

    private String payload_body;

    private int payload_userId;

    PayloadBuilder payload = new PayloadBuilder();;

    public TC5_PutServiceTest()
    {
        this.contentTypeHeaderKey = "Content-Type";
        this.contentTypeHeaderValue = "application/json";
        this.charsetHeaderKey = "charset";
        this.charsetHeaderValue = "UTF-8";
        this.param = "1";
        // this.payload

        this.payload.payload_id = 1;
        this.payload.payload_title = "abc";
        this.payload.payload_body = "xyz";
        this.payload.payload_userId = 1;

        /*this.payload_id = 1;
        this.payload_title = "abc";
        this.payload_body = "xyz";
        this.payload_userId = 1;*/
    }

    public Response getUpdatedResourceAfterPUT()
    {
        //build headers used in the test case
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(this.contentTypeHeaderKey, this.contentTypeHeaderValue);
        headers.put(this.charsetHeaderKey, this.charsetHeaderValue);

        RequestSpecification request = this.restHelpers.getRequestSpecification(null, headers);

        Response response = this.restHelpers
            .getResponse(Config.GET, request, Resource.readGetServiceResource(this.param));

        return response;

    }

    @BeforeTest
    public void initialization()
    {
        RestAssuredConfigurationBase.initBaseURI();

    }

    @Test
    public void PUTServiceTest()
    {
        //build headers used in the test case
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(this.contentTypeHeaderKey, this.contentTypeHeaderValue);
        headers.put(this.charsetHeaderKey, this.charsetHeaderValue);

        RequestSpecification request = this.restHelpers
            .getRequestSpecification(Payload.putPayload(this.payload), headers);

        Response response = this.restHelpers
            .getResponse(Config.PUT, request, Resource.readGetServiceResource(this.param));

        this.responseValidator
            .validateGetResponse(response, Constants.GET_STATUS_CODE, "JsonSchemaSingleRecord.json", -1);
        Response newResourceResponse = this.getUpdatedResourceAfterPUT();

        this.responseValidator.compareResponseData(response, newResourceResponse);

    }

}
