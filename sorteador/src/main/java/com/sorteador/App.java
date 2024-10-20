package com.sorteador;

import javax.swing.SwingUtilities;

import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.Map;

import com.sorteador.calculos.CalculoSimulacaoSalgados;
import com.sorteador.database.DatabaseConfiguration;
import com.sorteador.database.HibernateUtil;
import com.sorteador.views.MainWindow;

public class App 
{
	
    public static void main( String[] args )
    {
    	
        System.out.println( "Hello World!" );
        
       // DatabaseConfiguration.initializeDatabase();
        
     // Inicializa o Hibernate e a sessão
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        
     // Inicializa a interface gráfica (Swing)
        	SwingUtilities.invokeLater(() -> {
           	MainWindow frame = new MainWindow();
        	frame.setVisible(true);
        });
        	
        	
        
    	// Fechar o SessionFactory quando o aplicativo for fechado
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (sessionFactory != null) {
                sessionFactory.close();
                System.out.println("SessionFactory fechada.");
            }
        }));
    }
}
