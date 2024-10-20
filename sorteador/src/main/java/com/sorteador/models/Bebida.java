package com.sorteador.models;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Entity
@Table(name = "bebidas")
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bebida", nullable = false)
    private String bebida;

    @ManyToOne
    @JoinColumn(name = "tipo")
    private BebidaTipo tipo;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public BebidaTipo getTipo() {
        return tipo;
    }

    public void setTipo(BebidaTipo tipo) {
        this.tipo = tipo;
    }

    // Método para inicializar a tabela com dados
    public static void initializeBebidas(Connection conn) {
        String insertBebidasSQL = "INSERT INTO bebidas (bebida, tipo) "
                + "SELECT ?, ? WHERE NOT EXISTS (SELECT 1 FROM bebidas WHERE bebida = ? AND tipo = ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertBebidasSQL)) {
            // Adicionando as bebidas
            addBebida(pstmt, "Cerveja", 1);
            addBebida(pstmt, "Vinho", 1);
            addBebida(pstmt, "Vodka", 1);
            addBebida(pstmt, "Drink", 1);
            addBebida(pstmt, "Cerveja Sem Álcool", 2);
            addBebida(pstmt, "Água", 2);
            addBebida(pstmt, "Suco", 2);
            addBebida(pstmt, "Refrigerante", 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addBebida(PreparedStatement pstmt, String bebida, int tipo) throws SQLException {
        pstmt.setString(1, bebida);
        pstmt.setInt(2, tipo);
        pstmt.setString(3, bebida);
        pstmt.setInt(4, tipo);
        pstmt.executeUpdate();
    }
}
