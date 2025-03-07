package pages.sauce.demo.purchase;

import base.BasePage;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.component.pages.HeaderSection;
import pages.component.pages.ProductMenu;

import java.time.Duration;
import java.util.List;

public class SauceDemoPurchasePage extends BasePage {
    public static String  message;
    public static WebDriverWait wait;
    private ProductMenu productmenu;
    private HeaderSection headerSection;

    private final By inventoryItem = By.className("inventory_item");
    private final By btn_Inventory = By.cssSelector("button.btn_inventory");
    private final By link_Cart = By.className("shopping_cart_link");
    private final By lbl_CartItem = By.className("cart_item");
    private final By btn_Checkout = By.id("checkout");

    private final By txt_FirstName = By.id("first-name");
    private final By txt_LastName = By.id("last-name");
    private final By txt_PostalCode = By.id("postal-code");
    private final By btn_Continue = By.id("continue");

    private final By lbl_Summary = By.className("summary_subtotal_label");

    private final By lbl_SummaryTax = By.className("summary_tax_label");
    private final By lbl_SummaryTotal = By.className("summary_total_label");
    private final By btn_Finish = By.id("finish");

    private final By title_Complete = By.className("complete-header");


    public SauceDemoPurchasePage() {
        productmenu = new ProductMenu();
        headerSection = new HeaderSection();
    }


    public void addToCart() {
        wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryItem));

        List<WebElement> addToCartButtons = DriverManager.getDriver().findElements(btn_Inventory);
        for (int i = 0; i < 4; i++) {
            addToCartButtons.get(i).click();
        }
    }

    public void verifyCartDetails() {
        DriverManager.getDriver().findElement(link_Cart).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(lbl_CartItem));

        DriverManager.getDriver().findElement(btn_Checkout).click();

        DriverManager.getDriver().findElement(txt_FirstName).sendKeys("John");
        DriverManager.getDriver().findElement(txt_LastName).sendKeys("Doe");
        DriverManager.getDriver().findElement(txt_PostalCode).sendKeys("12345");
        DriverManager.getDriver().findElement(btn_Continue).click();

    }
    public void verifyCheckOutSummary() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lbl_Summary));
        String itemTotal = DriverManager.getDriver().findElement(lbl_Summary).getText();
        String tax = DriverManager.getDriver().findElement(lbl_SummaryTax).getText();
        String total = DriverManager.getDriver().findElement(lbl_SummaryTotal).getText();

        System.out.println("Item Total: " + itemTotal);
        System.out.println("Tax: " + tax);
        System.out.println("Total: " + total);
        DriverManager.getDriver().findElement(btn_Finish).click();
    }

        public String verifyPurchaseCompletedSuccessfully(){
        WebElement successMessage = DriverManager.getDriver().findElement(title_Complete);
           message =  successMessage.getText();


            return message;
        }

}


