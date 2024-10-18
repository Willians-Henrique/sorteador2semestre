package com.sorteador.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseConfiguration {

    
	 /**
     * Conecta-se ao banco de dados SQLite e cria as tabelas se ainda não existirem.
     */
     
    public static void initializeDatabase() {
        SQLiteConnection dbConnection = new SQLiteConnection();
        Connection conn = dbConnection.getConnection(); 
        
        	// Conectar ao banco de dados e criar tabelas
        	try (Statement stmt = conn.createStatement()) {	
        		
        		// SQL para criar as tabelas
                String createTableBebidas = "CREATE TABLE IF NOT EXISTS bebidas ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "bebida VARCHAR(255) NOT NULL, "
                    + "tipo INTEGER, "
                    + "FOREIGN KEY (tipo) REFERENCES bebidas_tipos(id)"
                    + ");";

                String createTableBebidasTipos = "CREATE TABLE IF NOT EXISTS bebidas_tipos ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "tipo VARCHAR(255) NOT NULL"
                    + ");";

                String createTableBebidasQuantidadePadrao = "CREATE TABLE IF NOT EXISTS bebidas_quantidade_padrao ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "id_bebidas INTEGER NOT NULL, "
                    + "quantidade INTEGER NOT NULL, "
                    + "FOREIGN KEY (id_bebidas) REFERENCES bebidas(id)"
                    + ");";
                
                String createTablePessoas = "CREATE TABLE IF NOT EXISTS pessoas ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "tipo VARCHAR(255) NOT NULL"
                        + ");";
                
                String createTablePessoasCategoria = "CREATE TABLE IF NOT EXISTS pessoas_categoria ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "categoria VARCHAR(255) NOT NULL"
                        + ");";
                
                String createTiposComidaSQL = "CREATE TABLE IF NOT EXISTS comidas_tipo ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "tipo TEXT NOT NULL"
                        + ");";
                
                String createCategoriaCarneSQL = "CREATE TABLE IF NOT EXISTS comidas_categoria_carne ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "categoria TEXT NOT NULL"
                        + ");";
                
                String createCategoriaSalgadoSQL = "CREATE TABLE IF NOT EXISTS comidas_categoria_salgado ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "categoria TEXT NOT NULL"
                        + ");";
                
                String createComidaSQL = "CREATE TABLE IF NOT EXISTS comidas ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "nome TEXT NOT NULL,"
                        + "id_tipo_comida INTEGER,"
                        + "id_categoria_carne INTEGER,"
                        + "id_categoria_salgado INTEGER,"
                        + "opcao_sem_carne BOOLEAN,"
                        + "aproveitamento REAL,"
                        + "FOREIGN KEY (id_tipo_comida) REFERENCES comidas_tipo(id),"
                        + "FOREIGN KEY (id_categoria_carne) REFERENCES comidas_categoria_carne(id),"
                        + "FOREIGN KEY (id_categoria_salgado) REFERENCES comidas_categoria_salgado(id)"
                        + ");";
                
                String createQuantidadePadraoSQL = "CREATE TABLE IF NOT EXISTS comidas_quantidade_padrao ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "id_pessoas_categoria INTEGER,"
                        + "quantidade_carne INTEGER,"
                        + "quantidade_salgado INTEGER,"
                        + "FOREIGN KEY (id_pessoas_categoria) REFERENCES pessoas_categoria(id)"
                        + ");";
                
                // Executar a criação das tabelas
                stmt.executeUpdate(createTableBebidas);
                stmt.executeUpdate(createTableBebidasTipos);
                stmt.executeUpdate(createTableBebidasQuantidadePadrao);
                stmt.executeUpdate(createTablePessoas);
                stmt.executeUpdate(createTablePessoasCategoria);
                stmt.executeUpdate(createTiposComidaSQL);
                stmt.executeUpdate(createCategoriaCarneSQL);
                stmt.executeUpdate(createCategoriaSalgadoSQL);
                stmt.executeUpdate(createComidaSQL);
                stmt.executeUpdate(createQuantidadePadraoSQL);

             // Inserção dos dados na tabela bebidas_tipos
                String insertTiposSQL = "INSERT INTO bebidas_tipos (tipo) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM bebidas_tipos WHERE tipo = ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertTiposSQL)) {
                    pstmt.setString(1, "Alcoólico");
                    pstmt.setString(2, "Alcoólico");
                    pstmt.executeUpdate();

                    pstmt.setString(1, "Não Alcoólico");
                    pstmt.setString(2, "Não Alcoólico");
                    pstmt.executeUpdate();
                }

                // Inserção dos dados na tabela bebidas
                String insertBebidasSQL = "INSERT INTO bebidas (bebida, tipo) "
                        + "SELECT ?, ? WHERE NOT EXISTS (SELECT 1 FROM bebidas WHERE bebida = ? AND tipo = ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertBebidasSQL)) {
                    pstmt.setString(1, "Cerveja");
                    pstmt.setInt(2, 1);
                    pstmt.setString(3, "Cerveja");
                    pstmt.setInt(4, 1);
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Vinho");
                    pstmt.setInt(2, 1);
                    pstmt.setString(3, "Vinho");
                    pstmt.setInt(4, 1);
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Vodka");
                    pstmt.setInt(2, 1);
                    pstmt.setString(3, "Vodka");
                    pstmt.setInt(4, 1);
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Drink");
                    pstmt.setInt(2, 1);
                    pstmt.setString(3, "Drink");
                    pstmt.setInt(4, 1);
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Cerveja Sem Álcool");
                    pstmt.setInt(2, 2);
                    pstmt.setString(3, "Cerveja Sem Álcool");
                    pstmt.setInt(4, 2);
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Água");
                    pstmt.setInt(2, 2);
                    pstmt.setString(3, "Água");
                    pstmt.setInt(4, 2);
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Suco");
                    pstmt.setInt(2, 2);
                    pstmt.setString(3, "Suco");
                    pstmt.setInt(4, 2);
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Refrigerante");
                    pstmt.setInt(2, 2);
                    pstmt.setString(3, "Refrigerante");
                    pstmt.setInt(4, 2);
                    pstmt.executeUpdate();
                }
                
             // Inserção dos dados na tabela bebidas_quantidade_padrao
                String insertQuantidadePadraoSQL = "INSERT INTO bebidas_quantidade_padrao (id_bebidas, quantidade) "
                        + "SELECT ?, ? WHERE NOT EXISTS (SELECT 1 FROM bebidas_quantidade_padrao WHERE id_bebidas = ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertQuantidadePadraoSQL)) {
                    pstmt.setInt(1, 1);
                    pstmt.setInt(2, 1500);
                    pstmt.setInt(3, 1);
                    pstmt.executeUpdate();
                    
                    pstmt.setInt(1, 2);
                    pstmt.setInt(2, 50);
                    pstmt.setInt(3, 2);
                    pstmt.executeUpdate();
                    
                    pstmt.setInt(1, 3);
                    pstmt.setInt(2, 50);
                    pstmt.setInt(3, 3);
                    pstmt.executeUpdate();
                    
                    pstmt.setInt(1, 4);
                    pstmt.setInt(2, 100);
                    pstmt.setInt(3, 4);
                    pstmt.executeUpdate();
                    
                    pstmt.setInt(1, 5);
                    pstmt.setInt(2, 500);
                    pstmt.setInt(3, 5);
                    pstmt.executeUpdate();
                    
                    pstmt.setInt(1, 6);
                    pstmt.setInt(2, 1000);
                    pstmt.setInt(3, 6);
                    pstmt.executeUpdate();
                    
                    pstmt.setInt(1, 7);
                    pstmt.setInt(2, 500);
                    pstmt.setInt(3, 7);
                    pstmt.executeUpdate();
                    
                    pstmt.setInt(1, 8);
                    pstmt.setInt(2, 1000);
                    pstmt.setInt(3, 8);
                    pstmt.executeUpdate();
                }
                
                // Inserção dos dados na tabela Pessoas
                String insertPessoasSQL = "INSERT INTO pessoas (tipo) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM pessoas WHERE tipo = ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertPessoasSQL)) {
                    pstmt.setString(1, "Come Carne");
                    pstmt.setString(2, "Come Carne");
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Não Come Carne");
                    pstmt.setString(2, "Não Come Carne");
                    pstmt.executeUpdate();
                }
                
                String insertPessoasCategoriaSQL = "INSERT INTO pessoas_categoria (categoria) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM pessoas_categoria WHERE categoria = ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertPessoasCategoriaSQL)) {
                    pstmt.setString(1, "Comilão");
                    pstmt.setString(2, "Comilão");
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Homem");
                    pstmt.setString(2, "Homem");
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Mulher");
                    pstmt.setString(2, "Mulher");
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Criança");
                    pstmt.setString(2, "Criança");
                    pstmt.executeUpdate();
                }
                
                // Inserção dos dados na tabela comidas_tipo
                String insertTiposComidaSQL = "INSERT INTO comidas_tipo (tipo) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM comidas_tipo WHERE tipo = ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertTiposComidaSQL)) {
                    pstmt.setString(1, "Churrasco");
                    pstmt.setString(2, "Churrasco");
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Salgado");
                    pstmt.setString(2, "Salgado");
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Obrigatório");
                    pstmt.setString(2, "Obrigatório");
                    pstmt.executeUpdate();
                }

                // Inserção dos dados na tabela comidas_categoria_carne
                String insertCategoriaCarneSQL = "INSERT INTO comidas_categoria_carne (categoria) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM comidas_categoria_carne WHERE categoria = ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertCategoriaCarneSQL)) {
                    pstmt.setString(1, "Bovino com Osso");
                    pstmt.setString(2, "Bovino com Osso");
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Suíno com Osso");
                    pstmt.setString(2, "Suíno com Osso");
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Frango com Osso");
                    pstmt.setString(2, "Frango com Osso");
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Bovino Sem Osso");
                    pstmt.setString(2, "Bovino Sem Osso");
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Suíno Sem Osso");
                    pstmt.setString(2, "Suíno Sem Osso");
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Agregados");
                    pstmt.setString(2, "Agregados");
                    pstmt.executeUpdate();
                }

                // Inserção dos dados na tabela comidas_categoria_salgado
                String insertCategoriaSalgadoSQL = "INSERT INTO comidas_categoria_salgado (categoria) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM comidas_categoria_salgado WHERE categoria = ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertCategoriaSalgadoSQL)) {
                    pstmt.setString(1, "Assados");
                    pstmt.setString(2, "Assados");
                    pstmt.executeUpdate();
                    
                    pstmt.setString(1, "Fritos");
                    pstmt.setString(2, "Fritos");
                    pstmt.executeUpdate();
                }

                // Inserção dos dados na tabela comida
                String insertComidaSQL = "INSERT INTO comidas (nome, id_tipo_comida, id_categoria_carne, id_categoria_salgado, opcao_sem_carne, aproveitamento) "
                        + "SELECT ?, ?, ?, ?, ?, ? "
                        + "WHERE NOT EXISTS ("
                        + "  SELECT 1 FROM comidas "
                        + "  WHERE nome = ? "
                        + ")";

            	try (PreparedStatement pstmt = conn.prepareStatement(insertComidaSQL)) {
                // Pizza
            	    pstmt.setString(1, "Pizza");
            	    pstmt.setInt(2, 2);
            	    pstmt.setInt(3, 0);
            	    pstmt.setInt(4, 1);
            	    pstmt.setBoolean(5, true);
            	    pstmt.setDouble(6, 1.0);
            	    pstmt.setString(7, "Pizza");  // Verificação no WHERE
            	    pstmt.executeUpdate();
                
                    // Esfirra
                    pstmt.setString(1, "Esfirra");
                    pstmt.setInt(2, 2);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 1);
                    pstmt.setBoolean(5, true);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Esfirra");
                    pstmt.executeUpdate();
                    
                    // Hamburger
                    pstmt.setString(1, "Hamburger");
                    pstmt.setInt(2, 2);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 1);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Hamburger");
                    pstmt.executeUpdate();
                    
                    // Empadinha
                    pstmt.setString(1, "Empadinha");
                    pstmt.setInt(2, 2);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 1);
                    pstmt.setBoolean(5, true);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Empadinha");
                    pstmt.executeUpdate();
                    
                    // Coxinha
                    pstmt.setString(1, "Coxinha");
                    pstmt.setInt(2, 2);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 2);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Coxinha");
                    pstmt.executeUpdate();
                    
                    // Kibe
                    pstmt.setString(1, "Kibe");
                    pstmt.setInt(2, 2);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 2);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Kibe");
                    pstmt.executeUpdate();
                    
                    // Pastel
                    pstmt.setString(1, "Pastel");
                    pstmt.setInt(2, 2);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 2);
                    pstmt.setBoolean(5, true);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Pastel");
                    pstmt.executeUpdate();
                    
                    // Bolinha de Queijo
                    pstmt.setString(1, "Bolinha de Queijo");
                    pstmt.setInt(2, 2);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 2);
                    pstmt.setBoolean(5, true);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Bolinha de Queijo");
                    pstmt.executeUpdate();
                    
                    // Risoles
                    pstmt.setString(1, "Risóles");
                    pstmt.setInt(2, 2);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 2);
                    pstmt.setBoolean(5, true);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Risóles");
                    pstmt.executeUpdate();
                    
                    // Cigarette
                    pstmt.setString(1, "Cigarette");
                    pstmt.setInt(2, 2);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 2);
                    pstmt.setBoolean(5, true);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Cigarette");
                    pstmt.executeUpdate();
                    
                    // Croquete
                    pstmt.setString(1, "Croquete");
                    pstmt.setInt(2, 2);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 2);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Croquete");
                    pstmt.executeUpdate();
                    
                    // Costela Bovina
                    pstmt.setString(1, "Costela Bovina");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 1);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 0.5);
                    pstmt.setString(7, "Costela Bovina");
                    pstmt.executeUpdate();
                    
                    // Prime Rib
                    pstmt.setString(1, "Prime Rib");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 1);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 0.6);
                    pstmt.setString(7, "Prime Rib");
                    pstmt.executeUpdate();
                    
                    // Chuleta Paulista
                    pstmt.setString(1, "Chuleta Paulista");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 1);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 0.6);
                    pstmt.setString(7, "Chuleta Paulista");
                    pstmt.executeUpdate();
                    
                    // Costela Suína
                    pstmt.setString(1, "Costela Suína");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 2);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 0.4);
                    pstmt.setString(7, "Costela Suína");
                    pstmt.executeUpdate();
                    
                    // Bisteca
                    pstmt.setString(1, "Bisteca");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 2);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 0.7);
                    pstmt.setString(7, "Bisteca");
                    pstmt.executeUpdate();
                    
                    // Pernil com Osso
                    pstmt.setString(1, "Pernil com Osso");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 2);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 0.7);
                    pstmt.setString(7, "Pernil com Osso");
                    pstmt.executeUpdate();
                    
                    // Asinha com Coxinha
                    pstmt.setString(1, "Asinha com Coxinha");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 3);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 0.7);
                    pstmt.setString(7, "Asinha com Coxinha");
                    pstmt.executeUpdate();
                    
                    // Tulipa da Asa
                    pstmt.setString(1, "Tulipa da Asa");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 3);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 0.6);
                    pstmt.setString(7, "Tulipa da Asa");
                    pstmt.executeUpdate();
                    
                    // Coxa com Sobrecoxa
                    pstmt.setString(1, "Coxa com Sobrecoxa");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 3);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 0.8);
                    pstmt.setString(7, "Coxa com Sobrecoxa");
                    pstmt.executeUpdate();
                    
                    // Picanha
                    pstmt.setString(1, "Picanha");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 4);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Picanha");
                    pstmt.executeUpdate();
                    
                    // Coxão Mole
                    pstmt.setString(1, "Coxão Mole");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 4);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Coxão Mole");
                    pstmt.executeUpdate();
                    
                    // Alcatra
                    pstmt.setString(1, "Alcatra");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 4);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Alcatra");
                    pstmt.executeUpdate();
                    
                    // Fraldinha
                    pstmt.setString(1, "Fraldinha");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 4);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Fraldinha");
                    pstmt.executeUpdate();
                    
                    // Contra Filé
                    pstmt.setString(1, "Contra Filé");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 4);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Contra Filé");
                    pstmt.executeUpdate();
                    
                    // File Mignon
                    pstmt.setString(1, "File Mignon");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 5);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "File Mignon");
                    pstmt.executeUpdate();
                    
                    // Picanha (suína)
                    pstmt.setString(1, "Picanha Suina");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 5);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Picanha Suina");
                    pstmt.executeUpdate();
                    
                    // Lombo
                    pstmt.setString(1, "Lombo");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 5);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Lombo");
                    pstmt.executeUpdate();
                    
                    // Paleta
                    pstmt.setString(1, "Paleta");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 5);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Paleta");
                    pstmt.executeUpdate();
                    
                    // Arroz
                    pstmt.setString(1, "Arroz");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 6);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Arroz");
                    pstmt.executeUpdate();
                    
                    // Farofa
                    pstmt.setString(1, "Farofa");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 6);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Farofa");
                    pstmt.executeUpdate();
                    
                    // Vinagrete
                    pstmt.setString(1, "Vinagrete");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 6);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Vinagrete");
                    pstmt.executeUpdate();
                    
                    // Coração de Frango
                    pstmt.setString(1, "Coração de Frango");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 6);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Coração de Frango");
                    pstmt.executeUpdate();
                    
                    // Pão de Alho
                    pstmt.setString(1, "Pão de Alho");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 6);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Pão de Alho");
                    pstmt.executeUpdate();
                    
                    // Pão Francês
                    pstmt.setString(1, "Pão Francês");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 6);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Pão Francês");
                    pstmt.executeUpdate();
                    
                    // Queijo Coalho
                    pstmt.setString(1, "Queijo Coalho");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 6);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.0);
                    pstmt.setString(7, "Queijo Coalho");
                    pstmt.executeUpdate();
                    
                    // Carvão
                    pstmt.setString(1, "Carvão");
                    pstmt.setInt(2, 1);
                    pstmt.setInt(3, 6);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 1.5);
                    pstmt.setString(7, "Carvão");
                    pstmt.executeUpdate();
                    
                    // Copo
                    pstmt.setString(1, "Copo");
                    pstmt.setInt(2, 3);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 5.0);
                    pstmt.setString(7, "Copo");
                    pstmt.executeUpdate();
                    
                    // Prato
                    pstmt.setString(1, "Prato");
                    pstmt.setInt(2, 3);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 5.0);
                    pstmt.setString(7, "Prato");
                    pstmt.executeUpdate();
                    
                    // Papel Toalha
                    pstmt.setString(1, "Papel Toalha");
                    pstmt.setInt(2, 3);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 0);
                    pstmt.setBoolean(5, false);
                    pstmt.setDouble(6, 10.0);
                    pstmt.setString(7, "Papel Toalha");
                    pstmt.executeUpdate();
                	}
                

                // Inserção dos dados na tabela comidas_quantidade_padrao
                String insertQuantidadePadraoComidaSQL = "INSERT INTO comidas_quantidade_padrao (id_pessoas_categoria, quantidade_carne, quantidade_salgado) "
                        + "SELECT ?, ?, ? WHERE NOT EXISTS (SELECT 1 FROM comidas_quantidade_padrao WHERE id_pessoas_categoria = ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertQuantidadePadraoComidaSQL)) {
                    pstmt.setInt(1, 1);
                    pstmt.setInt(2, 500);
                    pstmt.setInt(3, 15);
                    pstmt.setInt(4, 1);
                    pstmt.executeUpdate();
                    
                    pstmt.setInt(1, 2);
                    pstmt.setInt(2, 400);
                    pstmt.setInt(3, 12);
                    pstmt.setInt(4, 2);
                    pstmt.executeUpdate();
                    
                    pstmt.setInt(1, 3);
                    pstmt.setInt(2, 300);
                    pstmt.setInt(3, 10);
                    pstmt.setInt(4, 3);
                    pstmt.executeUpdate();
                    
                    pstmt.setInt(1, 4);
                    pstmt.setInt(2, 200);
                    pstmt.setInt(3, 6);
                    pstmt.setInt(4, 4);
                    pstmt.executeUpdate();
                }
                
            System.out.println("Banco de dados e tabelas inicializados.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteConnection.closeConnection(conn);
        }
    }
}
