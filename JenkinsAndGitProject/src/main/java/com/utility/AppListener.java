package com.utility;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class AppListener implements ITestListener,IAnnotationTransformer {

	String htmlFilePath=System.getProperty("user.dir")+"\\test-output\\ExtentReport.html";
	ExtentReports extentReport;
	EventManager evtmng=new EventManager();
	ThreadLocal<ExtentTest> threadExtent=new ThreadLocal<>();
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryTestLogic.class);
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extentReport.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
		extentTest.assignCategory(result.getMethod().getGroups());
		threadExtent.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		threadExtent.get().pass("Passed");
		try
		{
			threadExtent.get().addScreenCaptureFromPath("./Screenshots/"+result.getMethod().getMethodName()+".jpg");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		threadExtent.get().fail(result.getThrowable());
		try
		{
			threadExtent.get().addScreenCaptureFromPath("./Screenshots/"+result.getMethod().getMethodName()+".jpg");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		threadExtent.get().skip(result.getThrowable());
		extentReport.removeTest(threadExtent.get());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		try
		{
			extentReport=evtmng.genrateHTMLReport(htmlFilePath);
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		try
		{
			extentReport.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		
	}
}
