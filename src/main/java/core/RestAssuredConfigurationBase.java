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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constants.Config;
import io.restassured.RestAssured;
import utils.PropertiesUtility;

public class RestAssuredConfigurationBase
{
    public static Logger log = LogManager.getLogger(RestAssuredConfigurationBase.class.getName());

    // public static String baseURI = null;

    public static void initBaseURI()
    {
        String protocol = PropertiesUtility.readProperty(Config.PROTOCOL);
        String host = PropertiesUtility.readProperty(Config.HOST);
        //  RestAssuredConfigurationBase.baseURI = protocol + "://" + host;
        RestAssured.baseURI = protocol + "://" + host;

    }

}
