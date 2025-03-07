package pages.oes.user.pages;

import base.BasePage;
import com.github.javafaker.Faker;
import constants.StrategyType;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import pages.component.pages.HeaderSection;
import pages.component.pages.ProductMenu;


public class OESDashboardPage extends BasePage {

    private final By lnk_DashboardBreadCrumbLink = By.xpath("//ul[@class='breadcrumb-links']//a[text()='Dashboard']");
    private final By lbl_ToasterMessage = By.xpath("//div[contains(@class,'toast-message ng-star-inserted')]");
    private final By lbl_TenCredits = By.xpath("//h4[text()='My Wallet Balance']/..//h2");
    private final By lnk_ProfileUserID = By.xpath("(//strong[contains(text(),'OES-')])[1]");
    private final By lnk_ProfileName = By.xpath("//h6[@placement='bottom']");
    private final By lnk_Profile = By.xpath("//span[normalize-space()='Profile']");
    private final By icon_EditYourId = By.xpath("//input[@formcontrolname='affiliateName']/..//button");
    private final By icon_Save = By.xpath("//img[@alt='Save']");
    private final By lbl_NonDeclarationAgreementPop_Up = By.xpath("//div[@class='modal-header']//h4");
    private final By txt_NDAgreementSignaturePad = By.xpath("//div[@class='form-group signature-block']//canvas");
    private final By btn_NDAgreementAccept = By.xpath("//button[text()='Accept']");
    private final By checkB_NDAgreementCheckBox = By.xpath("//mat-checkbox[@formcontrolname='acceptTerms']");
    private final By checkB_NDAgreementConformCheckBox = By.xpath("//mat-checkbox[@formcontrolname='confirmation']");
    private final By lnk_BecomeAReseller = By.xpath("//button[text()=' Become a Reseller ']");
    private final By btn_TourButton = By.xpath("//section[@class='breadcrumb']//span[text()='Tour']");
    private final By btn_NextButton = By.xpath("//a[text()='Next']");
    private final By btn_DoneButton = By.xpath("//a[text()='Done']");
    private final By btn_GuideButton = By.xpath("//button[contains(@id,'updatesDropdown')]/span");
    private final By btn_VideoGuideButton = By.xpath("//button[@title='OES Video Guide']");
    private final By btn_CloseVideoButton = By.xpath("//button[@class='close-btn']");
    private final By btn_PdfGuideButton = By.xpath("//a[@title='OES PDF Guide']");
    private final By icon_NotificationBellIcon = By.xpath("(//i[@class='fa-bell far'])[1]");
    private final By btn_NotificationSeeAllOption = By.xpath("(//div[contains(@class,'notification')]//button[text()=' See All '])[1]");
    private final By btn_NotificationReadButton = By.xpath("//a[text()='Read ']");
    private final By btn_NotificationUnReadButton = By.xpath("//a[text()='Unread ']");
    private final By icon_RobotIcon = By.id("chat-menu");
    private final By btn_OChatButton = By.xpath("//div[@class='chat-icon-open']/p");
    private final By txt_TypeMessage = By.xpath("//textarea[@placeholder='Type your message here']");
    private final By btn_ChatSendButton = By.xpath("//a[@class='chat-send-btn']");
    private final By icon_UnLikeIcon = By.xpath("//a[contains(@class,'unlike')]");
    private final By lnk_RaiseATicketLink = By.xpath("//a[text()='Raise a Ticket']");
    private final By lnk_ProductNameLink = By.xpath("//button[text()='OES']");
    private final By drpD_SelectProductDropDown = By.xpath("//div[@class='form-group']//select[@formcontrolname='product']");
    private final By drpD_selectCategoryDropdown = By.xpath("//div[@class='form-group']//select[@formcontrolname='category']");
    private final By txt_RaiseTicketSubject = By.id("subject");
    private final By txt_TicketDescription = By.xpath("//textarea[@formcontrolname='description']");
    private final By btn_TicketSubmitButton = By.xpath("//button[text()='Submit']");
    private final By tab_TicketStatus = By.xpath("//div[@class='menubar-list']//div[text()='Ticket Status']");
    private final By lbl_TicketNumber = By.xpath("(//table[@role='table']/tbody/tr/td/a)[1]");
    private final By lbl_TicketDetails = By.xpath("//h2[text()='Ticket Details']");
    private final By btn_CloseChat = By.xpath("//a[@class='chat-close-btn']");
    private final By btn_CloseChatYesButton = By.xpath("//button[text()='Yes']");
    private final By lnk_FaqLink = By.xpath("//span[text()='FAQs ']");
    private final By lnk_UserManualLink = By.xpath("//span[text()='User Manual ']");
    private final By icon_ProfileIcon = By.xpath("//button[@id='profile-icon']");
    private final By lnk_Logout = By.xpath("//span[normalize-space()='Logout']");
    private final By lnk_Attachment = By.id("fileUpload");
    private final By btn_EditProfile = By.xpath("//button[normalize-space()='Edit Profile']");
    private final By txt_PhoneNumber = By.xpath("//input[@placeholder='Mobile Number']");
    private final By btn_ProfileSave = By.xpath(" //button[normalize-space()='Save']");
    private final By btn_LoginOES = By.xpath("//a[normalize-space()='Login']");
    private final By lbl_GetItNow = By.xpath("//div[@class='get-it-now']//*[text()='Get it Now ']");
    private final By btn_GetItNowProduct = By.xpath("(//div[contains(@class,'content-block')]//*[text()=' Get it now '])[1]");
    private final By btn_SubscribeNow = By.xpath("//div[contains(@class,'action')]//button[text()='Subscribe Now ']");
    private final By title_OrderSummery = By.xpath("//ul[@class='breadcrumb-links']//a[text()='Order Summary']");
    private final By lbl_TotalUsers = By.xpath("//strong[text()='Total Users']//parent::h5/../h2");
    private final By btn_TotalUsers = By.xpath("//strong[text()='Total Users']//..//..//../span[@class='learn-more']");
    private final By lbl_MyUsers = By.xpath("//ul[@class='breadcrumb-links']//a[text()='My Users']");
    private final By lbl_Affiliates = By.xpath("//strong[text()='Affiliates']//parent::h5/../h2");
    private final By btn_Affiliates = By.xpath("//strong[text()='Affiliates']//..//..//../span[@class='learn-more']");
    private final By lbl_Traffic = By.xpath("//strong[text()='Traffic']//parent::h5/../h2");
    private final By btn_Traffic = By.xpath("//strong[text()='Traffic']//..//..//../span[@class='learn-more']");
    private final By btn_GoToMaretPlaceFromDashboard = By.xpath("//div[@class='top-performing-product']//button[text()=' Go to Marketplace ']");
    private final By title_goToMarketPlace = By.xpath("//ul[@class='breadcrumb-links']//a[text()='Marketplace']");
    private final By list_PostVideos = By.xpath("//div[@class='video-slider-thumbnail']/div");
    private final By lnk_UpdatesReadMore = By.xpath("(//span[normalize-space()='Read More'])[1]");
    private final By icon_PreviousUpdate = By.xpath("(//i[@class='far fa-angle-left'])[2]");
    private final By icon_NextUpdate = By.xpath("(//i[@class='far fa-angle-right'])[2]");
    private final By icon_CloseUpdate = By.xpath("(//i[@class='far fa-times'])[1]");
    private final By icon_SwitchToDark = By.xpath("(//img[@src='../../../../../assets/images/icons/moon-icon.svg'])[1]");
    private final By icon_SwitchToLight = By.xpath("(//img[@src='../../../../../assets/images/icons/day_blue.svg'])[1]");


