package com.test.stepdefs;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps extends BaseSteps {

    @Dado("que o usuário está logado no marketplace")
    public void theUserIsOnAProductPage() {
        initializeDriver();
        driver.get("https://www.kabum.com.br");
    }

    @E("o usuário está na página de um produto")
    public void acessarProduto() {
        WebElement productImage = driver.findElement(By.xpath("(//img[@width='182'])[1]"));
        scrollToElement(productImage);
        clickElementWithJS(productImage);
    }

    @Quando("o usuário clica no botão adicionar ao carrinho")
    public void adicionarProduto() {
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@class='sc-50d5e82e-0 qqxnY']"));
        scrollToElement(addToCartButton);
        clickElementWithJS(addToCartButton);
    }

    @Então("o produto é adicionado ao carrinho")
    public void protudoAdicionado() {
        WebElement confirmationMessage = wait.until(driver -> driver.findElement(
            By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/header[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/span[1]")
        ));
        assertTrue(confirmationMessage.getText().contains("1"), "A mensagem de confirmação não contém o texto esperado '1'.");
    }

    @E("o usuário vê uma mensagem de confirmação {string}")
    public void exibirMensagem(String confirmationMessage) {
        String xpath = "//span[contains(., '" + confirmationMessage + "')]";
        WebElement confirmationMessageElement = wait.until(driver -> driver.findElement(By.xpath(xpath)));
        assertTrue(confirmationMessageElement.isDisplayed(), "A mensagem de confirmação não foi exibida.");
        takeScreenshot("mensagem_confirmação");
    }

    @Dado("que o usuário está na página do carrinho")
    public void acessarCarrinho() {
        driver.findElement(By.id("linkCarrinhoHeader")).click();
    }

    @Quando("o usuário clica no botão {string} ao lado do produto")
    public void removerProduto(String buttonText) {
        String xpath = "//span[contains(text(), '" + buttonText + "')]";
        WebElement removeButton = driver.findElement(By.xpath(xpath));
        scrollToElement(removeButton);
        clickElementWithJS(removeButton);
    }

    @Então("o produto é removido do carrinho")
    public void produtoRemovido() {
        WebElement confirmButton = driver.findElement(By.xpath("//button[contains(.,'Sim')]"));
        scrollToElement(confirmButton);
        clickElementWithJS(confirmButton);
    }

    @E("o usuário vê a mensagem de confirmação {string}")
    public void mensagemConfirmação(String msg) {
        String xpath = "//b[contains(text(), '" + msg + "')]";
        WebElement emptyCartMessage = wait.until(driver -> driver.findElement(By.xpath(xpath)));
        assertTrue(emptyCartMessage.getText().contains(msg), "A mensagem não contém o texto esperado: " + msg);
        takeScreenshot("mensagem_carrinho_vazio");
        driver.quit();
    }
}
