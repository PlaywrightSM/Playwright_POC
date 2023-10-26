package com.testrunner;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/PlayWrightDemoMvc.feature",
        glue = {"stepdef"},
        plugin = {"pretty", "html:target/cucumber-html-report", "pretty:target/cucumber-report.json",
                "junit:test-output/cucumber.xml"})
public class TestRunner {

}

