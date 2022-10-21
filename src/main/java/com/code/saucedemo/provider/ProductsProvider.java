package com.code.saucedemo.provider;

import com.code.saucedemo.models.Product;
import org.testng.annotations.DataProvider;

public class ProductsProvider {

    @DataProvider(name = "ProductsNameProvider")
    public static Object[][] getDataProductName(){
        return new Object[][] {
                { "Sauce Labs Backpack" },
                { "Sauce Labs Bike Light" },
                { "Sauce Labs Bolt T-Shirt" }
        };
    }

    @DataProvider(name = "ProductsProvider")
    public static Object[][] getDataProduct(){
        return new Object[][] {
                { new Product("Sauce Labs Bike Light", 152.0)}
        };
    }


}
