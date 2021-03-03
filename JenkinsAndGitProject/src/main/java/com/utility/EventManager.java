package com.utility;

import java.net.InetAddress;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ResourceCDN;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class EventManager extends TestCommonActions{
	
	public ExtentReports genrateHTMLReport(String filepath)
	{
		ExtentHtmlReporter htmlreport=new ExtentHtmlReporter(filepath);
		
		ExtentReports extent=new ExtentReports();
		try
		{
			htmlreport.config().setTheme(Theme.DARK);
			htmlreport.config().setDocumentTitle("My Report");
			htmlreport.config().setEncoding("utf-8");
			htmlreport.config().setReportName("BMC Report");
			htmlreport.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");
			htmlreport.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
			
			extent.attachReporter(htmlreport);
			//extent.setSystemInfo("Environment", objRB.getString("TestEnv"));
			extent.setSystemInfo("Executed By", System.getProperty("user.name"));
			extent.setSystemInfo("Operating System", System.getProperty("os.name"));
			extent.setSystemInfo("Module", "BMC Marketing URL");
			
			InetAddress localMachine=InetAddress.getLocalHost();
			extent.setSystemInfo("Host Name", localMachine.getHostName());
			//extent.setSystemInfo("URL", objRB.getString("TestURL"));
			return extent;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}

}
