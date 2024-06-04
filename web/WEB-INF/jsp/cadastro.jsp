<%-- 
    Document   : cadastro.jsp
    Created on : 30/04/2024, 14:08:23
    Author     : Senai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- Font Awesome -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <!-- Google Fonts -->
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
            />
        <!-- MDB -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.min.css"
            rel="stylesheet"
            />
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <link rel="stylesheet" href="./styles/cadastro.css">
        <title>Cadastro</title>
    </head>
    <body>
        <section class="vh-100 bg-image"
                 style="background-color: aliceblue;">
            <div class="mask d-flex align-items-center h-100 gradient-custom-3">
                <div class="container h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                            <div class="card" style="border-radius: 15px;">
                                <div class="card-body p-5">
                                    <h2 class="text-uppercase text-center mb-5">Criar Conta</h2>

                                    <form action="cadastrar" method="post">

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

                                        <div class="d-flex justify-content-center">
                                            <button type="submit" class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Cadastrar</button>
                                        </div>

                                        <p class="text-center text-muted mt-5 mb-0">Ja tem conta? <a href="./login" class="fw-bold text-body"><u>Login</u></a></p>

                                    </form>

                                    <!-- jQuery -->
                                    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

                                    <!-- Inputmask -->
                                    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/5.0.6/jquery.inputmask.min.js"></script>


                                    <script>
                                        $(document).ready(function () {
                                            // Máscara para telefone
                                            $('#telefone').inputmask('(99) 99999-9999');

                                            // Máscara para CPF
                                            $('#cpf').inputmask('999.999.999-99');

                                            // Validação do formulário
                                            $('form').submit(function (e) {
                                                var telefone = $('#telefone').val();
                                                var cpf = $('#cpf').val();

                                                // Validar telefone (deve ter pelo menos 14 caracteres, incluindo parênteses e traços)
                                                if (telefone.length < 14) {
                                                    alert('Por favor, insira um número de telefone válido.');
                                                    e.preventDefault(); // Impede o envio do formulário
                                                    return;
                                                }

                                                // Validar CPF (deve ter pelo menos 14 caracteres, incluindo pontos e traços)
                                                if (cpf.length < 14) {
                                                    alert('Por favor, insira um CPF válido.');
                                                    e.preventDefault(); // Impede o envio do formulário
                                                    return;
                                                }
                                            });
                                        }); 
                                    </script>
                                    




                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- MDB -->
        <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.umd.min.js"
        ></script>
    </body>
</html>
