import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Trem {
	private int x, y;
	private double dx, dy;
	private Image imagem;
	private double altura, largura;
	private int call;
	private int flag;
	private int time;
	public Trem(int timeToTravel) {
		this.call = 0;
		this.x = -410; //1375
		this.y = 638;
		this.time = timeToTravel;
		
	}
	
	public void load() {
		ImageIcon referencia = new ImageIcon("imgs\\trem_comvagao (1).png");
		imagem = referencia.getImage();
		System.out.println(imagem);
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}
	
	public void update() {
		this.call = this.call + 1;
		x +=dx;
//		this.setX(x+=dx);
		y +=dy;
		System.out.println(this.call);
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
	
	public void changeImagem(boolean going) {
		if(going) {
			ImageIcon referencia = new ImageIcon("imgs\\trem_comvagao (1).png");
			imagem = referencia.getImage();
			this.setImagem(imagem);
		} else {
			ImageIcon referencia = new ImageIcon("imgs\\train.png");
			imagem = referencia.getImage();
			this.setImagem(imagem);			
		}
	}
	
	public void setImagem(Image newImage) {
		this.imagem = newImage;
	}

	public void setDx(double timeMove, boolean going) {
		if(!going) {
			this.dx = 1410 / ((timeMove * 1000 ) / 15);			
		} else {
			this.dx = 1375 / ((timeMove * 1000 ) / 15);
		}
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getLargura() {
		return largura;
	}
	
	
}
