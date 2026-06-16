package com.automation.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.qa.base.BaseTest;
import com.automation.qa.pages.LoginPage;
import com.automation.qa.utils.ConfigReader;

public class LoginTest extends BaseTest {

	@Test
	public void testValidLogin() {
		LoginPage loginPage = new LoginPage(driver);

		String username = ConfigReader.getProperty("username");
		String password = ConfigReader.getProperty("password");

		loginPage.login(username, password);

		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("inventory"), "Login failed! URL does not contain inventory.");

		System.out.println("Login test PASSED! URL: " + currentUrl);
	}

	@Test
	public void testInvalidLogin() {
		LoginPage loginPage = new LoginPage(driver);

		loginPage.login("locked_out_user", "secret_sauce");

		String errorMessage = loginPage.getErrorMessage();

		Assert.assertTrue(errorMessage.contains("locked out"), "Error message not displayed correctly!");

		System.out.println("Invalid login test PASSED! Error: " + errorMessage);
	}

}
