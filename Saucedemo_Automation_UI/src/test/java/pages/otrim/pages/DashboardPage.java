package pages.otrim.pages;

import base.BasePage;
import com.jcraft.jsch.JSchException;
import constants.StrategyType;
import driver.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.MyLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import reports.ExtentLogger;
import utils.ReadData;
import utils.SQLConnector;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pages.otrim.pages.TrimURLPage.pastBrowserURL;



public class DashboardPage extends BasePage {
    public static int beforeClicksCount;
    public static int afterClicksCount;
    public static int beforeUniqueClicksCount;
    public static int afterUniqueClicksCount;
    public static int beforeTotalLinksCount;
    public static int afterTotalLinksCount;
    public static int beforeBrokenLinksCount;
    public static double beforeBrowserPercentage;
    public static double afterBrowserPercentage;
    public static double afterDesktopDevicePercentage;

    public static String browserUrl;
    public static String expectedDate;
    public static String resultString;
    private final By icon_Loading = By.xpath("//div[contains(@class,'ngx-spinner-overlay')]//div[3]");
    private final By tab_Dashboard = By.xpath("//ul[@class='menu-list']//div[text()='Dashboard']");
    private final By icon_Delete = By.xpath("(//a[@ngbtooltip='Delete'])[1]");
    private final By overlay_loader = By.xpath("//div[@class='pre-loader']");
    private final By btn_YesAreYouSure = By.xpath("//a[text()='Yes']");
    private final By lbl_ToasterMessage = By.xpath("(//div[contains(@class,'toast-message ng-star-inserted')])[1]");
    private final By lbl_TotalClicksCount = By.xpath("(//div[contains(@class,'status-cards')]//h2)[1]");
    private final By lbl_TotalLinksCount = By.xpath("//div[contains(@class,'unique-links-header')]//div/following::h3[2]");
    private final By lbl_BrokenLinksCount = By.xpath("(//div[contains(@class,'status-cards')]//h2)[3]");
    private final By lbl_TotalUniqueClicksCount = By.xpath("//div[@class='content text-start ps-3']//following::li//h2");
    private final By lnk_StartDate = By.xpath("(//div[@class='calendar left ng-star-inserted']//tbody//tr[2]//td[3])[1]");
    private final By lnk_EndDate = By.xpath("//div[@class='calendar left ng-star-inserted']//following::td[29]");
    private final By btn_Apply = By.xpath("//button[normalize-space()='Apply']");
    private final By lbl_DashboardPageTittle = By.xpath("//h2[normalize-space()='Dashboard']");
    private final By txt_ChooseDate = By.xpath("//section[@class='otrim-analytics']//input[@placeholder='Choose Date']");
    private final By lst_calendarList = By.xpath("//section[@class='otrim-analytics']//div[contains(@class,'md-drppicker drops-down-auto')]//button");
    private final By icon_calendarClear = By.xpath("//button[normalize-space()='Clear']//*[name()='svg']");
    private final By lnk_CopyURLLink = By
            .xpath("(//ul[contains(@class,'lists')]//li[contains(@class,'card')]//span[contains(@class,'text')])[1]");
    private final By lnk_MarketPlace = By.xpath("//a[@id='marketplaceID']");
    private final By btn_CustomRage = By.xpath("(//button[normalize-space()='Custom Range'])[1]");
    private final By btn_Export = By.xpath("//button[@role='button']");
    private final By lnk_ExportPDF = By.xpath("//button[normalize-space()='Export PDF']");
    private final By btn_ExportCSV = By.xpath("//button[normalize-space()='Export CSV']");
    private final By btn_TrimLink = By.xpath("//a[normalize-space()='Trim Link']");
    private final By txt_Email = By.xpath("//form[@class='form-signin']//input[@name='email']");
    private final By btn_EmailOk = By.xpath("//form[@class='form-signin']//button[@type='submit']");
    private final By txt_OTP = By.xpath("//form[@class='form-signin']//input[@name='otpnum']");
    private final By btn_OTPOk = By.xpath("//form[@class='form-signin']//button[@onclick='submitOTP()']");
    private final By icon_UrlEdit = By.xpath("(//div[contains(@class,'unique-links-header')]//following::ul//div//a[@ngbtooltip='Edit'])[1]");
    private final By icon_UrlDelete = By.xpath("(//div[contains(@class,'unique-links-header')]//following::ul//div//a[@ngbtooltip='Delete'])[1]");
    private final By icon_QRCode = By.xpath("(//div[contains(@class,'unique-links-header')]//following::ul//div//a[@ngbtooltip='QR Code'])[1]");
    private final By btn_QRCodeDownload = By.xpath("//div[@class='modal-content']//following::a");
    private final By icon_QRCodeCloseWindow = By.xpath("//div[@class='modal-content']//button[@type='button']");
    private final By icon_QRCodeVisible = By.xpath("//div[@class='content my-3']//ngx-qrcode-styling//canvas");
    private final By lbl_ChromePercentage = By.xpath("//li/div[text()='Chrome']//parent::li//div[@class='percentage']");
    private final By lbl_FirefoxPercentage = By.xpath("//li/div[text()='Firefox']//parent::li//div[@class='percentage']");
    private final By lbl_MicrosoftPercentage = By.xpath("//li/div[text()='Microsoft']//parent::li//div[@class='percentage']");

