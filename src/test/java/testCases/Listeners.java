package testCases;



import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;
import resources.baseTest;

public class Listeners extends baseTest implements ITestListener{

	ExtentReports extent= ExtentReporterNG.config();
	ExtentTest test;
	ThreadLocal<ExtentTest> parallel = new ThreadLocal<ExtentTest>(); 


	public void onTestFailure(ITestResult result) {
		
		parallel.get().fail(result.getThrowable());//extent report for test failure with failure logs
		
		//Code for Screenshot on failure
		
		//WebDriver driver=null; //declaring for parallel execution
		String testMethodName=result.getMethod().getMethodName();
		
		
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			
		}
			
		
		try {
			parallel.get().addScreenCaptureFromPath(getScreenshotPath(testMethodName,driver), result.getMethod().getMethodName());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public void onTestStart(ITestResult result) {


		test=extent.createTest(result.getMethod().getMethodName());
		parallel.set(test);
		
	}

	public void onTestSuccess(ITestResult result) {
		
		parallel.get().log(Status.PASS, "Test Passed");
		
	}

	public void onFinish(ITestContext context) {
		
		extent.flush();
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}



	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

}