    Faker faker = new Faker();

    private ProductMenu productmenu;
    private HeaderSection headerSection;

    public OESDashboardPage() {
        productmenu = new ProductMenu();
        headerSection = new HeaderSection();
    }

    public HeaderSection getHeaderSection() {
        return headerSection;
    }

    public void setHeaderSection(HeaderSection headerSection) {
        this.headerSection = headerSection;
    }

    public ProductMenu getProductmenu() {
        return productmenu;
    }

    public void setProductMenu(ProductMenu productmenu) {
        this.productmenu = productmenu;
    }


    // X-Path Functions
    public OESDashboardPage clickOnEditProfile() {
        waitStrategy(btn_EditProfile, StrategyType.ELEMENT_VISIBLE);
        click(btn_EditProfile, "Edit Profile ");
        return this;
    }

    public OESDashboardPage clickOnLogin() {
        waitStrategy(btn_LoginOES, StrategyType.ELEMENT_VISIBLE);
        mouseOverClick(btn_LoginOES, "Login");
        return this;
    }

    public OESDashboardPage clickOnSwitchToDark() {
        waitStrategy(icon_SwitchToDark, StrategyType.ELEMENT_VISIBLE);
        mouseOverClick(icon_SwitchToDark, "Switch To Dark");
        waitForPageLoad();
        return this;
    }

