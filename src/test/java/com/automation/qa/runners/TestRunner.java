package com.automation.qa.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "com.automation.qa.stepdefinitions" }, plugin = {
		"pretty", "html:target/cucumber-reports/cucumber.html",
		"json:target/cucumber-reports/cucumber.json" }, tags = "@smoke or @regression" , monochrome = true)

public class TestRunner {

//	Empty -- Cucumber uses this class as entry point

}
