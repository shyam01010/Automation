package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ReRunTest implements IRetryAnalyzer {



    private int retryCount = 0;
    private static final int maxRetryCount = 3;


    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }

}

