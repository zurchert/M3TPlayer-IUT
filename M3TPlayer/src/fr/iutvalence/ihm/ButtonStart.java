package fr.iutvalence.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.iutvalence.m3tplayer.M3TPlayer;

public class ButtonStart extends JButton implements ActionListener {
	
	public ButtonStart(){
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		M3TPlayer m3t = new M3TPlayer();
		m3t.playMedia();

	}
}
