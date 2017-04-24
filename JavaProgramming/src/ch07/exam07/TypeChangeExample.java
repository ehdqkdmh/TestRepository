package ch07.exam07;

public class TypeChangeExample {
	public static void main(String[] args) {
		Tire tire1 = new Tire();
		NormalTire tire2 = new NormalTire();
		SnowTire tire3 = new SnowTire();
		
		//자동 타입 변환
		tire1 = tire2;
		//강제 타입 변환
		tire2 = (NormalTire) tire1;
		
		//자동 타입 변환
		tire1 = tire3;
		//강제 타입 변환
		tire3 = (SnowTire) tire1;
	}
}
