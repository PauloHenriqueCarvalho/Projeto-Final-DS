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
import model.bean.FormaPagamento;

/**
 *
 * @author paulo
 */
public class PagamentoDAO {
    public FormaPagamento readById(int id){
        FormaPagamento f = new FormaPagamento();
        try{
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM forma_pagamento WHERE id_forma_pagamento = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                f.setId_forma_pagamento(rs.getInt("id_forma_pagamento"));
                f.setNome(rs.getString("nome"));
                f.setDescricao(rs.getString("descricao"));
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return f;
    }
}
