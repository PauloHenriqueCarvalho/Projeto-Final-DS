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
import model.bean.Massa;
import model.bean.Usuario;

/**
 *
 * @author paulo
 */
public class MassaDAO {

    public List<Massa> readMassas() {
        List<Massa> masssas = new ArrayList<>();
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("select * from massa");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Massa m = new Massa();
                m.setIdMassa(rs.getInt("id_massa"));
                m.setNome(rs.getString("nome"));
                m.setValorAdicional(rs.getFloat("valorAdicional"));
                masssas.add(m);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masssas;
    }

    

}
