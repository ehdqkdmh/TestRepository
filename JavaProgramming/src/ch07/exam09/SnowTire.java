package ch07.exam09;

public class SnowTire extends Tire {
	//Field
	//Constructor
	//Method
	@Override
	void roll() {
		System.out.println("스노우 타이어가 굴러갑니다.");
	}
	
	void notSlide() {
		System.out.println("눈에서 미끄러지지 않습니다.");
	}
}
