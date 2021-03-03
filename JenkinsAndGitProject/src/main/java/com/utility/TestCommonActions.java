package com.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class TestCommonActions {
	
	

	public boolean loadURL(String url, WebDriver driver)
	{
		driver.get(url);
		closeMessageBox(driver);
		return true;
	}
	
	public boolean loadURLWithTimeout(String url, WebDriver driver, int timeout)
	{
		driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
		try
		{
			driver.get(url);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean validateOutputString(String actualTitle, String expectedTitle)
	{
		if(actualTitle.equalsIgnoreCase(expectedTitle))
			return true;
		return false;
	}
	
	public boolean closeMessageBox(WebDriver driver)
	{
		try
		{
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id=\"truste-consent-button\"]")).click();
		driver.switchTo().frame("drift-widget");
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/span/div/div[2]/div/div"))).build().perform();
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/span/div/div[1]/button/img")).click();
		
		return true;
	}
}
