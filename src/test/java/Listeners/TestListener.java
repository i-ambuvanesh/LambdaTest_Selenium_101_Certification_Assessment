package Listeners;

import CommonUtils.ExtentReportManager;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("===============================================");
        System.out.println("New Test Session: " + result.getName());
        System.out.println("On Thread : " + Thread.currentThread().threadId());
        System.out.println("===============================================\n");

        String testName = result.getName();
        ExtentReportManager.startTest(testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("===============================================");
        System.out.println("Test Passed: " + result.getName());
        System.out.println("===============================================\n");

        ExtentReportManager.markTestPassed();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("===============================================");
        System.out.println("Test Failed: " + result.getName());
        if (result.getThrowable() != null) {
            System.out.println("Cause of Failure: " + result.getThrowable().getMessage());
        }
        System.out.println("===============================================\n");

        ExtentReportManager.markTestFailed(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println("===============================================");
        System.out.println("Test Skipped: " + result.getName());
        System.out.println("===============================================\n");

        ExtentReportManager.markTestSkipped();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

        System.out.println("===============================================");
        System.out.println("Test Failed but within success percentage: " + result.getName());
        System.out.println("===============================================\n");
        ExtentReportManager.markTestFailed(result.getThrowable());
    }
}
