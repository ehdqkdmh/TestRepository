package ch14.exam03;

public class LambdaExpressionExample {
	public static int a = 5;
	
	public static void main(String[] args) {
		method1( () -> {
			a = 8;
			System.out.println(a);
		} );
		
		int b = 5;
		method1( () -> {
			//b = 8;
			System.out.println(b);
		} );
	}
	
	public static void method1(FunctionalInterface1 i) {
		i.method();
	}
}
