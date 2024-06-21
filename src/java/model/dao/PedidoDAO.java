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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.bean.Endereco;
import model.bean.FormaPagamento;
import model.bean.Pedido;
import model.bean.Produto;
import model.bean.ProdutoPedido;
import model.bean.ProdutoPedidoSabores;
import model.bean.Usuario;

/**
 *
 * @author Paulo Henrique
 */
public class PedidoDAO {

    public List<Pedido> read() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pedido");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId_pedido(rs.getInt("id_pedido"));
                Usuario cliente = new Usuario();
                cliente.setIdUsuario(rs.getInt("id_cliente"));
                p.setId_cliente(cliente);
                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setId_forma_pagamento(rs.getInt("id_forma_pagamento"));
                p.setIdPagamento(formaPagamento);
                p.setFrete(rs.getFloat("frete"));
                p.setData_entrega(rs.getTimestamp("data_entrega"));
                p.setData_pedido(rs.getTimestamp("data_pedido"));
                p.setStatus(rs.getString("status"));
                p.setTotal(rs.getFloat("total"));

                pedidos.add(p);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public List<Pedido> readUsuario() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pedido WHERE id_cliente = ?");
            ps.setInt(1, Usuario.getIdUsuarioStatic());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId_pedido(rs.getInt("id_pedido"));
                Usuario cliente = new Usuario();
                cliente.setIdUsuario(rs.getInt("id_cliente"));
                p.setId_cliente(cliente);
                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setId_forma_pagamento(rs.getInt("id_forma_pagamento"));
                p.setIdPagamento(formaPagamento);
                p.setFrete(rs.getFloat("frete"));
                p.setData_entrega(rs.getTimestamp("data_entrega"));
                p.setData_pedido(rs.getTimestamp("data_pedido"));
                p.setStatus(rs.getString("status"));
                p.setTotal(rs.getFloat("total"));
                EnderecoDAO eDAO = new EnderecoDAO();
                Endereco e = eDAO.enderecoPorId(rs.getInt("id_endereco"));
                p.setId_endereco(e);

                pedidos.add(p);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }
    
    public List<Pedido> readAtuais() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pedido WHERE status != 'cancelado' AND status != 'entregue'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                UsuarioDAO c = new UsuarioDAO();
                PagamentoDAO f = new PagamentoDAO();
                EnderecoDAO eDAO = new EnderecoDAO();
                
                Usuario u = c.readById(rs.getInt("id_cliente"));
                FormaPagamento fp = f.readById(rs.getInt("id_forma_pagamento"));
                Endereco e = eDAO.enderecoPorId(rs.getInt("id_endereco"));
                
                p.setId_pedido(rs.getInt("id_pedido"));
                p.setId_cliente(u);   
                p.setIdPagamento(fp);
                p.setFrete(rs.getFloat("frete"));
                p.setData_entrega(rs.getTimestamp("data_entrega"));
                p.setData_pedido(rs.getTimestamp("data_pedido"));
                p.setStatus(rs.getString("status"));
                p.setTotal(rs.getFloat("total"));
                p.setId_endereco(e);

                pedidos.add(p);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }
    public List<Pedido> readTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pedido");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                UsuarioDAO c = new UsuarioDAO();
                PagamentoDAO f = new PagamentoDAO();
                EnderecoDAO eDAO = new EnderecoDAO();
                
                Usuario u = c.readById(rs.getInt("id_cliente"));
                FormaPagamento fp = f.readById(rs.getInt("id_forma_pagamento"));
                Endereco e = eDAO.enderecoPorId(rs.getInt("id_endereco"));
                
                p.setId_pedido(rs.getInt("id_pedido"));
                p.setId_cliente(u);   
                p.setIdPagamento(fp);
                p.setFrete(rs.getFloat("frete"));
                p.setData_entrega(rs.getTimestamp("data_entrega"));
                p.setData_pedido(rs.getTimestamp("data_pedido"));
                p.setStatus(rs.getString("status"));
                p.setTotal(rs.getFloat("total"));
                p.setId_endereco(e);

                pedidos.add(p);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }
    

    public void criarPedido(Pedido p) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexao.getConn();
            String sql = "INSERT INTO pedido(id_forma_pagamento, id_cliente, frete, data_entrega, status, total, id_endereco) VALUES (?, ?, ?, ?, ?, ?, ?)";

            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getIdPagamento().getId_forma_pagamento());
            ps.setInt(2, Usuario.getIdUsuarioStatic());
            ps.setFloat(3, p.getFrete());
            ps.setTimestamp(4, new Timestamp(p.getData_entrega().getTime()));
            ps.setString(5, p.getStatus());
            ps.setFloat(6, p.getTotal());
            ps.setInt(7, p.getId_endereco().getIdEndereco());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            int idPedido = 0;
            if (rs.next()) {
                idPedido = rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
            ProdutoPedidoDAO dao = new ProdutoPedidoDAO();
            CarrinhoProdutoDAO c = new CarrinhoProdutoDAO();
            List<Produto> itemCarrinho = c.listarProdutosDoCarrinho();
            ProdutoCarrinhoSaboresDAO s = new ProdutoCarrinhoSaboresDAO();
            dao.inserirProdutosPedido(idPedido);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCancelado(int id) {
        try {
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("UPDATE pedido set status = 'cancelado' WHERE id_pedido = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public void updateStatus(String status, int id) {
        try {
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("UPDATE pedido set status = ? WHERE id_pedido = ?");
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();

            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
