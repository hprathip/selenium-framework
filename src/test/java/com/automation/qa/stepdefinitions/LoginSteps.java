package com.automation.qa.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automation.qa.base.DriverFactory;
import com.automation.qa.pages.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	WebDriver driver;
	LoginPage loginPage;

	@Given("I am on the SauceDemo login page")
	public void iAmOnLoginPage() {
		driver = DriverFactory.getDriver();
		loginPage = new LoginPage(driver);
	}

	@When("I enter username {string} and password {string}")
	public void iEnterCredentials(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@And("I click the login button")
	public void iClickLoginButton() {
		loginPage.clickLogin();
	}

	@Then("I should be on the products page")
	public void iShouldBeOnProductsPage() {
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed - not on products page");
	}

	@Then("I should see error message {string}")
	public void iShouldSeeErrorMessage(String expectedError) {
		String actualError = loginPage.getErrorMessage();
		Assert.assertEquals(actualError, expectedError, "Error message mismatch");
	}

	@Then("I should see {string}")
	public void iShouldSee(String expectedText) {
		String currentUrl = driver.getCurrentUrl();
		String errorMsg = "";

		try {
			errorMsg = loginPage.getErrorMessage();
		} catch (Exception e) {
//			No error message - check URL instead
		}

		boolean passed = currentUrl.contains("inventory") || errorMsg.contains(expectedText)
				|| currentUrl.contains(expectedText);

		Assert.assertTrue(passed, "Expected to see: " + expectedText);

	}

}
