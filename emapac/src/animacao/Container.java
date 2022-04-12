package animacao;

import javax.swing.JFrame;

import animacao_modelo.Fase;

public class Container extends JFrame {
	public Container() {
		
		add(new Fase());
		setTitle("Anime");
		setSize(315, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public static void main(String []args) {
		new Container();
		
		Fase f = new Fase();
		f.setSinal(true);
		
	}
	
	public void ChamaTrem() {
		add(new Fase());
	}
}
