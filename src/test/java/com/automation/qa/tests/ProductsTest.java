package com.automation.qa.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.qa.base.BaseTest;
import com.automation.qa.pages.CartPage;
import com.automation.qa.pages.LoginPage;
import com.automation.qa.pages.ProductsPage;
import com.automation.qa.utils.ConfigReader;

public class ProductsTest extends BaseTest {

	@Test
	public void testProductsPageLoaded() {
//		Login first
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

//		Verify products page loaded
		ProductsPage productsPage = new ProductsPage(driver);

		Assert.assertTrue(productsPage.isPageLoaded(), "Products page did not load!");

		System.out.println("Products page title: " + productsPage.getPageTitle());

	}

	@Test
	public void testProductCount() {
//		Login first
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

//		Verify 6 products are displayed
		ProductsPage productsPage = new ProductsPage(driver);
		int count = productsPage.getProductCount();

		Assert.assertEquals(count, 6, "Expected 6 products but found: " + count);

		System.out.println("Product count: " + count);
	}

	@Test
	public void testGetAllProductNames() {
//		Login first
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

//		Get all product names
		ProductsPage productsPage = new ProductsPage(driver);
		List<String> names = productsPage.getAllProductNames();

//		print all names
		System.out.println("All products on page:");
		for (String name : names) {
			System.out.println(" - " + name);
		}

//		Verify list is not empty
		Assert.assertTrue(names.size() > 0, "No products dound on page!");

		Assert.assertTrue(names.contains("Sauce Labs Backpack"), "Sauce Labs Backpack not found!");

	}

	@Test
	public void testAddToCartAndVerify() {
//		Login first
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		ProductsPage productsPage = new ProductsPage(driver);

//		Add first product to cart
		productsPage.addFirstProductToCart();

//		Verify cart badge shows 1
		String cartCount = productsPage.getCartCount();
		Assert.assertEquals(cartCount.trim(), "1", "Cart count should be 1 after adding one item!");

		System.out.println("Cart count after adding: " + cartCount);

//		go to cart
		productsPage.goToCart();

//		Verify on cart page
		CartPage cartPage = new CartPage(driver);
		Assert.assertTrue(cartPage.isPageLoaded(), "Cart Page did not load!");

//		Verify item count in cart
		int itemsInCart = cartPage.getCartItemCount();
		Assert.assertEquals(itemsInCart, 1, "Expected 1 item in cart!");

		System.out.println("Items in cart: " + itemsInCart);

	}

}
