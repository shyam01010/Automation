package reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    private ExtentManager() {
        // Private constructor to prevent instantiation
    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    public static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static void unload() {
        extentTest.remove();
    }
}
