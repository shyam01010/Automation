package com.appium.pages;

import com.appium.base.Native_BasePage;
import driver.DriverManager;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Set;

public class Web_LetsShop extends Native_BasePage {




    public Web_LetsShop ecommerceLetsShop()  {
        pageLoad();
        pageLoad();
        DriverManager.getDriver().get("https://courses.rahulshettyacademy.com/courses/");
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverManager.getDriver().findElement(By.name("q")).sendKeys("shyam reddy");
        DriverManager.getDriver().findElement(By.name("q")).sendKeys(Keys.ENTER);
        DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.BACK));
        DriverManager.getDriver().context("NATIVE_APP");

        return this;
    }




}
