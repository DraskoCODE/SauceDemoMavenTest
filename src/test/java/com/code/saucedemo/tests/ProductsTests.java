package com.code.saucedemo.tests;

import com.code.saucedemo.models.Product;
import com.code.saucedemo.models.User;
import com.code.saucedemo.pages.LoginPage;
import com.code.saucedemo.pages.ProductsPage;
import com.code.saucedemo.provider.ProductsProvider;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductsTests extends BaseTest {

    @Test(dataProvider = "ProductsNameProvider", dataProviderClass = ProductsProvider.class)
    public void verifyAddToCart(String productName) {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        //ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        int currentProductNumInCart = productsPage.cartItemNo();
        productsPage.addItemToCartByName(productName);
        Assert.assertEquals(productsPage.cartItemNo(), currentProductNumInCart + 1, "Num in product is not as expected");
        //productsPage.addItemToCartByName("Sauce Labs Onesie");
        //productsPage.addItemToCartByName("Test.allTheThings() T-Shirt (Red)");

        //List<String> listOfProducts = new ArrayList<>();
        //listOfProducts.add("Sauce Labs Bolt T-Shirt");
        //listOfProducts.add("Sauce Labs Onesie");
        //productsPage.addItemToCartByName(listOfProducts);

        //productsPage.addAllItemsToCart();
    }

    @Test(dataProvider = "ProductsProvider", dataProviderClass = ProductsProvider.class)
    public void verifyAddProductToCart(Product product) {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        //ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        int currentProductNumInCart = productsPage.cartItemNo();
        productsPage.addItemToCartByName(product.getName());
        Assert.assertEquals(productsPage.cartItemNo(), currentProductNumInCart + 1, "Num in product is not as expected");

    }

}
