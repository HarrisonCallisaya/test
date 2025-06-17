package com.test.stepdefs;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class BaseSteps {

    protected WebDriver driver;
    protected WebDriverWait wait;


    protected void initializeDriver() {
        WebDriverManager.chromedriver().browserVersion("137.0.7151.104").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Função para rolar até o elemento
    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("window.scrollBy(0, -100);");
    }

    // Função para clicar usando JavascriptExecutor
    protected void clickElementWithJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    // Método para capturar a screenshot
    protected void takeScreenshot(String screenshotName) {
        // Caminho onde as screenshots serão salvas
        String screenshotDirectory = "./target/screenshots/";

        // Verifica se o diretório existe, se não, cria o diretório
        File directory = new File(screenshotDirectory);
        if (!directory.exists()) {
            directory.mkdirs();  // Cria o diretório, se necessário
        }

        // Captura a screenshot
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File source = screenshot.getScreenshotAs(OutputType.FILE);

        // Define o destino da imagem
        File destination = new File(screenshotDirectory + screenshotName + ".png");

        try {
            // Salva a imagem no diretório
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot salva em: " + destination.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
