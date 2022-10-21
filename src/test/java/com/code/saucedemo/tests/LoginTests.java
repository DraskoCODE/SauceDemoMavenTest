package com.code.saucedemo.tests;

import com.code.saucedemo.models.User;
import com.code.saucedemo.pages.LoginPage;
import com.code.saucedemo.pages.ProductsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void verifyLoginWithStandardUser() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        //ChromeDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        /*loginPage.setUserName("standard_userr");
        loginPage.setPassword("secret_sauce");
        loginPage.clickOnLogin();*/
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.isDisplayed(), true);


        productsPage.closePage();
    }

    @Test
    public void verifyLoginWithLockedUser() {

    }

}
