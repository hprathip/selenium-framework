package com.automation.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// prevents violation of DRY (don't repeat yourself) -- no repeated file I/O
// Single point of change -- if file path changes or the file type changes to .yaml, just update in 1 location, not in 20 different places for example
// implements abstraction -- hiding the complexity behind a simple interface

public class ConfigReader {

	private static Properties properties;

//	runs once when the class is first loaded, before anything else. Perfect for loading config one time.
	static {
		try {
			String path = "src/test/resources/config.properties";
			FileInputStream input = new FileInputStream(path);
			properties = new Properties();
			properties.load(input);
		} catch (IOException e) {
			System.out.println("Could not load config.properties: " + e.getMessage());
		}
	}
	
//	called without creating the ConfigReader object

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

}
