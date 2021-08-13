package synch;

/**
 * This program is a test for the bank class,
 * which runs correctly while multiple threads access a data structure.
 * @author M42
 */
public class SynchBankTest {
	public static final int NACCOUNTS = 100;
	public static final double INITIAL_BALANCE = 1000;
	public static final double MAX_AMOUNTS = 1000;
	public static final int DELAY = 10;
	
	public static void main(String args[]) {
		Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
		for(int i = 0; i < NACCOUNTS; i++) {
			int fromAccount = i;
			Runnable r = () -> {
				try {
					int toAccount = (int) (bank.size() * Math.random());
					double amount = MAX_AMOUNTS * Math.random();
					bank.transfer(fromAccount, toAccount, amount);
					Thread.sleep((int) (DELAY * Math.random()));
				} catch (InterruptedException e) {
					
				}
			};
			Thread t = new Thread(r);
			t.start();
		}
	}
}
