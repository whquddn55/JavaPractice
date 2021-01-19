package PracticeThread;

public class UnsynchBankTest {
	public static void main(String[] args) {
		Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
		
		for (int i = 0; i < NACCOUNTS; ++i) {
			TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}

	public static int NACCOUNTS = 100;
	public static int INITIAL_BALANCE = 1000;
}


class Bank {
	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		for (int i = 0; i < accounts.length; ++i)
			accounts[i] = initialBalance;
	}
	
	public void transfer(int from, int to, double amount) {
		if (accounts[from] < amount) return;
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf(" Total Balance : %10.2f\n", getTotalBalance());
	}
	
	public double getTotalBalance() {
		double sum = 0;
		for (double a: accounts)
			sum += a;
		return sum;
	}
	
	public int size() {
		return accounts.length;
	}
	
	private final double[] accounts;
}

class TransferRunnable implements Runnable {
	public TransferRunnable(final Bank b, final int i, final int initial_balance) {
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
	
	private final Bank b;
	private int from;
	private double maxAmount;
	private int DELAY = 10;
}