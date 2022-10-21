package com.code.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {

    private WebDriver driver;


    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public ProductsPage(WebDriver driver){
        this.driver = driver;
    }

    public void openPage() {
        this.driver.get("https://www.saucedemo.com/inventory.html");
        this.driver.manage().window().maximize();
    }

    public List<WebElement> getInventoryItems() {
        WebElement inventoryList = this.driver.findElement(By.xpath("//div[@class='inventory_list']"));
        return inventoryList.findElements(By.xpath(".//div[@class='inventory_item']"));
    }

    public boolean isDisplayed() {
        boolean toReturn = false;
        if(this.driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
            toReturn = true;
        }
        return toReturn;
    }

    public boolean isDisplayedByWebElement() {
        boolean toReturn = false;
        List<WebElement> inventoryContainerList = this.driver.findElements(By.xpath("//div[@id='inventory_container']"));
        if(inventoryContainerList.size() == 2) {
            toReturn = true;
        }
        return toReturn;
    }

    public void addItemToCartByName(String name) {
        List<WebElement> webElementList = this.getInventoryItems();

        int index = 0;
        for(int i = 0; i < webElementList.size(); i++) {
            WebElement webElementItemName = webElementList.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            String itemName = webElementItemName.getText();

            if(itemName.equals(name)) {
                index = i;
                break;
            }
        }
        WebElement btnAddToCart = webElementList.get(index).findElement(By.xpath(".//button"));
        btnAddToCart.click();
    }


    public void addItemToCartByName(List<String> listItemName) {
        List<WebElement> webElementList = this.getInventoryItems();

        for(int j = 0; j < listItemName.size(); j++) {

            int index = 0;
            for (int i = 0; i < webElementList.size(); i++) {
                WebElement webElementItemName = webElementList.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
                String itemName = webElementItemName.getText();

                if (itemName.equals(listItemName.get(j))) {
                    index = i;
                    break;
                }
            }
            WebElement btnAddToCart = webElementList.get(index).findElement(By.xpath(".//button"));
            btnAddToCart.click();
        }
    }

    public void addAllItemsToCart() {
        List<WebElement> webElementList = this.getInventoryItems();

        for(int i = 0; i < webElementList.size(); i++) {
            WebElement btnAddToCart = webElementList.get(i).findElement(By.xpath(".//button"));
            btnAddToCart.click();
        }
    }

    public int cartItemNo(){
        WebElement cartLink= this.driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        List<WebElement> spanList= cartLink.findElements(By.xpath(".//span"));
        int noOfItems;
        if (spanList.size()!=0){
            noOfItems=Integer.parseInt(spanList.get(0).getText());
        }
        else {
            noOfItems=0;
        }
        return noOfItems;
    }


    public void closePage() {
        this.driver.close();
        this.driver.quit();
    }

}
