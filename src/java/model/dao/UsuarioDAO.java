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
 * @author Paulo Henrique
 */
public class UsuarioDAO {



    public void insertCliente(Usuario usuario) {
        String senha = usuario.getSenha();
//        String hashedSenha = encoder.encode(senha);
    
        


        try {
            String sql = "INSERT INTO usuario (nome, senha, email, telefone, cpf) VALUES (?, ?, ?, ?, ?)";
            Connection c = Conexao.getConn();
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, senha);
            stmt.setString(3, usuario.getEmail());

            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getCpf());

            stmt.executeUpdate();
            stmt.close();
            c.close();

        } catch (SQLException e) {
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
                if (rs.getString("acesso").equals("cliente")) {
                    Usuario.setAcessoStatic(3);
                } else if (rs.getString("acesso").equals("funcionario")) {
                    Usuario.setAcessoStatic(2);
                } else if (rs.getString("acesso").equals("administrador")) {
                    Usuario.setAcessoStatic(1);
                } else {
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
           
            conn = Conexao.getConn(); 
            String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
                e.printStackTrace(); 
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
                u.setTelefone(rs.getString("telefone"));
                u.setIdUsuario(id);
          
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
