package com.sorteador.models;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Entity
@Table(name = "comidas_quantidade_padrao")
public class ComidaQuantidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pessoas_categoria")
    private PessoaCategoria pessoasCategoria;

    @Column(name = "quantidade_carne")
    private Integer quantidadeCarne;

    @Column(name = "quantidade_salgado")
    private Integer quantidadeSalgado;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PessoaCategoria getPessoasCategoria() {
        return pessoasCategoria;
    }

    public void setPessoasCategoria(PessoaCategoria pessoasCategoria) {
        this.pessoasCategoria = pessoasCategoria;
    }

    public Integer getQuantidadeCarne() {
        return quantidadeCarne;
    }

    public void setQuantidadeCarne(Integer quantidadeCarne) {
        this.quantidadeCarne = quantidadeCarne;
    }

    public Integer getQuantidadeSalgado() {
        return quantidadeSalgado;
    }

    public void setQuantidadeSalgado(Integer quantidadeSalgado) {
        this.quantidadeSalgado = quantidadeSalgado;
    }

    // Método para inicializar a tabela com dados
    public static void initializeComidaQuantidadePadrao(Connection conn) {
        String insertQuantidadePadraoComidaSQL = "INSERT INTO comidas_quantidade_padrao (id_pessoas_categoria, quantidade_carne, quantidade_salgado) "
                + "SELECT ?, ?, ? WHERE NOT EXISTS (SELECT 1 FROM comidas_quantidade_padrao WHERE id_pessoas_categoria = ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuantidadePadraoComidaSQL)) {
            // Adicionando as quantidades padrão para as categorias de pessoas
            addQuantidadePadrao(pstmt, 1, 500, 15);
            addQuantidadePadrao(pstmt, 2, 400, 12);
            addQuantidadePadrao(pstmt, 3, 300, 10);
            addQuantidadePadrao(pstmt, 4, 200, 6);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addQuantidadePadrao(PreparedStatement pstmt, int idPessoasCategoria, int quantidadeCarne, int quantidadeSalgado) throws SQLException {
        pstmt.setInt(1, idPessoasCategoria);
        pstmt.setInt(2, quantidadeCarne);
        pstmt.setInt(3, quantidadeSalgado);
        pstmt.setInt(4, idPessoasCategoria);
        pstmt.executeUpdate();
    }
}
