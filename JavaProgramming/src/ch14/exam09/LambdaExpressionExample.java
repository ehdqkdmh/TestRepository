package ch14.exam09;

import java.util.function.IntBinaryOperator;

public class LambdaExpressionExample {
	private static int[] scores = { 92, 95, 87 };
	
	public static void main(String[] args) {
		int  max = maxOrMin( (a, b) -> {
			if(a>=b) return a;
			else return b;
		} );
		System.out.println("max: " + max);
		
		int  min = maxOrMin( (a, b) -> {
			if(a<=b) return a;
			else return b;
		} );
		System.out.println("min: " + min);
	}
	
	public static int maxOrMin(IntBinaryOperator operator) {
		int result = scores[0];
		for(int score : scores) {
			result = operator.applyAsInt(result, score);
		}
		return result;	
	}
}
