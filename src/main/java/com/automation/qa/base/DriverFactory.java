package com.automation.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
//	ThreadLocal - each thread gets its own isolated driver
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void initDriver(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		} else if(browserName.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
		} else {
			throw new IllegalArgumentException("Browser not supported: "+browserName);
		}
		
		getDriver().manage().window().maximize();
	}
	
	public static void quitDriver() {
		if(getDriver()!=null) {
			getDriver().quit();
			driver.remove();
		}
	}
	

}
