package com.code.saucedemo.tests;

import com.code.saucedemo.models.User;
import com.code.saucedemo.pages.LoginPage;
import com.code.saucedemo.pages.ProductsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductsTests {

    @Test
    public void verifyAddToCart() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        //productsPage.addItemToCartByName("Sauce Labs Bolt T-Shirt");
        //productsPage.addItemToCartByName("Sauce Labs Onesie");
        //productsPage.addItemToCartByName("Test.allTheThings() T-Shirt (Red)");

        //List<String> listOfProducts = new ArrayList<>();
        //listOfProducts.add("Sauce Labs Bolt T-Shirt");
        //listOfProducts.add("Sauce Labs Onesie");
        //productsPage.addItemToCartByName(listOfProducts);

        productsPage.addAllItemsToCart();
    }

}
