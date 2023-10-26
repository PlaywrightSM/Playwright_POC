package com.testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/SauceDemo.feature",
        glue = {"stepdef"},
        plugin = {"pretty", "html:target/cucumber-html-report", "pretty:target/cucumber-report.json",
                "junit:test-output/cucumber.xml"})
public class TestRunner3 {

}

