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
import model.bean.Produto;

/**
 *
 * @author paulo
 */
public class EstoqueDAO {
    public void aumentarQuantidade(int idProduto){
        try{
            System.out.println("Aumentar Proiduto");
            int qtd = 0;
            Connection con  = Conexao.getConn();
            PreparedStatement ps2 = con.prepareStatement("select * from estoque where id_estoque = ?");
             ps2.setInt(1, idProduto);
             
             ResultSet rs = ps2.executeQuery();
             if(rs.next()){
                 qtd = rs.getInt("quantidade");
             }
             qtd++;
            PreparedStatement ps = con.prepareStatement("update estoque SET quantidade = ?  WHERE id_estoque = ?");
            ps.setInt(1, qtd);
            ps.setInt(2, idProduto);
            ps.executeUpdate();
            
            rs.close();
            ps2.close();
            con.close();

                    
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void diminuirQuantidade(int idProduto) {
    try {
        System.out.println("Diminuir Produto");
        int qtd = 0;
        Connection con = Conexao.getConn();

        PreparedStatement psSelect = con.prepareStatement("SELECT quantidade FROM estoque WHERE id_estoque = ?");
        psSelect.setInt(1, idProduto);
        ResultSet rs = psSelect.executeQuery();
        
        if (rs.next()) {
            qtd = rs.getInt("quantidade");
            if (qtd > 0) {
                qtd--; 
            }
        }
        PreparedStatement psUpdate = con.prepareStatement("UPDATE estoque SET quantidade = ? WHERE id_estoque = ?");
        psUpdate.setInt(1, qtd);
        psUpdate.setInt(2, idProduto);
        psUpdate.executeUpdate();
        
        rs.close();
        psSelect.close();
        psUpdate.close();
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    
    
}
