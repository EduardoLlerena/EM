package com.em.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    Logger log;
    String testSuiteName;
    String testMethodName;

    @Override
    public void onTestStart(ITestResult result){
        this.testMethodName = result.getMethod().getMethodName();
        log.info("[Starting test method:"+ testMethodName+ "]");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("[Test " + testMethodName + " passed]");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("[Test " + testMethodName + " failed]");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("[Test " + testMethodName + " skipped]");

    }

    @Override
    public void onStart(ITestContext context) {
        this.testSuiteName = context.getCurrentXmlTest().getName();
        this.log = LogManager.getLogger(testSuiteName);
        log.info("[Started testing on suite " + testSuiteName + "]");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("[Finished testing on suite " + testSuiteName + "]");
    }
}
