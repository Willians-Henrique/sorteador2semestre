package com.sorteador.models;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Entity
@Table(name = "comidas")
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_tipo_comida")
    private ComidaTipo tipoComida;

    @ManyToOne
    @JoinColumn(name = "id_categoria_carne")
    private ComidaCategoriaCarne categoriaCarne;

    @ManyToOne
    @JoinColumn(name = "id_categoria_salgado")
    private ComidaCategoriaSalgado categoriaSalgado;

    @Column(name = "opcao_sem_carne")
    private Boolean opcaoSemCarne;

    @Column(name = "aproveitamento")
    private Double aproveitamento;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ComidaTipo getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(ComidaTipo tipoComida) {
        this.tipoComida = tipoComida;
    }

    public ComidaCategoriaCarne getCategoriaCarne() {
        return categoriaCarne;
    }

    public void setCategoriaCarne(ComidaCategoriaCarne categoriaCarne) {
        this.categoriaCarne = categoriaCarne;
    }

    public ComidaCategoriaSalgado getCategoriaSalgado() {
        return categoriaSalgado;
    }

    public void setCategoriaSalgado(ComidaCategoriaSalgado categoriaSalgado) {
        this.categoriaSalgado = categoriaSalgado;
    }

    public Boolean getOpcaoSemCarne() {
        return opcaoSemCarne;
    }

    public void setOpcaoSemCarne(Boolean opcaoSemCarne) {
        this.opcaoSemCarne = opcaoSemCarne;
    }

    public Double getAproveitamento() {
        return aproveitamento;
    }

    public void setAproveitamento(Double aproveitamento) {
        this.aproveitamento = aproveitamento;
    }

    // Método para inicializar a tabela com dados
    public static void initializeComidas(Connection conn) {
        String insertComidaSQL = "INSERT INTO comidas (nome, id_tipo_comida, id_categoria_carne, id_categoria_salgado, opcao_sem_carne, aproveitamento) "
                + "SELECT ?, ?, ?, ?, ?, ? "
                + "WHERE NOT EXISTS ("
                + "  SELECT 1 FROM comidas "
                + "  WHERE nome = ? "
                + ")";

        try (PreparedStatement pstmt = conn.prepareStatement(insertComidaSQL)) {
            // Pizza
            addComida(pstmt, "Pizza", 2, 0, 1, true, 1.0);
            // Esfirra
            addComida(pstmt, "Esfirra", 2, 0, 1, true, 1.0);
            // Hamburger
            addComida(pstmt, "Hamburger", 2, 0, 1, false, 1.0);
            // Empadinha
            addComida(pstmt, "Empadinha", 2, 0, 1, true, 1.0);
            // Coxinha
            addComida(pstmt, "Coxinha", 2, 0, 2, false, 1.0);
            // Kibe
            addComida(pstmt, "Kibe", 2, 0, 2, false, 1.0);
            // Pastel
            addComida(pstmt, "Pastel", 2, 0, 2, true, 1.0);
            // Bolinha de Queijo
            addComida(pstmt, "Bolinha de Queijo", 2, 0, 2, true, 1.0);
            // Risoles
            addComida(pstmt, "Risóles", 2, 0, 2, true, 1.0);
            // Cigarette
            addComida(pstmt, "Cigarette", 2, 0, 2, true, 1.0);
            // Croquete
            addComida(pstmt, "Croquete", 2, 0, 2, false, 1.0);
            // Costela Bovina
            addComida(pstmt, "Costela Bovina", 1, 1, 0, false, 0.5);
            // Prime Rib
            addComida(pstmt, "Prime Rib", 1, 1, 0, false, 0.6);
            // Chuleta Paulista
            addComida(pstmt, "Chuleta Paulista", 1, 1, 0, false, 0.6);
            // Costela Suína
            addComida(pstmt, "Costela Suína", 1, 2, 0, false, 0.4);
            // Bisteca
            addComida(pstmt, "Bisteca", 1, 2, 0, false, 0.7);
            // Pernil com Osso
            addComida(pstmt, "Pernil com Osso", 1, 2, 0, false, 0.7);
            // Asinha com Coxinha
            addComida(pstmt, "Asinha com Coxinha", 1, 3, 0, false, 0.7);
            // Tulipa da Asa
            addComida(pstmt, "Tulipa da Asa", 1, 3, 0, false, 0.6);
            // Coxa com Sobrecoxa
            addComida(pstmt, "Coxa com Sobrecoxa", 1, 3, 0, false, 0.8);
            // Picanha
            addComida(pstmt, "Picanha", 1, 4, 0, false, 1.0);
            // Coxão Mole
            addComida(pstmt, "Coxão Mole", 1, 4, 0, false, 1.0);
            // Alcatra
            addComida(pstmt, "Alcatra", 1, 4, 0, false, 1.0);
            // Fraldinha
            addComida(pstmt, "Fraldinha", 1, 4, 0, false, 1.0);
            // Contra Filé
            addComida(pstmt, "Contra Filé", 1, 4, 0, false, 1.0);
            // File Mignon
            addComida(pstmt, "File Mignon", 1, 5, 0, false, 1.0);
            // Picanha (suína)
            addComida(pstmt, "Picanha Suina", 1, 5, 0, false, 1.0);
            // Lombo
            addComida(pstmt, "Lombo", 1, 5, 0, false, 1.0);
            // Paleta
            addComida(pstmt, "Paleta", 1, 5, 0, false, 1.0);
            // Arroz
            addComida(pstmt, "Arroz", 1, 6, 0, false, 1.0);
            // Farofa
            addComida(pstmt, "Farofa", 1, 6, 0, false, 1.0);
            // Vinagrete
            addComida(pstmt, "Vinagrete", 1, 6, 0, false, 1.0);
            // Coração de Frango
            addComida(pstmt, "Coração de Frango", 1, 6, 0, false, 1.0);
            // Pão de Alho
            addComida(pstmt, "Pão de Alho", 1, 6, 0, false, 1.0);
            // Pão Francês
            addComida(pstmt, "Pão Francês", 1, 6, 0, false, 1.0);
            // Queijo Coalho
            addComida(pstmt, "Queijo Coalho", 1, 6, 0, false, 1.0);
            // Carvão
            addComida(pstmt, "Carvão", 1, 6, 0, false, 1.5);
            // Copo
            addComida(pstmt, "Copo", 3, 0, 0, false, 5.0);
            // Continue com as outras inserções...

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addComida(PreparedStatement pstmt, String nome, int tipoComidaId, int categoriaCarneId, int categoriaSalgadoId, boolean opcaoSemCarne, double aproveitamento) throws SQLException {
        pstmt.setString(1, nome);
        pstmt.setInt(2, tipoComidaId);
        pstmt.setInt(3, categoriaCarneId);
        pstmt.setInt(4, categoriaSalgadoId);
        pstmt.setBoolean(5, opcaoSemCarne);
        pstmt.setDouble(6, aproveitamento);
        pstmt.setString(7, nome);
        pstmt.executeUpdate();
    }
}
