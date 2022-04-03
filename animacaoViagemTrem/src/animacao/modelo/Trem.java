package animacao.modelo;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Trem {
	private int x, y;
	private int dx, dy;
	private Image imagem;
	private int altura, largura;
	
	public Trem() {
		this.x = -350;
		this.y = 638;
	}
	
	public void load() {
		ImageIcon referencia = new ImageIcon("imgs\\trem_comvagao (1).png");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}
	
	public void update() {
		x +=dx;
		y +=dy;
	}
	
//	public void keyviagem() {
//		for(int i=0; i<500; i++) {
//			dx=0;
//			update();
//		}
//		
//		
//	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getLargura() {
		return largura;
	}
	
	
}
