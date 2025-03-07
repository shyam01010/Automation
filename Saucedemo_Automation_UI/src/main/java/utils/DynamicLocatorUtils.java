package utils;

import org.openqa.selenium.By;

public final class DynamicLocatorUtils {

    private DynamicLocatorUtils() {

    }

    public static By locateElementWithText(String pattern, String text) {

        return By.xpath(String.format(pattern, text));
    }
}
