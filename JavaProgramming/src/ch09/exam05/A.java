package ch09.exam05;

public class A {
	//Field
	//Constructor
	A() {
		//로컬 클래스
		class D {}
		D d = new D();
	}
	//Method
	void method() {
		//로컬 클래스
		class E {}	
		E e = new E();
	}
	
	//중첩 멤버 클래스
	class B {}
	static class C {}
}
