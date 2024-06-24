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
import model.bean.Auditoria;

/**
 *
 * @author paulo
 */
public class AuditoriaDAO {
    public List<Auditoria> read(){
        List<Auditoria> auditoria = new ArrayList<>();
        try{
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM auditoria ORDER BY data_operacao");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Auditoria a  = new Auditoria();
                a.setId(rs.getInt("id_auditoria"));
                a.setOperacao(rs.getString("operacao"));
                a.setTabela(rs.getString("tabela_alterada"));
                a.setData_operacao(rs.getTimestamp("data_operacao"));
                a.setNome(rs.getString("nome"));
                
                auditoria.add(a);
            }
            rs.close();
            ps.close();
            con.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return auditoria;
    }
    
}
