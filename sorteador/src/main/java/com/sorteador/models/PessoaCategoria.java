package com.sorteador.models;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Entity
@Table(name = "pessoas_categoria")
public class PessoaCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Método para inicializar a tabela com dados
    public static void initializePessoasCategoria(Connection conn) {
        String insertPessoasCategoriaSQL = "INSERT INTO pessoas_categoria (categoria) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM pessoas_categoria WHERE categoria = ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(insertPessoasCategoriaSQL)) {
            // Adicionando as categorias padrão
            addCategoria(pstmt, "Comilão");
            addCategoria(pstmt, "Homem");
            addCategoria(pstmt, "Mulher");
            addCategoria(pstmt, "Criança");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addCategoria(PreparedStatement pstmt, String categoria) throws SQLException {
        pstmt.setString(1, categoria);
        pstmt.setString(2, categoria);
        pstmt.executeUpdate();
    }
}
