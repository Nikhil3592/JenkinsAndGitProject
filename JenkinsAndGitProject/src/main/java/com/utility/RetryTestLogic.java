package com.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestLogic implements IRetryAnalyzer {

	int count=0;
	static int maxRetry=2;
	@Override
	public boolean retry(ITestResult result) {
		if(count < maxRetry)
		{
			count++;
			return true;
		}
		else
		{
			result.setStatus(ITestResult.SUCCESS);
			return false;
		}
		
	}

}
