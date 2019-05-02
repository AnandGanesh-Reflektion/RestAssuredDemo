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
package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constants.Constants;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import utils.TestUtils;

public class ResponseValidators
{
    public static Logger log = LogManager.getLogger(ResponseValidators.class.getName());

    public void validateGetResponse(Response res, int statusCode, String schemaFile, int recordCount)
    {
        //Validate the Response statuscode
        if (res != null)
        {
            res.then().log().all();
            if (statusCode != -1)
            {
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
                    Assert.assertTrue(false);
                }
            }

            //Validate the Response Schema
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
                    Assert.assertTrue(Constants.BOOLEAN_FALSE);
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

                    Assert.assertTrue(Constants.BOOLEAN_FALSE);
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
            Assert.assertTrue(Constants.BOOLEAN_FALSE);
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
                Assert.assertTrue(false);
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
                Assert.assertTrue(Constants.BOOLEAN_FALSE);
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
            }
        }
        else
        {
            ResponseValidators.log.error("[Validating the Response]: Received null response");
            Assert.assertTrue(Constants.BOOLEAN_FALSE);
        }
    }
}
