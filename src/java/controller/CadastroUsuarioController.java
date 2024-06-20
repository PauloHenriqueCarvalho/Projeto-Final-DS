/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Usuario;
import model.dao.UsuarioDAO;

/**
 *
 * @author Senai
 */
public class CadastroUsuarioController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = "/WEB-INF/jsp/cadastro2.jsp";

        if (Usuario.getIdUsuarioStatic() != 0) {
            UsuarioDAO u = new UsuarioDAO();
            List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());
            request.setAttribute("usuario", usuarios);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        if (url.equals("./cadastrar")) {
            String nextPage = "/WEB-INF/jsp/cadastro.jsp";
            UsuarioDAO dao = new UsuarioDAO();

            String errorMessage = "";
            System.out.println("Enrae");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String confirmarSenha = request.getParameter("confirmarSenha");
            String telefone = request.getParameter("telefone");
            String cpf = request.getParameter("cpf");
            

            if (nome == null || nome.trim().isEmpty()
                    || email == null || email.trim().isEmpty()
                    || senha == null || senha.trim().isEmpty()
                    || confirmarSenha == null || confirmarSenha.trim().isEmpty()
                    || telefone == null || telefone.trim().isEmpty()
                    || cpf == null || cpf.trim().isEmpty()) {
                errorMessage = "Todos os campos são obrigatórios.";
               request.getSession().setAttribute("erroSenha", errorMessage);

                response.sendRedirect("./cadastroUsuario");
            } else {
                if (senha.equals(confirmarSenha)) {
                    telefone = telefone.replaceAll("[^0-9]", "");
                    cpf = cpf.replaceAll("[^0-9]", "");

                    Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    usuario.setTelefone(telefone);
                    usuario.setCpf(cpf);

                    String cadastro = dao.insertCliente(usuario);
                    System.out.println("Cadastr,.: " + cadastro);
                    if(cadastro.equals("sucesso")){
                        request.setAttribute("successMessage", "Cadastro realizado com sucesso!");
                        request.getSession().removeAttribute("erroMsg");
                        response.sendRedirect(request.getContextPath() + "/login");
                    } else {
                        request.getSession().setAttribute("erroMsg", cadastro);
                        response.sendRedirect("./cadastroUsuario");
                    }
                    
                }
                errorMessage = "As senhas devem ser iguais!";
               
            }

            
        } else {
            processRequest(request, response);
        }

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
