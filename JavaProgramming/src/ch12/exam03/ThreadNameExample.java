package ch12.exam03;

public class ThreadNameExample {
	public static void main(String[] args) {
		
		//String threadName = Thread.currentThread().getName();
		Thread mainThread = Thread.currentThread();
		String threadName = mainThread.getName();
		
		System.out.println(threadName + "이 출력한 내용");
		
		WorkThread wt1 = new WorkThread("wt1");
		wt1.start();
		
		WorkThread wt2 = new WorkThread("wt2");
		wt2.start();
		
	}
}
