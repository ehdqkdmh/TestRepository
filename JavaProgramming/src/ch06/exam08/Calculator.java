package ch06.exam08;

public class Calculator {
	private static Calculator singleton = new Calculator();
	
	private Calculator() {
	}
	
	static Calculator getInstance() {
		return singleton;
	}
}
