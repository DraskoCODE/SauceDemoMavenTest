package com.code.saucedemo.pages;

import com.code.saucedemo.models.Product;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.webaudio.WebAudio;

import java.util.ArrayList;
import java.util.List;

public class CartPage {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getCartList() {
        return this.driver.findElement(By.xpath("//div[@class='cart_list']"));
    }

    public List<Product> getListProducts() {
        List<Product> toReturn = new ArrayList<>();

        WebElement webElemCartList = this.getCartList();
        List<WebElement> webElementListCartItem = webElemCartList.findElements(By.xpath(".//div[@class='cart_item']"));

        for(int i = 0; i < webElementListCartItem.size(); i++) {
            WebElement webElemInventoryItemName = webElementListCartItem.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            String inventoryItemName = webElemInventoryItemName.getText();

            WebElement webElemInventoryItemPrice = webElementListCartItem.get(i).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            String inventoryItemPrice = webElemInventoryItemPrice.getText();

            Product product = new Product(inventoryItemName, Double.parseDouble(inventoryItemPrice.substring(1)));
            toReturn.add(product);
        }
        return toReturn;
    }

    public void continueShopping() {
        this.driver.findElement(By.xpath("//button[@data-test='continue-shopping']")).click();
    }


}
