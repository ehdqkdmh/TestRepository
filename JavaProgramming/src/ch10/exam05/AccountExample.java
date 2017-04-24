package ch10.exam05;

public class AccountExample {
	public static void main(String[] args) {
		Account account = new Account();
		System.out.println("¿‹∞Ì: " + account.getBalance());
		account.deposit(100000);
		System.out.println("¿‹∞Ì: " + account.getBalance());
		try {
			account.withdraw(1000000);
		} catch (BalanceInsufficientException e) {
			//øπø‹ √≥∏Æ ƒ⁄µÂ
			System.out.println(e.getMessage());
		}
		System.out.println("¿‹∞Ì: " + account.getBalance());
	}
}
