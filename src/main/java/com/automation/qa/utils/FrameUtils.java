package com.automation.qa.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrameUtils {

//	Switch to frame by index
	public static void switchToFrameByIndex(WebDriver driver, int index, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

//	Switch to frame by name or ID
	public static void switchToFrameByName(WebDriver driver, String nameOrId, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
	}

//	Switch to frame by WebElement
	public static void switchToFrameByElement(WebDriver driver, By locator, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		WebElement frameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		driver.switchTo().frame(frameElement);
	}

//	Switch back to main page from any frame
	public static void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

//	Switch to parent frame (one level up)
	public static void switchToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}

}
