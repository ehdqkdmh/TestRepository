package ch11.exam09;

public class StringIndexOfExample {
	public static void main(String[] args) {
		String subject = "자바 프로그래밍";
		
		String search = "";
		if(!search.equals("")) {
			int location = subject.indexOf("");
			System.out.println(location);
		}
		
		if(subject.indexOf("자바") != -1) {
			System.out.println("자바와 관련된 책이군요");
		} else {
			System.out.println("자바와 관련없는 책이군요");
		}
	}
}

