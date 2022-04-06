import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener{
	private Image fundo;
	private Trem trem;
	private Timer timer;
	private int i = 0;
	public ConsumerThread tr;
	
	public Fase(ConsumerThread tremThread) {
		
		
		setFocusable(true);
		setDoubleBuffered(true);
		this.tr = tremThread;
		ImageIcon referencia = new ImageIcon("imgs\\fundo.png");
		fundo = referencia.getImage();
		trem = new Trem(tr.timeToTravel);
		trem.load();
		
		timer = new Timer(1, this);
		// A cada 1 mili segundo, ele vai executar essa bagaça
		// Então eu quero que em 1 segundo que é 1000 mili segundo ele percorra toda a tela
		// Para percorrer toda a tela eu tenho que andar 1410px a partir de onde a imagem foi criada
		// Ou seja que a cada 1 mili segundo eu tenho que somar x a posição inicial que é -410px para que ele alcance em 1410px em 1000 iterações
		timer.start();
		System.out.println(System.currentTimeMillis());
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(trem.getImagem(), trem.getX(), trem.getY(), this);			
		g.dispose();
	}
	
	public void keyviagem() {
		trem.setDx(1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(trem.getX() <= 1210 && i == 0) { 
			trem.setDx(tr.timeToTravel,false);
			trem.changeImagem(true);
			System.out.println(trem.getX());
			trem.update();
			repaint();
		} else {
			i = 1;
		}
		if(i == 1 && trem.getX() >= -410) {
			trem.setDx(-tr.timeToTravel,true);
			trem.changeImagem(false);
			System.out.println(trem.getX());
			trem.update();
			repaint();
		} else {
			i = 0;
		}
		
//		i++;
//		
//		if(i==((trem.getLargura()+trem.getLargura())+350)){
//			i=0;
//			flag = -flag;
//			System.out.println(System.currentTimeMillis());
//		}
		
		
	}
	
}
