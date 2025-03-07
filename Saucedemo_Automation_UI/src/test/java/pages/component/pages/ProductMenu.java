package pages.component.pages;
import base.BasePage;
import org.openqa.selenium.By;
import pages.otrim.pages.TrimURLPage;


public class ProductMenu extends BasePage {

    private final By icon_OTrimProduct = By.xpath("(//ul[@class='sidebar-products-list'])[1]//img[@alt='O-Trim']");
    private final By overlay_loader = By.xpath("//div[@class='pre-loader']");
    private final By icon_Loading = By.xpath("(//div[contains(@class,'loading-text ng-tns')]/p[text()='Loading...'])[1]");


//X-path Functions
    public void clickOnOTrimProductIcon()
    {
        mouseOverClick(icon_OTrimProduct, "OTrim Product Icon");
    }


    //Functional Methods
    public synchronized TrimURLPage navigateToOTrimUsingProductMenuLink() {
        waitForOverlaysToDisappear(overlay_loader);
        clickOnOTrimProductIcon();
        switchToTab(1,"O-Trim Application",
                "Successfully navigate to the O-trim from the OES application.",
                "Unsuccessfully navigate to the O-trim from the OES application.");
        waitForOverlaysToDisappear(icon_Loading);
        waitForPageLoad();
        return new TrimURLPage();
    }


}
