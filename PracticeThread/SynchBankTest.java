package PracticeThread;

import java.util.concurrent.locks.*;

public class SynchBankTest {
	public static void main(String[] args) {
		Bank2 b = new Bank2(NACCOUNTS, INITIAL_BALANCE);
		
		for (int i = 0; i < NACCOUNTS; ++i) {
			TransferRunnable2 r = new TransferRunnable2(b, i, INITIAL_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}

	public static int NACCOUNTS = 100;
	public static int INITIAL_BALANCE = 1000;
}


class Bank2 {
	public Bank2(int n, double initialBalance) {
		accounts = new double[n];
		for (int i = 0; i < accounts.length; ++i)
			accounts[i] = initialBalance;
		bankLock = new ReentrantLock();
	}
	
	public void transfer(int from, int to, double amount) {
		bankLock.lock();
		try {
			if (accounts[from] < amount) return;
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf(" %10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf(" Total Balance : %10.2f\n", getTotalBalance());
		}
		finally {
			bankLock.unlock(); 
		}
	}
	
	public double getTotalBalance() {
		bankLock.lock();
		try {
			double sum = 0;
			for (double a: accounts)
				sum += a;
			return sum;
		}
		finally { 
			bankLock.unlock();
		}
	}
	
	public int size() {
		return accounts.length;
	}
	
	private final double[] accounts;
	private Lock bankLock;
}

class TransferRunnable2 implements Runnable {
	public TransferRunnable2(final Bank2 b, final int i, final int initial_balance) {
		this.b = b;
		this.from = i;
		this.maxAmount = initial_balance;
	}
	
	public void run() {
		try {
			while(true) {
				int toAccount = (int) (b.size() * Math.random());
				double amount = maxAmount * Math.random();
				b.transfer(from, toAccount, amount);
				Thread.sleep((int)(DELAY * Math.random()));
			}
		} catch (Exception e) { }
	}
	
	private final Bank2 b;
	private int from;
	private double maxAmount;
	private int DELAY = 10;
}