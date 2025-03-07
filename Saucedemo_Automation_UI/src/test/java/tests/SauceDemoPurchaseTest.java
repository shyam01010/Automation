package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.oes.user.pages.OESDashboardPage;
import pages.oes.user.pages.OESLoginPage;
import pages.sauce.demo.purchase.SauceDemoPurchasePage;

public class SauceDemoPurchaseTest extends BaseTest {

    SauceDemoPurchasePage sauceDemoPurchasePage = new SauceDemoPurchasePage();
    OESDashboardPage oesDashboardPage;
    @Test()
    public void verifyPurchaseOnlineFlow() {
        oesDashboardPage = new OESLoginPage().Load().loginOES();
        sauceDemoPurchasePage.addToCart();
        sauceDemoPurchasePage.verifyCartDetails();
        sauceDemoPurchasePage.verifyCheckOutSummary();
        String purchase_Message =   sauceDemoPurchasePage.verifyPurchaseCompletedSuccessfully();
        Assert.assertEquals(purchase_Message,"Thank you for your order!");



    }
}
