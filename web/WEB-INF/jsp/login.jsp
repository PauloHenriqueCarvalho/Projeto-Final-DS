<%-- 
    Document   : login
    Created on : 30/04/2024, 14:08:07
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
         <link rel="stylesheet" href="./styles/cadastro.css">
        <title>Login</title>
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
                                    <h2 class="text-uppercase text-center mb-5">Login</h2>

                                    <form method="post" action="logar" class="card-body p-5 text-center">

                                        <div data-mdb-input-init class="form-outline mb-4">
                                            <input type="email" id="email"  name="email" class="form-control form-control-lg" />
                                            <label class="form-label" for="form3Example3cg">Seu Email</label>
                                        </div>

                                        <div data-mdb-input-init class="form-outline mb-4">
                                            <input type="password" id="senha" name="senha" class="form-control form-control-lg" />
                                            <label class="form-label" for="form3Example4cg">Senha</label>
                                        </div>
                                        <div class="d-flex justify-content-center">
                                            <button type="submit" class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Cadastrar</button>
                                        </div>

                                        <p class="text-center text-muted mt-5 mb-0">Não tem conta? <a href="./cadastroUsuario"
                                                                                                      class="fw-bold text-body"><u>Cadastrar-se</u></a></p>
                                    </form>

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
