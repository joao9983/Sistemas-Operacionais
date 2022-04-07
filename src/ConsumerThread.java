import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class ConsumerThread extends Thread {
	
	private int consumerNumber;
	private int wagonToTravel;
	public int timeToTravel;
	private BoundedBuffer buffer;
	private int x, y;
	private double dx, dy;
	private Image imagem;
	private double altura, largura;
	private int call;
	private int flag;
	private int time;
	private boolean start;
	
	//Initialize variables in constructor
	public ConsumerThread(BoundedBuffer buffer, int consumerNumber, int timeToTravel, int wagonToTravel) {
		this.buffer = buffer;
		this.consumerNumber = consumerNumber;
		this.wagonToTravel = wagonToTravel;
		this.timeToTravel = timeToTravel;
		this.call = 0;
		this.x = -410; //1375
		this.y = 638;
		this.time = timeToTravel;
		this.start = false;
	}
	
	public void run() {
		//Until the thread is interrupted...
		while(true) {
			//Wait until the buffer is not empty
			try {
				buffer.acquireFull(wagonToTravel);
				buffer.acquireMutex();
				System.out.println("HIIII");
				/* trem = new Trem();
				trem.load(); */
				//Remove the next item and put it in the box
				System.out.println("Retirando: " + wagonToTravel);
				buffer.removeItem(wagonToTravel);
				buffer.releaseMutex();
				buffer.releaseEmpty(wagonToTravel);
				this.start = true;
				this.load();
				wait(timeToTravel);
				System.out.println("Cheguei em A");
				this.start = false;
			} catch (InterruptedException e) {
				//Exit notice, print anything remaining in the box, die
				System.out.println("Thread " + consumerNumber + " exit");
				break;
			}
		}
	}
	
	public void load() {
		ImageIcon referencia = new ImageIcon("imgs\\trem_comvagao (1).png");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}
	
	public void update() {
		this.call = this.call + 1;
		x +=dx;
//		this.setX(x+=dx);
		y +=dy;
	}

	public boolean loaded() {
		return this.start;
	}
	private boolean wait(int timeToTravel) {
		LocalDateTime dateTime = LocalDateTime.now();
		long time = System.currentTimeMillis();
		while(System.currentTimeMillis() - time < timeToTravel * 2 * 1000) {}
		return true;
	}	

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


