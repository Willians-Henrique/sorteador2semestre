package com.sorteador.views;

import javax.swing.*;
import java.awt.*;
import com.sorteador.config.MainWindowConfig;
import com.sorteador.views.NovaSimulacao;
import com.sorteador.views.ListarSimulacao;
import com.sorteador.views.Configuracoes;
import com.sorteador.config.ToolBarConfig;

public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPanel; // Painel onde os painéis dinâmicos serão adicionados
    private JPanel mainPanel;

    public MainWindow() {
    	setTitle("Simulador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLayout(new BorderLayout());

        // Configurando a ToolBar e adicionando na parte norte
        JToolBar toolBar = ToolBarConfig.createToolBar();
        add(toolBar, BorderLayout.NORTH);

        // Painel principal onde o conteúdo será trocado
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        
        // Configura as ações dos botões da ToolBar
        configurarAcoesToolBar();
        
        // Exibe o painel inicial
        showPanel(new MainWindowConfig().getMainPanel()); 
        }


    // Configura as ações dos botões da ToolBar
    private void configurarAcoesToolBar() {
        ToolBarConfig.homeButton.addActionListener(e -> showPanel(new MainWindowConfig().getMainPanel()));
        ToolBarConfig.newSimButton.addActionListener(e -> showPanel(new NovaSimulacao().getPanel()));
        ToolBarConfig.listSimButton.addActionListener(e -> showPanel(new ListarSimulacao().getPanel()));
        ToolBarConfig.settingsButton.addActionListener(e -> showPanel(new Configuracoes().getPanel()));
    }


    // Exibe o painel especificado
    public void showPanel(JPanel panel) {
        System.out.println("Mostrando painel: " + panel.getClass().getSimpleName());
        mainPanel.removeAll();
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Mostra o painel de simulação de bebidas
    public void showSimulacaoBebidas() {
        SimulacaoBebidas bebidasPanel = new SimulacaoBebidas(this);
        showPanel(bebidasPanel);
    }

    // Função para outras classes voltarem à home
    public void goToHome() {
        showPanel(new MainWindowConfig().getMainPanel());
    }
}
