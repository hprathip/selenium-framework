package com.automation.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

	private WebDriver driver;

//	Locators
	private By cartItems = By.className("cart_item");
	private By cartItemNames = By.className("inventory_item_name");
	private By removeButtons = By.className("cart_button");
	private By checkoutButton = By.className("checkout");
	private By continueShoppingButton = By.id("continue-shopping");
	private By cartQuantity = By.className("cart_quantity");

//	Constructor
	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

//	Get all the item names in cart
	public List<String> getCartItemNames() {

		List<WebElement> itemElements = driver.findElements(cartItemNames);
		List<String> names = new ArrayList<String>();

		for (WebElement element : itemElements) {
			names.add(element.getText());
		}

		return names;

	}

//	Get total number of items in cart
	public int getCartItemCount() {
		return driver.findElements(cartItems).size();
	}

//	Check if specific item is in cart
	public boolean isItemInCart(String itemName) {
		List<String> items = getCartItemNames();
		return items.contains(itemName);
	}

//	Click checkout button
	public void clickCheckout() {
		driver.findElement(checkoutButton).click();
	}

//	Click continue shopping
	public void clickContinueShopping() {
		driver.findElement(continueShoppingButton).click();
	}

//	Remove the first item from the cart
	public void removeFirstItem() {
		List<WebElement> buttons = driver.findElements(removeButtons);

		if (buttons.size() > 0) {
			buttons.get(0).click();
		}
	}

//	Check if cart page is loaded
	public boolean isPageLoaded() {
		return driver.getCurrentUrl().contains("cart");
	}

}
