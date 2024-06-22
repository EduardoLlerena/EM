package com.em.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private String pageUrl = "https://devexpedientes.ezsoftwaretools.com/Login";

    private By logoElementLocator = By.xpath("//*[@id='form1']/div[1]/img");

    private By userLabelElementLocator = By.xpath("//*[@id=\"form1\"]/div[2]/label[1]/b");
    private By passwordLabelElementLocator = By.xpath("//*[@id=\"form1\"]/div[2]/label[2]/b");

    private By userInputElementLocator = By.xpath("//*[@id=\"userName\"]");
    private By passwordInputElementLocator = By.xpath("//*[@id=\"psw\"]");

    private By loginButtonElementLocator = By.xpath("//*[@id=\"goLogin\"]");
    private By loginWithGoogleElementLocator = By.xpath("//*[@id=\"container\"]/div/div[2]/span[1]");

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openPage(){
        log.info("Opening page: "+pageUrl);
        openUrl(pageUrl);
        log.info("Page opened.");
    }

    public StartPage logIn(String username, String password){
        log.info("Executing successful login with username["+ username + "] and password["+ password + "]");
        type(username,userInputElementLocator);
        type(password,passwordInputElementLocator);
        click(loginButtonElementLocator);
        return new StartPage(driver,log);
    }

    public void logInUnsuccessful(String username, String password){
        log.info("Executing login with username["+ username + "] and password["+ password + "]");
        type(username,userInputElementLocator);
        type(password,passwordInputElementLocator);
        click(loginButtonElementLocator);
    }


}
