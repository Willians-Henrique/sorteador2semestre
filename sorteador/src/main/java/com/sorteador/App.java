package com.sorteador;

import javax.swing.SwingUtilities;

import com.sorteador.database.DatabaseConfiguration;
import com.sorteador.views.MainWindow;

public class App 
{
	
    public static void main( String[] args )
    {
    	

        System.out.println( "Hello World!" );
        
        DatabaseConfiguration.initializeDatabase();
        
        SwingUtilities.invokeLater(() -> {
            MainWindow frame = new MainWindow();
            frame.setVisible(true);
        });
    	
    }
}
