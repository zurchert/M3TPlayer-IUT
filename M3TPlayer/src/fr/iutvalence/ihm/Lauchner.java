package fr.iutvalence.ihm;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import fr.iutvalence.m3tplayer.M3TPlayer;

public class Lauchner extends JFrame{
	
	public Lauchner(){
		JFrame frame = new JFrame();
		frame.setTitle("M3TPlayer");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ButtonStart play = new ButtonStart();
		frame.add(play);
		frame.setVisible(true);
	}
	
	
	
}
