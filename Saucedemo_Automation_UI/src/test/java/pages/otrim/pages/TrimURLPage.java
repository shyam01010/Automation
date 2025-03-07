package pages.otrim.pages;

import base.BasePage;
import com.github.javafaker.Faker;
import constants.StrategyType;
import driver.DriverManager;
import listeners.MyLogger;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import reports.ExtentLogger;
import utils.ReadData;

import java.util.List;
import java.util.Random;

import static constants.StrategyType.ELEMENT_VISIBLE;


public final class TrimURLPage extends BasePage {
    public static String aliseName2;

    public static String aliseName1;

    public static String customAliseName1;
    public static String customAliseName2;
    public  static String urlValue;
    public static String pastTrimUrl;

    public static String pastBrowserURL;
    private final By tab_TrimURL = By.xpath("//ul[@class='menu-list']//div[text()='Trim URL']");
    private final By logo_OTrim = By.xpath("//img[@alt='o-trim-logo']/../../div[text()='O-Trim']");

    private final By lbl_TrimUrlPageTittle = By.xpath("//h2[normalize-space()='Trim URL']");
    private final By icon_Loading = By.xpath("(//div[contains(@class,'loading-text ng-tns')]/p[text()='Loading...'])[1]");
    private final By url_Loading = By.xpath("(//div[@class='lds-spinner'])[1]");


    //CustomTrimURLLink
    private final By btn_CustomTrimURL = By.xpath("//label[normalize-space()='Create Custom URL']");
    private final By txt_PastCustomUrl = By.xpath("//label[text()='Create Custom URL']/../../..//input[@name='customURL']");
    private final By txt_CustomName = By.xpath("//label[text()='Create Custom URL']/../../..//input[@id='CustomPart']");
    private final By txt_CustomAliasName = By.xpath("//label[text()='Create Custom URL']/../../..//input[@name='addAlias']");
    private final By icon_CustomTrack = By.xpath("//div[text()=' https://www.w3schools.com/html/default.asp']//parent::td/../td[5]/div/button[7]");
    private final By icon_TrimUrlTrack = By.xpath("//div[text()=' https://www.w3schools.com/sql/default.asp']//parent::td/../td[5]/div/button[7]");
    private final By icon_EditCustomTrimUrl = By.xpath("(//div[text()=' https://www.w3schools.com/css/default.asp']//parent::td/../td[5]/div/button[2])[1]");
    private final By btn_CustomUrlSave = By.xpath("//label[text()='Create Custom URL']/../../../..//button[text()='Save']");


    //TrimURLLink
    private final By btn_TrimUrl = By.xpath("//label[normalize-space()='Trim URL']");
    private final By txt_PastTrimUrl = By.xpath("//label[text()='Trim URL']/../../..//input[@formcontrolname='inpurl']");
    private final By txt_TrimUrlAliseName = By.xpath("//label[text()='Trim URL']/../../..//input[@formcontrolname='aliasName']");
    private final By btn_TrimUrlSave = By.xpath("//label[text()='Trim URL']/../../../..//button[text()=' Save']");
    private final By btn_SubmitTrimURL = By.xpath("(//button[normalize-space()='Trim Link'])[1]");
    private final By icon_ForCorrectURL = By
            .xpath("//label[text()='Create Custom URL']/../../..//div[contains(@class,'trim-link-check')]");
    private final By btn_EnableOTPNo = By.xpath("//a[normalize-space()='No']");
    private final By btn_EnableOTPYes = By.xpath("//a[normalize-space()='Yes']");
    private final By lbl_ToasterMessage = By.xpath("(//div[contains(@class,'toast-message ng-star-inserted')])[1]");
    private final By lbl_LinkAlreadyShortened = By.xpath("//div[@class='modal-header']/..//a[normalize-space()='Ok']");
    private final By btn_CopyAlreadyShortenedURL = By.xpath("//div[@class='modal-header']/..//button[normalize-space()='Copy']");
    private final By btn_OKAlreadyShortenedURL = By.xpath("//div[@class='modal-header']/..//a[normalize-space()='Ok']");
    private final By icon_Delete = By.xpath("(//table[@class='table']//td[5]//button[@ngbtooltip='Delete'])[1]");
    private final By icon_QRCode = By.xpath("(//table[@class='table']//td[5]//button[@ngbtooltip='QR Code'])[1]");
    private final By icon_QRCodeVisible = By.xpath("//div[@class='content my-3']//ngx-qrcode-styling//canvas");
    private final By btn_QRCodeDownload = By.xpath("//div[@class='modal-content']//following::a");
    private final By icon_QRCodeCloseWindow = By.xpath("//div[@class='modal-content']//button[@type='button']");
    private final By btn_AreYouSureYes = By.xpath("//a[text()='Yes']");
    private final By icon_DeleteIconLists = By.xpath("//table[@class='table']//td[5]//button[@ngbtooltip='Delete']");
    private final By btn_okForSpamWindow = By.xpath("(//a[@class='btn btn-otrim-theme'])");
    private final By lbl_SpamWindow = By.xpath("//h4[normalize-space()='This is a SPAM Link']");
    private final By lbl_customSpamText = By.xpath("//label[text()='Custom Name']/..//small");
    private final By icon_URLDashboard = By.xpath("(//table[@class='table']//td[5]//button[@ngbtooltip='Dashboard'])[1]");
    private final By btn_Guide = By.xpath("//button[@ngbtooltip='User Guide']//span[text()='Guide']");
    private final By btn_Next = By.xpath("//a[text()='Next']");
    private final By btn_Done = By.xpath("//a[text()='Done']");
    private final By txt_SearchAlias = By.xpath("//label[text()='Create Custom URL']/../../..//..//input[@id='SearchAliasName']");
    private final By btn_ExportCSV = By.xpath("//section[@class='o-trim-url']//div//button[normalize-space()='Export CSV']");
    private final By icon_CopyTrimUrl = By.xpath("(//button[@ngbtooltip='Copy']//*[name()='svg'])[1]");

