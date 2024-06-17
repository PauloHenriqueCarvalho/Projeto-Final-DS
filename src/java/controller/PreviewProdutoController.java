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
import model.bean.Produto;
import model.bean.ProdutoImagem;
import model.bean.Projeto;
import model.bean.Sabor;
import model.dao.ProdutoDAO;
import model.dao.ProdutoImagemDAO;
import model.dao.SaborDAO;

/**
 *
 * @author paulo
 */
public class PreviewProdutoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = "/WEB-INF/jsp/previewProdutoAdministrador.jsp";
        ProdutoDAO dao = new ProdutoDAO();
        Produto produto = dao.readById(Projeto.getIdProdutoAtual());
        request.setAttribute("produto", produto);

        SaborDAO daoS = new SaborDAO();
        List<Sabor> sabores = daoS.listarTiposProduto(Projeto.getIdProdutoAtual());
        request.setAttribute("sabores", sabores);
        ProdutoImagemDAO pmd = new ProdutoImagemDAO();
        List<ProdutoImagem> imagensProdutos = pmd.listarImagem(Projeto.getIdProdutoAtual());
        for (ProdutoImagem c : imagensProdutos) {

            if (c.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(c.getImagemBytes());
                c.setImagemBase64(imagemBase64);

            }
        }
        request.setAttribute("imagensProdutos", imagensProdutos);

        List<Sabor> saboresEspecificos = daoS.listarTodosEspecificos();
        request.setAttribute("saboresEspecificos", saboresEspecificos);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

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
        String url = request.getServletPath();
        System.out.println("Chamado URL: " + url);

        if (url.equals("/excluir-especifico")) {
            SaborDAO saborDAO = new SaborDAO();
            saborDAO.delete(Integer.parseInt(request.getParameter("idSabor")));
            response.sendRedirect("./previewProduto");
        } else {
            processRequest(request, response);
        }

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
