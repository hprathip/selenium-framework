package com.automation.qa.base;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.automation.qa.utils.ConfigReader;
import com.automation.qa.utils.ExtentReportManager;
import com.automation.qa.utils.ScreenshotUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest {

	protected WebDriver driver;

//	Log4j logger - one per class
	protected static final Logger log = LogManager.getLogger("BaseTest.class");

//	ExtentReport - shared across all tests
	protected static ExtentReports extent;
	protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

//	---- Suite Level ------------------------------

	@BeforeSuite
	public void setupReport() {
		extent = ExtentReportManager.createInstance();
		log.info("ExtentReports initialized");
	}

	@AfterSuite
	public void tearDownReport() {
		if (extent != null) {
			extent.flush();
			log.info("ExtentReports flushed - report saved");
		}
	}

//	---- Test Level --------------------------------

	@BeforeMethod
	public void setUp(Method method) {

		String browser = ConfigReader.getProperty("browser");
		String url = ConfigReader.getProperty("url");

		log.info("Starting test: " + method.getName());
		log.info("Browser: " + browser + " | URL: " + url);

		DriverFactory.initDriver(browser);
		driver = DriverFactory.getDriver();

		driver.get(url);

//		Create ExtentTest entry for this test
		ExtentTest extentTest = extent.createTest(method.getName());
		test.set(extentTest);
		log.info("Browser launched and URL loaded");

	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		ExtentTest extentTest = test.get();

		if (result.getStatus() == ITestResult.FAILURE) {
//			Capture screenshot on failure
			String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());

//			log failure in ExtentReport
			extentTest.fail(result.getThrowable());

//			Attach screenshot to report
			if (screenshotPath != null) {

				extentTest.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");

			}

			log.error("TEST FAILED: " + result.getName());
			log.error("Cause: " + result.getThrowable().getMessage());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass("Test Passed");
			log.info("TEST PASSED: " + result.getName());

			/*
			 * String screenshotPath = ScreenshotUtils.captureScreenshot(driver,
			 * result.getName());
			 * 
			 * if (screenshotPath != null) {
			 * 
			 * extentTest.addScreenCaptureFromPath(screenshotPath, "Passed Screenshot");
			 * 
			 * }
			 */
		} else {
			extentTest.skip("Test skipped");
			log.warn("TEST SKIPPED: " + result.getName());
		}

		DriverFactory.quitDriver();
		log.info("Browser closed");

	}

}
