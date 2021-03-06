/*
 * Class: PropertiesUtility
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
package com.madhub.demo.utils;

import java.io.FileInputStream;
import java.util.Properties;

import com.madhub.demo.constants.Constants;

public class PropertiesUtility
{
    /**
     * @author madhub
     * This will read the properties file and retrive the requested property value
     * @param key
     * @return
     */
    public static String readProperty(String key)
    {
        Properties prop = new Properties();
        try
        {
            FileInputStream fis = new FileInputStream(
                Constants.ROOT_DIRECTORY + "/" + Constants.ENV_PROPERTIES_FILE_LOCATION);
            prop.load(fis);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return prop.getProperty(key);

    }
}
