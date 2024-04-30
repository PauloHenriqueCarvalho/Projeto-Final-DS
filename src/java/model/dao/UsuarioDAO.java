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
import model.bean.Usuario;

/**
 *
 * @author Senai
 */
public class UsuarioDAO {
    public void insertCliente(Usuario usuario) {
        try{
            String sql = "INSERT INTO usuario (nome, senha, email, telefone, cpf) VALUES (?, ?, ?, ?, ?)";           
            Connection c = Conexao.getConn();
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getEmail());
            
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getCpf());
            
            stmt.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public Usuario validaUser(Usuario user) {
        Usuario usuarioValido = new Usuario();
        try {
            Connection con = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ? AND senha = ?");
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getSenha());
            
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuarioValido.setIdUsuario(rs.getInt("id_usuario"));
                usuarioValido.setNome(rs.getString("nome"));
                usuarioValido.setEmail(rs.getString("email"));
                usuarioValido.setTelefone(rs.getString("telefone"));
                usuarioValido.setCpf(rs.getString("cpf"));
                Usuario.setIdUsuarioStatic(rs.getInt("id_usuario"));

            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            usuarioValido.setIdUsuario(0);
            usuarioValido.setNome("");
            usuarioValido.setEmail("");
        }
        return usuarioValido;
    }

    public int getId(String user) {
        int id = 0;
        try {
            Connection con = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ?");
            stmt.setString(1, user);

            rs = stmt.executeQuery();

            if (rs.next()) {
                
                id = rs.getInt("idUsuario");

            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Usuario getName(int id) {
        Usuario u = null;

        try {
            Connection con = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = con.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                // Aqui você precisa instanciar um objeto Usuario
                u = new Usuario();
                u.setNome(rs.getString("nome"));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
}
