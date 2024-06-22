package com.em.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners({ com.em.base.TestListener.class })
public class BaseTest {
    protected WebDriver driver;
    protected Logger log;

    @Parameters({ "browser","deviceName" })
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, @Optional("chrome") String browser,  @Optional String deviceName, ITestContext ctx) {
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        BrowserDriverFactory factory = new BrowserDriverFactory(browser,log);

        if(deviceName != null){
            driver = factory.createChromeWithMobileEmulation(deviceName);
        } else{
            driver = factory.createDriver();
        }

        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Close driver");
        driver.quit();
    }
}
