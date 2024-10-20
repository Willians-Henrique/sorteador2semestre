package com.sorteador.models;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Entity
@Table(name = "bebidas_quantidade_padrao")
public class BebidaQuantidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_bebidas", nullable = false)
    private Bebida bebida;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    // Método para inicializar a tabela com dados
    public static void initializeBebidaQuantidadePadrao(Connection conn) {
        String insertQuantidadePadraoSQL = "INSERT INTO bebidas_quantidade_padrao (id_bebidas, quantidade) "
                + "SELECT ?, ? WHERE NOT EXISTS (SELECT 1 FROM bebidas_quantidade_padrao WHERE id_bebidas = ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuantidadePadraoSQL)) {
            // Adicionando as quantidades padrão
            addQuantidade(pstmt, 1, 1500);
            addQuantidade(pstmt, 2, 50);
            addQuantidade(pstmt, 3, 50);
            addQuantidade(pstmt, 4, 100);
            addQuantidade(pstmt, 5, 500);
            addQuantidade(pstmt, 6, 1000);
            addQuantidade(pstmt, 7, 500);
            addQuantidade(pstmt, 8, 1000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addQuantidade(PreparedStatement pstmt, int idBebida, int quantidade) throws SQLException {
        pstmt.setInt(1, idBebida);
        pstmt.setInt(2, quantidade);
        pstmt.setInt(3, idBebida);
        pstmt.executeUpdate();
    }
}
