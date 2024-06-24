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
import model.bean.Usuario;

/**
 *
 * @author paulo
 */
public class CategoriaDAO {
    public boolean verificaCategoria(String nome) {
        boolean r = false;
        try {
            String sql = "SELECT * FROM categoria WHERE nome = ? ";
            Connection c = Conexao.getConn();
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                r = true;
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }
    
    
    public boolean insert(String nome) {
        
        if(verificaCategoria(nome)) return false;
        try {
            String sql = "INSERT INTO categoria (nome) VALUES (?)";
            Connection c = Conexao.getConn();
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.executeUpdate();
            stmt.close();
            c.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void updateStatus(int id, boolean status) {
        try {
            String sql = "UPDATE  categoria SET status = ? WHERE id_categoria = ?";
            Connection c = Conexao.getConn();
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setBoolean(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(int id) {
        try {
            String sql = "DELETE FROM categoria WHERE id_categoria= ?";
            Connection c = Conexao.getConn();
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public List<Categoria> listarTodos() {
        List<Categoria> categorias = new ArrayList();
        try {
            java.sql.Connection conexao = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conexao.prepareStatement("SELECT * FROM categoria");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNome(rs.getString("nome"));
                c.setStatus(rs.getBoolean("status"));
                if(rs.getBoolean("status"))categorias.add(c);
                
            }
            rs.close();
            stmt.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return categorias;
    }
     public List<Categoria> listarTodosAdm() {
        List<Categoria> categorias = new ArrayList();
        try {
            java.sql.Connection conexao = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conexao.prepareStatement("SELECT * FROM categoria");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNome(rs.getString("nome"));
                c.setStatus(rs.getBoolean("status"));
                categorias.add(c);
            }
            rs.close();
            stmt.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return categorias;
    }

    public Categoria readById(int id) {
        Categoria c = new Categoria();
        try {
            java.sql.Connection conexao = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conexao.prepareStatement("SELECT * FROM categoria WHERE id_categoria = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                c.setIdCategoria(id);
                c.setNome(rs.getString("nome"));
                c.setStatus(rs.getBoolean("status"));
            }

            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    public Categoria readByNome(String nome) {
        Categoria c = new Categoria();
        try {
            java.sql.Connection conexao = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conexao.prepareStatement("SELECT * FROM categoria WHERE nome = ?");
            stmt.setString(1, c.getNome());

            rs = stmt.executeQuery();

            if (rs.next()) {
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setStatus(rs.getBoolean("status"));
                c.setNome(nome);
            }

            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    public Categoria readByNome(Categoria c) {
        try {
            java.sql.Connection conexao = Conexao.getConn();;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conexao.prepareStatement("SELECT * FROM categoria WHERE nome = ?");
            stmt.setString(1, c.getNome());

            rs = stmt.executeQuery();

            if (rs.next()) {
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setStatus(rs.getBoolean("status"));
            }

            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
