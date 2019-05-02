/*
 * Class: TestBase
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
package core;

import constants.Config;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import utils.PropertiesUtility;

public class RestAssuredConfigurationBase
{
    public static String initBaseURI()
    {
        //  FileInputStream fis = new FileInputStream("C:\\");
        String protocol = PropertiesUtility.readProperty(Config.PROTOCOL);
        String host = PropertiesUtility.readProperty(Config.HOST);
        String baseURI = protocol + "://" + host;
        return baseURI;
    }

    private String contentTypeHeaderKey;

    private String contentTypeHeaderValue;

    private String charsetHeaderKey;

    private String charsetHeaderValue;

    public RestAssuredConfigurationBase()
    {
        this.contentTypeHeaderKey = "Content-Type";
        this.contentTypeHeaderValue = "application/json";
        this.charsetHeaderKey = "charset";
        this.charsetHeaderValue = "UTF-8";
    }

    public RequestSpecification getRequestSpecification()
    {
        RequestSpecification request = RestAssured.given();

        request.header(this.contentTypeHeaderKey, this.contentTypeHeaderValue);
        request.header(this.charsetHeaderKey, this.charsetHeaderValue);
        return request;
    }

}
