package com.appium.base;


import com.google.common.collect.ImmutableMap;
import driver.DriverManager;
import io.appium.java_client.AppiumBy;
import listeners.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reports.ExtentLogger;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.devtools.v85.systeminfo.SystemInfo.getInfo;

public class Native_BasePage {

    public static WebDriverWait wait;
    public static WebElement element;
    public static List<WebElement> listElements;
    public static int number;
    public static List<WebElement> elementText;


    public static WebDriverWait waitForElement(By locator) {

        try {

            wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (element.isDisplayed()) {
                ExtentLogger.pass("Element is displayed", true);
                MyLogger.info(getInfo() + "Page has displayed ");


            } else {
                ExtentLogger.fail("Element is not displayed", true);
                MyLogger.info(getInfo() + "Page has not displayed ");

            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return wait;
    }

    public void enterText(By locator, String value, String elementName) {

        wait = waitForElement(locator);
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (element.isEnabled()) {
                element.clear();
                element.sendKeys(value);
                ExtentLogger.pass("Successfully enter text on " + elementName, true);
                MyLogger.info(getInfo() + "Successfully enter text on " + elementName);

            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            ExtentLogger.fail("Element could not be found " + elementName, true);
            MyLogger.info(getInfo() + "Element is not enable for enter text " + elementName);

        }

    }

    public void hideKeyboard() {
        DriverManager.getDriver().hideKeyboard();
    }


    public void click(By locator, String elementName) {
        wait = waitForElement(locator);
        try {

            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (element.isEnabled()) {
                element.clear();
                element.click();
                ExtentLogger.pass("Successfully Clicked on " + elementName, true);
                MyLogger.info(getInfo() + "Successfully Clicked on " + elementName);

            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            ExtentLogger.fail("Element could not be found " + elementName, true);
            MyLogger.info(getInfo() + "Element could not be found " + elementName);

        }
    }


    public void scrollIntoElementView(String txt) {
        DriverManager.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + txt + "\"));"));

    }

    public boolean verifyGetElementText(By locator, String value, String elementName) {
        wait = waitForElement(locator);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String actualValue = element.getText();
        if (actualValue.equalsIgnoreCase(value)) {
            ExtentLogger.pass("Successfully get the Web element value ", true);
             MyLogger.info(getInfo() + "Element Text is " + elementName);
            return true;
        } else {
            ExtentLogger.fail("Element is not found ", true);
            MyLogger.info(getInfo() + "Element Text is not found " + elementName);
        }
        return false;
    }



    public static void getWebElementsInList(By locator) {
        if ((listElements != null) && (listElements.size() > 0)) {
            listElements.clear();
        }
        listElements = DriverManager.getDriver().findElements(locator);
    }

    public static int getElementSize(By locator, String elementName) {
        getWebElementsInList(locator);
        number  = listElements.size();

        return number;
    }



    public String getElementText(By locator, String elementName) {
        String displayText = null;
        waitForElement(locator);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        displayText = element.getText();
        ExtentLogger.pass("Successfully get the text for " + elementName, true);
        MyLogger.info(getInfo() + "Successfully get the text for " + elementName);
        return displayText;
    }

    public Double getFormattedAmount(String amount){
        Double price =   Double.parseDouble(amount.substring(1));
        return price;
    }

    public  void pageLoad()  {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void long_PressAuction(WebElement element){
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),"duration", 2000));
    }


    public  void swipe_Element(WebElement element, String direction){
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }



}