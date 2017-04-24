package ch08.exam01;

public class Car implements Manual {
	private int speed;
	
	@Override
	public void turnOn() {
		System.out.println("자동차를 켭니다.");
	}

	@Override
	public void turnOff() {
		System.out.println("자동차를 끕니다.");
	}

	@Override
	public void setSpeed(int speed) {
		System.out.println("속도를 " + speed + "로 변경합니다.");
		if(speed < Manual.MIN_SPEED) {
			this.speed = Manual.MIN_SPEED;
		} else if(speed > Manual.MAX_SPEED) {
			this.speed = Manual.MAX_SPEED;
		} else {
			this.speed = speed;
		}
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public void run() {
		System.out.println("자동차가 " + speed + " 속도로 달립니다.");
	}

}
