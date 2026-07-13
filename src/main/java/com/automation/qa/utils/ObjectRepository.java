package com.automation.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectRepository {

	private static Properties props = new Properties();

//	Load properties file once when class loads
	static {
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/objectrepository.properties");
			props.load(fis);
			System.out.println("Object Repostiory loaded successfully");
		} catch (IOException e) {
			System.err.println("Failed to load Object Repository: " + e.getMessage());
		}
	}

//	Parse locator string and return By object
	public static By getLocator(String key) {
		String value = props.getProperty(key);

		if (value == null)
			throw new IllegalArgumentException("Locator not found in Object Repository: " + key);

//		Split "type:value" -> ["id", "user-name"]
		String[] parts = value.split(":", 2);
		String type = parts[0].trim().toLowerCase();
		String locatorValue = parts[1].trim();

		switch (type) {
		case "id":
			return By.id(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		case "css":
			return By.xpath(locatorValue);
		case "name":
			return By.name(locatorValue);
		case "classname":
			return By.className(locatorValue);
		case "linktext":
			return By.linkText(locatorValue);
		case "partiallinktext":
			return By.partialLinkText(locatorValue);
		case "tagname":
			return By.tagName(locatorValue);
		default:
			throw new IllegalArgumentException("Unsupported locator type: " + type);

		}

	}

//	Convenience method --get locator value as String
	public static String getLocatorValue(String key) {
		String value = props.getProperty(key);
		if (value == null)
			throw new IllegalArgumentException("Key not found: " + key);
		return value.split(":", 2)[1].trim();
	}

}
