package PracticeThread;

public class JoinInterrupt {
	public static void threadMessage(final String message) {
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s\n", threadName, message);
	}
	
	public static void main(String[] args) {
		Thread thread = new Thread(new SimpleRunnable());
		thread.start();
		
		int waitingCount = 0;
		while(thread.isAlive()) {
			try {
				threadMessage("Still waiting...");
				thread.join(1000);
				waitingCount++;
				if (waitingCount == 5 && thread.isAlive()) {
					threadMessage("Time is up!. It's time to interrupt " + thread.getName());
					thread.interrupt();
					thread.join();
				}
			} catch(Exception e) {
				
			}
		}
	}

}

class SimpleRunnable implements Runnable {
	public void run() {
		String threadName = Thread.currentThread().getName();
		int i = 0;
		while(true) {
			System.out.printf("%s: %d\n", threadName, i);
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				JoinInterrupt.threadMessage("Terminated by Interrupt");
				break;
			}
		}
	}
}

