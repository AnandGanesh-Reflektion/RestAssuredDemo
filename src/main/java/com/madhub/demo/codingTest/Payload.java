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

import org.json.simple.JSONObject;

import com.madhub.demo.helpers.POSTPayloadBuilder;
import com.madhub.demo.helpers.PayloadBuilder;

public class Payload
{

    public static String postPayload(POSTPayloadBuilder payload)
    {
        JSONObject requestParam = new JSONObject();

        requestParam.put("title", payload.payload_title);
        requestParam.put("body", payload.payload_body);
        requestParam.put("userId", payload.payload_userId);
        return requestParam.toJSONString();

        /* String payload = "{" + "\"title\" : \"foo\" ," + "\"body\" : \"bar\" ," + "\"userId\" : 1" + "}";
        return payload;*/
    }

    public static String putPayload(PayloadBuilder payload)
    {
        /*String body = "{\r\n\"id\" : " + payload.payload_id + " ,\r\n\"title\" : \"" + payload.payload_title
                + "\" ,\r\n\"body\" : \"" + payload.payload_body + "\" ,\r\n\"userId\" : " + payload.payload_userId
                + "\r\n}";*/
        JSONObject requestParam = new JSONObject();
        requestParam.put("id", payload.payload_id);
        requestParam.put("title", payload.payload_title);
        requestParam.put("body", payload.payload_body);
        requestParam.put("userId", payload.payload_userId);

        return requestParam.toJSONString();
    }

}
