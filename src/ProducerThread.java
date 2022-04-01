import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ProducerThread extends Thread {
	
	private int productCount = 1;
	private BoundedBuffer buffer;
	private int timeToProduce;
  private int producerNumber;
	private long t = 5L;
	//Initialize in constructor
	public ProducerThread(BoundedBuffer buffer, int timeToProduce, int producerNumber) {
		this.buffer = buffer;
		this.timeToProduce = timeToProduce;
		this.producerNumber = producerNumber;
	}
	
	public void run() {
		while(true) { // Nunca vai parar
			try {
				//Wait until the buffer is not full
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

	private boolean wait(int timeToProduce) {
		LocalDateTime dateTime = LocalDateTime.now();
		long time = System.currentTimeMillis();
		while(System.currentTimeMillis() - time < timeToProduce * 1000) {}
		return true;
	}		
}
