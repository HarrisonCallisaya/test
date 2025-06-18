package com.test.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.test.stepdefs.BaseSteps;

import java.io.File;
import java.io.IOException;

public class Hooks extends BaseSteps {

    @Before
    public void setUp() {
        initializeDriver();
        System.out.println("=== Navegador iniciado ===");
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File source = screenshot.getScreenshotAs(OutputType.FILE);

                String screenshotDirectory = "./target/screenshots/";
                String screenshotName = scenario.getName().replaceAll(" ", "_") + ".png";
                File destination = new File(screenshotDirectory + screenshotName);
                FileUtils.copyFile(source, destination);

                System.out.println("Screenshot salvo: " + destination.getAbsolutePath());

                byte[] screenshotBytes = FileUtils.readFileToByteArray(destination);
                scenario.attach(screenshotBytes, "image/png", "Screenshot - " + scenario.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
                System.out.println("=== Navegador finalizado ===");
            }
        }
    }
}
