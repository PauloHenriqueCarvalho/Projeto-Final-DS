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
import model.bean.Categoria;
import model.bean.Sabor;
import model.bean.Topper;

/**
 *
 * @author paulo
 */
public class SaborDAO {
    public List<Sabor> readSabor(int idCat){
        List<Sabor> masssas = new ArrayList<>();
        try{
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("select * from Sabor where id_categoria = ?");
            ps.setInt(1, idCat);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Sabor m = new Sabor();
                Categoria ca = new Categoria();
                ca.setIdCategoria(rs.getInt("id_categoria"));
                m.setIdSabor(rs.getInt("id_sabor"));
                m.setNome(rs.getString("nome"));
                m.setValorAdicional(rs.getFloat("valorAdicional"));
                m.setIdCategoria(ca);
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
