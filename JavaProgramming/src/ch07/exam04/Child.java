package ch07.exam04;

public class Child extends Parent {
	//Field
	String firstName;
	//Constructor
	Child(String lastName, String firstName) {
		super(lastName);
		this.firstName = firstName;
	}
	//Method
	void play() {
		System.out.println("³î¾Æ¿ä");
	}
	
	@Override
	void sound() {
		System.out.println("³¥³¥");
	}
	
	void parentSound() {
		super.sound();
	}
}