    public OESDashboardPage clickOnSwitchToLight() {
        waitStrategy(icon_SwitchToLight, StrategyType.ELEMENT_VISIBLE);
        mouseOverClick(icon_SwitchToLight, "Switch To Light");
        waitForPageLoad();
        return this;
    }


    public OESDashboardPage navigateUsingNextButton() {
        int count = 0;
        while (count == 0) {
            click(btn_NextButton, "Next button");
            count = DriverManager.getDriver().findElements(btn_DoneButton).size();
        }
        clickDoneButton();
        return this;
    }


    public OESDashboardPage clickOnDashboardBreadCrumb() {
        waitStrategy(lnk_DashboardBreadCrumbLink, StrategyType.ELEMENT_VISIBLE);
        click(lnk_DashboardBreadCrumbLink, "Dashboard BreadCrumb");
        waitForPageLoad();
        return this;
    }


    public void getAffiliatesCount() {
        String affiliatesCount = getElementText(lbl_Affiliates, "Affiliates count");
        System.out.println(affiliatesCount);
    }

    public void affiliateButton() {
        click(btn_Affiliates, "Affiliate Button");
    }


    public void getTrafficCount() {
        String trafficCount = getElementText(lbl_Traffic, "Traffic count");
        System.out.println(trafficCount);
    }

    public void goToMarketPlaceFromDashboard() {
        waitForPageLoad();
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        waitForPageLoad();
        scrollToElement(btn_GoToMaretPlaceFromDashboard, "Go To Market Place Button");
        waitForPageLoad();
        waitStrategy(btn_GoToMaretPlaceFromDashboard, StrategyType.ELEMENT_VISIBLE);
        click(btn_GoToMaretPlaceFromDashboard, "Go To Market place button");
    }

    public OESDashboardPage updatePhoneNumber() {
        String number = faker.number().digits(7);
        String phoneNumber = "987" + number;
        enterText(txt_PhoneNumber, phoneNumber, "PhoneNumber");
        return this;
    }

    public OESDashboardPage clickOnSaveProfile() {
        click(btn_ProfileSave, "Save Profile Button");
        return this;
    }

    public OESDashboardPage clickOnProfileIcon() {
        waitForPageLoad();
        click(icon_ProfileIcon, "Profile Icon");
        return this;
    }

    public OESDashboardPage clickOnProfileLink() throws InterruptedException {
        waitStrategy(lnk_ProfileUserID, StrategyType.ELEMENT_VISIBLE);
        waitStrategy(lnk_ProfileName, StrategyType.ELEMENT_VISIBLE);
        mouseOverClick(lnk_ProfileName, "Profile Name");
        waitStrategy(lnk_Profile, StrategyType.ELEMENT_VISIBLE);
        mouseOverClick(lnk_Profile, "Profile ");
        Thread.sleep(1000);
        return this;
    }

