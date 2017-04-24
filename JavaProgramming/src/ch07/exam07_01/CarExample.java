package ch07.exam07_01;

public class CarExample {

	public static void main(String[] args) {
		Car car1= new Car(new SnowTire());
		car1.run();
		
		Car car2=new Car(new NormalTire());
		car2.run();
	}

}
