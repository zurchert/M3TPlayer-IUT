package fr.iutvalence.m3tplayer.launcher;

import javax.swing.SwingUtilities;

import fr.iutvalence.m3tplayer.core.gui.MainWindow;

/**
 * The M3TPlayer applcation's launcher
 */
public class Main {

	/**
	 * Application's main
	 * @param args command-line arguments (unused)
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new MainWindow());
	}

}