    private final By lbl_CopyTrimUrl = By.xpath("(//table[@class='table']//td[2]/div)[1]");
    private final By icon_UrlTrack = By.xpath("//div[text()=' https://www.w3schools.com/sql/default.asp']//parent::td/../td[5]/div/a[7]");
    private final By icon_EditTrimUrl = By.xpath("//div[text()=' https://www.w3schools.com/sql/default.asp']//parent::td/../td[5]/div/button[2]");
    private final By icon_RedirectingDashboard = By.xpath("(//div[text()=' https://www.onpassive.com/products/']//parent::td/../td[5]/div/button[3])[1]");
    private final By lbl_CountryName = By.xpath("(//div[text()='India'])[1]");

    private final By icon_CloseTrackWindow = By.xpath("//div[@class='modal-header']//following::button/i");
    private final By overlay_loader = By.xpath("//div[@class='pre-loader']");
    private final By lbl_UrlListVisible = By.xpath("(//tr[@class='ng-star-inserted']//td[3]/div/div)[1]");
    private final By lnk_StartDate = By.xpath("(//div[@class='calendar left ng-star-inserted']//tbody//tr[2]//td[3])[1]");
    private final By lnk_EndDate = By.xpath("(//div[@class='calendar left ng-star-inserted']//following::td[29])[1]");
    private final By btn_Apply = By.xpath("//button[normalize-space()='Apply']");
    private final By txt_ChooseDate = By.xpath("//section[@class='o-trim-url']//input[@placeholder='Choose Date']");
    private final By lst_calendarList = By.xpath("//section[@class='o-trim-url']//div[contains(@class,'md-drppicker drops-down-auto')]//button");
    private final By icon_calendarClear = By.xpath("//button[normalize-space()='Clear']//*[name()='svg']");
    private final By btn_CustomRage = By.xpath("//button[normalize-space()='Custom Range']");
    private final By lbl_TotalClicksCount = By.xpath("(//div[contains(@class,'status-cards')]//h2)[1]");
    private final By lbl_CopyUrl = By.xpath("(//input[@id='shortenURL'])[1]");

    private final By lbl_Guide = By.xpath("//h4[text()='Click on Trim Link to short URL']");
    Faker faker = new Faker();


    public TrimURLPage() {
    }

    //X-Path Functions
    public TrimURLPage openNewTab() {
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        waitForPageLoad();
        DriverManager.getDriver().get(urlValue);
        waitForPageLoad();
        switchToTab(2,"Switch to deleted trim Url tab",
                "The deleted, trimmed URL is not opened in a new window tab.",
                "The deleted, trimmed URL is opened in a new window tab.");
        return this;
    }



    public TrimURLPage clickOnStartDate() {
        click(lnk_StartDate, "Start Date");
        return this;
    }

    public TrimURLPage clickOnCustomDate() {
        click(btn_CustomRage, "Custom Date");
        return this;
    }

    public TrimURLPage clickOnEndDate() {
        click(lnk_EndDate, "End Date");
        return this;
    }

    public TrimURLPage clickOnApplyButton() {
        click(btn_Apply, "Apply Button");

        return this;
    }

