import java.util.ArrayList;

import javax.swing.JFrame;

import src.animacao.Container;
import src.animacao.Fase;


public class Executor {
	
	private static ArrayList<ProducerThread> producerThreads;
	private static ArrayList<ConsumerThread> consumerThreads;
	
	//This class simply contains the main method for the program
	//It didn't really seem like it belonged in bounded buffer and it
	//  was easier to manage over here, so there you go
	public static void main(String[] args) throws InterruptedException {
		//Parse the arguments
		int[] timesToProduce = {2,2,2};
		int numProducers = Integer.parseInt("3");
		int numConsumers = Integer.parseInt("1");
		int bufferSize = Integer.parseInt("50");
		int timeToProduce = Integer.parseInt("10");
		int timeToTravel = Integer.parseInt("10");
		int wagonCapacity = Integer.parseInt("2");
		//Create a new buffer of the appropriate size
		BoundedBuffer buffer = new BoundedBuffer(bufferSize);
		//Create and start the producers
		System.out.println("hi");
		producerThreads = new ArrayList<ProducerThread>(numProducers);
		for(int i = 0; i < numProducers; i++) {
			ProducerThread producer = new ProducerThread(buffer, timesToProduce[i], i);
			producerThreads.add(producer);
			producer.start();
		}
//		//Create and start the consumers
		ConsumerThread consumer;
		consumer = new ConsumerThread(buffer,1,timeToTravel,wagonCapacity);
		new Container(consumer);
		System.out.println("hi");
		
	}
}

