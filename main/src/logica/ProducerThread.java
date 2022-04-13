package logica;

import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

import animacao_modelo.Fase;

import java.awt.Image;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ProducerThread extends Thread {
	
	private int productCount = 1;
	private BoundedBuffer buffer;
	private int timeToProduce;
	private int producerNumber;
	private long t = 5L;
	public int x, y;
	private double dx, dy;
	private Fase fas;
	private Image imagem;
	private ImageIcon empacotador = new ImageIcon("imgs\\empacotador.png");
	private ImageIcon empacotador_voltando = new ImageIcon("imgs\\empacotador_voltando.png");
	private ImageIcon empacotador_dormindo = new ImageIcon("imgs\\empacotador_dormindo.png");
	private int altura, largura;
	private double totalMoved;
	//Initialize in constructor
	public ProducerThread(BoundedBuffer buffer, int timeToProduce, int producerNumber,int y) {
		this.buffer = buffer;
		this.timeToProduce = timeToProduce;
		this.producerNumber = producerNumber;
		this.x = 25;
		this.y = y;
		this.totalMoved = 0;
	}
	
	public void run() {
		while(true) { // Nunca vai parar
			try {
				//Wait until the buffer is not full
				if(buffer.empty.availablePermits() != 0) { 
					setImagem("trabalhar"); 
					fas.repaint();
				} else {
					setImagem("dormindo");
					fas.repaint();
				}
				wait(timeToProduce);
				buffer.acquireEmpty();
				buffer.acquireMutex();
				//Wait until no one else is using it and prevent other access
				//Add the produced item
				System.out.println("Adicionando ao buffer");
				buffer.addItem();
				System.out.println("Buffer depois de adicionado:");
				buffer.printBuffer();
				//Release semaphores
				buffer.releaseFull();			
				buffer.releaseMutex();					
			} catch (InterruptedException e) {
				//Should never happen. Prints out if it does.
				System.out.println("Producer thread interrupted!");
			}
		}
	}
	
	public void load() {
		this.imagem = empacotador.getImage();
	}
	
	public Image getImage() {
		return this.imagem;
	}
	
	public void setFas(Fase fas) {
		this.fas = fas;
	}

	public void update(Fase fas,int times) {
		totalMoved = totalMoved + (times * dx);
		System.out.print("Movido: ");
		System.out.println(totalMoved);
		x = ((int) totalMoved);
//		this.setX(x+=dx);
		y +=dy;
		fas.repaint();
	}
	
	public void setImagem(String name) {
		if(name == "dormindo") {
			this.imagem = empacotador_dormindo.getImage();
		} else if (name == "trabalhar") {
			this.imagem = empacotador.getImage();
		} else if (name == "voltando") {
			this.imagem = empacotador_voltando.getImage();
		}
	}
	public void setDx(boolean going) {
		if(!going) {
			this.dx = (double) (50) - 25 / ((timeToProduce/2) * 1000 );			
		} else {
			//System.out.println(timeToTravel * (( 1010 - (- 410) ) / 1000 ));
			this.dx = (double)  (25 - (50)) / ((timeToProduce/2) * 1000 );
		}
	}
	
	private boolean wait(int timeToProduce) {
		long time = System.currentTimeMillis();
		long time2 = time; 
		if(timeToProduce == 1) {
			while(System.currentTimeMillis() - time < 500) {
				while(System.currentTimeMillis() - time2 < 10) {}
				time2 = System.currentTimeMillis();
				update(fas,10);
				//System.out.println(call);
			}
			System.out.println("Passou primeira parte");
			time = System.currentTimeMillis();
			time2 = time;
			setDx(true);
			setImagem("voltando");
			while(System.currentTimeMillis() - time < 500) {
				while(System.currentTimeMillis() - time2 < 10) {}
				time2 = System.currentTimeMillis();
				update(fas,10);
			}
		} else if ( timeToProduce % 2 != 0){
			int timeToBack = (timeToProduce - 2 * ((int) (timeToProduce / 2 )));
			
			while(System.currentTimeMillis() - time < (timeToProduce - timeToBack) * 1000) {
				while(System.currentTimeMillis() - time2 < 10) {}
				time2 = System.currentTimeMillis();
				update(fas,10);
				//System.out.println(call);
			}
			System.out.println("Passou primeira parte");
			time = System.currentTimeMillis();
			time2 = time;
			setDx(true);
			setImagem("voltando");
			while(System.currentTimeMillis() - time < timeToBack * 1000) {
				while(System.currentTimeMillis() - time2 < 10) {}
				time2 = System.currentTimeMillis();
				update(fas,10);
			}
		} else {
			setDx(false);
			setImagem("trabalhando");
			while(System.currentTimeMillis() - time < (timeToProduce/2) * 1000) {
				while(System.currentTimeMillis() - time2 < 10) {}
				time2 = System.currentTimeMillis();
				update(fas,10);
				//System.out.println(call);
			}
			System.out.println("Passou primeira parte");
			time = System.currentTimeMillis();
			time2 = time;
			setDx(true);
			setImagem("voltando");
			while(System.currentTimeMillis() - time < (timeToProduce/2) * 1000) {
				while(System.currentTimeMillis() - time2 < 10) {}
				time2 = System.currentTimeMillis();
				update(fas,10);
			}
		}

		// Atualizar a cada x ms, encontrar quanto que atualiza a cada 1ms
		// mas atualizar somente a cada y ms, o que faria o valor de 
		// att ser y * dx;
		return true;
	}		
}
