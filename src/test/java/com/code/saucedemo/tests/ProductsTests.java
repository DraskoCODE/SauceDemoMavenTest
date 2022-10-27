package com.code.saucedemo.tests;

import com.code.saucedemo.assertions.AssertProducts;
import com.code.saucedemo.models.Product;
import com.code.saucedemo.models.User;
import com.code.saucedemo.pages.CartPage;
import com.code.saucedemo.pages.LoginPage;
import com.code.saucedemo.pages.ProductsPage;
import com.code.saucedemo.provider.ProductsProvider;
import com.code.saucedemo.provider.SortProvider;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.List;

public class ProductsTests extends BaseTest {

    @Test(dataProvider = "ProductsNameProvider", dataProviderClass = ProductsProvider.class)
    public void verifyAddToCart(String nameOfProduct) {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        //ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        int currentProductNumInCart = productsPage.cartItemNo();
        productsPage.addItemToCartByName(nameOfProduct);
        Assert.assertEquals(productsPage.cartItemNo(), currentProductNumInCart + 1, "Num in product is not as expected");
        //productsPage.addItemToCartByName("Sauce Labs Onesie");
        //productsPage.addItemToCartByName("Test.allTheThings() T-Shirt (Red)");

        //List<String> listOfProducts = new ArrayList<>();
        //listOfProducts.add("Sauce Labs Bolt T-Shirt");
        //listOfProducts.add("Sauce Labs Onesie");
        //productsPage.addItemToCartByName(listOfProducts);

        //productsPage.addAllItemsToCart();
    }

    @Test()
    public void verifyAddProductToCart() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\comp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        //ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        int currentProductNumInCart = productsPage.cartItemNo();
        productsPage.addItemToCartByName("");
        Assert.assertEquals(productsPage.cartItemNo(), currentProductNumInCart + 1, "Num in product is not as expected");

    }

    @Test(dataProvider = "ProductsNameProviderOnCartPage", dataProviderClass = ProductsProvider.class)
    public void verifyAddProductToCartOnCartPage(String productName) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.clickOnCart();
        CartPage cartPage = new CartPage(driver);
        List<Product> productListExpected = cartPage.getListProducts();
        cartPage.continueShopping();

        productsPage.addItemToCartByName(productName);
        productsPage.clickOnCart();
        List<Product> productListActual = cartPage.getListProducts();

        productListExpected.add(new Product(productName, 23.2));

        AssertProducts assertProducts = new AssertProducts();
        assertProducts.assertListOfProducts(productListActual, productListExpected);

    }

    @Test
    public void verifyAddCheapestProduct() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        //productsPage.sortBy("Price (low to high)");
        productsPage.selectSortBy("Price (low to high)");
        productsPage.selectSortBy("Price (high to low)");
        productsPage.selectSortBy("Name (A to Z)");
        productsPage.selectSortBy("Name (Z to A)");
        //productsPage.selectCheapestItem();
        System.out.println("test");
    }

    @Test(dataProvider = "ProductsNameProvider", dataProviderClass = ProductsProvider.class)
    public void verifyRemoveItem(String productName) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        int cartItemNumber = productsPage.cartItemNo();
        productsPage.addItemToCartByName(productName);
        productsPage.removeItem(productName);

        Assert.assertEquals(productsPage.cartItemNo(), cartItemNumber, "Cart number is not as expected");
    }

    @Test(dataProvider = "ProductsListNameProvider", dataProviderClass = ProductsProvider.class)
    public void verifyRemoveItems(String productFirstName, String productSecondName, String productThirdName) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        int cartItemNumber = productsPage.cartItemNo();
        productsPage.addItemToCartByName(productFirstName);
        productsPage.addItemToCartByName(productSecondName);
        productsPage.addItemToCartByName(productThirdName);

        productsPage.removeItem(productFirstName);
        Assert.assertEquals(productsPage.cartItemNo(), cartItemNumber + 2, "Cart number is not as expected");
    }

    @Test
    public void verifySortProductByPriceLowToHigh() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        //productsPage.selectSortBy("Price (low to high)");
        productsPage.selectSortBy("Price (high to low)");

        List<Product> productListActual = productsPage.getListProducts();

        AssertProducts assertProducts = new AssertProducts();
        assertProducts.assertListProductSortByPriceFromHighToLow(productListActual);

    }

    @Test(dataProvider = "SortingPriceDataProvider", dataProviderClass = SortProvider.class)
    public void verifySortProductBy(String typeOfSorting) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectSortBy(typeOfSorting);

        List<Product> productListActual = productsPage.getListProducts();

        AssertProducts assertProducts = new AssertProducts();
        assertProducts.assertListProductSortByPrice(productListActual, typeOfSorting);

    }

    @Test(dataProvider = "ProductsNameProvider", dataProviderClass = ProductsProvider.class)
    public void verifyRemoveProduct(String productName) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemToCartByName(productName);
        int productInCartExpected = productsPage.cartItemNo(); //3
        productsPage.removeItem(productName); //-1
        int productInCartActual = productsPage.cartItemNo(); //2

        Assert.assertEquals(productInCartActual, productInCartExpected - 1, "Product number in cart is not as expected");
    }

    @Test(dataProvider = "ProductsNameProviderList", dataProviderClass = ProductsProvider.class)
    public void verifyRemoveProductList(String productNameFirst, String productNameSecond, String productNameThird) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(new User("standard_user", "secret_sauce"));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemToCartByName(productNameFirst);
        productsPage.addItemToCartByName(productNameSecond);
        productsPage.addItemToCartByName(productNameThird);
        int productInCartExpected = productsPage.cartItemNo(); //3
        productsPage.removeItem(productNameSecond);
        productsPage.removeItem(productNameThird);//-1
        int productInCartActual = productsPage.cartItemNo(); //2

        Assert.assertEquals(productInCartActual, productInCartExpected - 2, "Product number in cart is not as expected");
    }

}
