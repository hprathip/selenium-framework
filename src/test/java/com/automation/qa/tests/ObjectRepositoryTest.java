package com.automation.qa.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.qa.base.BaseTest;
import com.automation.qa.utils.ConfigReader;
import com.automation.qa.utils.ObjectRepository;
import com.automation.qa.utils.WaitUtils;

public class ObjectRepositoryTest extends BaseTest {

	@Test
	public void testLoginUsingObjectRepository() {

//		Using Object Repository instead of @FindBy
		WebElement usernameField = driver.findElement(ObjectRepository.getLocator("login.username"));

		WebElement passwordField = driver.findElement(ObjectRepository.getLocator("login.password"));

		WebElement loginButton = driver.findElement(ObjectRepository.getLocator("login.button"));

//		Interact using locators from properties file
		WaitUtils.waitForVisibility(driver, ObjectRepository.getLocator("login.username"), 10);

		usernameField.sendKeys(ConfigReader.getProperty("username"));

		passwordField.sendKeys(ConfigReader.getProperty("password"));

		loginButton.click();

//		Verify login success
		WaitUtils.waitForUrlToContain(driver, "inventory", 10);

		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login via Object Repository failed");

		log.info("Object Repository test PASSED");
	}

}
