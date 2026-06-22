package com.automation.qa.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.automation.qa.utils.ConfigReader;

public class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		String browser = ConfigReader.getProperty("browser");
		DriverFactory.initDriver(browser);
		driver = DriverFactory.getDriver();
		
		String url = ConfigReader.getProperty("url");
		driver.get(url);
		
	}
	
	@AfterMethod
	public void tearDown() {
//		DriverFactory.quitDriver();
	}

}
