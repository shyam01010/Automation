package listeners;

import org.apache.commons.mail.EmailException;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentLogger;
import reports.ExtentReport;
import utils.EmailUtils;

import java.io.IOException;

public class TestListener implements ITestListener, ISuiteListener {

    public static String suiteName;

    public static String getSuiteName() {
        return suiteName;
    }

    @Override
    public void onStart(ISuite suite) {
        suiteName = suite.getXmlSuite().getName();
        MyLogger.info("Current Suite Name===========>" + suiteName);
        ExtentReport.initReport();
    }


    @Override
    public void onFinish(ISuite suite) {
        try {
            ExtentReport.flushReport();
           // EmailUtils.sendEmail();
        } catch (IOException e) {
           /* throw new RuntimeException(e);
        } catch (EmailException e) {*/
            throw new RuntimeException(e);
        }
    }


    @Override
    public void onTestStart(ITestResult result) {
       /* String currentBrowser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        System.setProperty("browserName", currentBrowser);*/
        ExtentReport.createTest(System.getProperty("browserName") + " : " + result.getMethod().getDescription());
        ExtentLogger.pass(result.getMethod().getDescription() + " Test is Started");
        MyLogger.info(result.getMethod().getDescription() + " Test is Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getMethod().getDescription() + " Test is Passed");
        MyLogger.info(result.getMethod().getDescription() + " Test is Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(result.getMethod().getDescription() + " Test is Failed", true);
        ExtentLogger.fail((result.getThrowable().getMessage()));
        MyLogger.info(result.getThrowable().getMessage());
        MyLogger.info(result.getMethod().getDescription() + " Test is Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(result.getMethod().getDescription() + " Test is Skipped", true);
        MyLogger.info(result.getMethod().getDescription() + " Test is Skipped");
    }
}
