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
import model.bean.Carrinho;
import model.bean.Produto;
import model.dao.ProdutoDAO;

/**
 *
 * @author paulo
 */
public class HomeClienteController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String url = "/WEB-INF/jsp/homeCliente.jsp";

        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> produtos = dao.listarTodos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(produtos.get(i).getImagemBytes());
                produtos.get(i).setImagemBase64(imagemBase64);
            }

        }
        request.setAttribute("produtos", produtos);

        RequestDispatcher d = getServletContext().getRequestDispatcher(url);
        d.forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath();

        if (url.equals("/busca")) {
            String termo = request.getParameter("termo");
            termo = "%" + termo + "%";

            ProdutoDAO dao = new ProdutoDAO();
            List<Produto> produtos = dao.busca(termo);
            for (int i = 0; i < produtos.size(); i++) {
                if (produtos.get(i).getImagemBytes() != null) {
                    String imagemBase64 = Base64.getEncoder().encodeToString(produtos.get(i).getImagemBytes());
                    produtos.get(i).setImagemBase64(imagemBase64);
                }

            }

            request.setAttribute("produtos", produtos);

            // Redirecione de volta para a página principal
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/homeCliente.jsp");
            dispatcher.forward(request, response);
        } else {
            processRequest(request, response);
            System.out.println("Else");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
