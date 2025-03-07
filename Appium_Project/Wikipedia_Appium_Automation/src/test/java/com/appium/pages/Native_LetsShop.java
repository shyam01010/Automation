package com.appium.pages;

import com.appium.base.Native_BasePage;
import dataObjects.EmailConfig;
import dataObjects.LetsShopConfig;
import driver.DriverManager;
import org.openqa.selenium.By;
import utils.JacksonUtils;

public class Native_LetsShop extends Native_BasePage {



    private final By search_box = By.xpath("//android.widget.TextView[@text='Search Wikipedia']");
    private final By index_First = By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Selenium']");
    private final By lbl_History = By.xpath("//android.widget.TextView[@resource-id='History']");
    private final By btn_Skip = By.id("org.wikipedia:id/fragment_onboarding_skip_button");



    public Native_LetsShop  native_Wikipedia() throws InterruptedException {
        Thread.sleep(10000);
        click(btn_Skip,"click skip");

        enterText(search_box,"Selenium","search");

        click(index_First,"First option");

        scrollIntoElementView("History");
        DriverManager.getDriver().navigate().back();

        return this;
    }




}
