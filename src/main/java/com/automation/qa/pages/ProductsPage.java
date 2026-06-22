package com.automation.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {

	private WebDriver driver;

//	Locators
	private By pageTitle = By.className("title");
	private By productNames = By.className("inventory_item_name");
	private By addToCartButtons = By.className("btn_inventory");
	private By cartIcon = By.className("shopping_cart_link");
	private By cartBadge = By.className("shopping_cart_badge");

//	Constructor
	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

//	Get page title text
	public String getPageTitle() {
		return driver.findElement(pageTitle).getText();
	}

//	Get all product names as a List of Strings
//	THIS is where Collections comes in - List<WebElement>
	public List<String> getAllProductNames() {
		List<WebElement> productElements = driver.findElements(productNames);

		List<String> names = new ArrayList<String>();
		for (WebElement element : productElements) {
			names.add(element.getText());
		}
		return names;

	}

//	Get total number of products displayed
	public int getProductCount() {
		return driver.findElements(productNames).size();
	}

//	Add first product to cart
	public void addFirstProductToCart() {
		List<WebElement> buttons = driver.findElements(addToCartButtons);
		buttons.get(0).click();
	}

//	Add product by name
	public void addProductToCartByName(String productName) {
		List<WebElement> products = driver.findElements(productNames);
		for (WebElement product : products) {
			if (product.getText().equals(productName)) {
				product.click();
				break;
			}
		}

	}

//	Get cart badge count
	public String getCartCount() {
		return driver.findElement(cartBadge).getText();
	}

//	Click cart icon to go to cart
	public void goToCart() {
		driver.findElement(cartIcon).click();
	}

//	Check if products page is loaded
	public boolean isPageLoaded() {
		return driver.findElement(pageTitle).getText().equals("Products");
	}

}
