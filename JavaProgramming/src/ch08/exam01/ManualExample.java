package ch08.exam01;

public class ManualExample {
	public static void main(String[] args) {
		Manual manual = new Car();
		manual.turnOn();
		manual.setSpeed(10);
		manual.run();
		manual.turnOff();
		
		move(new Bike());
		move(new Car());
	}
	
	public static void move(Manual manual) {
		manual.turnOn();
		manual.setSpeed(10);
		manual.run();
		manual.turnOff();
	}
}
