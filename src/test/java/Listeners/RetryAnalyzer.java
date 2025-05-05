package Listeners;

import Constants.FrameworkConstants;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {

        int maxRetryCount = FrameworkConstants.MAX_RETRIES;

        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
