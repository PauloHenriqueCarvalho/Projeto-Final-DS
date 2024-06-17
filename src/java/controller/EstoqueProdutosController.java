/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Categoria;

import model.bean.Produto;
import model.bean.Usuario;
import model.dao.CategoriaDAO;

import model.dao.ProdutoDAO;

/**
 *
 * @author paulo
 */
public class EstoqueProdutosController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = "/WEB-INF/jsp/estoqueProdutos.jsp";

        ProdutoDAO dao = new ProdutoDAO();

        CategoriaDAO cat = new CategoriaDAO();
        List<Categoria> listaCategorias = cat.listarTodos();
        request.setAttribute("categorias", listaCategorias);
        
        

        List<Produto> produto = dao.listarTodos();
        for (int i = 0; i < produto.size(); i++) {
            if (produto.get(i).getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(produto.get(i).getImagemBytes());
                produto.get(i).setImagemBase64(imagemBase64);
            }

        }
        request.setAttribute("produtos", produto);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
//        String url = request.getServletPath();
//        if (url.equals("/adicionarQuantidade")) {
//            int produtoId = Integer.parseInt(request.getParameter("produtoId"));
//            EstoqueDAO dao = new EstoqueDAO();
//            dao.aumentarQuantidade(produtoId);
//            // Redirecionar para a página de produtos ou fazer alguma outra coisa após a operação
//        } else if (url.equals("/removerQuantidade")) {
//            int produtoId = Integer.parseInt(request.getParameter("produtoId"));
//            EstoqueDAO dao = new EstoqueDAO();
//            dao.diminuirQuantidade(produtoId);
//            // Redirecionar para a página de produtos ou fazer alguma outra coisa após a operação
//        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