    public OESDashboardPage clickOnEditUserIdIcon() {
        waitStrategy(icon_EditYourId, StrategyType.ELEMENT_VISIBLE);
        click(icon_EditYourId, "Edit Icon ");
        return this;
    }

    public OESDashboardPage clickOnSaveUserId() {
        waitStrategy(icon_Save, StrategyType.ELEMENT_VISIBLE);
        click(icon_Save, "Edit Icon ");
        waitForPageLoad();
        return this;
    }


    public OESDashboardPage checkNonDeclarationAgreementPop_Up() {
        waitStrategy(lbl_NonDeclarationAgreementPop_Up, StrategyType.ELEMENT_VISIBLE);
        return this;
    }

    public OESDashboardPage signTheNDAgreement() {
        waitStrategy(txt_NDAgreementSignaturePad, StrategyType.ELEMENT_VISIBLE);
        WebDriver driver = DriverManager.getDriver();
        Actions builder = new Actions(driver);
        WebElement canvasElement = getElement(txt_NDAgreementSignaturePad);
        Action signature = builder.click(canvasElement).clickAndHold().moveToElement(canvasElement, 20, -50).moveByOffset(10, 50).moveByOffset(80, -50).moveByOffset(100, 50).moveByOffset(20, 50).release(canvasElement).build();
        signature.perform();
        return this;
    }

    public OESDashboardPage clickNDAgreementAcceptButton() {
        click(btn_NDAgreementAccept, "AcceptButton");
        return this;
    }

    public OESDashboardPage checkTheNDAgreementCheckBoxOne() {
        click(checkB_NDAgreementCheckBox, "Accept Check Box");
        return this;
    }

    public OESDashboardPage checkTheNDAgreementCheckBoxTwo() {
        click(checkB_NDAgreementConformCheckBox, "Conform Check Box");
        return this;
    }

    public OESDashboardPage clickBecomeAReseller() {
        click(lnk_BecomeAReseller, "Become a Reseller");
        return this;
    }

    public OESDashboardPage clickTourButton() {
        click(btn_TourButton, "Tour Button");
        return this;
    }


    public OESDashboardPage clickDoneButton() {
        click(btn_DoneButton, "Done Button");
        return this;
    }

    public OESDashboardPage clickGuideButton() {
        click(btn_GuideButton, "Guide Button");
        return this;
    }


    public void trafficButton() {
        click(btn_Traffic, "Traffic button");
    }

    public OESDashboardPage clickOnReadMoreUpdate() {
        waitStrategy(lnk_UpdatesReadMore, StrategyType.ELEMENT_VISIBLE);
        mouseOverClick(lnk_UpdatesReadMore, "Read More Updates");
        return this;
    }


    public OESDashboardPage clickOnPreviousUpdate() {
        waitStrategy(icon_PreviousUpdate, StrategyType.ELEMENT_VISIBLE);
        mouseOverClick(icon_PreviousUpdate, "Read More Updates");
        return this;
    }


    public OESDashboardPage clickOnNextUpdate() {
        waitStrategy(icon_NextUpdate, StrategyType.ELEMENT_VISIBLE);
        mouseOverClick(icon_NextUpdate, "Read More Updates");
        return this;
    }

    public OESDashboardPage clickOnCloseUpdateWindow() {
        waitStrategy(icon_CloseUpdate, StrategyType.ELEMENT_VISIBLE);
        mouseOverClick(icon_CloseUpdate, "Read More Updates");
        return this;
    }

    public OESDashboardPage clickVideoGuideButton() {
        click(btn_VideoGuideButton, "Video Guide Button");
        return this;
    }

    public OESDashboardPage clickCloseVideoGuideButton() {
        waitStrategy(btn_CloseVideoButton, StrategyType.ELEMENT_VISIBLE);
        click(btn_CloseVideoButton, "Close Video Guide Button");
        return this;
    }

