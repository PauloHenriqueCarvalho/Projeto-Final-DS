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
import model.bean.Endereco;
import model.bean.FormaPagamento;
import model.bean.Pedido;
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
//                Endereco endereco = new Endereco();
//                endereco.setIdEndereco(rs.getInt("id_endereco"));
//                p.setId_endereco(endereco);

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
}
