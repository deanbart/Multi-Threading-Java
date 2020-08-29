package threads;

public class TwoPersonsWithdrawing implements Runnable {
	private BankAccount account = new BankAccount();

	public static void main(String[] args) {
		TwoPersonsWithdrawing job = new TwoPersonsWithdrawing();
		Thread firstThread = new Thread(job);
		Thread secondThread = new Thread(job);
		firstThread.setName("A ");
		secondThread.setName("B ");
		firstThread.start();
		secondThread.start();
	}

	@Override
	public void run() {
		for (int x = 0; x < 10; x++) {
			makeWithdrawal(10);
		}
	}

	public void log(String string) {
		System.out.println(Thread.currentThread().getName() + "" + string);
	}

	private synchronized void makeWithdrawal(int amount) {
		if (account.getBalance() >= amount) {
			log("is about to withdraw");
		}
		try {
			log("is going to sleep");
			Thread.sleep(500);
		} catch (InterruptedException e) {

		}
		log("woke up");
		account.withdraw(amount);
		log("withdrawal complete");
		if (account.getBalance() < 0) {
			log("Overdrawn!!!!!");
		} else {
			log("Not enough Funds!");
		}
	}
}