    public OESDashboardPage clickPdfGuideButton() {
        click(btn_PdfGuideButton, "PDf Guide Button");
        return this;
    }

    public OESDashboardPage clickNotificationBellIcon() {
        click(icon_NotificationBellIcon, "Notification Bell Icon");
        return this;
    }

    public OESDashboardPage clickSeeAllNotificationButton() {
        click(btn_NotificationSeeAllOption, "Notification See All");
        return this;
    }

    public OESDashboardPage clickNotificationReadButton() {
        click(btn_NotificationReadButton, "Notification Read");
        return this;
    }

    public OESDashboardPage clickNotificationUnReadButton() {
        click(btn_NotificationUnReadButton, "Notification UnRead");
        return this;
    }

    public void mouserOverOnRobotIcon() {
        mouseOver(icon_RobotIcon, "Chat Boat Robot Icon");
    }

    public OESDashboardPage mouserOverClickOChatButton() {
        mouseOverClick(btn_OChatButton, "O-Chat");
        return this;
    }

    public OESDashboardPage enterChatTypeMessage() {
        enterText(txt_TypeMessage, "how to raise ticket", "Type the Message");
        return this;
    }

    public OESDashboardPage clickChatSendButton() {
        click(btn_ChatSendButton, "Send Chat Button");
        return this;
    }

    public OESDashboardPage clickUnlikeIcon() {
        click(icon_UnLikeIcon, "Un Like Icon");
        return this;
    }

    public OESDashboardPage clickRaiseTicket() {
        waitForPageLoad();
        waitStrategy(lnk_RaiseATicketLink, StrategyType.ELEMENT_PRESENT);
        click(lnk_RaiseATicketLink, "Raise Ticket");
        return this;
    }

    public OESDashboardPage mouseOverClickProductName() {
        waitForPageLoad();
        waitStrategy(lnk_ProductNameLink, StrategyType.ELEMENT_PRESENT);
        mouseOverClick(lnk_ProductNameLink, "Product Name");
        return this;
    }

    public OESDashboardPage selectProductName() {
        selectDropDownBySelectClass(drpD_SelectProductDropDown, "OES");
        waitForPageLoad();
        return this;
    }

    public OESDashboardPage selectCategoryType() {
        waitForPageLoad();
        selectDropDownBySelectClass(drpD_selectCategoryDropdown, "Dashboard");
        waitForPageLoad();
        return this;
    }


    public OESDashboardPage addAttachments() {
        fileUploadByID(lnk_Attachment, "dashBoard_Ticket.png", "Ticket Attachment");
        waitForPageLoad();
        return this;
    }

    public OESDashboardPage enterTicketSubject() {
        enterText(txt_RaiseTicketSubject, faker.name().title(), "Ticket Subject");
        waitForPageLoad();
        return this;
    }

    public OESDashboardPage enterTicketDescription() {
        enterText(txt_TicketDescription, faker.name().title(), "Ticket Description");
        scrollToElement(btn_TicketSubmitButton, "Scroll to Submit Button");
        return this;
    }

    public OESDashboardPage ClickTicketSubmitButton() {
        click(btn_TicketSubmitButton, "Ticket submit");
        return this;
    }

    public OESDashboardPage ClickTicketStatusTab() {
        waitStrategy(tab_TicketStatus, StrategyType.ELEMENT_VISIBLE);
        click(tab_TicketStatus, "Ticket Status");
        waitForPageLoad();
        return this;
    }

    public OESDashboardPage checkTicketNumber() {
        waitStrategy(lbl_TicketNumber, StrategyType.ELEMENT_VISIBLE);
        click(lbl_TicketNumber, "Ticket Number");
        return this;
    }

    public OESDashboardPage checkTicketDetails() {
        waitForPageLoad();
        waitStrategy(lbl_TicketDetails, StrategyType.ELEMENT_VISIBLE);
        return this;
    }

