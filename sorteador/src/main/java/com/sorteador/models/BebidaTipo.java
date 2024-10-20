package com.sorteador.models;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Entity
@Table(name = "bebidas_tipos")
public class BebidaTipo {

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
    public static void initializeBebidaTipos(Connection conn) {
        String insertTiposSQL = "INSERT INTO bebidas_tipos (tipo) "
                + "SELECT ? WHERE NOT EXISTS (SELECT 1 FROM bebidas_tipos WHERE tipo = ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertTiposSQL)) {
            // Adicionando os tipos de bebida
            addTipo(pstmt, "Alcoólico");
            addTipo(pstmt, "Não Alcoólico");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addTipo(PreparedStatement pstmt, String tipo) throws SQLException {
        pstmt.setString(1, tipo);
        pstmt.setString(2, tipo);
        pstmt.executeUpdate();
    }
}
