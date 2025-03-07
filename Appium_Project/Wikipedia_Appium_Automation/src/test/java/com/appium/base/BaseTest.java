package com.appium.base;

import driver.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

public class BaseTest {


    protected BaseTest() {
    }

   

    @BeforeMethod(groups = {"Smoke Test", "Regression Test"})
    @Parameters({"appName", "runMode"})
    protected void setUp(String appName, @Optional("local") String runMode) throws MalformedURLException {

        //String browser = System.getProperty("browser", "CHROME"); // To be used when running from command line
        System.setProperty("appName", appName);
        Driver.initializeDriver(appName, runMode);
        System.out.println("browser is" + appName);

    }


    @AfterMethod(groups = {"Smoke Test", "Regression Test"})
    protected void tearDown() {
        Driver.quitDriver();
    }


}