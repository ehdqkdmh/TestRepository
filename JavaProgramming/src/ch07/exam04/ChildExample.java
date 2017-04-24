package ch07.exam04;

public class ChildExample {
	public static void main(String[] args) {
		Child child = new Child("±è", "±æµ¿");
		System.out.println(child.lastName);
		System.out.println(child.firstName);
		child.sound();
		child.play();
		
		child.parentSound();
	}
}
