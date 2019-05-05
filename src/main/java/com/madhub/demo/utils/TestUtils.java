/*
 * Class: TestUtils
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

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestUtils
{
    public static JsonPath rawToJson(Response res)
    {
        String response = res.asString();
        JsonPath js = new JsonPath(response);
        return js;
    }
}
