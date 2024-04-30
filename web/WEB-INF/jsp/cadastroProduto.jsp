<%-- 
    Document   : cadastroProduto
    Created on : 30/04/2024, 17:02:04
    Author     : Senai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />

        <link rel="stylesheet" type="text/css" href="//assets.locaweb.com.br/locastyle/edge/stylesheets/locastyle.css">


        <link rel="stylesheet" href="./styles/index.css">
        <link rel="stylesheet" href="./styles/header.css" />
        <link rel="stylesheet" href="./styles/produtos.css" />


        <script src="https://kit.fontawesome.com/0ba8cb147b.js" crossorigin="anonymous"></script>
        <title>Cadastro de Produto</title>
    </head>
    <body>
        <div class="promocao">
            <h3>
                UTILIZE O CUPOM: <b>BEMVINDO10</b> E GANHE 10% NA SUA PRIMEIRA COMPRA
                <span>(EXCETO PRODUTOS PROMOCIONAIS)</span>
            </h3>
        </div>
        <header>
            <div class="logo">
                <a href="./inicio">
                    <h1>FanFut Store</h1>
                </a>
            </div>

            <div class="carrinho">

                <div class="ls-cart">
                    <input type="checkbox" class="ls-checkbox-cart">
                    <header class="ls-cart-header">
                        <strong id="qtd"><i class="fa-solid fa-cart-shopping"></i></strong>
                    </header>
                    <div class="ls-cart-content">
                        <ul class="ls-no-list-style">
                            <li>
                                <h2 class="ls-title-product">
                                    <strong>01</strong> Email Marketing Locaweb
                                </h2>
                                <p> (Identificador) </p>
                                <a href="">Excluir</a>
                            </li>
                            <li>
                                <h2 class="ls-title-product">
                                    <strong>02</strong> Monitor LED 27"
                                </h2>
                                <p> (Identificador) </p>
                                <a href="">Excluir</a>
                            </li>
                            <li>
                                <h2 class="ls-title-product">
                                    <strong>06</strong> Honda FIT 2010 4p Flex 1.4 16v
                                </h2>
                                <p> (Identificador) </p>
                                <a href="">Excluir</a>
                            </li>
                            <li>
                                <h2 class="ls-title-product">
                                    <strong>01</strong> Lorem Ipsum is simply dummy text
                                </h2>
                                <p> (Identificador) </p>
                                <a href="">Excluir</a>
                            </li>
                        </ul>
                        <footer class="ls-cart-footer ls-txt-center">
                            <a href="" class="ls-btn-primary">Finalizar Compra</a>
                        </footer>
                    </div>
                </div>

            </div>
            <div class="search">
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="O que você procura?" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit"><i id="search-icon"
                                                                             class="fa-solid fa-magnifying-glass"></i></button>
                </form>
            </div>

            <div class="icons">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                        <i class="fa-solid fa-user"></i>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="./login">Login</a></li>
                        <li><a class="dropdown-item" href="./cadastroUsuario">Cadastre-se</a></li>
                        <li><a class="dropdown-item" href="#">Meus Pedidos</a></li>


                    </ul>
                </div>
            </div>
        </header>

        <form action="cadastroProduto" method="post" name="frmCadastro" enctype="multipart/form-data">
            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" required>
            
            <label for="nome">Descricao</label>
            <input type="text" id="descricao" name="descricao" required>
            
            <label for="nome">Unidade Medida</label>
            <input type="text" id="unidade" name="unidade" required>
           
            
            <label for="preco">Preço</label>
            <input type="number" id="preco" name="preco" step="0.01" required>

            <label for="imagem">Imagem</label>
            <input type="file" id="imagem" name="imagem" required accept="image/*">

            <label for="desconto">Desconto</label>
            <input type="number" id="desconto" name="desconto" step="0.01">

            <label for="categoria">Categoria</label>
            <input type="text" id="categoria" name="categoria">

            <label for="subcategoria">Subcategoria</label>
            <input type="text" id="subcategoria" name="subcategoria">

            <label for="clube">Clube</label>
            <input type="text" id="clube" name="clube">

            <div class="comprar">         
                <button id="btn" type="submit" class="btn btn-success">Cadastrar</button>
            </div>
        </form>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
    </body>
</html>
