package com.automation.qa.base;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

//	ThreadLocal - each thread gets its own isolated driver
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void initDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();

			
			// Disable password manager completely via prefs
	        HashMap<String, Object> prefs = new HashMap<>();
	        prefs.put("credentials_enable_service", false);
	        prefs.put("profile.password_manager_enabled", false);
	        prefs.put("profile.password_manager_leak_detection", false);
	        options.setExperimentalOption("prefs", prefs);
	        
	        
			// Disable password save popup
			options.addArguments("--disable-save-password-bubble");

			// Disable all Chrome notifications
			options.addArguments("--disable-notifications");

			// Disable infobars
			options.addArguments("--disable-infobars");

			driver.set(new ChromeDriver(options));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
		} else {
			throw new IllegalArgumentException("Browser not supported: " + browserName);
		}

		getDriver().manage().window().maximize();
	}

	public static void quitDriver() {
		if (getDriver() != null) {
			getDriver().quit();
			driver.remove();
		}
	}

}
