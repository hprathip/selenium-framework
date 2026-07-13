package com.automation.qa.stepdefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.automation.qa.base.DriverFactory;
import com.automation.qa.utils.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	@Before
	public void setUp() {
		String browser = ConfigReader.getProperty("browser");
		String url = ConfigReader.getProperty("url");

		DriverFactory.initDriver(browser);
		DriverFactory.getDriver().get(url);

		System.out.println("Browser launched for scenario");
	}

	@After
	public void tearDown(Scenario scenario) {
		WebDriver driver = DriverFactory.getDriver();

//		Screenshot on Failure
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "Failure Screenshot");

			System.out.println("Screenshot attached for failed scenario: " + scenario.getName());
		}
		DriverFactory.quitDriver();
		System.out.println("Browser closed after scenario: " + scenario.getName());

	}

}
