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
import model.bean.Endereco;
import model.bean.Pedido;
import model.bean.Produto;
import model.bean.Usuario;
import model.dao.CarrinhoDAO;
import model.dao.CarrinhoProdutoDAO;
import model.dao.CategoriaDAO;
import model.dao.EnderecoDAO;
import model.dao.UsuarioDAO;

/**
 *
 * @author paulo
 */
public class CheckoutPagamentoController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/WEB-INF/jsp/checkoutPagamento.jsp";
        
        if (Usuario.getIdUsuarioStatic() != 0) {
            UsuarioDAO u = new UsuarioDAO();
            List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());
            request.setAttribute("usuario", usuarios);
        }
        CategoriaDAO cat = new CategoriaDAO();
        List<Categoria> categoria = cat.listarTodos();
        request.setAttribute("categorias", categoria);
        EnderecoDAO daoEndereco = new EnderecoDAO();

        CarrinhoDAO cDAO = new CarrinhoDAO();
        float total = cDAO.precoCarrinho();
        request.setAttribute("total", total);

        Endereco enderecoAtual = daoEndereco.enderecoPadrao();
        request.setAttribute("e", enderecoAtual);

        CarrinhoProdutoDAO car = new CarrinhoProdutoDAO();
        List<Produto> carrinho = car.listarProdutosDoCarrinho();
        for (Produto c : carrinho) {
            if (c.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(c.getImagemBytes());
                c.setImagemBase64(imagemBase64);

            }
        }
        
      
        request.setAttribute("data", Pedido.getData_entregaAtual());
        request.setAttribute("carrinhos", carrinho);
        
        RequestDispatcher d = getServletContext().getRequestDispatcher(url);
        d.forward(request, response);
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
        
        if(url.equals("/finalizar-compra")){
            Pedido p = new Pedido();
            PedidoDAO pDAO = new PedidoDAO();
            String paymentMethod = request.getParameter("payment");
            String pixKey = request.getParameter("pix-key");
            String cardNumber = request.getParameter("card-number");
            String cardName = request.getParameter("card-name");
            String cardExpiry = request.getParameter("card-expiry");
            String cardCVV = request.getParameter("card-cvv");
            String couponCode = request.getParameter("coupon-code");

            // Imprima os valores capturados
            System.out.println("Método de Pagamento: " + paymentMethod);
            System.out.println("Chave Pix: " + pixKey);
            System.out.println("Número do Cartão: " + cardNumber);
            System.out.println("Nome no Cartão: " + cardName);
            System.out.println("Validade do Cartão: " + cardExpiry);
            System.out.println("CVV do Cartão: " + cardCVV);
            System.out.println("Código do Cupom: " + couponCode);
            
            if(paymentMethod.equals("pix")){
                response.sendRedirect("./pagamentoPix");
            } else{
                response.sendRedirect("./checkoutFinal");
            }
            
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
