package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.test.stepdefs.BaseSteps.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarrinhoPage {

    WebDriver driver;
    WebDriverWait wait;

    public CarrinhoPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void acessarMarketplace(String url) {
        driver.get(url);
    }

    public void acessarPrimeiroProduto() {
        WebElement productImage = driver.findElement(By.xpath("(//img[@width='182'])[1]"));
        scrollToElement(productImage);
        clickElementWithJS(productImage);
    }

    public void clicarAdicionarAoCarrinho() {
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@class='sc-50d5e82e-0 qqxnY']"));
        scrollToElement(addToCartButton);
        clickElementWithJS(addToCartButton);
    }

    public void verificarProdutoAdicionado() {
        WebElement confirmationMessage = wait.until(driver -> driver.findElement(
                By.xpath("//span[contains(.,'Produto adicionado com sucesso no carrinho')]")));
        assertTrue(confirmationMessage.isDisplayed(), "Produto não foi adicionado ao carrinho.");
    }

    public void validarMensagemConfirmacao(String confirmationMessage) {
        String xpath = "//span[contains(., '" + confirmationMessage + "')]";
        WebElement message = wait.until(driver -> driver.findElement(By.xpath(xpath)));
        assertTrue(message.isDisplayed(), "Mensagem não exibida.");
        takeScreenshot("mensagem_confirmacao");
    }

    public void acessarCarrinho() {
        driver.findElement(By.id("linkCarrinhoHeader")).click();
    }

    public void clicarRemoverProduto(String buttonText) {
        String xpath = "//span[contains(text(), '" + buttonText + "')]";
        WebElement removeButton = driver.findElement(By.xpath(xpath));
        scrollToElement(removeButton);
        clickElementWithJS(removeButton);
    }

    public void confirmarRemocao() {
        WebElement confirmButton = driver.findElement(By.xpath("//button[contains(.,'Sim')]"));
        scrollToElement(confirmButton);
        clickElementWithJS(confirmButton);
    }

    public void validarMensagemCarrinhoVazio(String msg) {
        String xpath = "//*[contains(text(), '" + msg + "')]";
        WebElement emptyCartMessage = wait.until(driver -> driver.findElement(By.xpath(xpath)));
        assertTrue(emptyCartMessage.isDisplayed(), "A mensagem de carrinho vazio não foi exibida.");
        takeScreenshot("carrinho_vazio");
    }
}
