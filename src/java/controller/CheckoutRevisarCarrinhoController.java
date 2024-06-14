/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Categoria;
import model.bean.Produto;
import model.bean.ProdutoCarrinhoSabores;
import model.bean.Usuario;
import model.dao.CarrinhoDAO;
import model.dao.CarrinhoProdutoDAO;
import model.dao.CategoriaDAO;
import model.dao.EnderecoDAO;
import model.dao.ProdutoCarrinhoSaboresDAO;
import model.dao.UsuarioDAO;
import model.dao.WishListDAO;

/**
 *
 * @author paulo
 */
public class CheckoutRevisarCarrinhoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/WEB-INF/jsp/checkoutRevisarCarrinho.jsp";

        CarrinhoDAO cDAO = new CarrinhoDAO();
        float total = cDAO.precoCarrinho();
        request.setAttribute("total", total);
        
        
        if (Usuario.getIdUsuarioStatic() != 0) {
            UsuarioDAO u = new UsuarioDAO();
            List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());
            request.setAttribute("usuario", usuarios);
        }
        CategoriaDAO cat = new CategoriaDAO();
        List<Categoria> categoria = cat.listarTodos();
        request.setAttribute("categorias", categoria);
        EnderecoDAO daoEndereco = new EnderecoDAO();
        
        ProdutoCarrinhoSaboresDAO pcsDAO = new ProdutoCarrinhoSaboresDAO();
        List<ProdutoCarrinhoSabores> sabores = pcsDAO.read();
        request.setAttribute("sabores", sabores);

        CarrinhoProdutoDAO car = new CarrinhoProdutoDAO();
        List<Produto> carrinho = car.listarProdutosDoCarrinho();
        for (Produto c : carrinho) {
            if (c.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(c.getImagemBytes());
                c.setImagemBase64(imagemBase64);

            }
        }
        request.setAttribute("carrinhos", carrinho);

        RequestDispatcher r = getServletContext().getRequestDispatcher(url);
        r.forward(request, response);
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

        String url = request.getServletPath();

        if (url.equals("/deletarProdutoCarrinho")) {
            CarrinhoProdutoDAO dao = new CarrinhoProdutoDAO();
            boolean a = dao.excluirProdutoCarrinho(Integer.parseInt(request.getParameter("idProduto")));
            response.sendRedirect(request.getContextPath() + "/revisar-carrinho");
        } else if (url.equals("/listaDesejosCarrinho")) {
            WishListDAO w = new WishListDAO();
            if (Usuario.getIdUsuarioStatic() != 0) {
                boolean valida = w.adicionarProdutoAoCarrinho(Integer.parseInt(request.getParameter("idProduto")));
                request.getSession().setAttribute("validacaoLista", valida);

            } else {
                request.getSession().setAttribute("alerta", "Você precisa estar logado para adicionar produtos à lista de desejos");
            }
            response.sendRedirect(request.getContextPath() + "/revisar-carrinho");
        } else if (url.equals("/atualizarCarrinho")) {
            CarrinhoProdutoDAO dao = new CarrinhoProdutoDAO();
            Enumeration<String> parameterNames = request.getParameterNames();
            boolean success = true;

            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                if (paramName.startsWith("quantity_")) {
                    int idProdutoCarrinho = Integer.parseInt(paramName.split("_")[1]);
                    int quantidade = Integer.parseInt(request.getParameter(paramName));
                    success &= dao.atualizarQuantidade(idProdutoCarrinho, quantidade);
                }
            }

            if (success) {
                request.getSession().setAttribute("feedback", "Quantidade atualizada com sucesso!");
            } else {
                request.getSession().setAttribute("feedback", "Erro ao atualizar a quantidade.");
            }

            response.sendRedirect(request.getContextPath() + "/revisar-carrinho");
        } else if (url.equals("/continuar-checkout")) {
            
            CarrinhoProdutoDAO dao = new CarrinhoProdutoDAO();
            List<Produto> carrinho = dao.listarProdutosDoCarrinho();

            response.sendRedirect("./checkout-endereco");
   
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
