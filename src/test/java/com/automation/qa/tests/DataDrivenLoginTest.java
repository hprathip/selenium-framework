package com.automation.qa.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.qa.base.BaseTest;
import com.automation.qa.pages.LoginPage;

public class DataDrivenLoginTest extends BaseTest {

//	DataProvider  - supplies multiple data sets
//	Each row = one test execution
	@DataProvider(name = "loginData")
	public Object[][] getLoginData() {
		return new Object[][] {
//			{username, password, expectedResult, testDescription}
				{ "standard_user", "secret_sauce", true, "Valid login should succeed" },
				{ "locked_out_user", "secret_sauce", false, "Locked user should fail" },
				{ "invalid_user", "wrong_password", false, "Invalid credentials should fail" },
				{ "standard_user", "wrong_password", false, "Wrong password should fail" }, };
	}

	@Test(dataProvider = "loginData")
	public void testLoginWithMultipleUsers(String username, String password, boolean shouldSucceed,
			String description) {
		System.out.println("Testing: " + description);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		if (shouldSucceed) {
//			Valid login - should reach inventory page
			Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login should succeed for: " + username);
			System.out.println("PASSED - " + description);
		} else {
//			Invalid login - should show error
			String error = loginPage.getErrorMessage();
			Assert.assertTrue(error.length() > 0, "Error message should show for: " + username);
			System.out.println("PASSED - " + description + " | Error: " + error);
		}

	}

}
