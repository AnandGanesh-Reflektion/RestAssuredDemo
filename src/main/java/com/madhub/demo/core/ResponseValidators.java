/*
 * Class: ResponseValidators
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.madhub.demo.constants.Constants;
import com.madhub.demo.helpers.ResponsePojo;
import com.madhub.demo.utils.TestUtils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class ResponseValidators
{
    public static Logger log = LogManager.getLogger(ResponseValidators.class.getName());

    /**
     * @param response
     * @param statusCode
     * @param created
     */
    public static void validateResourceCreated(Response response, int statusCode, boolean created)
    {
        if (created == Constants.BOOLEAN_TRUE)
        {
            try
            {
                Assert.assertEquals(statusCode, response.getStatusCode());
                ResponseValidators.log
                    .info("[Validating the resource created by POST request]: Resource created successfully");
            }
            catch (Throwable t)
            {
                ResponseValidators.log.error(
                    "[Validating the resource created by POST request]: Resource not created. Expected StatusCode is "
                            + statusCode + ", Received is " + response.getStatusCode());
                Assert.fail(
                    "[Validating the resource created by POST request]: Resource not created. [Status Code Validation]: "
                            + t);
            }
        }
    }

    /**
     * this will compare response data
     *
     * @param expectedResponse expected response
     * @param receivedResponse
     */
    public void compareResponseData(Response expectedResponse, Response receivedResponse)
    {
        ResponsePojo expectedBody = expectedResponse.getBody().as(ResponsePojo.class);
        ResponsePojo receivedBody = receivedResponse.getBody().as(ResponsePojo.class);
        //Assert.assertTrue(expectedBody.equals(receivedBody));

        if (!(expectedBody.id == receivedBody.id))
        {
            Assert.fail(
                "[Validting Newly Created/Updated Resource]: \"id\" value is not as expected.  Value requested ="
                        + expectedBody.id + ", but received=" + receivedBody.id);
        }
        if (!(expectedBody.title == receivedBody.title))
        {
            Assert.fail(
                "[Validting Newly Created/Updated Resource]: \"title\" value is not as expected.  Value requested ="
                        + expectedBody.title + ", but received=" + receivedBody.title);
        }
        if (!(expectedBody.body == receivedBody.body))
        {
            Assert.fail(
                "[Validting Newly Created/Updated Resource]: \"body\" value is not as expected.  Value requested ="
                        + expectedBody.body + ", but received=" + receivedBody.body);
        }
        if (!(expectedBody.userId == receivedBody.userId))
        {
            Assert.fail(
                "[Validting Newly Created/Updated Resource]: \"userId\" value is not as expected.  Value requested ="
                        + expectedBody.userId + ", but received=" + receivedBody.userId);
        }

    }

    /**
     * @param res
     * @param statusCode
     * @param schemaFile
     * @param recordCount
     */
    public void validateGetResponse(Response res, int statusCode, String schemaFile, int recordCount)
    {

        //Validate the Response statuscode
        if (res != null)
        {
            res.then().log().all();
            if (statusCode != -1)
            {
                // Assert.assertEquals(statusCode, res.getStatusCode());
                try
                {
                    Assert.assertEquals(statusCode, res.getStatusCode());
                    ResponseValidators.log.info(
                        "[Validating Status Code]: Received Status Code = " + res.getStatusCode()
                                + ". Matches the Expected.");
                }
                catch (Throwable t)
                {
                    ResponseValidators.log.error(
                        "[Validating Status Code]: Expected Status Code is = " + statusCode
                                + ", Received Status Code is " + res.getStatusCode());
                    Assert.fail(
                        "[Validating Status Code]: Expected Status Code is = " + statusCode
                                + ", Received Status Code is " + res.getStatusCode());
                }
            }

            //Validate the Response Schema
            //res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFile));
            if (schemaFile != null)
            {
                try
                {
                    res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFile));
                    ResponseValidators.log.info("[Validating Json Schema]: Json Schema is as expected.");
                }
                catch (Throwable t)
                {
                    ResponseValidators.log.error("[Validating Json Schema]: Json Schema validation failed\n");
                    ResponseValidators.log.debug("Json schema validation error:\n" + t);
                    Assert.fail("[Validating Json Schema]: Json Schema validation failed. \n" + t.getMessage());
                }
            }

            //Validate the Response records count
            if (recordCount != -1)
            {
                JsonPath js = TestUtils.rawToJson(res);
                int actualCount = js.get("$.size()");
                if (actualCount < recordCount)
                {
                    ResponseValidators.log.error(
                        "[Validating Record Count]: Expected Record count is >= 100, but received Record count is "
                                + recordCount);

                    Assert.fail(
                        "[Validating Record Count]: Expected Record count is >= 100, but received Record count is "
                                + recordCount);
                }
                else
                {
                    ResponseValidators.log.info(
                        "[Validating Record Count]: Received Record count is = " + recordCount
                                + ". Matches the Expectation of >= 100 ");
                    Assert.assertTrue(Constants.BOOLEAN_TRUE);
                }
            }

        }
        else
        {
            ResponseValidators.log.error("[Validating Response]: Received null response");
            Assert.fail("[Validating Response]: Received null response");
        }
    }

    public void validateGetResponse(
        Response res,
        int statusCode,
        String schemaFile,
        String field,
        String expectedFieldValue)
    {
        //Validate the Response statuscode
        if (res != null)
        {
            res.then().log().all();
            try
            {
                Assert.assertEquals(statusCode, res.getStatusCode());
                ResponseValidators.log.info(
                    "[Validating Status Code]: Received Status Code = " + res.getStatusCode()
                            + ". Matches the Expected.");
            }
            catch (Throwable t)
            {
                ResponseValidators.log.error(
                    "[Validating Status Code]: Expected Status Code is = " + statusCode + ", Received Status Code is "
                            + res.getStatusCode());
                Assert.fail(
                    "[Validating Status Code]: Expected Status Code is = " + statusCode + ", Received Status Code is "
                            + res.getStatusCode());
            }

            //Validate the Response Schema
            try
            {
                res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFile));
                ResponseValidators.log.info("[Validating Json Schema]: Json Schema is as Expected.");
                ResponseValidators.log.info("[Validating Record Count]: Received single record as expected");
            }
            catch (Throwable t)
            {
                ResponseValidators.log.error("[Validating Json Schema]: Json Schema validation failed\n");
                ResponseValidators.log.debug("Json schema validation error:\n" + t);
                Assert.fail("[Validating Json Schema]: Json Schema validation failed");
            }

            //Validate the Response records count
            JsonPath js = TestUtils.rawToJson(res);
            int receivedFieldValue = js.get(field);
            try
            {
                Assert.assertEquals(Integer.parseInt(expectedFieldValue), receivedFieldValue);
                ResponseValidators.log.info(
                    "[Validating Response Field]: Validation of response field \"" + field
                            + "\" has Passed: Received value = " + receivedFieldValue + " as Expected");
            }
            catch (Throwable t)
            {
                ResponseValidators.log.error(
                    "[Validating Response Field]: Validation of response field " + field
                            + " has Failed: Expected value = " + Integer.parseInt(expectedFieldValue)
                            + ", Received value = " + receivedFieldValue);
                Assert.fail(
                    "[Validating Response Field]: Validation of response field " + field
                            + " has Failed: Expected value = " + Integer.parseInt(expectedFieldValue)
                            + ", Received value = " + receivedFieldValue);
            }
        }
        else
        {
            ResponseValidators.log.error("[Validating the Response]: Received null response");
            Assert.fail("[Validating Response]: Received null response");
        }
    }

}
