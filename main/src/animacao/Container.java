package animacao;

import java.util.ArrayList;

//
//
//import javax.swing.JFrame;
//import animacao_modelo.Fase;
//import logica.ConsumerThread;
//
//public class Container extends JFrame {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	public Container(ConsumerThread consumer) {
//		add(new Fase(consumer));
//		setTitle("Anime");
//		setSize(1024, 750);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
//		this.setResizable(true);	//amppliar janela
//		setVisible(true);
//	}
//	
//}

import javax.swing.JFrame;

import animacao_modelo.Fase;
import logica.ProducerThread;


public class Container extends JFrame {
	private static final long serialVersionUID = 1L;

	public Container(ArrayList<ProducerThread> list) {
		
		add(new Fase(list));
		setTitle("Empacotador");
		setSize(315, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public static void main(String []args) {
		new Container();
		
		Fase f = new Fase();;
		f.setSinal(true);
		
	}
	
	public void ChamaTrem() {
		add(new Fase());
	}
}

