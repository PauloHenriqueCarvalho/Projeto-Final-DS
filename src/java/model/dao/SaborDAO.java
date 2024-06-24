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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.bean.Categoria;
import model.bean.Sabor;

/**
 *
 * @author paulo
 */
public class SaborDAO {


    
    public void createEspecificacao(Sabor sabor) {
        try{
            Connection connection = Conexao.getConn();
            String sql = "INSERT INTO sabor (idPai, nome, descricao, valorAdicional) VALUES (?, ?, ?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, sabor.getIdPai());
            stmt.setString(2, sabor.getNome());
            stmt.setString(3, sabor.getDescricao());
            stmt.setDouble(4, sabor.getValorAdicional());

            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTipo(Sabor sabor) {
        try{
            Connection connection = Conexao.getConn();
            String sql = "INSERT INTO sabor (id_produto, nome, descricao) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, sabor.getIdProduto());
            stmt.setString(2, sabor.getNome());
            stmt.setString(3, sabor.getDescricao());

            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Sabor readById(int id) {
        Sabor sabor = null;
        try (Connection connection = Conexao.getConn()) {
            String sql = "SELECT * FROM sabor WHERE id_sabor = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        sabor = new Sabor();
                        sabor.setIdSabor(rs.getInt("id_sabor"));
                        sabor.setIdPai(rs.getObject("id_produto") != null ? rs.getInt("id_produto") : null);
                        sabor.setNome(rs.getString("nome"));
                        sabor.setIdPai(rs.getObject("idPai") != null ? rs.getInt("idPai") : null);
                        sabor.setDescricao(rs.getString("descricao"));
                        sabor.setValorAdicional(rs.getDouble("valorAdicional"));
                        sabor.setStatus(rs.getString("status"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sabor;
    }

    public List<Sabor> listarTodosTipos() {
        List<Sabor> sabores = new ArrayList<>();
        try (Connection connection = Conexao.getConn()) {
            String sql = "SELECT * FROM sabor WHERE id_produto != 0";
            try (PreparedStatement stmt = connection.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sabor sabor = new Sabor();
                    sabor.setIdSabor(rs.getInt("id_sabor"));
                    sabor.setIdPai(rs.getObject("id_produto") != null ? rs.getInt("id_produto") : null);
                    sabor.setNome(rs.getString("nome"));
                    sabor.setIdPai(rs.getObject("idPai") != null ? rs.getInt("idPai") : null);
                    sabor.setDescricao(rs.getString("descricao"));
                    sabor.setValorAdicional(rs.getDouble("valorAdicional"));
                    sabor.setStatus(rs.getString("status"));
                    sabores.add(sabor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sabores;
    }

    public List<Sabor> listarTiposProduto(int idProduto) {
        List<Sabor> sabores = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sabor WHERE id_produto = ?";

            Connection connection = Conexao.getConn();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Sabor sabor = new Sabor();
                sabor.setIdSabor(rs.getInt("id_sabor"));
                sabor.setIdPai(rs.getObject("id_produto") != null ? rs.getInt("id_produto") : null);
                sabor.setNome(rs.getString("nome"));
                sabor.setIdPai(rs.getObject("idPai") != null ? rs.getInt("idPai") : null);
                sabor.setDescricao(rs.getString("descricao"));
                sabor.setValorAdicional(rs.getDouble("valorAdicional"));
                sabor.setStatus(rs.getString("status"));
                sabores.add(sabor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sabores;
    }

    public List<Sabor> listarTodosEspecificos() {
        List<Sabor> sabores = new ArrayList<>();
        try (Connection connection = Conexao.getConn()) {
            String sql = "SELECT * FROM sabor WHERE idPai != 0";
            try (PreparedStatement stmt = connection.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sabor sabor = new Sabor();
                    sabor.setIdSabor(rs.getInt("id_sabor"));
                    sabor.setIdPai(rs.getObject("id_produto") != null ? rs.getInt("id_produto") : null);
                    sabor.setNome(rs.getString("nome"));
                    sabor.setIdPai(rs.getObject("idPai") != null ? rs.getInt("idPai") : null);
                    sabor.setDescricao(rs.getString("descricao"));
                    sabor.setValorAdicional(rs.getDouble("valorAdicional"));
                    sabor.setStatus(rs.getString("status"));
                    sabores.add(sabor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sabores;
    }

    public void update(Sabor sabor) {
        try (Connection connection = Conexao.getConn()) {
            String sql = "UPDATE sabor SET id_produto = ?, nome = ?, idPai = ?, descricao = ?, valorAdicional = ?, status = ? WHERE id_sabor = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                if (sabor.getIdProduto() != null) {
                    stmt.setInt(1, sabor.getIdProduto());
                } else {
                    stmt.setNull(1, Types.INTEGER);
                }
                stmt.setString(2, sabor.getNome());
                if (sabor.getIdPai() != null) {
                    stmt.setInt(3, sabor.getIdPai());
                } else {
                    stmt.setNull(3, Types.INTEGER);
                }
                stmt.setString(4, sabor.getDescricao());
                stmt.setDouble(5, sabor.getValorAdicional());
                stmt.setString(6, sabor.getStatus());
                stmt.setInt(7, sabor.getIdSabor());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = Conexao.getConn()) {
            String sql = "DELETE FROM sabor WHERE id_sabor = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
