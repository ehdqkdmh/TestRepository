package ch12.exam08;

public class WaitNotifyExample {
	public static void main(String[] args) {
		//공유 객체 생성
		DataBox dataBox = new DataBox();
		
		//스레드 객체 생성
		ReadThread t1 = new ReadThread(dataBox);
		WriteThread t2 = new WriteThread(dataBox);
		
		//스레드 실행
		t1.start();
		t2.start();
	}
}
