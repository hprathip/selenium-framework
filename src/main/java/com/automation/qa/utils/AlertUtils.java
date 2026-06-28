package com.automation.qa.utils;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertUtils {

//	Wait for alert and accept it (click OK)
	public static void acceptAlert(WebDriver driver, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

//	Wait for alert and dismiss it (click Cancel)
	public static void dismissAlert(WebDriver driver, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().dismiss();
	}

//	Get alert text
	public static String getAlertText(WebDriver driver, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		wait.until(ExpectedConditions.alertIsPresent());
		return driver.switchTo().alert().getText();
	}

//	Send text to prompt alert
	public static void sendTextToAlert(WebDriver driver, String text, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(text);
		alert.accept();
	}

//	Handle confirm alert - true = accept, false = dismiss
	public static void handleConfirmAlert(WebDriver driver, boolean accept, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		wait.until(ExpectedConditions.alertIsPresent());

		Alert alert = driver.switchTo().alert();
		if (accept) {
			alert.accept();
		} else {
			alert.dismiss();
		}
	}

}