    public TrimURLPage clickOnDatePicker() {
        waitStrategy(txt_ChooseDate, StrategyType.ELEMENT_VISIBLE);
        click(txt_ChooseDate, "Date Picker");
        return this;
    }

    public TrimURLPage clickOnClearCalender() {
        click(icon_calendarClear, "Clear Calender");
        return this;
    }


    public TrimURLPage enterCustomSpam() {
        enterText(txt_CustomName, "21footart", "Custom Name For Spam ");
        click(txt_CustomAliasName, "Click Custom Alias");
        return this;
    }

    public TrimURLPage enterSearchAlias() {
        enterText(txt_SearchAlias, aliseName1, "Search Alias");
        return this;
    }


    public TrimURLPage clickOnEditTrimUrl() {
        scrollToElement(icon_EditTrimUrl, "Scroll To Element");
        click(icon_EditTrimUrl, "Edit Trim Url Icon");
        return this;
    }

    public TrimURLPage clickOnQRCodeIcon() {
        click(icon_QRCode, "QA Code Link");
        return this;
    }

    public TrimURLPage clickOnDownLoadQRCode() {
        click(btn_QRCodeDownload, "Download QRCode");
        return this;
    }

    public TrimURLPage clickOnCloseQRCode() {
        click(icon_QRCodeCloseWindow, "Close QRCode Window");
        return this;
    }

    public TrimURLPage clickOnExportCSV() {
        waitForPageLoad();
        click(btn_ExportCSV, "Export CSV Icon");
        return this;
    }


    public TrimURLPage clickOnEditCustomTrimUrl() throws Throwable {
            scrollToElement(icon_EditCustomTrimUrl, "Scroll To Element");
            Thread.sleep(1000);
            click(icon_EditCustomTrimUrl, "Edit Custom Trim Url Icon");
            Thread.sleep(1000);
        return this;
    }





    public TrimURLPage clickOnSaveTrimUrl() {
        click(btn_TrimUrlSave, "Save Trim Url Button");
        return this;
    }

    public TrimURLPage clickOnSaveCustomUrl() {
        click(btn_CustomUrlSave, "Save Custom Trim Url Button");
        return this;
    }


