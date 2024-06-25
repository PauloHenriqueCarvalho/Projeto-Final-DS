<%-- 
    Document   : login2
    Created on : 31/05/2024, 21:33:41
    Author     : paulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.min.css"
            rel="stylesheet"
            />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
         <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />

        <link rel="stylesheet" href="./styles/cadastro.css">
        <link rel="stylesheet" href="css/login.css">
        <title>Cadastro</title>

        <script src="http://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="http://jqueryvalidation.org/files/dist/jquery.validate.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.js"></script>

    </head>
    <body>

        <main>
            <div class="container-left">
                <div class="sair">
                    <a href="./login"><p><i class="fa-solid fa-arrow-left"></i></p></a>
                </div>
                <img src="./img/LogoEvelinVerissimoLogin.png" alt="">
                <div class="sair">

                </div>
            </div>
            <div class="container-right">

                <div class="inputs">
                    <h2 class="text-uppercase text-center mb-5">Criar Conta</h2>

                    <form action="cadastrar "method="post" >


                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="text" id="nome" name="nome" class="form-control form-control-lg" required />
                            <label class="form-label" for="nome">Seu Nome</label>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="email" id="email" name="email" class="form-control form-control-lg" required />
                            <label class="form-label" for="email">Seu Email</label>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="password" id="senha" name="senha" class="form-control form-control-lg" required />
                            <label class="form-label" for="senha">Senha</label>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="password" id="confirmarSenha" name="confirmarSenha" class="form-control form-control-lg" required />
                            <label class="form-label" for="confirmarSenha">Confirmar Senha</label>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="tel" id="telefone" name="telefone" class="form-control form-control-lg" required />
                            <label class="form-label" for="telefone">Telefone</label>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="text" id="cpf" name="cpf" class="form-control form-control-lg" required />
                            <label class="form-label" for="cpf">CPF</label>
                        </div>



                        <c:if test="${not empty errorMessage}">
 
                            <p style="color: red;">${errorMessage}</p>
                
                        </c:if>

                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn-login">Cadastrar</button>
                        </div>

                        <p class="text-center text-muted mt-5 mb-0">Ja tem conta? <a href="./login" class="fw-bold text-body"><u>Login</u></a></p>

                    </form>




                </div>
            </div>
        </main>
    </body>
    <script src="js/validacoes.js" type="text/javascript"></script>
    <script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.umd.min.js"
    ></script>
</html>
