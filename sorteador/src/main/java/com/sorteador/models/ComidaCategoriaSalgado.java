package com.sorteador.models;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Entity
@Table(name = "comidas_categoria_salgado")
public class ComidaCategoriaSalgado {

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
    public static void initializeComidaCategoriaSalgado(Connection conn) {
        String insertCategoriaSalgadoSQL = "INSERT INTO comidas_categoria_salgado (categoria) "
                + "SELECT ? WHERE NOT EXISTS (SELECT 1 FROM comidas_categoria_salgado WHERE categoria = ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(insertCategoriaSalgadoSQL)) {
            // Adicionando as categorias de salgado
            addCategoria(pstmt, "Assados");
            addCategoria(pstmt, "Fritos");
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
