package com.sorteador.models;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Entity
@Table(name = "comidas_categoria_carne")
public class ComidaCategoriaCarne {

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
    public static void initializeComidaCategoriaCarne(Connection conn) {
        String insertCategoriaCarneSQL = "INSERT INTO comidas_categoria_carne (categoria) "
                + "SELECT ? WHERE NOT EXISTS (SELECT 1 FROM comidas_categoria_carne WHERE categoria = ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(insertCategoriaCarneSQL)) {
            // Adicionando as categorias de carne
            addCategoria(pstmt, "Bovino com Osso");
            addCategoria(pstmt, "Suíno com Osso");
            addCategoria(pstmt, "Frango com Osso");
            addCategoria(pstmt, "Bovino Sem Osso");
            addCategoria(pstmt, "Suíno Sem Osso");
            addCategoria(pstmt, "Agregados");
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
