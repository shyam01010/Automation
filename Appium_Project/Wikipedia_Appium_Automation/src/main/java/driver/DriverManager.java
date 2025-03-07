package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public final class DriverManager {
    private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static AndroidDriver getDriver() {

        return driver.get();
    }

    public static void setDriver(AndroidDriver driverRef) {
        driver.set(driverRef);
    }


    public static void unload() {
        driver.remove();
    }
}
