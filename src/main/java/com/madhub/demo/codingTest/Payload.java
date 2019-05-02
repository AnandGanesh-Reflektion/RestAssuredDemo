/*
 * Class: Payload
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

public class Payload
{

    public static String postPayload()
    {
        String payload = "{" + "\"title\" : \"foo\" ," + "\"body\" : \"bar\" ," + "\"userId\" : 1" + "}";
        return payload;
    }

    public static String putPayload()
    {
        String payload = "{\r\n\"id\" : 1 ,\r\n\"title\" : \"abc\" ,\r\n\"body\" : \"xyz\" ,\r\n\"userId\" : 1\r\n}";
        return payload;
    }
}
