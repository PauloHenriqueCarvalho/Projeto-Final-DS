/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
public class LoginController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String nextPage = "/WEB-INF/jsp/login2.jsp";
       
       if(Usuario.getIdUsuarioStatic() != 0) {
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

        if (url.equals("/logar")) {
            Usuario user = new Usuario();
            UsuarioDAO valida = new UsuarioDAO();

            user.setEmail(request.getParameter("email"));
            user.setSenha(request.getParameter("senha"));

            try {
                valida.validaUser(user);

                if (Usuario.getAcessoStatic() != 0) {
                    if(Usuario.getAcessoStatic() == 1){
                        int idUsuario = valida.getId(request.getParameter("email"));                   
                        response.sendRedirect(request.getContextPath() + "/inicioAdministrador");
                        return;
                    }  else if(Usuario.getAcessoStatic() == 3){
                        int idUsuario = valida.getId(request.getParameter("email"));                   
                        response.sendRedirect(request.getContextPath() + "/inicio");
                        return;
                    } 
                    
                } else {
                    request.setAttribute("errorMessage", "Usuário ou senha inválidos");
                     response.sendRedirect(request.getContextPath() + "/login");
                }
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Erro ao autenticar usuário");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            processRequest(request, response);
        }
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(Usuario.getIdUsuarioStatic());
        request.setAttribute("usuario", usuario); 
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
