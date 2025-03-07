package pages.otrim.pages;

import base.BasePage;
import constants.StrategyType;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import reports.ExtentLogger;

import java.util.List;

public class BulkTrimPage extends BasePage {

    private final By tab_BulkTrimURL = By.xpath("//ul[@class='menu-list']//div[text()='Bulk Trim']");
    private final By btn_DownloadTemplate = By.xpath("//button[normalize-space()='Download']");
    private final By btn_UploadFile = By.id("formFile");
    private final By lbl_BulkTrimPage = By.xpath("//h2[normalize-space()='Bulk Trim']");
    private final By btn_Submit = By.xpath("//button[normalize-space()='Submit']");
    //private final By icon_Delete = By.xpath("(//table[@class='table table-hover']//td[4]//button[@ngbtooltip='Delete'])[1]");
    private final By icon_Delete = By.xpath("(//table[@class='table table-hover']//td[4]//button[2])[1]");
    private final By lbl_ToasterMessage = By.xpath("//div[contains(@class,'toast-message ng-star-inserted')]");
    private final By list_BulkFile = By.xpath("//table[@class='table table-hover']//button[2]");
    private final By icon_View = By.xpath("(//table[@class='table table-hover']//td[4]//button[3])[1]");
    private final By icon_Download = By.xpath("(//table[@class='table table-hover']//td[4]//button[1])[1]");
    private final By windowCloseIcon = By.xpath("(//button[@aria-label='Close'])[1]");
    private final By btn_AreYouSure = By.xpath("//button[text()='Yes']");
    private final By rBtn_BulkCustom = By.xpath("//input[@id='BulkCustomTrim']");
    private final By Icon_QRCode = By.xpath("(//button[contains(@class,'shadow-none btn btn-link')])[1]");
    private final By icon_QRCodeVisible = By.xpath("//div[@class='content my-3']//ngx-qrcode-styling//canvas");
    private final By btn_QRCodeDownload = By.xpath("//div[@class='modal-content']//following::a");
    private final By icon_QRCodeCloseWindow = By.xpath("(//i[@class='far fa-times fs-4'])[1]");
    private final By lbl_AlreadyExisted = By.xpath("//div[@aria-label='FileName already exist, Please change the FileName']");
    private final By icon_Loading = By.xpath("(//div[contains(@class,'loading-text ng-tns')]/p[text()='Loading...'])[1]");

    //X-Path Functions
    public BulkTrimPage clickOnBulkTrimTab() {
        click(tab_BulkTrimURL, "Bulk Trim Tab");
        waitForPageLoad();
        waitForOverlaysToDisappear(icon_Loading);

        return this;
    }

    public BulkTrimPage clickOnUploadedBulkTrimFileDownload() {
        click(icon_Download, "Download Bulk Trim file In List");
        return this;
    }

    public BulkTrimPage clickOnBulkTrimViewWindowClose() {
        click(windowCloseIcon, "Bulk Trim View Window Close");
        return this;
    }

    public BulkTrimPage clickOnBulkTrimViewIcon() {
        waitStrategy(icon_View, StrategyType.ELEMENT_VISIBLE);
        click(icon_View, "Download Bulk Trim View In List");
        return this;
    }

