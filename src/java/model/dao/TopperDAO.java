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
import model.bean.Topper;

/**
 *
 * @author paulo
 */
public class TopperDAO {
    public List<Topper> readTopper(){
        List<Topper> masssas = new ArrayList<>();
        try{
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("select * from topper");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Topper m = new Topper();
                m.setIdTopper(rs.getInt("id_topper"));
                m.setNome(rs.getString("nome"));
                m.setValorAdicional(rs.getFloat("valorAdicional"));
                 masssas.add(m);
            }
            rs.close();
            ps.close();
            c.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return masssas;
    }
}
