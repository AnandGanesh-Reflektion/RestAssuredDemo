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
import utils.PropertiesUtility;

public class TestBase
{
    public static String initBaseURI()
    {
        //  FileInputStream fis = new FileInputStream("C:\\");
        String protocol = PropertiesUtility.readProperty(Config.PROTOCOL);
        String host = PropertiesUtility.readProperty(Config.HOST);
        String baseURI = protocol + "://" + host;
        return baseURI;
    }

}
