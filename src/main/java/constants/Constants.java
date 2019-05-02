/*
 * Class: Constants
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
package constants;

/**
 * @author MadhuB
 * This class helps in containing all the required constants.
 */
public class Constants
{
    public static final String ROOT_DIRECTORY = System.getProperty("user.dir");

    public static final String ENV_PROPERTIES_FILE_LOCATION = "/src/main/java/resources/env.properties";

    public static final boolean BOOLEAN_TRUE = true;

    public static final boolean BOOLEAN_FALSE = false;

    public static final int GET_STATUS_CODE = 200;

    public static final int POST_STATUS_CODE = 201;

    public static final int ERROR_STATUS_CODE = 404;

}