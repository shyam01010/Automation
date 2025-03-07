package pages.component.pages;

import base.BasePage;
import com.github.javafaker.Faker;
import constants.StrategyType;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import reports.ExtentLogger;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class HeaderSection extends BasePage {

    private final By btn_BecomeAnAffiliate = By.xpath("//header//button[text()=' Become An Affiliate ']");
    private final By icon_NON_PopUp_Close = By.xpath("//button[@class='modal-close']//i[@class='far fa-times']");
    private final By drp_Nationality = By.xpath("//select[@aria-label='Disabled select example']");
    private final By txt_EmailAddress = By.xpath("(//input[@placeholder='Enter email address'])[1]");
    private final By icon_send = By.xpath(" //div[@class='send-icon']//i[@class='far fa-paper-plane']");
    private final By lnk_SendInvite = By.xpath(" (//button[@id='sendInvite'])[1]");
    private final By lbl_ToasterMessage = By.xpath("//div[contains(@class,'toast-message ng-star-inserted')]");

    Faker faker = new Faker();


    //X-Path Functions

    public HeaderSection clickOnSendInvite() {
        waitStrategy(lnk_SendInvite, StrategyType.ELEMENT_VISIBLE);
        click(lnk_SendInvite, "Send Invite");
        return this;
    }

    public HeaderSection enterInviteMail() {
        String emailIds = faker.internet().emailAddress();
        waitStrategy(txt_EmailAddress, StrategyType.ELEMENT_VISIBLE);
        enterText(txt_EmailAddress, emailIds, "Email Address");
        return this;
    }

    public HeaderSection clickOnSendIcon() {
        waitStrategy(icon_send, StrategyType.ELEMENT_VISIBLE);
        click(icon_send, "Send Icon");
        waitForPageLoad();
        return this;
    }


}

