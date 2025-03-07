package factory;

import constants.FrameworkConstants;
import constants.StrategyType;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reports.ExtentLogger;

import java.time.Duration;

public final class ExplicitWaitFactory {

    private ExplicitWaitFactory() {

    }

    public static WebElement performExplicitWait(By locator, StrategyType strategyType) {
        WebElement element = null;
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.getWaitMode()));
        switch (strategyType) {
            case ELEMENT_VISIBLE:
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;
            case ELEMENT_CLICKABLE:
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                break;
            case ELEMENT_INVISIBLE:
                boolean b = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
                element = b ? driver.findElement(locator) : null;
                break;
            case ELEMENT_PRESENT:
                element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                break;
            case FRAME_AVAILABLE:
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
                break;
            case ELEMENT_SELECTED:
                boolean b1 = wait.until(ExpectedConditions.elementToBeSelected(locator));
                element = b1 ? driver.findElement(locator) : null;
                break;
            case NONE:
                element = driver.findElement(locator);
                break;
            default:
                ExtentLogger.pass("Explicit Wait : No Strategy matched");
        }
        return element;
    }

    public static boolean performExplicitWait(StrategyType strategyType, String text) {
        boolean b = false;
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.getWaitMode()));
        switch (strategyType) {
            case TITLE_TO_HAVE:
                b = wait.until(ExpectedConditions.titleContains(text));
                break;
            case URL_TO_HAVE:
                b = wait.until(ExpectedConditions.urlContains(text));
                break;
        }
        return b;
    }
}
