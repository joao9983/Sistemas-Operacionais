//package animacao_modelo;
//
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//
//import javax.swing.ImageIcon;
//import javax.swing.JPanel;
//import javax.swing.Timer;
//
//import logica.ConsumerThread;
//
//public class Fase extends JPanel {
//	private Image fundo;
//	private Timer timer;
//	private int i = 0;
//	public ConsumerThread tr;
//	
//	public Fase(ConsumerThread tremThread) {
//		
//		
//		setFocusable(true);
//		setDoubleBuffered(true);
//		this.tr = tremThread;
//		tr.setFas(this);
//		ImageIcon referencia = new ImageIcon("imgs\\fundo.png");
//		fundo = referencia.getImage();	
//		//timer = new Timer(1, this);
//		// A cada 1 mili segundo, ele vai executar essa bagaça
//		// Então eu quero que em 1 segundo que é 1000 mili segundo ele percorra toda a tela
//		// Para percorrer toda a tela eu tenho que andar 1410px a partir de onde a imagem foi criada
//		// Ou seja que a cada 1 mili segundo eu tenho que somar x a posição inicial que é -410px para que ele alcance em 1410px em 1000 iterações
//		//timer.start();
//		tr.load();
//		tr.start();
//	}
//	
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D graficos = (Graphics2D) g;
//		graficos.drawImage(fundo, 0, 0, null);
//		graficos.drawImage(this.tr.getImagem(), this.tr.getX(), this.tr.getY(), this);
//		g.dispose();
//	}
//	
////	@Override
////	public void actionPerformed(ActionEvent e) {
////		// TODO Auto-generated method stub
////		if(this.tr.loaded()) {
////			if(this.tr.getX() <= 1210 && i == 0) { 
////				this.tr.setDx(tr.timeToTravel,false);
////				this.tr.changeImagem(true);
////				this.tr.update(this);
////			} else {
////				i = 1;
////			}
////			if(i == 1 && this.tr.getX() >= -410) {
////				this.tr.setDx(-tr.timeToTravel,true);
////				this.tr.changeImagem(false);
////				this.tr.update(this);
////			} else {
////				i = 0;
////			}
////		}
//		
////		i++;
////		
////		if(i==((trem.getLargura()+trem.getLargura())+350)){
////			i=0;
////			flag = -flag;
////			System.out.println(System.currentTimeMillis());
////		}
//		
//		
//	}

package animacao_modelo;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import animacao.modelo.Trem;
import logica.ProducerThread;

public class Fase extends JPanel implements ActionListener{
	private Image fundo;
	private Trem trem;
	private Timer timer;
	public static boolean Sinal=false;
	public int i = 0, j=0, flag = 4, qtd=3;
	public int menosflag = 0-flag;
	private int distancia = 220/flag;
	private ArrayList<ProducerThread> list;
	private int length;
	
	
	public Fase(ArrayList<ProducerThread> list) {
		
//		setFocusable(true);
//		setDoubleBuffered(true);
		
		ImageIcon referencia = new ImageIcon("imgs\\fundo_deposito.png");
		fundo = referencia.getImage();
		this.list = list;
		int i = 0;
		for(i = 0; i < list.size(); i ++) {
			System.out.println("Hello");
			list.get(i).setFas(this);
			list.get(i).load();
			list.get(i).start();
		}
		//timer = new Timer(30, this);
		//timer.start();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graficos = (Graphics2D) g;
		int i = 0;
		Image img;
		graficos.drawImage(fundo, 0, 0, null);
		for(i = 0;i < list.size(); i++) {
			graficos.drawImage(list.get(i).getImage(), list.get(i).x, list.get(i).y, this);			
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(Sinal==true) {

			if(i<=distancia) {
				trem.setImagem(trem.getEmpacotador().getImage());
				trem.setDx(flag);
				trem.update();
				repaint();
			}
			
			if(i>distancia && i<=(2*distancia)) {
				trem.setImagem(trem.getEmpacotador_voltando().getImage());
				trem.setDx(menosflag);
				trem.update();
				repaint();
			}
			if(i>=2*distancia) {
				trem.setImagem(trem.getEmpacotador_dormindo().getImage());
				trem.setDx(0);
				trem.update();
				repaint();
				i=0;
				j++;
				//Sinal=false;
				
			}
			i++;
		}else {
			trem.setImagem(trem.getEmpacotador_dormindo().getImage());
			trem.setDx(0);
			trem.update();
			repaint();
		}
		if(j>(qtd-1)) {
			Sinal=false;
		}
		

	}

	public void setSinal(boolean sinal) {
		Sinal = sinal;
	}	
	
}

