package com.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.utility.TestCommonActions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_01_CheckURLStatus {
	
	WebDriver driver;
	TestCommonActions testActions=new TestCommonActions();
	@BeforeMethod
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test(description="checking url status" , groups="Regression")
	public void TC01_CheckURLStatus()
	{
		if(testActions.loadURL("https://www.bmc.com/it-solutions/control-m.html", driver))
			System.out.println("load");
			//if(testActions.validateOutputString("", ""))
	}
	
	@AfterMethod
	public void tearDown()
	{
		if(driver!=null)
			System.out.println("quit");
			//driver.quit();
	}
}
