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
package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredHelpers extends RestAssuredConfigurationBase
{
    public static Logger log = LogManager.getLogger(RestAssuredHelpers.class.getName());

    private String contentTypeHeaderKey;

    private String contentTypeHeaderValue;

    private String charsetHeaderKey;

    private String charsetHeaderValue;

    Response response;

    RequestSpecification request;

    public RestAssuredHelpers()
    {
        this.contentTypeHeaderKey = "Content-Type";
        this.contentTypeHeaderValue = "application/json";
        this.charsetHeaderKey = "charset";
        this.charsetHeaderValue = "UTF-8";
    }

    /* public Response executeRestAssuredAPI(String httpRquestType, String resourcePath, String requestBody)
            throws URISyntaxException
    {

        if (httpRquestType.equalsIgnoreCase("GET"))
        {
             RequestSpecification request = RestAssured.given();
            request.header(this.contentTypeHeaderKey, this.contentTypeHeaderValue);
            request.header(this.charsetHeaderKey, this.charsetHeaderValue);
            return request;
            this.response = RestAssured.given().header(this.contentTypeHeaderKey, this.contentTypeHeaderValue)
                .header(this.charsetHeaderKey, this.charsetHeaderValue).when()
                .get(new URI(RestAssuredConfigurationBase.baseURI + resourcePath)).then().extract().response();
            return this.response;
        }
        else if (httpRquestType.equalsIgnoreCase("POST"))
        {

        }
        return this.response;

    }
    */
    public RequestSpecification getRequestSpecification(String httpRquestType, String Payload)
    {
        if (httpRquestType.equalsIgnoreCase("GET"))
        {
            this.request = RestAssured.given();
            this.request.header(this.contentTypeHeaderKey, this.contentTypeHeaderValue);
            this.request.header(this.charsetHeaderKey, this.charsetHeaderValue);
            this.request.log().all();
        }
        else if (httpRquestType.equalsIgnoreCase("POST") || httpRquestType.equalsIgnoreCase("PUT"))
        {
            this.request = RestAssured.given();
            this.request.header(this.contentTypeHeaderKey, this.contentTypeHeaderValue);
            this.request.header(this.charsetHeaderKey, this.charsetHeaderValue);
            this.request.body(Payload);
            this.request.log().all();
        }
        return this.request;

    }

    public Response getResponse(String httpRquestType, RequestSpecification request, String resource)
    {
        if (httpRquestType.equalsIgnoreCase("GET"))
        {

            this.response = request.get(resource);
            RestAssuredHelpers.log.debug("Response Logging :\n" + this.response.asString());

        }
        else if (httpRquestType.equalsIgnoreCase("POST"))
        {
            this.response = request.post(resource);
            RestAssuredHelpers.log.debug("Response Logging :\n" + this.response.asString());
        }
        return this.response;

    }

}
