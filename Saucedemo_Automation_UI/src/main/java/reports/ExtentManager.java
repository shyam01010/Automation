package reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    private ExtentManager() {

    }

    static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    static void unload() {
        extentTest.remove();
    }
}
