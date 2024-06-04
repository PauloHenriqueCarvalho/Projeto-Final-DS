document.addEventListener("DOMContentLoaded", function() {
    const carrinho = document.getElementById("carrinho");
    const btnAbrir = document.getElementById("btn-abrir-carrinho");
    const btnFechar = document.getElementById("fechar-carrinho");

    btnAbrir.addEventListener("click", function() {
        carrinho.classList.add("ativo");
    });

    btnFechar.addEventListener("click", function() {
        carrinho.classList.remove("ativo");
    });
});