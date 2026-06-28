package com.automation.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver driver;

//	Private locators - encapsulation in action

//	@FindBy annonations - PageFactory pattern
//	Cleaner than By objects - initialized once via PageFactory
//	"Lazy initialisation" — elements aren't actually found until they're used. This is faster than finding all elements upfront.

	@FindBy(id = "user-name")
	private WebElement usernameField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	@FindBy(css = "[data-test='error']")
	private WebElement errorMessage;

//	Constructor - PageFactory initializes all @FindBy elements here

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	Public action methods - this is what tests call

	public void enterUsername(String username) {
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void clickLogin() {
		loginButton.click();
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}

//	Combines 3 actions into 1 reusable method
	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

}