    public BulkTrimPage clickOnQRCodeIcon() {
        try {

            click(Icon_QRCode, "QA Code Link");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public BulkTrimPage clickOnDownLoadQRCode() {
        try {
            Thread.sleep(1000);
            click(btn_QRCodeDownload, "Download QRCode");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public BulkTrimPage clickOnCloseQRCode() {
        click(icon_QRCodeCloseWindow, "Close QRCode Window");
        return this;
    }

    public BulkTrimPage clickOnBulkCustomRadioButton() {
        click(rBtn_BulkCustom, "Bulk Custom Radio");
        return this;
    }

    public BulkTrimPage clickOnDownLoad() {
        click(btn_DownloadTemplate, "Download");
        return this;
    }

    public BulkTrimPage clickOnSubmit() {
        click(btn_Submit, "Submit");
        return this;
    }

    public BulkTrimPage clickOnDeleteIcon() {
        click(icon_Delete, "Delete");
        return this;
    }

    public BulkTrimPage clickOnAreYouSureDelete() {
        click(btn_AreYouSure, "Yes Are you Sure");
        return this;
    }

    public BulkTrimPage toasterMessageInvisible() {
        waitForOverlaysToDisappear(lbl_ToasterMessage);
        return this;
    }


    //Functional Methods
    public boolean verifyDownloadTemplateToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "Sample CSV downloaded successfully", "Download Suse's Message",
                "Successfully download the template.",
                "Unsuccessfully download the template.");
    }

    public boolean verifyQRCodeDownloadToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "Qr Code Downloaded Successfully", "QR Code Download Suse's Message",
                "Successfully download the QR code from the trimmed URL file.",
                "Unsuccessfully download the QR code from the trimmed URL file.");
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


    public boolean verifyFileUploadToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "File Uploaded Successfully", "Upload Suse's Message",
                "Successfully upload the trim URL,s file.",
                "Unsuccessfully upload the trim URL,s  file.");
    }

    public boolean verifyFileDeletedToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "File Deleted Successfully", "Deleted Suse's Message",
                "Successfully deleted the trimmed URL file, and it is no longer available in the uploaded files.",
                "Unsuccessfully deleted the trimmed URL file, and it is available in the uploaded files.");
    }

    public boolean verifyFileDownloadToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "File Downloaded Successfully", "Downloaded Suse's Message",
                "Successfully download the trimmed URL file.",
                "Unsuccessfully download the trimmed URL file.");
    }

    public BulkTrimPage verifyBulkTrimDownload() {
        clickOnBulkTrimTab().clickOnDownLoad();
        return this;
    }

    public BulkTrimPage verifyCustomBulkTrimDownload() {
        clickOnBulkTrimTab().clickOnBulkCustomRadioButton().clickOnDownLoad();
        return this;
    }

    public boolean verifyLoadingOfBulkTrimPage() {
        return verifyGetElementText(lbl_BulkTrimPage, "Bulk Trim", "Bulk Trim",
                "Successfully verified the Bulk Trim page.","Unsuccessfully verified the Bulk Trim page.");
    }

    public BulkTrimPage deleteMultipleRecords() {
        boolean element = waitStrategyFor(icon_Delete);
            if (element == false) {
            } else {
                List<WebElement> listElements = DriverManager.getDriver().findElements(list_BulkFile);
                for (WebElement val : listElements) {
                    clickOnDeleteIcon();
                    clickOnAreYouSureDelete();
                    waitForOverlaysToDisappear(lbl_ToasterMessage);
                }
            }
        return this;
    }

    public synchronized BulkTrimPage verifyBulkTrimFileUpload() throws InterruptedException {
        clickOnBulkTrimTab();
        fileUploadByID(btn_UploadFile, "oTrim Bulk File.csv", "Bulk Trim File Upload");
        Thread.sleep(3000);
        boolean element = waitStrategyFor(lbl_AlreadyExisted);
        Thread.sleep(2000);
        //waitForOverlaysToDisappear(lbl_ToasterMessage);
        if (element == true) {
            //waitForOverlaysToDisappear(lbl_AlreadyExisted);
            deleteMultipleRecords();
            //clickOnDeleteIcon().clickOnAreYouSureDelete();
            waitForOverlaysToDisappear(lbl_ToasterMessage);
            Thread.sleep(1000);
            fileUploadByID(btn_UploadFile, "oTrim Bulk File.csv", "Bulk Trim File Upload");
            clickOnSubmit();
        }
        clickOnSubmit();
        Thread.sleep(1000);
        return this;
    }

    public BulkTrimPage verifyCustomBulkTrimFileUpload() throws InterruptedException {
        clickOnBulkTrimTab();
        fileUploadByID(btn_UploadFile, "customBulkTrim.csv", "Custom Bulk Trim File Upload");
        boolean element = waitStrategyFor(lbl_AlreadyExisted);
        if (element == true) {
                //waitForOverlaysToDisappear(lbl_AlreadyExisted);
                //clickOnDeleteIcon().clickOnAreYouSureDelete();
                deleteMultipleRecords();
                waitForOverlaysToDisappear(lbl_ToasterMessage);
                Thread.sleep(1000);
                fileUploadByID(btn_UploadFile, "customBulkTrim.csv", "Custom Bulk Trim File Upload");
                clickOnSubmit();
        }
        clickOnSubmit();
        return this;
    }


    public BulkTrimPage CreateTrimUrl(String fileName) {
        fileUploadByID(btn_UploadFile, fileName, "Bulk Trim File Upload");
        //remoteUpload(btn_UploadFile, fileName);

        return this;
    }

    public BulkTrimPage verifyUploadTrimUrl() {
        clickOnBulkTrimTab();
        CreateTrimUrl("oTrim Bulk Dl.csv");
        clickOnSubmit();
        return this;
    }

    public BulkTrimPage verifyDownloadedBulkTrimFile() {
        clickOnBulkTrimTab();
        boolean element = waitStrategyFor(icon_Download);
        if (element == true) {
            clickOnUploadedBulkTrimFileDownload();
        } else {
            try {
                CreateTrimUrl("oTrim Bulk Dn.csv");
                clickOnSubmit();
                waitForOverlaysToDisappear(lbl_ToasterMessage);
                Thread.sleep(2000);
                clickOnUploadedBulkTrimFileDownload();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }

    public BulkTrimPage verifyDownloadedBulkTrimQRCode() {
        clickOnBulkTrimTab();
        boolean element = waitStrategyFor(icon_View);
        if (element == true) {
            clickOnBulkTrimViewIcon()
                    .clickOnQRCodeIcon().clickOnDownLoadQRCode().clickOnBulkTrimViewWindowClose();
        } else {
            try {
                CreateTrimUrl("oTrim Bulk View.csv");
                clickOnSubmit();
                waitForOverlaysToDisappear(lbl_ToasterMessage);
                Thread.sleep(2000);
                clickOnBulkTrimViewIcon().clickOnQRCodeIcon().clickOnDownLoadQRCode().clickOnBulkTrimViewWindowClose();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }

    public BulkTrimPage verifyVisibleAndCloseBulkTrimQRCode() throws InterruptedException {
        clickOnBulkTrimTab();
        boolean element = waitStrategyFor(icon_View);
        if (element == true) {
            clickOnBulkTrimViewIcon()
                    .clickOnQRCodeIcon().clickOnCloseQRCode();
            Thread.sleep(2000);
            clickOnBulkTrimViewWindowClose();
            Thread.sleep(2000);
        } else {
            try {
                CreateTrimUrl("oTrim Bulk View.csv");
                clickOnSubmit();
                waitForOverlaysToDisappear(lbl_ToasterMessage);
                Thread.sleep(2000);
                clickOnBulkTrimViewIcon().clickOnQRCodeIcon().clickOnCloseQRCode();
                Thread.sleep(2000);
               clickOnBulkTrimViewWindowClose();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }


    public BulkTrimPage verifyDeleteBulkTrimFile() {
        clickOnDeleteIcon().clickOnAreYouSureDelete();
        return this;
    }
}