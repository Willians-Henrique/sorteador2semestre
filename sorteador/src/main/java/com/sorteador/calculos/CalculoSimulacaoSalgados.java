package com.sorteador.calculos;

import java.util.Map;

public class CalculoSimulacaoSalgados {

    private Map<String, Integer> pessoasPorCategoria;
    private Map<String, Integer> quantidadePadraoPorCategoria;

    // Construtor que aceita dois mapas
    public CalculoSimulacaoSalgados(Map<String, Integer> pessoasPorCategoria, Map<String, Integer> quantidadePadraoPorCategoria) {
        this.pessoasPorCategoria = pessoasPorCategoria;
        this.quantidadePadraoPorCategoria = quantidadePadraoPorCategoria;
    }

    // Método para calcular a quantidade total de salgados
    public void calcularSalgados() {
        int totalSalgados = 0;

        // Iterar pelas categorias de pessoas e calcular a quantidade de salgados necessária
        for (Map.Entry<String, Integer> entry : pessoasPorCategoria.entrySet()) {
            String categoria = entry.getKey();
            int numeroPessoas = entry.getValue();
            int quantidadePorPessoa = quantidadePadraoPorCategoria.getOrDefault(categoria, 0);

            totalSalgados += numeroPessoas * quantidadePorPessoa;
        }

        // Exibir o resultado no console
        System.out.println("Total de salgados necessários: " + totalSalgados);
    }
}
