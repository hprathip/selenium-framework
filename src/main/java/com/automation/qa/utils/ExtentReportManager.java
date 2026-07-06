package com.automation.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	private static ExtentReports extent;

	public static ExtentReports createInstance() {

//		 Report file path
		String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

//		Spark reporter - generates the HTML file
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);	
		
//		Report configuration
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setDocumentTitle("SauceDemo Automation Report");
		sparkReporter.config().setReportName("Selenium Framework - Test Execution Report");
		sparkReporter.config().setEncoding("utf-8");

//		Create ExtentReports instance
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

//		System info - appears in report header
		extent.setSystemInfo("Application", "SauceDemo");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester", "Harika Priya");
		extent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
		extent.setSystemInfo("URL", ConfigReader.getProperty("url"));
		extent.setSystemInfo("Framework", "Selenium + TestNG + POM + PageFactory");

		return extent;
	}

}
