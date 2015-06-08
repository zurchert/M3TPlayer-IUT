package fr.iutvalence.ihm;

import javax.swing.SwingUtilities;

public class Launcher {

	public static void main(String[] argv){
		SwingUtilities.invokeLater(new MainWindow());
	}
	
}
