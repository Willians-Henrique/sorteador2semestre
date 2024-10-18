package com.sorteador.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.sorteador.config.MainWindowConfig;


public class SimulacaoBebidasChurrasco extends JPanel {
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow; // Referência à MainWindow

	public SimulacaoBebidasChurrasco(MainWindow mainWindow) {
        this.mainWindow = mainWindow; // Armazena a referência 
        
        setLayout(new BorderLayout());

        // Painel principal
        JPanel painelMae = new JPanel();
        painelMae.setLayout(new GridLayout(0, 1)); // Adiciona layout vertical

        // Adicionar componentes de bebidas
        painelMae.add(new JLabel("Escolha as bebidas:"));

        JCheckBox refrigerante = new JCheckBox("Refrigerante");
        JCheckBox cerveja = new JCheckBox("Cerveja");
        JCheckBox agua = new JCheckBox("Água");

        painelMae.add(refrigerante);
        painelMae.add(cerveja);
        painelMae.add(agua);

        // Adicionar botões Voltar, Cancelar, Simular no final
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton voltarButton = new JButton("Voltar");
        JButton cancelarButton = new JButton("Cancelar");
        JButton simularButton = new JButton("Simular");

        painelBotoes.add(voltarButton);
        painelBotoes.add(cancelarButton);
        painelBotoes.add(simularButton);

        // Adiciona os paineis ao painel principal
        add(painelMae, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Lógica para os botões

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.showPanel(new NovaSimulacaoChurrasco(mainWindow));
            }
        });

	     // Botão Cancelar: retorna para a tela inicial
	        cancelarButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("Botão Cancelar clicado!"); // Para verificar se o botão está funcionando
	                mainWindow.showPanel(new MainWindowConfig().getMainPanel()); // Chama o método para mostrar o painel inicial
	            }
	        });



        // Botão Simular: lógica para salvar os dados no banco
        simularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode adicionar a lógica para salvar os dados no banco
                // por exemplo: salvar a escolha das bebidas
                System.out.println("Simulação finalizada. Dados enviados para o banco.");
            }
        });
    }
	public JPanel getPanel() {
        return this; // Retorna o painel para exibição
    }
}
