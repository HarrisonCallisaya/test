Feature: Efetuar uma compra e remover do carrinho

  Scenario: Produto adicionado e removido do carrinho com sucesso
    Given que o usuário está logado no marketplace
    And o usuário está na página de um produto
    When o usuário clica no botão adicionar ao carrinho
    Then o produto é adicionado ao carrinho
    And o usuário vê uma mensagem de confirmação "Produto adicionado com sucesso no carrinho"
    Given que o usuário está na página do carrinho
    When o usuário clica no botão "Remover" ao lado do produto
    Then o produto é removido do carrinho
    And o usuário vê a mensagem de confirmação "O seu carrinho está vazio."

