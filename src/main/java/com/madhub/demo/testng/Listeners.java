/*
 * Class: Listeners
 *
 * Created on Mar 15, 2019
 *
 * (c) Copyright Lam Research Corporation, unpublished work, created 2019
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Lam Research Corporation
 * 4000 N. First Street
 * San Jose, CA
 */
package com.madhub.demo.testng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener
{
    public static Logger log = LogManager.getLogger(Listeners.class.getName());

    public void onFinish(ITestContext arg0)
    {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext arg0)
    {
        // TODO Auto-generated method stub

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0)
    {
        // TODO Auto-generated method stub

    }

    public void onTestFailure(ITestResult result)
    {
        // TODO Auto-generated method stub
        Listeners.log.info("======================================================");
        Listeners.log.info("Failed Test Case: [" + result.getName() + "]");
        Listeners.log.info("======================================================");

    }

    public void onTestSkipped(ITestResult arg0)
    {
        // TODO Auto-generated method stub

    }

    public void onTestStart(ITestResult result)
    {
        // TODO Auto-generated methodstub
        Listeners.log.info("======================================================");
        Listeners.log.info("Begin Test Case: [" + result.getName() + "]");
        Listeners.log.info("======================================================");

    }

    public void onTestSuccess(ITestResult result)
    {
        // TODO Auto-generated method stub
        Listeners.log.info("======================================================");
        Listeners.log.info("Passed Test Case: [" + result.getName() + "]");
        Listeners.log.info("======================================================");
    }

}
