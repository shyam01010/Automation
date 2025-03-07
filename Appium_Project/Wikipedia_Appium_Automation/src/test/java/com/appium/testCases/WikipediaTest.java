package com.appium.testCases;

import com.appium.base.BaseTest;
import com.appium.pages.Native_LetsShop;
import org.testng.annotations.Test;

public class WikipediaTest extends BaseTest {



    Native_LetsShop letsShop;

    @Test(priority = 1, groups = {"Regression Test"}, description = "Validate the Lets Shop.")
    public void verify_Wikipedia() throws InterruptedException {

        letsShop = new Native_LetsShop();
        letsShop.native_Wikipedia();


    }


}