    private final By lbl_DesktopPercentage = By.xpath("//li/div[text()='Desktop']//parent::li//div[@class='percentage']");
    private final By lbl_SafariPercentage = By.xpath("//li/div[text()='Safari']//parent::li//div[@class='percentage']");
    private final By lbl_ClicksCount = By.xpath("(//div[@class='row align-items-start']//p)[1]");
    private final By lbl_CurrentDate = By.xpath("(//div[@class='row align-items-start']//li[2])[1]");

    //input[@placeholder='Choose Date']
    public int afterBrokenLinksCount;


    public String dashboardValue;
    //String value = TrimURLPage.pastTrimUrl;

      String  value = TrimURLPage.urlValue;
    String value1 = TrimURLPage.pastTrimUrl;

    public DashboardPage() {
    }





    //X-Path Functions
    public DashboardPage clickOnCopyURL() {
        click(lnk_CopyURLLink, "Copy Trim URL Link");
        return this;
    }

    public String getExpectedDate() throws InterruptedException {
        java.util.Date date = new java.util.Date();
        //SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy");
        expectedDate = outputFormat.format(date);
        MyLogger.info(getInfo() + "Expected Date=========>" + expectedDate);
        return expectedDate;
    }


    public String getActualDateDate() {
        waitForPageLoad();
        String actualDate = getElementText(lbl_CurrentDate, "Current Date");
        String timePattern = "\\s\\d{2}:\\d{2}\\s[APMapm]{2}";
        Pattern pattern = Pattern.compile(timePattern);
        Matcher matcher = pattern.matcher(actualDate);
         resultString = matcher.replaceAll("");
        System.out.println(resultString);
        MyLogger.info(getInfo() + "Actual Date=========>" + resultString);
        return actualDate;
    }

    public boolean verifyCreateAndUpdateDate() throws InterruptedException {
        getExpectedDate();
        getActualDateDate();
        if (expectedDate.contains(resultString)) {
            ExtentLogger.pass("Successfully verified the create and updated date " +expectedDate+ "  " +resultString );
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the create and updated date " +expectedDate+ "  " +resultString);
        }
        return false;
    }


    public DashboardPage getCopyURL() {
        browserUrl = getElementText(lnk_CopyURLLink, "Copy Trim URL Link");
        return this;
    }

    public String getTrimmedCopyURL()
    {
            dashboardValue = getElementText(lnk_CopyURLLink, "Copy Trim URL Link");
            System.out.println("Assign Value=========>"+dashboardValue);
        return dashboardValue;
    }
       // dashboardValue = getElementText(lnk_CopyURLLink, "Copy Trim URL Link");
       /* if(value==dashboardValue)
        {

            value==dashboardValues;

        }
        dashboardValue = getElementText(lnk_CopyURLLink, "Copy Trim URL Link");*/


    public DashboardPage clickOnEditIconFromDashboard() {
        click(icon_UrlEdit, "Edit The Trim URL Link");
        waitForPageLoad();
        return this;
    }

