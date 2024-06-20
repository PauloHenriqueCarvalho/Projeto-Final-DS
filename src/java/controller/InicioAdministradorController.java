/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Categoria;
import model.bean.Empresa;
import model.bean.Pedido;

import model.bean.Produto;
import model.bean.ProdutoPedido;
import model.bean.Projeto;
import model.dao.CategoriaDAO;
import model.dao.PedidoDAO;
import model.dao.ProdutoDAO;
import model.dao.ProdutoPedidoDAO;

/**
 *
 * @author paulo
 */
public class InicioAdministradorController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String nextPage = "/WEB-INF/jsp/inicioAdministrador.jsp";
        
        ProdutoDAO dao = new ProdutoDAO();
        PedidoDAO daoP = new PedidoDAO();
        ProdutoPedidoDAO daoPP = new ProdutoPedidoDAO();
        
        List<Pedido> pedidos = daoP.read();
        List<ProdutoPedido> produtoPedidos = daoPP.read();
        float valor = 0;
        float preco = 0;
        float custo = 0;
        
        for (int i = 0; i < produtoPedidos.size(); i++) {
            custo+= produtoPedidos.get(i).getId_produto().getPrecoCusto();
            preco+= produtoPedidos.get(i).getId_produto().getValor();
        }
        
        
        for (int i = 0; i < pedidos.size(); i++) {
            valor+= pedidos.get(i).getTotal();
        }
        
        Empresa e = new Empresa();
        e.setFuncionarios(0);
        
        e.setVendas(pedidos.size());
        e.setVendido(valor);
        e.setLucro(valor - custo);
        
        
        
        request.setAttribute("e", e);
        
        
        for (Pedido p : pedidos) {
            p.setDataEntregaFormatada(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(p.getData_entrega()));
        }
        request.setAttribute("pedidos", pedidos);
        

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


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        if(url.equals("/sair")){
            Projeto.setSair(true);
            response.sendRedirect(request.getContextPath() + "/inicio");
            
        } else {
            processRequest(request, response);
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
