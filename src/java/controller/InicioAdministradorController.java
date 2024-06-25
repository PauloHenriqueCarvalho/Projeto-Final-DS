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
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Auditoria;
import model.bean.Categoria;
import model.bean.Empresa;
import model.bean.Pedido;
import model.bean.PedidosGrafico;

import model.bean.Produto;
import model.bean.ProdutoPedido;
import model.bean.Projeto;
import model.dao.AuditoriaDAO;
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
        String nextPage = "/WEB-INF/jsp/inicioAdministrador.jsp";
        
        CategoriaDAO cat = new CategoriaDAO();
        ProdutoDAO dao = new ProdutoDAO();
        PedidoDAO daoP = new PedidoDAO();
        ProdutoPedidoDAO daoPP = new ProdutoPedidoDAO();
        AuditoriaDAO daoA= new AuditoriaDAO();
        Empresa e = new Empresa();
        
        List<Pedido> pedidos = daoP.read();
        List<ProdutoPedido> produtoPedidos = daoPP.read();
        List<Pedido> produtoPedidosAtuais = daoP.readAtuais();
        List<Auditoria> auditoria = daoA.read();
        List<Categoria> listaCategorias = cat.listarTodos();
        
        for (int i = 0; i < auditoria.size(); i++) {
            if(auditoria.get(i).getOperacao().equals("INSERT")){
                auditoria.get(i).setOperacao("Adicionado");
            } else if(auditoria.get(i).getOperacao().equals("DELETE")){
                auditoria.get(i).setOperacao("Deletado");
            } else{
                auditoria.get(i).setOperacao("Alterado");
            }
            
            
        }
         
        double custo = 0;
        double preco = 0;
        double valor = 0;
        
        for (ProdutoPedido pp : produtoPedidos) {
            custo += pp.getId_produto().getPrecoCusto() * pp.getQuantidade();
            preco += pp.getId_produto().getValor() * pp.getQuantidade();
        }
   
        for (Pedido p : pedidos) {
            valor += p.getTotal();
        }

        
        e.setFuncionarios(produtoPedidosAtuais.size());
        e.setVendas(pedidos.size());
        e.setVendido(valor);
        e.setLucro(valor - custo);

        

        for (Pedido p : pedidos) {
            p.setDataEntregaFormatada(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(p.getData_entrega()));
        }

        Map<String, Double> data = pedidos.stream()
                .collect(Collectors.groupingBy(
                        p -> new SimpleDateFormat("yyyy-MM-dd").format(p.getData_entrega()),
                        Collectors.summingDouble(Pedido::getTotal)
                ));
        List<PedidosGrafico> grafico = data.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(a -> new PedidosGrafico(a.getKey(), a.getValue()))
                .collect(Collectors.toList());      
        

        List<Produto> produto = dao.listarTodos();
        for (int i = 0; i < produto.size(); i++) {
            if (produto.get(i).getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(produto.get(i).getImagemBytes());
                produto.get(i).setImagemBase64(imagemBase64);
            }

        }
        
        request.setAttribute("e", e);
        request.setAttribute("grafico", grafico);
        request.setAttribute("categorias", listaCategorias);
        request.setAttribute("produtos", produto);
        request.setAttribute("auditoria", auditoria);
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
        if (url.equals("/sair")) {
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
