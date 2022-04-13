package animacao_modelo;

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
	public static boolean Sinal=false;
	public int i = 0, flag = 40;
	public int menosflag = 0-flag;
	private int distancia = 1700/flag;
	
	public Fase() {
		
//		setFocusable(true);
//		setDoubleBuffered(true);
		
		ImageIcon referencia = new ImageIcon("imgs\\fundo.png");
		fundo = referencia.getImage();
		
		trem = new Trem();
		trem.load();
		
		timer = new Timer(30, this);
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(trem.getImagem(), trem.getX(), trem.getY(), this);
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(Sinal==true) {

			if(i<=distancia) {
				trem.setDx(flag);
				trem.update();
				repaint();
			}
			
			if(i>distancia && i<=(2*distancia)) {
				trem.setDx(menosflag);
				trem.update();
				repaint();
			}
			if(i>=2*distancia) {
				trem.setDx(0);
				trem.update();
				repaint();
				i=0;
				Sinal=false;
				
			}
			i++;
		}
		
	}

	public static void setSinal(boolean sinal) {
		Sinal = sinal;
	}
	
	
	
}
