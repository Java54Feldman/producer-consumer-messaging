package telran.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class ProducerSender extends Thread {
	//TODO
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
				BlockingQueue<String> messageBox = null;
				messageBox = i % 2 == 0 ? messageBoxEven : messageBoxOdd;
				messageBox.put("message" + i);
			} catch (InterruptedException e) {
				// no interrupt
			}
		});
	}
}