    public TrimURLPage clickOnCustomTrack() {
        try {
            scrollToElement(icon_CustomTrack, "Scroll To element");
            waitStrategy(icon_CustomTrack, ELEMENT_VISIBLE);
            javScriptClick(icon_CustomTrack, "Custom Track Icon");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public TrimURLPage clickOnTrimTrack() {
        try {
            scrollToElement(icon_TrimUrlTrack, "Scroll To element");
            waitStrategy(icon_TrimUrlTrack, ELEMENT_VISIBLE);
            javScriptClick(icon_TrimUrlTrack, "Custom Track Icon");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return this;
    }


    public void clickOnRecord() {
        waitStrategy(icon_CustomTrack, ELEMENT_VISIBLE);
        mouseOverClick(icon_CustomTrack, "Custom Track Icon");
        waitForPageLoad();
        /*WebElement element = waitStrategy(icon_CustomTrack, ELEMENT_PRESENT);
        if (element.isDisplayed()) {

        } else {
            clickOnRecord();
        }*/
    }

    public TrimURLPage clickOnTrimUrlTrack() {
        scrollToElement(icon_UrlTrack, "Scroll To Element");
        waitStrategy(icon_UrlTrack, StrategyType.ELEMENT_VISIBLE);
        trimURLTrack();
        return this;
    }

    public void trimURLTrack() {

        WebElement element = waitStrategy(icon_UrlTrack, StrategyType.ELEMENT_PRESENT);
        if (element.isDisplayed()) {
            click(icon_UrlTrack, "TrimURL Track Icon");
            waitForPageLoad();
        } else {
            trimURLTrack();
        }

    }

    public TrimURLPage clickOnCloseTrackWindow() {
        click(icon_CloseTrackWindow, "Close Track Window");
        return this;
    }



    public TrimURLPage clickOnGuide() {
        click(btn_Guide, "Guide Button");
        return this;
    }

    public TrimURLPage clickOnCopyTrimUrlFromList() {
        waitForPageLoad();
        click(icon_CopyTrimUrl, "Copy Url");
        return this;
    }


    public TrimURLPage clickOnNext() {
        click(btn_Next, "Next Button");
        return this;
    }

    public TrimURLPage clickOnDone() {
        click(btn_Done, "Done Button");
        return this;
    }

    public TrimURLPage clickOnOKForSpamWindow() {
        click(btn_okForSpamWindow, "Ok For Spam Window");
        return this;
    }

    public TrimURLPage navigateUsingNextButton() {
        int count = 0;
        while (count == 0) {
            clickOnNext();
            count = DriverManager.getDriver().findElements(btn_Done).size();
        }
        clickOnDone();
        return this;
    }

    public TrimURLPage clickOnDashboardIconFormURLPage() {
        click(icon_URLDashboard, "Click on Dashboard Icon From Url Page");
        return this;
    }

    public TrimURLPage enterCustomTrimURL(String URL) {
        enterText(txt_PastCustomUrl, URL, "URL");
        return this;
    }

    public TrimURLPage clickOnTrimURLTab() {
        click(tab_TrimURL, "Trim URL");
        waitForOverlaysToDisappear(icon_Loading);
        return this;
    }

    public TrimURLPage enterCustomName() {
        String customName = faker.name().lastName().concat("user");
        click(txt_CustomName, "Click custom");
        waitForOverlaysToDisappear(url_Loading);
        waitStrategy(icon_ForCorrectURL, StrategyType.ELEMENT_VISIBLE);
        waitForPageLoad();
        enterText(txt_CustomName, customName, "Custom Name");
        return this;
    }

    public TrimURLPage enterCustomAddAlias() {

          customAliseName1 = faker.name().firstName().concat("custom");
        enterText(txt_CustomAliasName, customAliseName1, "ADD Custom Alias");


        return this;
    }

    public TrimURLPage updateCustomAddAlias() {
         customAliseName2 = faker.name().firstName().concat("custom");
        enterText(txt_CustomAliasName, customAliseName2, "ADD Custom Alias");
        return this;
    }

    public TrimURLPage visibleForCustomURLLoaded() {
        waitStrategy(icon_ForCorrectURL, StrategyType.ELEMENT_VISIBLE);
        return this;
    }

    public TrimURLPage clickOnSubmitCustomTrimURL() {
        click(btn_SubmitTrimURL, "Submit TrimURL ");
        return this;
    }

    public TrimURLPage clickOnDeleteIcon() {
        click(icon_Delete, "Delete");
        return this;
    }

    public TrimURLPage clickOnAreYouSureYes() {
        click(btn_AreYouSureYes, "Yes Are you Sure");
        return this;
    }

    public TrimURLPage toasterMessageInvisible() {
        waitForOverlaysToDisappear(lbl_ToasterMessage);
        return this;
    }

    public TrimURLPage deleteMultipleRecords() {
        WebElement element = waitStrategy(icon_Delete, ELEMENT_VISIBLE);
        try {
            if (element.isDisplayed()) {
                List<WebElement> listElements = DriverManager.getDriver().findElements(icon_DeleteIconLists);
                for (WebElement val : listElements) {
                    clickOnDeleteIcon();
                    clickOnAreYouSureYes();
                    waitForOverlaysToDisappear(lbl_ToasterMessage);
                    Thread.sleep(3000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }


    public TrimURLPage clickOnTrimURLButton() {
        click(btn_TrimUrl, "Trim URL Button");
        //waitForPageLoad();
        waitForOverlaysToDisappear(icon_Loading);
        return this;
    }

    public TrimURLPage enterTrimURL(String URL) {
        waitForPageLoad();
        enterText(txt_PastTrimUrl, URL, "URL");
        return this;
    }

    public TrimURLPage enterTrimURL() {
        String urlAppend = faker.name().firstName();
        ReadData.getOTrimData();
        String url = ReadData.trimUrl;
       // String trimUrl = url + urlAppend;
        waitForPageLoad();
        enterText(txt_PastTrimUrl, url, " Trim URL");
        return this;
    }

    public TrimURLPage enterAppendTrimURL() {
        String urlAppend = faker.name().firstName();
        ReadData.getOTrimData();
        String url = ReadData.appendUrl;
        String character =  RandomStringUtils.randomAlphabetic(3);
        String trimUrl = url + urlAppend + character ;
        waitForPageLoad();
        enterText(txt_PastTrimUrl, trimUrl, " Trim URL");
        return this;
    }

    public TrimURLPage enterBrokenTrimURL() {
        String urlAppend = faker.name().firstName();
        ReadData.getOTrimData();
        String url = ReadData.brokenUrl;
        String trimUrl = url + urlAppend;
        waitForPageLoad();
        enterText(txt_PastTrimUrl, trimUrl, " Broken Trim URL");
        return this;
    }

    public TrimURLPage enterAddAlias() {
        aliseName1 = faker.name().firstName();
        click(txt_TrimUrlAliseName, "Click Add Alias");
        waitForOverlaysToDisappear(url_Loading);
        waitStrategy(icon_ForCorrectURL, StrategyType.ELEMENT_VISIBLE);
        waitForPageLoad();
        enterText(txt_TrimUrlAliseName, aliseName1, "ADD Alias");
        return this;
    }

    public TrimURLPage enterEditAddAlias() {
        aliseName2 = faker.name().firstName();
        enterText(txt_TrimUrlAliseName, aliseName2, "ADD Alias");
        return this;
    }


    public TrimURLPage visibleForURLLoaded() {
        click(txt_TrimUrlAliseName, "Click Add Alias");
        waitStrategy(icon_ForCorrectURL, StrategyType.ELEMENT_VISIBLE);
        return this;
    }

    public TrimURLPage clickOnSubmitTrimURLButton() {
        click(btn_SubmitTrimURL, "Submit Trim URL");
        return this;
    }

    public TrimURLPage clickOnEnableOTPNoButton() {
        click(btn_EnableOTPNo, "Enable OTP No");
        return this;
    }

    public TrimURLPage clickOnCustomEnableOTPNoButton() {
        click(btn_EnableOTPNo, "Enable OTP No");
        return this;
    }

    public TrimURLPage clickOnEnableOTPYesButton() {
        click(btn_EnableOTPYes, "Enable OTP Yes");
        return this;
    }

    public TrimURLPage clickOnCopyAlreadyShortenedURL() {
        click(btn_CopyAlreadyShortenedURL, "Copy Existing URL");
        return this;
    }




    public TrimURLPage clickOnOKAlreadyShortenedURL() {
        click(btn_OKAlreadyShortenedURL, "OK");
        return this;
    }


    //Functional Methods
    public TrimURLPage verifyCalenderCustomDate() {
        clickOnDatePicker().clickOnCustomDate();
        clickOnStartDate().clickOnEndDate().clickOnApplyButton();
        return this;
    }


    static String hiddenValue;
    static String dateFilter;
    public TrimURLPage verifyCalenderFilter() {
        getWebElementsInList(lst_calendarList);
        clickOnDatePicker();
        waitForPageLoad();
        for (int j = 1; j < listElements.size()-1; j++) {
            dateFilter= String.valueOf(listElements.get(j).getText());
            listElements.get(j).click();
            WebElement element = DriverManager.getDriver().findElement(txt_ChooseDate);
            hiddenValue = (String) ((JavascriptExecutor)  DriverManager.getDriver()).executeScript("return arguments[0].value;", element);
            MyLogger.info("Successfully verified the "+dateFilter+" ========>"+ hiddenValue +" date functionality on the trim Url Page");
            clickOnDatePicker();
            waitForPageLoad();
        }
        return this;
    }

    public boolean verifyDateFilter() {
        WebElement element = DriverManager.getDriver().findElement(txt_ChooseDate);
        String actualValue = (String) ((JavascriptExecutor)  DriverManager.getDriver()).executeScript("return arguments[0].value;", element);
        // String actualValue = element.getText();
        if (actualValue.equalsIgnoreCase("Nov 01, 2023 - Nov 30, 2023")) {
            ExtentLogger.pass("Successfully verified the date filter functionality on the trim Url Page " +dateFilter+" ========>"+ actualValue);
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the date filter functionality on the trim Url Page " +dateFilter+" ========>"+ actualValue);
        }
        return false;
    }

    public boolean verifyCustomDateFilter() {
        WebElement element = DriverManager.getDriver().findElement(txt_ChooseDate);
        String actualValue = (String) ((JavascriptExecutor)  DriverManager.getDriver()).executeScript("return arguments[0].value;", element);
        // String actualValue = element.getText();
        System.out.println("value=================>"+actualValue);
        if (actualValue.equalsIgnoreCase("Nov 08, 2023 - Nov 27, 2023")) {
            ExtentLogger.pass("Successfully verified the custom date functionality on the trim Url Page Custom date ========>"+ actualValue);
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully verified the custom date functionality on the trim Url Page Custom date ========>"+ actualValue);
        }
        return false;
    }


    public boolean verifyCopyAndCreateTrimURLSuccessToasterMessage() {
        WebElement element = waitStrategy(lbl_ToasterMessage, StrategyType.ELEMENT_VISIBLE);
        boolean successToasterMessage = element.getText().equalsIgnoreCase("URL Trimmed Successfully");
        boolean copyToasterMessage = element.getText().equalsIgnoreCase("URL Copied Successfully");
        if (successToasterMessage == true) {
            waitForOverlaysToDisappear(lbl_ToasterMessage);
            ExtentLogger.pass("Successfully trimmed the URL, and it is available in the Trim URL list.");
            MyLogger.info(getInfo() + "Toaster Message is URL Trimmed Successfully");
            return true;
        } else if (copyToasterMessage == true) {
            waitForOverlaysToDisappear(lbl_ToasterMessage);
            ExtentLogger.pass("Successfully trimmed the URL, and it is available in the Trim URL list.");
            MyLogger.info(getInfo() + "Toaster Message is URL Copied Successfully");
            return true;
        } else {
            ExtentLogger.skip("Unsuccessfully trimmed the URL and it is not available in the Trim URL list.");
            MyLogger.info(getInfo() + "Toaster Message is not found " + element);

        }
        return false;
    }


    public boolean verifyCreateTrimURLSuccessToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "URL Trimmed Successfully", "URL Trimmed Successfully","Successfully trimmed the URL, and it is available in the Trim URL list.","Unsuccessfully trimmed the URL and it is not available in the Trim URL list.");

    }

    public boolean verifySearchAliasMap() {
        waitForPageLoad();
        return verifyGetElementText(lbl_UrlListVisible, aliseName1, "Search Alias",
                "Successfully get the record as per the given alise name ====> "+aliseName1,
                "Not get the record as per the given alise name ====> "+aliseName1);
    }

    public boolean verifyCustomSpamText() {
        waitForPageLoad();
        return verifyGetElementText(lbl_customSpamText, "Custom name recognized as SPAM", "Custom name recognized as SPAM",
                "Successfully verified the spam custom name the name is =====> 21footart",
                "Unsuccessfully verified the spam custom name the name is =====> 21footart");
    }

    public boolean verifyDeletedURLToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "Deleted Successfully", "Deleted Suse's Message",
                "Successfully deleted the trimmed URL and it is no longer available in the Trim URL list.",
                "Unsuccessfully deleted the trimmed URL, and it is available in the Trim URL list.");
    }

    public boolean verifyQRCodeDownloadSuccessToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "QR Code Downloaded Successfully", "Download QR Code Suse's Message",
                "Successfully downloading the QR code and opening it by scanning.",
                "Unsuccessfully downloading the QR code and did not opening it by scanning.");
    }


    public boolean verifySpamLinkWindow() {
        return verifyGetElementText(lbl_SpamWindow, "This is a SPAM Link", "Spam Window",
                "successfully verified the spam URL.",
                "unsuccessfully verified the spam URL.");
    }

    public boolean verifyDeletedTrimUrl() {

        return verifyGetCurrentPageTittle("Not Found","Get Current Page tittle",
                "Successfully verified the Deleted Trim Url",
                "Unsuccessfully verified the Deleted Trim Url");
    }

    public boolean verifyLoadingOfOTrimPage() {
        waitForPageLoad();
        return verifyGetElementText(logo_OTrim, "O-Trim", "OTrim Logo",
                "Successfully verified the Trim url page.","Unsuccessfully verified the Trim url page.");
    }



    public boolean verifyQRCodeWindowClose() throws InterruptedException {
        Thread.sleep(2000);
        boolean element = waitStrategyFor(icon_QRCodeCloseWindow);
        if (element == true) {
            ExtentLogger.skip("Unsuccessfully QR code window was  not closed.");
            return false;
        }
        else {
            ExtentLogger.pass("successfully QR code window was closed.");
        }
        return true;
    }


    public boolean verifyTrackWindowClose() throws InterruptedException {
        Thread.sleep(2000);
        boolean element = waitStrategyFor(icon_CloseTrackWindow);
        if (element == true) {
            ExtentLogger.skip("Unsuccessfully track window was not closed.");

            return false;
        }
        else {
            ExtentLogger.pass("successfully track window was closed.");
        }
        return true;
    }



    public boolean verifyUpdatedUrlSuccessToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "Edited Successfully", "Suse's Updated Message",
                "Successfully updated the alise name from ===>" + aliseName1 + "   to ===>"+ aliseName2 + " and the updated trimmed URL details are available in the Trim URL list.",
                "Unsuccessfully updated the trimmed URL, and it is not updated with the trimmed URL details.");
    }


    public boolean verifyUpdatedCustomUrlSuccessToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "Edited Successfully", "Suse's Updated Message",
                "Successfully updated the custom alise name from ===>" + customAliseName1 + "   to ===>"+ customAliseName2 +
                        " and the updated trimmed URL details are available in the Trim URL list.",
                "Unsuccessfully updated the trimmed URL, and it is not updated with the trimmed URL details.");
    }


    public boolean verifyFileDownloadSuccessToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "File Downloaded Successfully", "Downloaded Successfully",
                "successfully download the file.",
                "unsuccessfully download the file.");
    }

    public boolean verifyNavigateToDashboardFromTrimURLPage() {
        waitForPageLoad();
        ReadData.getOTrimData();
        String expectedURL = ReadData.dashboardPageNavigation;
        return verifyGetCurrentURL(expectedURL,"Successfully navigated to the dashboard page for specific URL was reached.",
                "Unsuccessfully navigated to the dashboard page for specific URL was reached.");
    }


    public String createTrimURLWithOutOTP() {
        ReadData.getOTrimData();
        String url = ReadData.trimUrlWithOutOtp;
        clickOnTrimURLButton().enterTrimURL(url).
                enterAddAlias().clickOnSubmitTrimURLButton().clickOnEnableOTPNoButton();
        boolean element = waitStrategyFor(lbl_LinkAlreadyShortened);
        if (element == true) {
            WebElement element1 = DriverManager.getDriver().findElement(lbl_CopyUrl);
            urlValue = element1.getAttribute("value");
            clickOnCopyAlreadyShortenedURL().clickOnOKAlreadyShortenedURL();
        }
        else{
            WebElement element1 = DriverManager.getDriver().findElement(lbl_CopyTrimUrl);
            urlValue = element1.getText();
            clickOnCopyTrimUrlFromList();
        }
        return urlValue;
    }


    public String createTrimURLWithOTP() {
        ReadData.getOTrimData();
        String url = ReadData.trimUrlWithOtp;
        clickOnTrimURLButton().enterTrimURL(url).
                enterAddAlias().clickOnSubmitTrimURLButton().clickOnEnableOTPYesButton();
        boolean element = waitStrategyFor(lbl_LinkAlreadyShortened);
        if (element == true) {
            WebElement element1 = DriverManager.getDriver().findElement(lbl_CopyUrl);
            urlValue = element1.getAttribute("value");
            clickOnCopyAlreadyShortenedURL().clickOnOKAlreadyShortenedURL();
        }
        else{
            WebElement element1 = DriverManager.getDriver().findElement(lbl_CopyTrimUrl);
            urlValue = element1.getText();
            clickOnCopyTrimUrlFromList();

        }
        return urlValue;
    }


    public String createCustomURLWithOutOTP() {
        ReadData.getOTrimData();
        String url = ReadData.customUrlWithOutOtp;
        enterCustomTrimURL(url).
                enterCustomName().enterCustomAddAlias().clickOnSubmitCustomTrimURL();
        clickOnCustomEnableOTPNoButton();
        boolean element = waitStrategyFor(lbl_LinkAlreadyShortened);
        if (element == true) {
            WebElement element1 = DriverManager.getDriver().findElement(lbl_CopyUrl);
            urlValue = element1.getAttribute("value");
            clickOnCopyAlreadyShortenedURL().clickOnOKAlreadyShortenedURL();
        }
        else{
            WebElement element1 = DriverManager.getDriver().findElement(lbl_CopyTrimUrl);
            urlValue = element1.getText();
            clickOnCopyTrimUrlFromList();

        }
        return urlValue;
    }


    public TrimURLPage clickDashboardIcon()
    {
      click(icon_RedirectingDashboard,"Dashboard Icon for Browser Count");
        return this;
    }


    public String createTrimUrl() {
        clickOnTrimURLButton().enterTrimURL();
        enterAddAlias().clickOnSubmitTrimURLButton().clickOnEnableOTPNoButton();
        boolean element = waitStrategyFor(lbl_LinkAlreadyShortened);
        if (element == true) {
            WebElement element1 = DriverManager.getDriver().findElement(lbl_CopyUrl);
            pastBrowserURL = element1.getAttribute("value");
            clickOnCopyAlreadyShortenedURL().clickOnOKAlreadyShortenedURL();
        }
        else{
            WebElement element1 = DriverManager.getDriver().findElement(lbl_CopyTrimUrl);
            pastBrowserURL = element1.getText();
            clickOnCopyTrimUrlFromList();
        }
        return pastBrowserURL;

    }

    public String createAppendTrimUrl() {
        clickOnTrimURLButton().enterAppendTrimURL();
        enterAddAlias().clickOnSubmitTrimURLButton().clickOnEnableOTPNoButton();
        boolean element = waitStrategyFor(lbl_LinkAlreadyShortened);
        if (element == true) {
            WebElement element1 = DriverManager.getDriver().findElement(lbl_CopyUrl);
            urlValue = element1.getAttribute("value");
            clickOnCopyAlreadyShortenedURL().clickOnOKAlreadyShortenedURL();
        }
        else{
            WebElement element1 = DriverManager.getDriver().findElement(lbl_CopyTrimUrl);
            urlValue = element1.getText();
            clickOnCopyTrimUrlFromList();
        }
        return urlValue;

    }


    public TrimURLPage createTrimUrlAppend() {
        clickOnTrimURLButton().enterAppendTrimURL();
        enterAddAlias().clickOnSubmitTrimURLButton().clickOnEnableOTPNoButton();
        return this;
    }

    public TrimURLPage VerifyCreatedDeletedURL() {
        createAppendTrimUrl();
        return this;
    }

    public TrimURLPage VerifyAfterDeletedURL() {
        openNewTab();
        return this;
    }



    public TrimURLPage createBrokenTrimUrl() {
       clickOnTrimURLButton().enterBrokenTrimURL();
        boolean element = waitStrategyFor(lbl_SpamWindow);
        if (element == true) {
            clickOnOKForSpamWindow().enterBrokenTrimURL().
                    enterAddAlias().clickOnSubmitTrimURLButton().clickOnEnableOTPNoButton();
        } else {
            enterAddAlias().clickOnSubmitTrimURLButton().clickOnEnableOTPNoButton();
        }
        return this;
    }


    public String createCustomURLWithOTP() {
        ReadData.getOTrimData();
        String url = ReadData.customUrlWithOtp;
        enterCustomTrimURL(url).
                enterCustomName().enterCustomAddAlias().clickOnSubmitCustomTrimURL();
        clickOnEnableOTPYesButton();
        boolean element = waitStrategyFor(lbl_LinkAlreadyShortened);
        if (element == true) {
            WebElement element1 = DriverManager.getDriver().findElement(lbl_CopyUrl);
            urlValue = element1.getAttribute("value");
            clickOnCopyAlreadyShortenedURL().clickOnOKAlreadyShortenedURL();
        }
        else{
            WebElement element1 = DriverManager.getDriver().findElement(lbl_CopyTrimUrl);
            urlValue = element1.getText();
            clickOnCopyTrimUrlFromList();
        }
        return urlValue;

    }

    public TrimURLPage verifyUsingGuideButton() {
        clickOnGuide().navigateUsingNextButton();
        return this;
    }


    public boolean verifyCountryTrack() {
        waitForPageLoad();
        return verifyGetElementText(lbl_CountryName, "India", "Track Country","successfully track the county name","Unsuccessfully track the county name");
    }

    public boolean verifyGuideTrack() {
        waitForPageLoad();
        return verifyGetElementText(lbl_TrimUrlPageTittle, "Trim URL", "Guide","Successfully verified guide","Unsuccessfully verified guide");
    }


    public TrimURLPage verifyQRCodeDownload() {
        clickOnQRCodeIcon().clickOnDownLoadQRCode();
        return this;
    }

    public TrimURLPage verifyQRCodeCloseWindow() {
        clickOnQRCodeIcon();
        clickOnCloseQRCode();
        return this;
    }

    public TrimURLPage verifyUpdateTrimUrlDetails() {

        clickOnEditTrimUrl().enterEditAddAlias().clickOnSaveTrimUrl();



        return this;
    }

    public TrimURLPage verifyCustomNameSpam() {
        enterCustomSpam();
        return this;
    }

    public TrimURLPage verifyUpdateCustomUrlDetails() throws Throwable{
        clickOnEditCustomTrimUrl();
        updateCustomAddAlias().clickOnSaveCustomUrl();
        return this;
    }

    public TrimURLPage verifyNavigateDashboard() {
        clickOnDashboardIconFormURLPage();
        return this;
    }



    public TrimURLPage verifyDeleteUrl() {
        clickOnDeleteIcon().clickOnAreYouSureYes();
        return this;
    }

    public TrimURLPage verifyTrimUrlSpamLink() {
        ReadData.getOTrimData();
        String url = ReadData.trimSpamUrl;
        clickOnTrimURLButton().enterTrimURL(url).clickOnSubmitTrimURLButton();
        return this;
    }

    public TrimURLPage verifyCustomUrlSpamLink() {
        ReadData.getOTrimData();
        String url = ReadData.customSpamUrl;
        enterCustomTrimURL(url).clickOnSubmitCustomTrimURL();
        return this;
    }


}