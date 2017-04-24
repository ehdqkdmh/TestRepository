package ch06.exam06;

public class CalculatorExample2 {
	public static void main(String[] args) {
		System.out.println(Calculator2.field2);
		Calculator2.method2();
		Calculator2 calc=new Calculator2();
		System.out.println(calc.field1);
		calc.method1();
	}
}
