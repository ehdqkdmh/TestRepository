package ch11.exam05;

public class Member {
	//Field
	private String id;
	//Constructor
	public Member(String id) {
		this.id = id;
	}
	//Method
	@Override
	public String toString() {
		return id;
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println(id + "°¡ Á¦°ÅµÊ");
	}
}
