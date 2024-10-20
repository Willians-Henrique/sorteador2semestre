package com.sorteador.models;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Entity
@Table(name = "comidas_tipo")
public class ComidaTipo {

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
    public static void initializeComidaTipo(Connection conn) {
        String insertTiposComidaSQL = "INSERT INTO comidas_tipo (tipo) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM comidas_tipo WHERE tipo = ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(insertTiposComidaSQL)) {
            // Adicionando os tipos padrão
            addTipoComida(pstmt, "Churrasco");
            addTipoComida(pstmt, "Salgado");
            addTipoComida(pstmt, "Obrigatório");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addTipoComida(PreparedStatement pstmt, String tipo) throws SQLException {
        pstmt.setString(1, tipo);
        pstmt.setString(2, tipo);
        pstmt.executeUpdate();
    }
}
