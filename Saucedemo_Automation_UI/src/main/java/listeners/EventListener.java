package listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventListener implements org.openqa.selenium.support.events.WebDriverListener {

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        System.out.println("Trying to find the element with locator.." + locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        System.out.println("Element with the locator.." + locator + "is .. " + result);
    }

    @Override
    public void beforeClick(WebElement element) {
        System.out.println("Before clicking on .." + element);
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        throw new RuntimeException("Test Skipped due to exception .. check target" + target.toString());
    }
}
