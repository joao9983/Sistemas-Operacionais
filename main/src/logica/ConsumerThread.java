package logica;

import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ConsumerThread extends Thread {
	
	private int consumerNumber;
	private int wagonToTravel;
	private int timeToTravel;
	private BoundedBuffer buffer;
	
	//Initialize variables in constructor
	public ConsumerThread(BoundedBuffer buffer, int consumerNumber, int timeToTravel, int wagonToTravel) {
		this.buffer = buffer;
		this.consumerNumber = consumerNumber;
		this.wagonToTravel = wagonToTravel;
		this.timeToTravel = timeToTravel;
	}
	
	public void run() {
		//Until the thread is interrupted...
		while(true) {
			//Wait until the buffer is not empty
			try {
				buffer.acquireFull(wagonToTravel);
				buffer.acquireMutex();
				System.out.println("HIIII");
				//Remove the next item and put it in the box
				System.out.println("Retirando: " + wagonToTravel);
				buffer.removeItem(wagonToTravel);
				buffer.releaseMutex();
				buffer.releaseEmpty(wagonToTravel);
				wait(timeToTravel);
				System.out.println("Cheguei em A");
			} catch (InterruptedException e) {
				//Exit notice, print anything remaining in the box, die
				System.out.println("Thread " + consumerNumber + " exit");
				break;
			}
		}
	}
	
	private boolean wait(int timeToTravel) {
		LocalDateTime dateTime = LocalDateTime.now();
		long time = System.currentTimeMillis();
		while(System.currentTimeMillis() - time < timeToTravel * 2 * 1000) {}
		return true;
	}	
	
}


