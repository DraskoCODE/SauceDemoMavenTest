package com.code.saucedemo.pages;

import com.code.saucedemo.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

   /* private WebDriver driver;


    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }*/

    public void openPage() {
        this.driver.get("https://www.saucedemo.com/");
        this.driver.manage().window().maximize();
    }

    //mapiranje(nalazenje) elementa
    public WebElement getInpUserName() {
        WebElement toReturn = this.driver.findElement(By.xpath("//input[@id='user-name']"));
        //return this.driver.findElement(By.xpath("//input[@data-test='username']"));
        return toReturn;
    }

    public WebElement getInpPassword() {
        return driver.findElement(By.xpath("//input[@data-test='password']"));
    }

    public WebElement getBtnLogin() {
        return driver.findElement(By.xpath("//input[@data-test='login-button']"));
    }

    //akcije ili interakcija sa WebElementim-a
    public void setUserName(String userName) {
        WebElement inpUserName = this.getInpUserName();
        inpUserName.click();
        inpUserName.sendKeys(userName);
    }

    public void setPassword(String password) {
        this.getInpPassword().sendKeys(password);
    }

    public void clickOnLogin() {
        this.getBtnLogin().click();
    }

    public void login(String userName, String password) {
        this.setUserName(userName);
        this.setPassword(password);
        this.closePage();
    }

    public void login(User user) {
        this.setUserName(user.getUserName());
        this.setPassword(user.getPassword());
        this.clickOnLogin();
    }

    public void closePage() {
        this.driver.close();
        this.driver.quit();
    }

}
