package com.sorteador.models;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Método para inicializar a tabela com dados
    public static void initializePessoas(Connection conn) {
        String insertPessoasSQL = "INSERT INTO pessoas (tipo) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM pessoas WHERE tipo = ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(insertPessoasSQL)) {
            // Adicionando os tipos padrão
            addPessoa(pstmt, "Come Carne");
            addPessoa(pstmt, "Não Come Carne");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addPessoa(PreparedStatement pstmt, String tipo) throws SQLException {
        pstmt.setString(1, tipo);
        pstmt.setString(2, tipo);
        pstmt.executeUpdate();
    }
}
