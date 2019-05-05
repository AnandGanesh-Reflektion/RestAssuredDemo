/*
 * Class: Resources
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
package com.madhub.demo.codingTest;

public class Resource
{
    /**
     * @author madhub
     * This method will contruct resource for the API request
     * @return
     */
    public static String readGetServiceInvalidResource()
    {
        String resource = "/invalidposts";
        return resource;
    }

    /**
     * @author madhub
     * This method will contruct resource for the API request
     *
     * @param input
     * @return
     */
    public static String readGetServiceResource(String input)
    {
        String resource;

        if (input == null)
        {
            resource = "/posts";
            //return resource;
        }
        else
        {
            resource = "/posts/" + input;
        }
        return resource;
    }

}
