package animacao;

import javax.swing.JFrame;
import animacao_modelo.FaseTrem;
import logica.ConsumerThread;

public class ContainerTrem extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContainerTrem(ConsumerThread consumer) {
		add(new FaseTrem(consumer));
		setTitle("Anime");
		setSize(1024, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(true);	//amppliar janela
		setVisible(true);
	}
	
}