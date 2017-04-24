package ch10.exam03;

public class CatchByExceptionKindExample2 {
	public static void main(String[] args) {
		try {
			String str = null;
			System.out.println(str.length());
			
			int[] scores = { 90, 85 };
			for(int i=0; i<3; i++) { 
				System.out.println(scores[i]);
			}
			
			String strNum = "a";
			int num = Integer.parseInt(strNum);
		} catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
			//内靛1
			System.out.println("抗寇 贸府 内靛2");
		} catch(NumberFormatException e) {
			//内靛2
			System.out.println("抗寇 贸府 内靛1");
		}
	}
}
