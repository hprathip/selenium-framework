package com.automation.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;

//	Private locators - encapsulation in action
	private By usernameField = By.id("user-name");
	private By passwordField = By.id("password");
	private By loginButton = By.id("login-button");
	private By errorMessage = By.cssSelector("[data-test='error']");

//	Constructor - receives driver from the test
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
//	Public action methods - this is what tests call
	public void enterUsername(String username) {
		driver.findElement(usernameField).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}
	
	public void clickLogin() {
		driver.findElement(loginButton).click();
	}
	
	public String getErrorMessage() {
		return driver.findElement(errorMessage).getText();
	}
	
//	Combines 3 actions into 1 reusable method
	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

}
