package com.test.stepdefs;

import com.test.pages.CarrinhoPage;
import io.cucumber.java.pt.*;
import static com.test.utils.ConfigReader.getProperty;

public class LoginSteps extends BaseSteps {

    CarrinhoPage carrinhoPage;

    @Dado("que o usuário está logado no marketplace")
    public void acessarMarketplace() {
        carrinhoPage = new CarrinhoPage(driver, wait);
        String url = getProperty("url");
        carrinhoPage.acessarMarketplace(url);
    }

    @E("o usuário está na página de um produto")
    public void acessarProduto() {
        carrinhoPage.acessarPrimeiroProduto();
    }

    @Quando("o usuário clica no botão adicionar ao carrinho")
    public void adicionarProduto() {
        carrinhoPage.clicarAdicionarAoCarrinho();
    }

    @Então("o produto é adicionado ao carrinho")
    public void verificarProdutoAdicionado() {
        carrinhoPage.verificarProdutoAdicionado();
    }

    @E("o usuário vê uma mensagem de confirmação {string}")
    public void exibirMensagem(String confirmationMessage) {
        carrinhoPage.validarMensagemConfirmacao(confirmationMessage);
    }

    @Dado("que o usuário está na página do carrinho")
    public void acessarCarrinho() {
        carrinhoPage.acessarCarrinho();
    }

    @Quando("o usuário clica no botão {string} ao lado do produto")
    public void clicarRemover(String buttonText) {
        carrinhoPage.clicarRemoverProduto(buttonText);
    }

    @Então("o produto é removido do carrinho")
    public void confirmarRemocao() {
        carrinhoPage.confirmarRemocao();
    }

    @E("o usuário vê a mensagem de confirmação {string}")
    public void mensagemCarrinhoVazio(String msg) {
        carrinhoPage.validarMensagemCarrinhoVazio(msg);
    }
}
