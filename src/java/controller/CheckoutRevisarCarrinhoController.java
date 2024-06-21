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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
        } else if (url.equals("/atualizarCarrinhoAdicionar")) {
            CarrinhoProdutoDAO dao = new CarrinhoProdutoDAO();
            int idProdutoCarrinho = Integer.parseInt(request.getParameter("idProdutoCarrinho"));
            float quantidadeAtual = Float.parseFloat(request.getParameter("qtd"));
            dao.atualizarQuantidade(idProdutoCarrinho, quantidadeAtual + 1);
            response.sendRedirect("./revisar-carrinho");
        } else if (url.equals("/atualizarCarrinhoDiminuir")) {
            CarrinhoProdutoDAO dao = new CarrinhoProdutoDAO();
            int idProdutoCarrinho = Integer.parseInt(request.getParameter("idProdutoCarrinho"));
            float quantidadeAtual = Float.parseFloat(request.getParameter("qtd"));
            if (quantidadeAtual - 1 <= 0) {
                dao.excluirProdutoCarrinho(idProdutoCarrinho);
            } else {
                dao.atualizarQuantidade(idProdutoCarrinho, quantidadeAtual - 1);
            }
            response.sendRedirect("./revisar-carrinho");

        } else if (url.equals("/continuar-checkout")) {

            CarrinhoProdutoDAO dao = new CarrinhoProdutoDAO();
            List<Produto> carrinho = dao.listarProdutosDoCarrinho();
            if (carrinho == null || carrinho.isEmpty()) {
                String next = "/WEB-INF/jsp/checkoutRevisarCarrinho.jsp";

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

                for (Produto c : carrinho) {
                    if (c.getImagemBytes() != null) {
                        String imagemBase64 = Base64.getEncoder().encodeToString(c.getImagemBytes());
                        c.setImagemBase64(imagemBase64);
                    }
                }

                request.setAttribute("carrinhos", carrinho);
                

                RequestDispatcher r = getServletContext().getRequestDispatcher(next);
                r.forward(request, response);
            } else {
                response.sendRedirect("./checkout-endereco");
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
