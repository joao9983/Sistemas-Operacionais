package src.animacao;

import javax.swing.JFrame;

import src.animacao.*;

public class Container extends JFrame {
	public Container() {
		add(new Fase());
		setTitle("Anime");
		setSize(1024, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(true);	//amppliar janela
		setVisible(true);
	}
	
	public static void main(String []args) {
		new Container();
	}
}
