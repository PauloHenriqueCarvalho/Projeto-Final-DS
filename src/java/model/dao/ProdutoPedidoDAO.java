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
                pp.setId_produto_pedido(rs.getInt("id_produto_pedido"));
                
                Pedido pedido = new Pedido();
                
                
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pp.setId_pedido(pedido);

                Produto produto = new Produto();
                ProdutoDAO produtod = new ProdutoDAO();
                produto = produtod.readById(rs.getInt("id_produto"));
             
                pp.setId_produto(produto);
                
                pp.setValorAdicional(rs.getFloat("valorAdicional"));
                pp.setQuantidade(rs.getFloat("quantidade"));
                
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
}
