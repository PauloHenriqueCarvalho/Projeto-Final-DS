/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Categoria;
import model.bean.Pedido;
import model.bean.ProdutoCarrinhoSabores;
import model.bean.ProdutoPedido;
import model.bean.ProdutoPedidoSabores;
import model.bean.Projeto;
import model.bean.Sabor;
import model.bean.TipoProduto;
import model.dao.CategoriaDAO;
import model.dao.PedidoDAO;
import model.dao.ProdutoCarrinhoSaboresDAO;
import model.dao.ProdutoDAO;
import model.dao.ProdutoPedidoDAO;
import model.dao.ProdutoPedidoSaboresDAO;
import model.dao.SaborDAO;
import model.dao.TiposProdutosDAO;

/**
 *
 * @author paulo
 */
public class ProdutoPedidoDetalhesController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    boolean saborSelect = false;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = "/WEB-INF/jsp/produtosPedido.jsp";

        ProdutoDAO dao = new ProdutoDAO();
        CategoriaDAO cat = new CategoriaDAO();
        List<Categoria> listaCategorias = cat.listarTodos();
        request.setAttribute("categorias", listaCategorias);

        ProdutoPedidoDAO pDAO = new ProdutoPedidoDAO();
        ProdutoPedidoSaboresDAO pcsDAO = new ProdutoPedidoSaboresDAO();
        List<ProdutoPedidoSabores> sabores = pcsDAO.read();
        request.setAttribute("sabores", sabores);

        List<ProdutoPedido> produtos = pDAO.readPedido(Projeto.getIdPedidoStatic());
        for (ProdutoPedido pp : produtos) {
            if (pp.getId_produto().getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(pp.getId_produto().getImagemBytes());
                pp.getId_produto().setImagemBase64(imagemBase64);
            }
        }
        request.setAttribute("produtos", produtos);

        PedidoDAO daoP = new PedidoDAO();
        Pedido pedido = daoP.readById(Projeto.getIdPedidoStatic());
        request.setAttribute("p", pedido);

        TiposProdutosDAO tDAO = new TiposProdutosDAO();
        List<TipoProduto> tipoProdutos = new ArrayList<>();

        for (Categoria categoria : listaCategorias) {
            TipoProduto tipoProduto = new TipoProduto();
            tipoProduto.setNomeCategoria(categoria.getNome());
            int qtdAtual = 0;
            for (ProdutoPedido pp : produtos) {
                if (categoria.getNome().equals(pp.getId_produto().getCategoria().getNome())) {
                    qtdAtual++;
                }
            }
            tipoProduto.setValor(qtdAtual);
            tipoProdutos.add(tipoProduto);
        }
        request.setAttribute("listaCategoria", tipoProdutos);


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
