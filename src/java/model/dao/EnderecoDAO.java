/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.sun.org.apache.bcel.internal.generic.Type;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.bean.Endereco;
import model.bean.Usuario;

/**
 *
 * @author paulo
 */
public class EnderecoDAO {
    public void insert(Endereco e){
        try{
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("insert into endereco (cep, rua, cidade, estado,complemento,numero,id_usuario, endereco_padrao) values (?,?,?,?,?,?,?,?)");
            ps.setString(1, e.getCep());
            ps.setString(2, e.getLogradouro());
            ps.setString(3, e.getLocalidade());
            ps.setString(4, e.getUf());
            if(e.getComplemento() != null){
                ps.setString(5, e.getComplemento());
            } else {
                 ps.setNull(5, Types.INTEGER);
            }
           
            ps.setInt(6, e.getNumero());
            ps.setInt(7, Usuario.getIdUsuarioStatic());
            if(!verificarEnderecoPadrao()){
                ps.setBoolean(8, true);
            } else {
                ps.setBoolean(8, false);
            }
            ps.executeUpdate();
             ps.close();
            con.close();
        } catch (SQLException es){
            es.printStackTrace();
        }
        
    }
    public List<Endereco> readEnderecos() {
        List<Endereco> enderecos = new ArrayList<>();
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM endereco where id_usuario = ?");
            ps.setInt(1, Usuario.getIdUsuarioStatic());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Endereco e = new Endereco();
                e.setIdEndereco(rs.getInt("id_endereco"));
                e.setCep(rs.getString("cep"));
                e.setLogradouro(rs.getString("rua"));
                e.setLocalidade(rs.getString("cidade"));
                e.setUf(rs.getString("estado"));
                e.setComplemento(rs.getString("complemento"));
                e.setNumero(rs.getInt("numero"));
                e.setEnderecoPadrao(rs.getBoolean("endereco_padrao"));
                UsuarioDAO dao = new UsuarioDAO();
                Usuario u = dao.getName(Usuario.getIdUsuarioStatic());
                e.setIdUsuario(u);
                enderecos.add(e);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }
    
    
    public boolean verificarEnderecoPadrao() {
        List<Endereco> enderecos = new ArrayList<>();
        boolean retorno = false;
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM endereco where id_usuario = ?");
            ps.setInt(1, Usuario.getIdUsuarioStatic());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Endereco e = new Endereco();
                e.setIdEndereco(rs.getInt("id_endereco"));
                e.setCep(rs.getString("cep"));
                e.setLogradouro(rs.getString("rua"));
                e.setLocalidade(rs.getString("cidade"));
                e.setUf(rs.getString("estado"));
                e.setComplemento(rs.getString("complemento"));
                e.setNumero(rs.getInt("numero"));
                if(rs.getBoolean("endereco_padrao")){
                    retorno = true;
                }
                UsuarioDAO dao = new UsuarioDAO();
                Usuario u = dao.getName(Usuario.getIdUsuarioStatic());
                e.setIdUsuario(u);
                enderecos.add(e);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retorno;
    }
    
    public int idEnderecoPadrao() {
        List<Endereco> enderecos = new ArrayList<>();
        int retorno = 0;
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM endereco where id_usuario = ?");
            ps.setInt(1, Usuario.getIdUsuarioStatic());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Endereco e = new Endereco();
                e.setIdEndereco(rs.getInt("id_endereco"));
                e.setCep(rs.getString("cep"));
                e.setLogradouro(rs.getString("rua"));
                e.setLocalidade(rs.getString("cidade"));
                e.setUf(rs.getString("estado"));
                e.setComplemento(rs.getString("complemento"));
                e.setNumero(rs.getInt("numero"));
                if(rs.getBoolean("endereco_padrao")){
                    retorno = rs.getInt("id_endereco");
                    System.out.println("Retorno: "+ retorno);
                    break;
                }
                UsuarioDAO dao = new UsuarioDAO();
                Usuario u = dao.getName(Usuario.getIdUsuarioStatic());
                e.setIdUsuario(u);
                enderecos.add(e);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retorno;
    }
    
    public void mudarEnderecoPadrao(int novoEnderecoPadrao) {

        try {
            Connection c = Conexao.getConn();
            
            PreparedStatement ps = c.prepareStatement("UPDATE endereco set endereco_padrao = ? where id_endereco = ?");
            ps.setBoolean(1, false);
            ps.setInt(2, idEnderecoPadrao());
            System.out.println("Endereco Padrao: " + idEnderecoPadrao());
            ps.executeUpdate();
            ps.close();
            
            
            ps = c.prepareStatement("UPDATE endereco set endereco_padrao = ? where id_endereco = ?");
            ps.setBoolean(1, true);
            ps.setInt(2, novoEnderecoPadrao);          
            ps.executeUpdate();
            ps.close();
      
            
            
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delet(int id){
        try{
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("DELETE FROM ENDERECO WHERE ID_ENDERECO = ?");
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException es){
            es.printStackTrace();
        }
        
    }
    
    
     public Endereco enderecoPadrao() {
        Endereco e = new Endereco();
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM endereco where id_usuario = ? and endereco_padrao = true");
            ps.setInt(1, Usuario.getIdUsuarioStatic());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e.setIdEndereco(rs.getInt("id_endereco"));
                e.setCep(rs.getString("cep"));
                e.setLogradouro(rs.getString("rua"));
                e.setLocalidade(rs.getString("cidade"));
                e.setUf(rs.getString("estado"));
                e.setComplemento(rs.getString("complemento"));
                e.setNumero(rs.getInt("numero"));
                UsuarioDAO dao = new UsuarioDAO();
                Usuario u = dao.getName(Usuario.getIdUsuarioStatic());
                e.setIdUsuario(u);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }
    
     
     public Endereco enderecoPorId(int id) {
        Endereco e = new Endereco();
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM endereco where id_endereco = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e.setIdEndereco(rs.getInt("id_endereco"));
                e.setCep(rs.getString("cep"));
                e.setLogradouro(rs.getString("rua"));
                e.setLocalidade(rs.getString("cidade"));
                e.setUf(rs.getString("estado"));
                e.setComplemento(rs.getString("complemento"));
                e.setNumero(rs.getInt("numero"));
                UsuarioDAO dao = new UsuarioDAO();
                Usuario u = dao.getName(Usuario.getIdUsuarioStatic());
                e.setIdUsuario(u);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }
    
    
}
