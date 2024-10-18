package com.sorteador.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    private Connection conn;

    /**
     * Construtor que cria a conexão automaticamente com o banco de dados
     * @exception retorna caso nao consiga conectar com o banco
     */
    public SQLiteConnection() {
        try {
            String url = "jdbc:sqlite:sorteador.db";
            this.conn = DriverManager.getConnection(url);
            System.out.println("Conexao com SQLite estabelecida.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    // Método estático para fechar a conexão
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexao fechada.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
