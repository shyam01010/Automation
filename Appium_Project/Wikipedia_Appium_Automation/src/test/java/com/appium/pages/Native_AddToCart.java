package com.appium.pages;

import com.appium.base.Native_BasePage;
import dataObjects.AddToProductConfig;
import dataObjects.LetsShopConfig;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.JacksonUtils;

public class Native_AddToCart extends Native_BasePage {

    private final By lbl_productName = By.id("com.androidsample.generalstore:id/productName");
    private final By lbl_AddToCart = By.id("com.androidsample.generalstore:id/productAddCart");
    private final By btn_AddToCart = By.id("com.androidsample.generalstore:id/appbar_btn_cart");
    private final By title_ToolBar = By.id("com.androidsample.generalstore:id/toolbar_title");


    public Native_AddToCart addProductToCart() {
        AddToProductConfig addToCart = JacksonUtils.deserializeJson("AddToCart.json", AddToProductConfig.class);

        scrollIntoElementView(addToCart.getProductName());
        pageLoad();
        int product_Count = getElementSize(lbl_productName, "Product Name");
        for (int i = 0; i < product_Count; i++) {

            getWebElementsInList(lbl_productName);
            String product_Name = listElements.get(i).getText();
            if (product_Name.equalsIgnoreCase(addToCart.getProductName())) {
                getWebElementsInList(lbl_AddToCart);
                listElements.get(i).click();
            }
        }
        click(btn_AddToCart, "Add To Cart");
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(title_ToolBar));
        wait.until(ExpectedConditions.attributeContains(DriverManager.getDriver().findElement(title_ToolBar), "text", "Cart"));
        String add_Product = getElementText(lbl_productName, "product Names");
        Assert.assertEquals(add_Product, addToCart.getProductName());

        return this;
    }

}
