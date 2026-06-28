package com.automation.qa.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.qa.base.BaseTest;
import com.automation.qa.utils.AlertUtils;

public class AlertTest extends BaseTest {

//	Override URL for this test class
//	demoqa.com has real alerts unlike SauceDemo
	private static final String ALERT_URL = "https://demoqa.com/alerts";

	@Test
	public void testSimpleAlert() {

		/*
		 * AlertTest needs demoqa.com instead. Notice I call driver.get(ALERT_URL) at
		 * the start of each test — this overrides the SauceDemo URL that BaseTest
		 * loads. This is a pattern called test-level URL override — BaseTest sets up
		 * the driver, individual test navigates to its own URL. Clean and flexible.
		 */
		driver.get(ALERT_URL);

//		Click the button that triggers simple alert
		driver.findElement(By.id("alertButton")).click();

//		Get alert text before accepting
		String alertText = AlertUtils.getAlertText(driver, 5);
		System.out.println("Alert text: " + alertText);

//		accept the alert
		AlertUtils.acceptAlert(driver, 5);

//		verify we're back on page
		Assert.assertTrue(driver.getCurrentUrl().contains("demoqa"), "Should be back on demoqa after accepting alert");

		System.out.println("Simple alert test PASSED!");
	}

	@Test
	public void testConfirmAlert_Accept() {
		driver.get(ALERT_URL);

//		Click confirm button
		driver.findElement(By.id("confirmButton")).click();

//		get text then accept
		String alertText = AlertUtils.getAlertText(driver, 5);
		System.out.println("Confirm alert text: " + alertText);

		AlertUtils.handleConfirmAlert(driver, true, 5);

//		Verify result text shows "Ok"
		String result = driver.findElement(By.id("confirmResult")).getText();
		Assert.assertTrue(result.contains("Ok"), "Result should show Ok after accepting");

		System.out.println("Confirm accept text PASSED!");
	}

	@Test
	public void testConfirmAlert_Dismiss() {
		driver.get(ALERT_URL);

//		Click confirm button
		driver.findElement(By.id("confirmButton")).click();

//		get text then accept
		String alertText = AlertUtils.getAlertText(driver, 5);
		System.out.println("Confirm alert text: " + alertText);

		AlertUtils.handleConfirmAlert(driver, false, 5);

//		Verify result text shows "Cancel"
		String result = driver.findElement(By.id("confirmResult")).getText();
		Assert.assertTrue(result.contains("Cancel"), "Result should show Cancel after accepting");

		System.out.println("Confirm dismiss text PASSED!");
	}

	@Test
	public void testPromptAlert() {
		driver.get(ALERT_URL);

//		Click prompt button
		driver.findElement(By.id("promtButton")).click();

//		send text to prompt and accept
		AlertUtils.sendTextToAlert(driver, "Harika", 5);

//		Verify the entered text appears in result
		String result = driver.findElement(By.id("promptResult")).getText();
		Assert.assertTrue(result.contains("Harika"), "Result should contain the entered name");

		System.out.println("Prompt alert test PASSED! Result: " + result);

	}

}
