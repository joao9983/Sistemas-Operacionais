
import java.util.ArrayList;


public class Executor {
	
	private static ArrayList<ProducerThread> producerThreads;
	private static ArrayList<ConsumerThread> consumerThreads;
	
	//This class simply contains the main method for the program
	//It didn't really seem like it belonged in bounded buffer and it
	//  was easier to manage over here, so there you go
	public static void main(String[] args) throws InterruptedException {
		//Parse the arguments
		int[] timesToProduce = {1,1,5};
		int numProducers = Integer.parseInt("3");
		int numConsumers = Integer.parseInt("1");
		int bufferSize = Integer.parseInt("12");
		int timeToProduce = Integer.parseInt("2");
		int timeToTravel = Integer.parseInt("5");
		int wagonCapacity = Integer.parseInt("10");
		//Create a new buffer of the appropriate size
		BoundedBuffer buffer = new BoundedBuffer(bufferSize);
		//Create and start the producers
		producerThreads = new ArrayList<ProducerThread>(numProducers);
		for(int i = 0; i < numProducers; i++) {
			ProducerThread producer = new ProducerThread(buffer, timesToProduce[i], i);
			producerThreads.add(producer);
			producer.start();
		}
		//Create and start the consumers
		for(int i = 0; i < numConsumers; i++) {
			ConsumerThread consumer = new ConsumerThread(buffer,i,timeToTravel,wagonCapacity);
			consumer.start();
		}
	
	}
}

//producerThreads = new ArrayList<ProducerThread>(numProducers);
//for(int i = 0; i < numProducers; i++) {
	//ProducerThread producer = new ProducerThread(buffer, timeToProduce, i);
	//producerThreads.add(producer);
	//producer.start();
//}
//Create and start the consumers
//for(int i = 0; i < numConsumers; i++) {
//	ConsumerThread consumer = new ConsumerThread(buffer,i,timeToTravel,wagonCapacity);
//	consumer.start();
//}