    public OESDashboardPage ClickCloseChatBox() {
        waitForPageLoad();
        waitStrategy(btn_CloseChat, StrategyType.ELEMENT_VISIBLE);
        click(btn_CloseChat, "Close Chat");
        return this;
    }

    public OESDashboardPage ClickCloseChatYesButton() {
        waitStrategy(btn_CloseChatYesButton, StrategyType.ELEMENT_VISIBLE);
        click(btn_CloseChatYesButton, "Yes Button");
        return this;
    }

    public OESDashboardPage navigateToONPassiveWebSite() {
        DriverManager.getDriver().navigate().to("https://www.onpassive.com/");
        waitForPageLoad();
        return this;
    }

    public OESDashboardPage ClickOnFaqLink() {
        click(lnk_FaqLink, "FAQ Link");
        return this;
    }

    public OESDashboardPage ClickOnUserManualLink() {
        click(lnk_UserManualLink, "User Manual Link");
        return this;
    }

    public OESDashboardPage ClickOnProfileIcon() {
        click(icon_ProfileIcon, "Profile Icon");
        return this;
    }

    public OESDashboardPage clickOnLogout() {
        waitStrategy(lnk_Logout, StrategyType.ELEMENT_VISIBLE);
        mouseOverClick(lnk_Logout, "Log Out");
        return this;
    }

    public void getItNow() {
        click(lbl_GetItNow, "Get It Now Link");
    }

    public void getItNowProduct() {
        boolean element = waitStrategyFor(btn_GetItNowProduct);
        if (element == true) {

            click(btn_GetItNowProduct, "Click on get it now product");

        } else {
            System.out.println("No products found!");
        }
    }


    public void subscribeNow() {
        boolean element = waitStrategyFor(btn_SubscribeNow);
        if (element == true) {
            System.out.println("Subscribe button is displayed ");
        } else {
            System.out.println("No Subscribe found!");
        }
    }

    public void getTotalUsersCount() {
        String totalUsers = getElementText(lbl_TotalUsers, "Total user count");
        System.out.println(totalUsers);
    }

    public void totalUserButton() {
        click(btn_TotalUsers, "Total users button");
    }


    //Functional Methods
    public boolean verifyTenCredits() {
        WebElement element = waitStrategy(lbl_TenCredits, StrategyType.ELEMENT_VISIBLE);
        return element.getText().equalsIgnoreCase("10 Credits") ? true : false;
    }



    public boolean verifyNavigationToOESDashboard() {
        waitForPageLoad();
        return verifyGetElementText(lnk_DashboardBreadCrumbLink, "Dashboard", "Dashboard",
                "Successfully logged in to the OES application and verified the dashboard page."
                ,"Unsuccessfully logged in to the OES application and verified the dashboard page.");

    }


    public boolean waitElementInvisible() {
        return !waitStrategy(lbl_ToasterMessage, StrategyType.ELEMENT_INVISIBLE).isDisplayed();
    }

    public boolean verifyNewUserCreditScore() {
        return verifyTenCredits();
    }

    public OESDashboardPage verifyFAQ() {
        clickOnProfileIcon().verifyFAQ();
        return this;
    }

    public OESDashboardPage verifyUserManual() {
        clickOnProfileIcon().verifyUserManual();
        return this;
    }

    public OESLoginPage logOut() {
        clickOnProfileIcon().clickOnLogout();
        return new OESLoginPage();

    }

    public OESDashboardPage verifyBecomeAResellerThroughHeader() {
        clickBecomeAReseller().checkNonDeclarationAgreementPop_Up().signTheNDAgreement().checkTheNDAgreementCheckBoxOne().checkTheNDAgreementCheckBoxTwo()
                .clickNDAgreementAcceptButton();
        return this;
    }

    public OESDashboardPage verifyNavigateUsingGuideButton() {
        clickGuideButton().clickVideoGuideButton().clickCloseVideoGuideButton().clickGuideButton()
                .clickPdfGuideButton().switchToTabWithURL("pdf", "Navigate PDF Guide","pass","fail");
        closeChildToTabWithURL("pdf");
        switchToTabWithURL("dashboard", "Navigate OES Window","pass","fail");
        return this;
    }


