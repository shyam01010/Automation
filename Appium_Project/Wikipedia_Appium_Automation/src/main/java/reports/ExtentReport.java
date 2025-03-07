package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import constants.FrameworkConstants;
import listeners.TestListener;
import org.testng.ITestContext;
import utils.ConfigLoader;

import java.io.IOException;
import java.util.Objects;

import static listeners.TestListener.suiteName;

public final class ExtentReport {

    private static ExtentReports extent;
    private static ITestContext storedContext;

    private ExtentReport() {

    }


    public static void initReport() {
        if (Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportPath());
            extent.attachReporter(spark);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Appium - TEST AUTOMATION REPORT");
            spark.config().setReportName("Appium Ecommerce REPORT");
            extent.setSystemInfo("OS Name", System.getProperty("os.name").toUpperCase());
            extent.setSystemInfo("User Name", ConfigLoader.getInstance().getUsername());
            extent.setSystemInfo("Password", ConfigLoader.getInstance().getPassword());
            extent.setSystemInfo("URL", ConfigLoader.getInstance().getBaseUrl());
            extent.setSystemInfo("Environment Type", System.getProperty("env"));
            String suiteName = TestListener.getSuiteName();
        }
    }


    public static void flushReport() throws IOException {
        if (Objects.nonNull(extent)) {
            extent.flush();
            ExtentManager.unload();
        }
        //open the file in the desktop default browser
       // Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportPath()).toURI());
    }

    public static void createTest(String testcaseName) {

        ExtentTest test = extent.createTest(testcaseName).assignDevice(System.getProperty("appName")).
                assignAuthor(System.getProperty("user.name")).assignCategory(suiteName);
        ExtentManager.setExtentTest(test);

    }

}
