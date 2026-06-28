package com.automation.qa.utils;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

//	ExplicitWait - wait for element to be visible
	public static WebElement waitForVisibility(WebDriver driver, By locator, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

//	ExplicitWait - wait for element to be clickable
	public static WebElement waitForClickable(WebDriver driver, By locator, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

//	ExplicitWait - wait for element to contain text
	public static boolean waitForText(WebDriver driver, By locator, String text, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}

//	FluentWait - custom polling + ignore exceptions
	public static WebElement fluentWait(WebDriver driver, By locator, int timeoutSeconds, int pollingSeconds) {
		FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeoutSeconds))
				.pollingEvery(Duration.ofSeconds(pollingSeconds)).ignoring(NoSuchElementException.class);

		return wait.until(d -> d.findElement(locator));
	}

//	Wait for page title to contain text
	public static boolean waitForTitle(WebDriver driver, String title, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		return wait.until(ExpectedConditions.titleContains(title));
	}

}
