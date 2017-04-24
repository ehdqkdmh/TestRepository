package ch12.exam07;

public class SumThread extends Thread {
	//Field
	private long sum;
	//Constructor
	//Method
	@Override
	public void run() {
		for(int i=1; i<=100; i++) {
			sum += i;
		}
	}
	public long getSum() {
		return sum;
	}
}
