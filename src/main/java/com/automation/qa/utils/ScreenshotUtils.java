package com.automation.qa.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

//		Screenshot folder path
	private static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/screenshots";

//		Capture screenshot - returns file path for ExtentReports
	public static String captureScreenshot(WebDriver driver, String testName) {

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		String fileName = testName + "_" + timeStamp + ".png";
		String filePath = SCREENSHOT_PATH + fileName;

		try {
//				Take screenshot
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // driver implements TakesScreenshot interface and OutputType.FILE - -returns screenshot as a temporary File object

//				Create screenshots folder if it doesn't exist
			File destFile = new File(filePath);
			destFile.getParentFile().mkdirs();

//				Copy to destination using Commons IO
			FileUtils.copyFile(srcFile, destFile);

			System.out.println("Screenshot saved: " + filePath);

			return filePath;

		} catch (IOException e) {
			System.err.println("Screenshot failed: " + e.getMessage());

			return null;
		}
	}

	public static void main(String[] args) {
	}

}
