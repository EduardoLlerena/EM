package com.em.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class BrowserDriverFactory {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;
    private Logger log;

    public BrowserDriverFactory(String browser, Logger log){
        this.browser = browser.toLowerCase();
        this.log = log;
    }

    public WebDriver createDriver(){
        log.info("Create driver: "+browser);

        switch (browser){
            case "chrome":
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                driver.set(new FirefoxDriver());
                break;
            default:
                log.info("Can't start: "+browser + ". Starting tests in Chrome");
                driver.set(new ChromeDriver());
                break;
        }
        return driver.get();
    }

    public WebDriver createChromeWithMobileEmulation(String deviceName) {
        log.info("Creating chrome driver for device: " + deviceName);

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        driver.set(new ChromeDriver(chromeOptions));
        return driver.get();
    }
}
