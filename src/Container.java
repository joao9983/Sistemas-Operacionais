import javax.swing.JFrame;

public class Container extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Container(ConsumerThread consumer) {
		add(new Fase(consumer));
		setTitle("Anime");
		setSize(1024, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(true);	//amppliar janela
		setVisible(true);
	}
	
}
