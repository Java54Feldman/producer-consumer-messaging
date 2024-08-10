package telran.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class ConsumerReceiver extends Thread {
	private BlockingQueue<String> messageBoxEven;
	private BlockingQueue<String> messageBoxOdd;
	private static AtomicLong messagesCounter = new AtomicLong();

	public void setMessageBox(BlockingQueue<String> messageBoxEven, BlockingQueue<String> messageBoxOdd) {
		this.messageBoxEven = messageBoxEven;
		this.messageBoxOdd = messageBoxOdd;
	}
	public void run() {
		boolean running = true;
		BlockingQueue<String> messageBox = getId() % 2 == 0 ? messageBoxEven : messageBoxOdd;
		while(running) {
			try {
				String message = messageBox.take();
				processMessage(message);
			} catch (InterruptedException e) {
				running = false;
				processRemainingMessages(messageBox);

			}
		}
	}
	private void processRemainingMessages(BlockingQueue<String> messageBox) {
		while(!messageBox.isEmpty()) {
			processMessage(messageBox.remove());
		}
	}
	private void processMessage(String message) {
		System.out.printf("Thread %s - %s\n", getName(), message);
		messagesCounter.getAndIncrement();
		
	}
	public static long getMessagesCounter() {
		return messagesCounter.get();
	}
}
