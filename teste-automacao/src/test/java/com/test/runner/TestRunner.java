package com.test.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources", // Diretório onde está o arquivo .feature
    glue = "com.test.stepdefs", // Pacote onde estão os Step Definitions
    plugin = {"pretty", "html:target/cucumber-report.html"} // Relatório em HTML
)
public class TestRunner {
}
