/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Pedido;
import model.bean.Produto;
import model.bean.ProdutoPedido;
import model.bean.ProdutoPedidoSabores;
import model.bean.Usuario;

/**
 *
 * @author paulo
 */
public class ProdutoPedidoDAO {

    public List<ProdutoPedido> read() {
        List<ProdutoPedido> produtoPedidos = new ArrayList<>();
        try {
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM produto_pedido");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProdutoPedido pp = new ProdutoPedido();
                Pedido pedido = new Pedido();
                PedidoDAO pedidoD = new PedidoDAO();
                Produto produto = new Produto();
                ProdutoDAO produtod = new ProdutoDAO();
                
                pedido = pedidoD.readById(rs.getInt("id_pedido"));
                produto = produtod.readById(rs.getInt("id_produto"));
                
                pp.setId_pedido(pedido);
                pp.setId_produto(produto);
                pp.setId_produto_pedido(rs.getInt("id_produto_pedido"));
                pp.setValorAdicional(rs.getDouble("valorAdicional"));
                pp.setQuantidade(rs.getDouble("quantidade"));

                produtoPedidos.add(pp);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtoPedidos;
    }
    
    public List<ProdutoPedido> readPedido(int pedidoId) {
        List<ProdutoPedido> produtoPedidos = new ArrayList<>();
        try {
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM produto_pedido WHERE id_pedido = ?");
            ps.setInt(1, pedidoId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProdutoPedido pp = new ProdutoPedido();
                Pedido pedido = new Pedido();
                Produto produto = new Produto();
                ProdutoDAO produtod = new ProdutoDAO();
                
                pedido.setId_pedido(rs.getInt("id_pedido"));
                produto = produtod.readById(rs.getInt("id_produto"));
                
                pp.setId_pedido(pedido);
                pp.setId_produto(produto);
                pp.setId_produto_pedido(rs.getInt("id_produto_pedido"));
                pp.setValorAdicional(rs.getDouble("valorAdicional"));
                pp.setQuantidade(rs.getDouble("quantidade"));

                produtoPedidos.add(pp);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtoPedidos;
    }

    public void inserirProdutosPedido(int idPedido) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConn();

            String sqlConsultaCarrinho = "SELECT pc.id_produto_carrinho, pc.id_produto, pc.valorAdicional, pc.quantidade "
                    + "FROM produto_carrinho pc "
                    + "WHERE pc.id_usuario = ?";

            ps = conn.prepareStatement(sqlConsultaCarrinho);
            ps.setInt(1, Usuario.getIdUsuarioStatic());
            rs = ps.executeQuery();

            while (rs.next()) {
                int idProdutoCarrinho = rs.getInt("id_produto_carrinho");
                int idProduto = rs.getInt("id_produto");
                Double valorAdicional = rs.getDouble("valorAdicional");
                Double quantidade = rs.getDouble("quantidade");
                
                String sqlProdutoPedido = "INSERT INTO produto_pedido (id_produto, id_pedido, valorAdicional, quantidade) VALUES (?, ?, ?, ?)";
                ps = conn.prepareStatement(sqlProdutoPedido);
                ps.setInt(1, idProduto);
                ps.setInt(2, idPedido);
                ps.setDouble(3, valorAdicional);
                ps.setDouble(4, quantidade);
                ps.executeUpdate();

            }
            CarrinhoProdutoDAO cDAO  = new CarrinhoProdutoDAO();
            cDAO.limparCarrinhoUsuario();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
