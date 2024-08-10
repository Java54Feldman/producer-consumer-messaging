package telran.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class ProducerSender extends Thread {
	//dispatching functionality
	//two message boxes
	
	private BlockingQueue<String> messageBoxEven;
	private BlockingQueue<String> messageBoxOdd;
	private int nMessages;
	public ProducerSender(BlockingQueue<String> messageBoxEven, BlockingQueue<String> messageBoxOdd, int nMessages) {
		this.messageBoxEven = messageBoxEven;
		this.messageBoxOdd = messageBoxOdd;
		this.nMessages = nMessages;
	}
	public void run() {
		IntStream.rangeClosed(1, nMessages)
		.forEach(i -> {
			try {
				String message = "message" + i;
				if(i % 2 == 0) {
					messageBoxEven.put(message);
				} else {
					messageBoxOdd.put(message);
				}
			} catch (InterruptedException e) {
				// no interrupt
			}
		});
	}
}
