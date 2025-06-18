package com.test.stepdefs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseSteps {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    protected void initializeDriver() {
        WebDriverManager.chromedriver().browserVersion("137.0.7151.104").setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("window.scrollBy(0, -100);");
    }

    public static void clickElementWithJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public static void takeScreenshot(String screenshotName) {
        String screenshotDirectory = "./target/screenshots/";
        File directory = new File(screenshotDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String timestamp = new SimpleDateFormat("dd-MM-yyy_HH-mm").format(new Date());
        String finalScreenshotName = screenshotName + "_" + timestamp + ".png";

        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File(screenshotDirectory + finalScreenshotName);

        try {
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot salvo em: " + destination.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
