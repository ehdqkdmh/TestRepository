package ch11.exam13;

public class Member implements Comparable<Member> {
	private String name;
	private int age;
	
	public Member(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return name+"("+ age + ")";
	}

	@Override
	public int compareTo(Member o) {
		/*
		if(age<o.age) {
			return -1;
		} else if(age == o.age) {
			return 0;
		} else {
			return 1;
		}
		*/
		return Integer.compare(age, o.age);
	}
}
