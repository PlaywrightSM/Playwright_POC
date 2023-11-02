package com.testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/SauceDemo.feature",
        glue = "stepdef", // Change to your step definitions package
        plugin = {
                "json:target/cucumber-report/cucumber.json", // Specify a JSON report file
                "pretty", // Generate readable console output
                "html:target/cucumber-report/cucumber-html-report", // Specify an HTML report directory
                "junit:target/cucumber-report/cucumber.xml" // Specify a JUnit report file
        }
)
public class CucumberRunner {
    // This block of code generates the HTML report after all tests have run
    @AfterClass
    public static void generateReport() {
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-report/cucumber.json");

        Configuration configuration = new Configuration(new File("target/cucumber-report"), "Your Project Name");
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", "Chrome");
        configuration.addClassifications("Environment", "Test");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }


    // This block of code clears the previous report files before running the tests
    @BeforeClass
    public static void clearReportFiles() {
        File reportDir = new File("target/cucumber-report");
        if (reportDir.exists()) {
            for (File file : reportDir.listFiles()) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }


}
