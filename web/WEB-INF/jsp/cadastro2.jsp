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
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
            />
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.min.css"
            rel="stylesheet"
            />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="./styles/cadastro.css">
        <link rel="stylesheet" href="css/login.css">
        <title>JSP Page</title>
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

                    <form action="cadastrar" method="post">
                        <c:set var="errorMessage" value="${sessionScope.erroMsg}" />
                        <c:remove var="erroMsg" scope="session" />

                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger" role="alert">
                                ${errorMessage}
                            </div>
                        </c:if>
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

                        <div class="form-check d-flex justify-content-center mb-5">
                            <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3cg" required />
                            <label class="form-check-label" for="form2Example3g">
                                Eu aceito os <a href="#!" class="text-body"><u>Termos de serviço</u></a>
                            </label>
                        </div>

                        <c:if test="${not empty sessionScope.erroSenha}">
                            <p style="color: red">${sessionScope.erroSenha}</p>
                        </c:if>

                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn-login">Cadastrar</button>
                        </div>

                        <p class="text-center text-muted mt-5 mb-0">Ja tem conta? <a href="./login" class="fw-bold text-body"><u>Login</u></a></p>

                    </form>

                    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

                    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/5.0.6/jquery.inputmask.min.js"></script>


                    <script>
                        $(document).ready(function () {
                            $('#telefone').inputmask('(99) 99999-9999');

                            $('#cpf').inputmask('999.999.999-99');
                            $('form').submit(function (e) {
                                var telefone = $('#telefone').val();
                                var cpf = $('#cpf').val();
                                if (telefone.length < 14) {
                                    alert('Por favor, insira um número de telefone válido.');
                                    e.preventDefault();
                                    return;
                                }

                                if (cpf.length < 14) {
                                    alert('Por favor, insira um CPF válido.');
                                    e.preventDefault();
                                    return;
                                }
                            });
                        });
                    </script>


                </div>
            </div>
        </main>
    </body>
    <script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.umd.min.js"
    ></script>
</html>
