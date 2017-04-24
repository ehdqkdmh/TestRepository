package ch12.exam04;

public class User1 extends Thread {
	//Field
	private Calculator calculator;
	//Constructor
	public User1() {
		super("User1");
	}
	//Method
	@Override
	public void run() {
		calculator.setMemory(100);
	}

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
}
