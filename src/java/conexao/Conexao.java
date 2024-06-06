/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Senai
 */
public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/projeto_final_db?useSSL=false";;;
//    private static final String url = "jdbc:mysql://localhost:3306/projeto_final_db";
    private static final String user = "root";
    private static final String senha = "1234";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, senha);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