    public OESDashboardPage verifySeeAllNotifications() {
        clickNotificationBellIcon().clickSeeAllNotificationButton();
        return this;
    }


    public OESDashboardPage verifyReadNotifications() {
        clickNotificationBellIcon().clickSeeAllNotificationButton().clickNotificationReadButton();
        return this;
    }


    public OESDashboardPage verifyUnReadNotifications() {
        clickNotificationBellIcon().clickSeeAllNotificationButton().clickNotificationUnReadButton();
        return this;
    }

    public OESDashboardPage verifyChatBoardInteract() {
        mouserOverOnRobotIcon();
        mouserOverClickOChatButton();
        return this;
    }

    public OESDashboardPage verifyRaiseTickets() {
        mouseOverClickProductName().enterChatTypeMessage().clickChatSendButton().clickUnlikeIcon()
                .clickRaiseTicket().selectProductName().selectCategoryType().enterTicketSubject().enterTicketDescription().addAttachments().
                ClickTicketSubmitButton().ClickCloseChatBox().ClickCloseChatYesButton();
        return this;
    }

    public OESDashboardPage verifyRaiseTicketDetails() {
        checkTicketNumber().checkTicketDetails();
        return this;
    }

    public OESDashboardPage verifyUserIDAndProfileName() throws InterruptedException {
        clickOnProfileLink().clickOnEditUserIdIcon().clickOnSaveUserId();
        return this;
    }


    public OESDashboardPage verifyUserProfileUpdate() {
        clickOnEditProfile().updatePhoneNumber().clickOnSaveProfile().waitForPageLoad();
        return this;
    }

    public OESDashboardPage verifyUserLogOut() {
        clickOnProfileIcon().clickOnLogout();
        return this;
    }


    public OESDashboardPage verifyLoginFromWebsite() {
        navigateToONPassiveWebSite().clickOnLogin();
        waitForPageLoad();
        return this;
    }

    public boolean verifyOrderSummeryPageTitle() {
        String orderSummeryPageText = getElementText(title_OrderSummery, "Order summery page");
        return (orderSummeryPageText.equalsIgnoreCase("Order Summary")) ? true : false;
    }


    public boolean verifyMyUsersPageTitle() {
        String myUsersPageText = getElementText(lbl_MyUsers, "My Users page");
        return (myUsersPageText.equalsIgnoreCase("My Users")) ? true : false;
    }


    public boolean verifyGoToMarketPlacePageTitle() {
        String myUsersPageText = getElementText(title_goToMarketPlace, "Go To Market Place page");
        return (myUsersPageText.equalsIgnoreCase("Marketplace")) ? true : false;
    }


    public void verifyPostVideos() {
        waitForPageLoad();
        boolean element = waitStrategyFor(list_PostVideos);
        if (element == true) {
            System.out.println("videos are found!");

        } else {
            System.out.println("No videos found!");
        }
    }


    public OESDashboardPage verifyReadUpdates() {
        clickOnReadMoreUpdate().clickOnPreviousUpdate().clickOnNextUpdate().clickOnCloseUpdateWindow();
        return this;
    }


    public OESDashboardPage verifyNotifications() {
        clickNotificationBellIcon().clickSeeAllNotificationButton().clickNotificationReadButton().clickNotificationUnReadButton();
        return this;
    }


    public OESDashboardPage verifyNavigateUsingTourButton() {
        clickTourButton().navigateUsingNextButton();
        return this;
    }


    public OESDashboardPage verifySwitchToDarkAndLight() {
        clickOnSwitchToDark().clickOnSwitchToLight();
        return this;
    }

    public boolean verifyUpdateProfileSuccessToasterMessage() {
        return ToasterMessage(lbl_ToasterMessage, "Profile updated successfully", "Phone Number Updated Suse's Message","pass","fail");
    }

}