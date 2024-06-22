package com.em.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected Logger log;

    public BasePage(WebDriver driver, Logger log){
        this.driver = driver;
        this.log = log;
    }

    protected void openUrl(String url){
        driver.get(url);
    }

    protected WebElement find(By locator){
        return driver.findElement(locator);
    }

    protected List<WebElement> findAll(By locator){
        return  driver.findElements(locator);
    }

    protected void click(By locator){
        waitForVisibilityOf(locator);
        find(locator).click();
    }

    protected void type(String text, By locator){
        waitForVisibilityOf(locator);
        find(locator).sendKeys(text);
    }

    private void waitFor(ExpectedCondition<WebElement> condition, Duration timeOutInSeconds){
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds: Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

    protected void waitForVisibilityOf(By locator,Duration... timeOutInSeconds){
        int attempts =0;
        while (attempts <2){
            try{
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),timeOutInSeconds.length>0?timeOutInSeconds[0]:null);
                break;
            }catch(StaleElementReferenceException e){
                log.info(e.getStackTrace());
            }

            attempts++;
        }
    }

    protected void waitForInteractOf(By locator,Duration... timeOutInSeconds){
        int attempts =0;
        while (attempts <2){
            try{
                waitFor(ExpectedConditions.elementToBeClickable(locator),timeOutInSeconds.length>0?timeOutInSeconds[0]:null);
                break;
            }catch(StaleElementReferenceException e){
                log.info(e.getStackTrace());
            }

            attempts++;
        }
    }
}
