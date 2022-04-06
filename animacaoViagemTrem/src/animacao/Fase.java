package src.animacao;

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

import animacao.modelo.Trem;

public class Fase extends JPanel implements ActionListener{
	private Image fundo;
	private Trem trem;
	private Timer timer;
	private int i = 0, flag = 1;
	
	public Fase() {
		
		setFocusable(true);
		setDoubleBuffered(true);
		
		ImageIcon referencia = new ImageIcon("imgs\\fundo.png");
		fundo = referencia.getImage();
		
		trem = new Trem();
		trem.load();
		
		//keyviagem();

		
		timer = new Timer(1, this);
		timer.start();
		
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
		
		
		if(i<((trem.getLargura()+trem.getLargura())+350)) {
			trem.setDx(flag);
			trem.update();
			repaint();
		}
		
		i++;
		
		if(i==((trem.getLargura()+trem.getLargura())+350)){
			i=0;
			flag = -flag;
		}
		
		
	}
	
}