    public DashboardPage clickOnQRCodeIcon() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(icon_QRCode, "QA Code Link");
        return this;
    }

    public DashboardPage clickOnDownLoadQRCode() {
        click(btn_QRCodeDownload, "Download QRCode");
        return this;
    }

    public DashboardPage clickOnCloseQRCode() {
        click(icon_QRCodeCloseWindow, "Close QRCode Window");
        return this;
    }

    public DashboardPage enterEmailAddress() {
        enterText(txt_Email, "muraliboyana42@gmail.com", "Enter Email Address");
        return this;
    }

    public DashboardPage clickOnEmailOk() {
        click(btn_EmailOk, "EMail Ok");
        return this;
    }

    public DashboardPage enterOTP() throws SQLException {
        try {
            waitStrategy(txt_OTP, StrategyType.ELEMENT_VISIBLE);
            String latestOTP = SQLConnector.trimOTP();
            Thread.sleep(5000);
            enterText(txt_OTP, latestOTP, "Enter OTP");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public DashboardPage clickOnOTPOk() {
        click(btn_OTPOk, "EMail Ok");
        return this;
    }


    public DashboardPage clickOnExportButton() {
        javScriptClick(btn_Export, "Export Button");
        return this;
    }

    public DashboardPage clickOnExportPDF() {
        mouseOverClick(lnk_ExportPDF, "Export PDF");
        fluentWaitForOverlaysToDisappear(icon_Loading);

        return this;
    }

    public DashboardPage clickOnExportCSV() {
        mouseOverClick(btn_ExportCSV, "Export CSV");
        return this;
    }


    public DashboardPage clickOnTrimLinkFromDashboard() {
        click(btn_TrimLink, "Trim Link");
        return this;
    }

    public DashboardPage clickOnStartDate() {
        click(lnk_StartDate, "Start Date");
        return this;
    }

    public DashboardPage clickOnCustomDate() {
        click(btn_CustomRage, "Custom Date");
        return this;
    }

    public DashboardPage clickOnEndDate() {
        click(lnk_EndDate, "End Date");
        return this;
    }

    public DashboardPage clickOnApplyButton() {
        click(btn_Apply, "Apply Button");
        return this;
    }

    public DashboardPage clickOnDatePicker() {
        click(txt_ChooseDate, "Date Picker");
        return this;
    }

    public DashboardPage clickOnClearCalender() {
        click(icon_calendarClear, "Clear Calender");
        return this;
    }


   /* public DashboardPage pastURLCopy() {
        //value = pastURL("pastureland");
      //  MyLogger.info("Successfully Copied Current URL ------->" + value);
        return this;
    }*/


    public  DashboardPage openNewTabWithOutOtp(String url) throws InterruptedException {
        waitForPageLoad();
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        DriverManager.getDriver().get(url);
        Thread.sleep(2000);
        switchToTab(2,"Switch to new tab",
                "Successfully opened URL In a new incognito window and closing tab",
                "Unsuccessfully opened URL In a new incognito window and closing tab");
        Thread.sleep(3000);
        DriverManager.getDriver().close();
        switchToTab(1,"switch to o-trim dashboard",
                "Successfully switch to the o-trim dashboard page",
                "Unsuccessfully switch to the o-trim dashboard page");
        return this;
    }



    public  DashboardPage openNewTabWithOtp(String url) throws SQLException, Throwable {
        waitForPageLoad();
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        DriverManager.getDriver().get(url);
        Thread.sleep(2000);
        switchToTab(2,"Switch to new tab",
                "Successfully opened URL In a new incognito window and closing tab",
                "Unsuccessfully opened URL In a new incognito window and closing tab");
        enterEmailAddress().clickOnEmailOk().enterOTP().clickOnOTPOk();
        Thread.sleep(3000);
        DriverManager.getDriver().close();
        switchToTab(1,"switch to o-trim dashboard",
                "Successfully switch to the o-trim dashboard page",
                "Unsuccessfully switch to the o-trim dashboard page");
        return this;
    }

    public  DashboardPage openIncognitoTab(String Url) throws AWTException {
        try {
            DriverManager.getDriver().switchTo().newWindow(WindowType.WINDOW);
            DriverManager.getDriver().get(Url);
            switchToTab(2,"Switch to new window","Successfully opened URL In a new incognito window and closing tab",
            "Unsuccessfully opened URL In a new incognito window and closing tab");
            DriverManager.getDriver().close();
            switchToTab(1,"switch to o-trim dashboard",
                    "Successfully switch to the o-trim dashboard page",
                    "Unsuccessfully switch to the o-trim dashboard page");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }






    //Total Clicks Count
    public DashboardPage getBeforeTotalClicksCount() {
        waitForPageLoad();
        String beforeCount = getElementText(lbl_TotalClicksCount, "Clicks Count");
        beforeClicksCount = Integer.parseInt(beforeCount);
        return this;
    }

    public DashboardPage getAfterTotalClicksCount() throws InterruptedException {
        DriverManager.getDriver().navigate().refresh();
        Thread.sleep(4000);
        waitStrategy(lbl_TotalClicksCount, StrategyType.ELEMENT_VISIBLE);
        String afterCount = getElementText(lbl_TotalClicksCount, "Clicks Count");
        afterClicksCount = Integer.parseInt(afterCount);
        return this;
    }

    public  synchronized boolean verifyTotalClicksCount()
    {
         waitForPageLoad();
        if (beforeClicksCount < afterClicksCount) {
            ExtentLogger.pass("Successfully verified the total clicks count =====> before total clicks count is : " +beforeClicksCount + " =====> after total clicks count is : " +afterClicksCount);
            MyLogger.info(getInfo() + "Successfully verified the total clicks count =====> before total clicks count is : " +beforeClicksCount + " =====> after total clicks count is : " +afterClicksCount);
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the total clicks count =====> before broken links count is : " +beforeClicksCount + " =====> after total clicks count is : " +afterClicksCount);
            MyLogger.info(getInfo() +"Unsuccessfully verified the total clicks count =====> before total clicks count is : " +beforeClicksCount + " =====> after total clicks count is : " +afterClicksCount);
        }
        return false;
    }




 //Total Broken Links Count
    public DashboardPage getBeforeBrokenClicksCount() {
        waitForPageLoad();
        String beforeCount = getElementText(lbl_BrokenLinksCount, "Broken Links Count");
        beforeBrokenLinksCount = Integer.parseInt(beforeCount);
        return this;
    }

    public DashboardPage getAfterBrokenLinksCount() throws InterruptedException {
        //DriverManager.getDriver().navigate().refresh();
        Thread.sleep(3000);
        waitStrategy(lbl_BrokenLinksCount, StrategyType.ELEMENT_VISIBLE);
        String afterCount = getElementText(lbl_BrokenLinksCount, "Broken Links Count");
        afterBrokenLinksCount = Integer.parseInt(afterCount);
        return this;
    }
    public   boolean verifyBrokenLinksCount()
    {

        waitForPageLoad();
        if (beforeBrokenLinksCount < afterBrokenLinksCount) {
            ExtentLogger.pass("Successfully verified the broken links count =====> before broken links count is : " +beforeBrokenLinksCount + " =====> after broken links count is : " +afterBrokenLinksCount);
            MyLogger.info(getInfo() + "Successfully verified the broken links count =====> before broken links count is : " +beforeBrokenLinksCount + " =====> after broken links count is : " +afterBrokenLinksCount);
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the broken links count =====> before broken links count is : " +beforeBrokenLinksCount + " =====> after broken links count is : " +afterBrokenLinksCount);
            MyLogger.info(getInfo() +"Unsuccessfully verified the broken links count =====> before broken links count is : " +beforeBrokenLinksCount + " =====> after broken links count is : " +afterBrokenLinksCount);
        }
        return false;
    }


    //Total Unique Clicks Count
    public DashboardPage getBeforeUniqueClicksCount() {
        waitForPageLoad();
        String beforeCount = getElementText(lbl_TotalUniqueClicksCount, "Unique Clicks Count");
        beforeUniqueClicksCount = Integer.parseInt(beforeCount);
        return this;
    }

    public DashboardPage getAfterTotalUniqueClicksCount() throws InterruptedException {
        DriverManager.getDriver().navigate().refresh();
        waitForOverlaysToDisappear(icon_Loading);
        Thread.sleep(3000);
        waitStrategy(lbl_TotalUniqueClicksCount, StrategyType.ELEMENT_VISIBLE);
        String afterCount = getElementText(lbl_TotalUniqueClicksCount, "Unique Clicks Count");
        afterUniqueClicksCount = Integer.parseInt(afterCount);
        return this;
    }
    public   boolean verifyTotalUniqueClicksCount()
    {
        waitForPageLoad();
        if (beforeUniqueClicksCount < afterUniqueClicksCount) {
            ExtentLogger.pass("Successfully verified the unique clicks count =====> before unique clicks count is : " +beforeUniqueClicksCount + " =====> after unique clicks count is : " +afterUniqueClicksCount);
            MyLogger.info(getInfo() + "Successfully verified the unique clicks count =====> before unique clicks count is : " +beforeUniqueClicksCount + " =====> after unique clicks count is : " +afterUniqueClicksCount);
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the unique clicks count =====> before unique clicks count is : " +beforeUniqueClicksCount + " =====> after unique clicks count is : " +afterUniqueClicksCount);
            MyLogger.info(getInfo() +"Unsuccessfully verified the unique clicks count =====> before unique clicks count is : " +beforeUniqueClicksCount + " =====> after unique clicks count is : " +afterUniqueClicksCount);
        }
        return false;
    }



   //Total Links Count
    public DashboardPage getBeforeTotalLinksCount() {
        waitForPageLoad();
        waitForOverlaysToDisappear(icon_Loading);
        String beforeCount = getElementText(lbl_TotalLinksCount, "Total Links Count");
        beforeTotalLinksCount = Integer.parseInt(beforeCount);
        return this;
    }

    public DashboardPage getAfterTotalLinksCount() throws InterruptedException {
        Thread.sleep(1000);
        waitStrategy(lbl_TotalLinksCount, StrategyType.ELEMENT_VISIBLE);
        String afterCount = getElementText(lbl_TotalLinksCount, "Total Links Count");afterTotalLinksCount = Integer.parseInt(afterCount);
        return this;
    }

    public   boolean verifyTotalLinksCount()
    {
        waitForPageLoad();
        if (beforeTotalLinksCount < afterTotalLinksCount) {
            ExtentLogger.pass("Successfully verified the total links count =====> before total links count is : " +beforeTotalLinksCount + " =====> after total links count is : " +afterTotalLinksCount);
            MyLogger.info(getInfo() + "Successfully verified the total links count =====> before total links count is : " +beforeTotalLinksCount + " =====> after total links count is : " +afterTotalLinksCount);
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the total links count =====> before total links count is : " +beforeTotalLinksCount + " =====> after total links count is : " +afterTotalLinksCount);
            MyLogger.info(getInfo() +"Unsuccessfully verified the total links count =====> before total links count is : " +beforeTotalLinksCount + " =====> after total links count is : " +afterTotalLinksCount);
        }
        return false;
    }


// Total Browser Percentage
    public DashboardPage getBeforePercentage(By Locator) throws InterruptedException {
        clickOnDashboard();
        DriverManager.getDriver().navigate().refresh();
        Thread.sleep(2000);
        scrollToElement(lbl_MicrosoftPercentage, "Scroll To Element");
        Thread.sleep(1000);
        String percentage = getElementText(Locator, "Before Percentage");
        String beforePercentage = percentage.replace("%", " ").trim();
        beforeBrowserPercentage = Double.parseDouble(beforePercentage);
        return this;
    }
    public DashboardPage getAfterPercentage(By Locator) throws InterruptedException {
        DriverManager.getDriver().navigate().refresh();
        Thread.sleep(3000);
        waitStrategy(lbl_MicrosoftPercentage, StrategyType.ELEMENT_VISIBLE);
        scrollToElement(lbl_MicrosoftPercentage, "Scroll To Element");
        waitStrategy(lbl_MicrosoftPercentage, StrategyType.ELEMENT_VISIBLE);
        String percentage = getElementText(Locator, "After Percentage");
        String afterPercentage = percentage.replace("%", " ").trim();
        afterBrowserPercentage = Double.parseDouble(afterPercentage);
        return this;
    }

    public   boolean verifyTotalBrowserPercentage() {
        waitForPageLoad();
        if (beforeBrowserPercentage < afterBrowserPercentage) {
            ExtentLogger.pass("Successfully verified the browser percentage =====> before browser percentage is : " + beforeBrowserPercentage + " =====> after browser percentage is : " + afterBrowserPercentage);
            MyLogger.info(getInfo() + "Successfully verified the browser percentage =====> before browser percentage is : " + beforeBrowserPercentage + " =====> after browser percentage is : " + afterBrowserPercentage);
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the browser percentage =====> before browser percentage is : " + beforeBrowserPercentage + " =====> after browser percentage is : " + afterBrowserPercentage);
            MyLogger.info(getInfo() + "Unsuccessfully verified the browser percentage =====> before browser percentage is : " + beforeBrowserPercentage + " =====> after browser percentage is : " + afterBrowserPercentage);
        }
        return false;
    }



//Total Desktop Device Percentage
    public DashboardPage getAfterDesktopDevicePercentage(By Locator) throws InterruptedException {
        DriverManager.getDriver().navigate().refresh();
        Thread.sleep(3000);
        waitStrategy(lbl_DesktopPercentage, StrategyType.ELEMENT_VISIBLE);
        scrollToElement(lbl_DesktopPercentage, "Scroll To Element");
        waitStrategy(lbl_DesktopPercentage, StrategyType.ELEMENT_VISIBLE);
        String percentage = getElementText(Locator, "After Desktop device Percentage");
        String afterPercentage = percentage.replace("%", " ").trim();
        afterDesktopDevicePercentage = Double.parseDouble(afterPercentage);
        return this;
    }

    public   boolean verifyTotalDesktopDevicePercentage() {
        waitForPageLoad();
        if (100 == afterDesktopDevicePercentage) {
            ExtentLogger.pass("Successfully verified the desktop device percentage =====> before desktop device percentage is :  0  =====> after desktop device percentage is : " + afterDesktopDevicePercentage);
            MyLogger.info(getInfo() + "Successfully verified the desktop device percentage =====> before desktop device percentage is : 0 =====> after desktop device percentage is : " + afterDesktopDevicePercentage);
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the desktop device percentage =====> before desktop device percentage is : 0 =====> after desktop device percentage is : " + afterDesktopDevicePercentage);
            MyLogger.info(getInfo() + "Unsuccessfully verified the desktop device percentage =====> before desktop device percentage is : 0 =====> after desktop device percentage is : " + afterDesktopDevicePercentage);
        }
        return false;
    }

    //Individual Chrome Percentage
    public   boolean verifyIndividualChromeBrowserPercentage() {
        waitForPageLoad();
        if (100 == afterBrowserPercentage) {
            ExtentLogger.pass("Successfully verified the chrome browser percentage =====> before chrome browser percentage is :  0  =====> after chrome browser percentage is : " + afterBrowserPercentage);
            MyLogger.info(getInfo() + "Successfully verified the chrome browser percentage =====> before chrome browser percentage is : 0 =====> after chrome browser percentage is : " + afterBrowserPercentage);
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the chrome browser percentage =====> before chrome browser percentage is : 0 =====> after chrome browser percentage is : " + afterBrowserPercentage);
            MyLogger.info(getInfo() + "Unsuccessfully verified the chrome browser percentage =====> chrome browser device percentage is : 0 =====> after chrome browser percentage is : " + afterBrowserPercentage);
        }
        return false;
    }


    //Individual Clicks Count
    public DashboardPage getBeforeIndividualClicksCount() {
        waitForPageLoad();
        String beforeClicks = getElementText(lbl_ClicksCount, "Individual Clicks Count");
        String[] splitPart = beforeClicks.split("(?<=\\D)(?=\\d)");
        String beforeCount = splitPart[1];
        beforeClicksCount = Integer.parseInt(beforeCount);
        return this;
    }

    public DashboardPage getAfterIndividualClicksCount() throws InterruptedException {
        DriverManager.getDriver().navigate().refresh();
        Thread.sleep(3000);
        waitStrategy(lbl_ClicksCount, StrategyType.ELEMENT_VISIBLE);
        String afterClicks = getElementText(lbl_ClicksCount, "Individual Clicks Count");
        String[] splitPart = afterClicks.split("(?<=\\D)(?=\\d)");
        String afterCount = splitPart[1];
        afterClicksCount = Integer.parseInt(afterCount);
        return this;
    }

    public  boolean verifyIndividualClicksCount()
    {
        waitForPageLoad();
        if (beforeClicksCount < afterClicksCount) {
            ExtentLogger.pass("Successfully verified the individual clicks count =====> before individual clicks count is : " +beforeClicksCount + " =====> after individual clicks count is : " +afterClicksCount);
            MyLogger.info(getInfo() + "Successfully verified the individual clicks count =====> before individual clicks count is : " +beforeClicksCount + " =====> after individual clicks count is : " +afterClicksCount);
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the individual clicks count =====> before individual clicks count is : " +beforeClicksCount + " =====> after individual clicks count is : " +afterClicksCount);
            MyLogger.info(getInfo() +"Unsuccessfully verified the individual clicks count =====> before individual clicks count is : " +beforeClicksCount + " =====> after individual clicks count is : " +afterClicksCount);
        }
        return false;
    }


    //Individual Broken Links Count
    public boolean verifyIndividualBrokenLinksCount() throws InterruptedException {

        waitForPageLoad();
        if (afterBrokenLinksCount <=1) {
            ExtentLogger.pass("Successfully verified the individual broken Links Count =====> before individual broken Links Count is :  0  =====> after individual broken Links Count is : " + afterBrokenLinksCount);
            MyLogger.info(getInfo() + "Successfully verified the individual broken Links Count =====> before individual broken Links Count is : 0 =====> after individual broken Links Count is : " + afterBrokenLinksCount);
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the individual broken Links Count =====> before individual broken Links Count is : 0 =====> after individual broken Links Count is : " + afterBrokenLinksCount);
            MyLogger.info(getInfo() + "Unsuccessfully verified the individual broken Links Count =====> individual broken Links Count is : 0 =====> after individual broken Links Count is : " + afterBrokenLinksCount);
        }
        return false;
    }


    //Individual Unique Clicks Count
    public DashboardPage getAfterIndividualUniqueClicksCount() throws InterruptedException {
        waitStrategy(lbl_TotalUniqueClicksCount, StrategyType.ELEMENT_VISIBLE);
        String afterCount = getElementText(lbl_TotalUniqueClicksCount, "Unique Clicks Count");
        afterUniqueClicksCount = Integer.parseInt(afterCount);
        ExtentLogger.pass("Successfully get the After Total Unique Clicks Count------->" + afterUniqueClicksCount);
        MyLogger.info("Successfully get the After Individual Unique Clicks Count ------->" + afterUniqueClicksCount);
        Assert.assertTrue(afterUniqueClicksCount <=1, "Individual Unique Clicks count is not working");
        return this;
    }



    public DashboardPage clickOnDashboard() {
        javScriptClick(tab_Dashboard, "Dashboard");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public DashboardPage clickOnDeleteIcon() {
        click(icon_Delete, "Delete URL");
        return this;
    }

    public DashboardPage clickOnDeletePOPUpYes() {
        click(btn_YesAreYouSure, "Are you Sure Yes");
        return this;
    }


    //Functional Methods
    public DashboardPage verifyDeleteURLUrl() {
        clickOnDeleteIcon().clickOnDeletePOPUpYes();
        return this;
    }

    public DashboardPage verifyAfterDesktopPercentage() throws InterruptedException {
        getAfterDesktopDevicePercentage(lbl_DesktopPercentage);
        return this;
    }

    public DashboardPage verifyAfterChromePercentage() throws InterruptedException {
        getAfterPercentage(lbl_ChromePercentage);
        return this;
    }

    public DashboardPage verifyNavigateTrimUrlPage() {
        clickOnDashboard().clickOnTrimLinkFromDashboard();
        return this;
    }

    public DashboardPage verifyQRCodeDownload() {
        clickOnQRCodeIcon().clickOnDownLoadQRCode();
        return this;
    }

    public DashboardPage verifyClicksCount() {
        waitForPageLoad();
        String ClicksCount = getElementText(lbl_TotalClicksCount, "Clicks Count");
        return this;
    }

    public boolean verifyDeletedURLToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "Deleted Successfully", "Deleted Suse's Message",
                "Successfully deleted the trimmed URL and it is no longer available in the dashboard page list.",
                "Unsuccessfully deleted the trimmed URL and it is available in the dashboard page list.");
    }

    public boolean verifyQRCodeDownloadSuccessToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "QR Code Downloaded Successfully", "Download QR Code Suse's Message","Successfully downloading the QR code and opening it by scanning.","Unsuccessfully downloading the QR code and did not opening it by scanning.");
    }

    public DashboardPage verifyToasterMessageInvisible() {
        waitForOverlaysToDisappear(lbl_ToasterMessage);
        return this;
    }

    public DashboardPage verifyIndividualShortenLinksCount() {
        clickOnDashboard().getBeforeIndividualClicksCount().getTrimmedCopyURL();
        clickOnCopyURL();
        return this;
    }

    public DashboardPage verifyIndividualShortenAfterLinksCount() throws InterruptedException {
        openNewTabWithOutOtp(dashboardValue).clickOnCopyURL().getAfterIndividualClicksCount();
        return this;
    }


    public boolean verifyCopyURLFromToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "Shorten link is copied successfully", "Link is Copied Successfully","Successfully copied the trimmed URL.","Unsuccessfully copied the trimmed URL");
    }

    public boolean verifyExportPDFDownloadToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "PDF file downloaded successfully", "PDF file downloaded successfully",
                "Successfully export the trimmed list in a PDF file from the dashboard page.",
                "Unsuccessfully export the trimmed list in a PDF file from the dashboard page.");
    }

    public boolean verifyExportCSVDownloadToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "CSV file downloaded successfully", "CSV file downloaded successfully",
                "Successfully export the trimmed list in a CSV file from the dashboard page.",
                "Unsuccessfully export the trimmed list in a CSV file from the dashboard page.");
    }


    public boolean verifyLoadingOfOTrimDashboardPage() {
        waitForPageLoad();
        return verifyGetElementText(lbl_DashboardPageTittle, "Dashboard", "Dashboard",
                "Successfully verified the dashboard page.","Unsuccessfully verified the Dashboard page.");

    }



    public boolean verifyNavigateToTrimURLPageFromDashboard() {
        waitForPageLoad();
        ReadData.getOTrimData();
        String expectedURL = ReadData.trimUrlPageNavigation;
        return verifyGetCurrentURL(expectedURL,
                "Successfully navigated to the trim URL page, then clicked on the trim link button on the dashboard page.",
                "Unsuccessfully navigated to the trim URL page, then clicked on the trim link button on the dashboard page");
    }

    public DashboardPage verifyExportPDFDownload() throws InterruptedException {
        clickOnDashboard();
        Thread.sleep(1000);
        clickOnExportButton();
        clickOnExportPDF();
        return this;
    }

    public DashboardPage verifyExportCSVDownload() throws InterruptedException {
        clickOnDashboard();
        Thread.sleep(1000);
        clickOnExportButton();
        clickOnExportCSV();
        return this;
    }

    public DashboardPage verifyQRCodeCloseWindow() {
        clickOnDashboard().clickOnQRCodeIcon().clickOnCloseQRCode();
        return this;
    }


    public DashboardPage openBrowser(String browserName, String URL) throws InterruptedException {
        WebDriver driver;
        if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.get(URL);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            Thread.sleep(2000);
            driver.close();
            Thread.sleep(1000);

        } else if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions Options = new ChromeOptions();
            Options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            Options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(Options);
            driver.get(URL);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            Thread.sleep(2000);
            driver.close();
            Thread.sleep(1000);


            //switchToTabWithURL("o-trim/dashboard", "Switch To O-Trim Dashboard Page");
        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions Options = new EdgeOptions();
            Options.addArguments("--remote-allow-origins=*");
            driver = new EdgeDriver(Options);
            driver.get(URL);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            Thread.sleep(2000);
            driver.close();
            Thread.sleep(1000);

            //switchToTabWithURL("o-trim/dashboard", "Switch To O-Trim Dashboard Page");
        }
        return this;
    }


    public DashboardPage verifyFirstTimeBrowser(String url) throws InterruptedException {
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        Thread.sleep(1000);
        DriverManager.getDriver().get(url);
        switchToTab(2,"Switch to new window","Successfully opened URL In a new incognito window and closing tab",
                "Unsuccessfully opened URL In a new incognito window and closing tab");
        DriverManager.getDriver().close();
        switchToTab(1,"switch to o-trim dashboard",
                "Successfully switch to the o-trim dashboard page",
                "Unsuccessfully switch to the o-trim dashboard page");
        //closeChildToTabWithURL("products");
        return this;
    }



    public synchronized DashboardPage verifyChromeBrowserPercentage() throws InterruptedException {
        clickOnDashboard();
        verifyFirstTimeBrowser(pastBrowserURL);
        getBeforePercentage(lbl_ChromePercentage);
        openBrowser("Chrome", pastBrowserURL);
        getAfterPercentage(lbl_ChromePercentage);
        return this;
    }

    public synchronized DashboardPage verifyAfterIndividualChromeBrowserPercentage() throws InterruptedException {
        getBeforePercentage(lbl_ChromePercentage);

        getAfterPercentage(lbl_ChromePercentage);
        return this;
    }


    public synchronized DashboardPage verifyFirefoxBrowserPercentage() throws InterruptedException {
        clickOnDashboard();
        getCopyURL();
        //pastBrowserURL();
        verifyFirstTimeBrowser(pastBrowserURL);
        getBeforePercentage(lbl_FirefoxPercentage);
        openBrowser("Firefox", pastBrowserURL);
        getAfterPercentage(lbl_FirefoxPercentage);
        return this;
    }

    public synchronized DashboardPage verifyEdgeBrowserPercentage() throws InterruptedException {
        clickOnDashboard();
        getCopyURL();
        verifyFirstTimeBrowser(pastBrowserURL);
        waitForPageLoad();
        getBeforePercentage(lbl_MicrosoftPercentage);
        openBrowser("Edge", pastBrowserURL);
        getAfterPercentage(lbl_MicrosoftPercentage);
        return this;
    }

    public DashboardPage verifyCalenderCustomDate() {
        clickOnDashboard();
        clickOnDatePicker().clickOnCustomDate();
        clickOnStartDate().clickOnEndDate().clickOnApplyButton();

        return this;
    }
    static String hiddenValue;
    static String dateFilter;
    public DashboardPage verifyCalenderFilter() {
        clickOnDashboard();
        getWebElementsInList(lst_calendarList);
        clickOnDatePicker();
        waitForPageLoad();
        for (int j = 1; j < listElements.size()-1; j++) {
             dateFilter= String.valueOf(listElements.get(j).getText());
            listElements.get(j).click();
             WebElement element = DriverManager.getDriver().findElement(txt_ChooseDate);
             hiddenValue = (String) ((JavascriptExecutor)  DriverManager.getDriver()).executeScript("return arguments[0].value;", element);
             MyLogger.info("Successfully verified the "+dateFilter+" ========>"+ hiddenValue +" date functionality on the dashboard");
            clickOnDatePicker();
            waitForPageLoad();
        }
        return this;
    }


    public boolean verifyDateFilter() {
        WebElement element = DriverManager.getDriver().findElement(txt_ChooseDate);
        String actualValue = (String) ((JavascriptExecutor)  DriverManager.getDriver()).executeScript("return arguments[0].value;", element);
       // String actualValue = element.getText();
        if (actualValue.equalsIgnoreCase("01 Nov, 2023 - 30 Nov, 2023")) {
            ExtentLogger.pass("Successfully verified the date filter functionality on the dashboard " +dateFilter+" ========>"+ actualValue);
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the date filter functionality on the dashboard " +dateFilter+" ========>"+ actualValue);
        }
        return false;
    }

    public boolean verifyCustomDateFilter() {
        WebElement element = DriverManager.getDriver().findElement(txt_ChooseDate);
        String actualValue = (String) ((JavascriptExecutor)  DriverManager.getDriver()).executeScript("return arguments[0].value;", element);
        // String actualValue = element.getText();
        System.out.println("value=================>"+actualValue);
        if (actualValue.equalsIgnoreCase("08 Nov, 2023 - 27 Nov, 2023")) {
            ExtentLogger.pass("Successfully verified the custom date functionality on the dashboard  Custom date ========>"+ actualValue);
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the custom date functionality on the dashboard  Custom date ========>"+ actualValue);
        }
        return false;
    }


    public synchronized DashboardPage VerifyTrimURlWithOutOTP() throws InterruptedException {
        clickOnDashboard();
        getBeforeTotalClicksCount();
        openNewTabWithOutOtp(value);
        getAfterTotalClicksCount();
        return this;
    }

    public synchronized DashboardPage VerifyPastUrlFromDashboard() throws InterruptedException {
        openNewTabWithOutOtp(dashboardValue);
        return this;
    }

    public   DashboardPage VerifyUniqueClicksWithOutOTP() throws InterruptedException, AWTException {
        clickOnDashboard();
        getBeforeUniqueClicksCount();
        openIncognitoTab(value);
        getAfterTotalUniqueClicksCount();
        return this;
    }

    public   DashboardPage verifyIndividualUniqueClicksCount() throws AWTException, InterruptedException {
               clickOnDashboard().clickOnCopyURL().verifyToasterMessageInvisible()
               .getBeforeUniqueClicksCount().getTrimmedCopyURL();
                openIncognitoTab(dashboardValue);
                DriverManager.getDriver().navigate().refresh();
                Thread.sleep(2000);
                clickOnCopyURL();
                verifyToasterMessageInvisible().getAfterIndividualUniqueClicksCount();
        return this;
    }

    public  DashboardPage VerifyTrimURlWithOTP() throws Throwable {
        clickOnDashboard();
        getBeforeTotalClicksCount();
        openNewTabWithOtp(value);
        getAfterTotalClicksCount();
        return this;
    }


    public  DashboardPage VerifyCustomTrimURlWithOutOTP() throws InterruptedException {
        clickOnDashboard();
        getBeforeTotalClicksCount();
        openNewTabWithOutOtp(value);
        getAfterTotalClicksCount();
        return this;
    }

    public synchronized DashboardPage VerifyCustomTrimURlWithOTP() throws Throwable {
        clickOnDashboard();
        getBeforeTotalClicksCount();
        openNewTabWithOtp(value);
        getAfterTotalClicksCount();
        return this;
    }

    public  DashboardPage verifyIndividualUniqueClicks() throws Throwable {
        getBeforeUniqueClicksCount();
        getCopyURL();
        verifyFirstTimeBrowser(browserUrl);
        waitForPageLoad();
        return this;
    }

    public  DashboardPage verifyIndividualChromePercentage() throws Throwable {
        getCopyURL();
        verifyFirstTimeBrowser(browserUrl);
        waitForPageLoad();
        return this;
    }

    public DashboardPage verifyDeviceDesktopPercentage() throws InterruptedException {
        getCopyURL();
        verifyFirstTimeBrowser(browserUrl);
        waitForPageLoad();
        return this;
    }


}
