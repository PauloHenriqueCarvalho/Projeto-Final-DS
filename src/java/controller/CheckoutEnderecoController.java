/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Categoria;
import model.bean.Endereco;
import model.bean.Pedido;
import model.bean.Produto;
import model.bean.Projeto;
import model.bean.Usuario;
import model.dao.CarrinhoDAO;
import model.dao.CarrinhoProdutoDAO;
import model.dao.CategoriaDAO;
import model.dao.EnderecoDAO;
import model.dao.UsuarioDAO;
import org.apache.http.ParseException;

/**
 *
 * @author paulo
 */
public class CheckoutEnderecoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        double totalP = cDAO.precoCarrinho();
        double totalPFinal = cDAO.precoCarrinho() + 10;
        
        Projeto p = new Projeto();
        String total = p.fortatador(totalP);
        
        String totalFinal = p.fortatador(totalPFinal);
        
        request.setAttribute("total", total);
        request.setAttribute("totalFinal", totalFinal);

        Endereco enderecoAtual = daoEndereco.enderecoPadrao();
        
        System.out.println("ENdereco> :" + enderecoAtual);
        request.setAttribute("e", enderecoAtual);
        
        CarrinhoProdutoDAO car = new CarrinhoProdutoDAO();
        List<Produto> carrinho = car.listarProdutosDoCarrinho();
        for (Produto c : carrinho) {
            if (c.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(c.getImagemBytes());
                c.setImagemBase64(imagemBase64);

            }
        }
        request.setAttribute("qtdCarrinho", carrinho.size());
        request.setAttribute("carrinhos", carrinho);

        String url = "/WEB-INF/jsp/checkoutEndereco.jsp";
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
        if (url.equals("/continuar-checkout-pagamento")) {
            String dataEntrega = request.getParameter("dataEntrega");
            String horarioEntrega = request.getParameter("horarioEntrega");
            Timestamp dataFinal = null;
            String dataHoraString = dataEntrega + " " + horarioEntrega;
            

            if (dataEntrega == null || dataEntrega.isEmpty() || horarioEntrega == null || horarioEntrega.isEmpty()) {
                request.setAttribute("erro", "Data e horário de entrega são obrigatórios.");
                processRequest(request, response);
                return;
            }

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                java.util.Date parsedDate = dateFormat.parse(dataHoraString);
                dataFinal = new Timestamp(parsedDate.getTime());
            } catch (java.text.ParseException e) {
                request.setAttribute("erro", "Data e horário de entrega inválidos.");
                processRequest(request, response);
                return;
            }
            

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                java.util.Date parsedDate = dateFormat.parse(dataHoraString);
                dataFinal = new Timestamp(parsedDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (java.text.ParseException ex) {
            }
           
            EnderecoDAO daoEndereco = new EnderecoDAO();
            Endereco enderecoAtual = daoEndereco.enderecoPorId(Integer.parseInt(request.getParameter("idEndereco")));
            
            Pedido.setData_entregaAtual(dataFinal);
            Pedido.setId_enderecoAtual(enderecoAtual);
            response.sendRedirect("./checkout-pagamento");
        } else {
            processRequest(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
