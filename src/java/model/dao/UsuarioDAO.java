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
    
    public void validaUser(Usuario user) {
  
        try {
            Connection con = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ? AND senha = ?");
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getSenha());
            
            rs = stmt.executeQuery();

            if (rs.next()) {
                if(rs.getString("acesso").equals("cliente")){
                    Usuario.setAcessoStatic(3);
                } else if(rs.getString("acesso").equals("funcionario")){
                    Usuario.setAcessoStatic(2);
                } else if(rs.getString("acesso").equals("administrador")){
                    Usuario.setAcessoStatic(1);
                } else{
                }
                Usuario.setIdUsuarioStatic(rs.getInt("id_usuario"));
            }        
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
     public List<Usuario> getUsuarioById(int idUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            // Obtém uma conexão com o banco de dados (substitua este código pelo método de obtenção de conexão adequado)
            conn = Conexao.getConn(); // Substitua MeuConectorBancoDados pelo seu conector

            // Prepara a consulta SQL
            String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
            stmt = conn.prepareStatement(sql);

            // Define o ID do usuário na consulta preparada
            stmt.setInt(1, idUsuario);

            // Executa a consulta
            rs = stmt.executeQuery();

            // Verifica se a consulta retornou algum resultado
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuarios.add(usuario);
                // Adicione outros atributos do usuário conforme necessário
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trate adequadamente esta exceção em sua aplicação
        } finally {
            // Fecha os recursos (ResultSet, PreparedStatement e Connection)
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Trate adequadamente esta exceção em sua aplicação
            }
        }

        return usuarios;
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
                id = rs.getInt("id_usuario");

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
