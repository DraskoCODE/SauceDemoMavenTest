package com.code.saucedemo.assertions;

import com.code.saucedemo.models.Product;
import org.testng.Assert;

import java.util.List;

public class AssertProducts {

    public void assertListOfProducts(List<Product> actual, List<Product> expected) {
        Assert.assertEquals(actual.size(), expected.size(), "List size is not as expected");

        boolean isListContainsElement = true;
        for(int i = 0; i < actual.size(); i++) {
            boolean isElementExist = false;
            for(int j = 0; j < expected.size(); j++) {
                if(actual.get(i).getName().equals(expected.get(j).getName())) {
                    isElementExist = true;
                    break;
                }

            }
            if(!isElementExist) {
                isListContainsElement = false;
                break;
            }
        }

        Assert.assertEquals(isListContainsElement, true, "List is not equals");
    }


}
