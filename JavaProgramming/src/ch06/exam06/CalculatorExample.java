package ch06.exam06;

public class CalculatorExample {
	public static void main(String[] args) {
		System.out.println(Calculator.field2);
		Calculator.method2();
		
		Calculator calc = new Calculator();
		System.out.println(calc.field1);
		calc.method1();
	}
}
