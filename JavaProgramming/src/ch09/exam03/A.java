package ch09.exam03;

public class A {
	//Field
	//Constructor
	A() {
		int localVariable = 2;
		//로컬 클래스
		class D {
			//Field
			//Constructor
			//Method
			void dMethod() {
				int result = localVariable + 8;
			}
		}
	}
	//Method
	void aMethod() {
		//로컬 클래스
		class E {
			//Field
			//Constructor
			//Method
			void eMethod() {
			}
		}		
	}
}