package ch15.exam01;

import java.util.ArrayList;
import java.util.List;

public class ArrayExample {
	public static void main(String[] args) {
		Student[] array = new Student[3];
		array[0] = new Student("s1");
		array[1] = new Student("s2");
		array[2] = new Student("s3");
		//array[3] = new Student("s4");
		array[1] = null;
		
		List<Student> list = new ArrayList<>();
		for(int i=0; i<100; i++) {
			list.add(new Student("s" + i));
		}
		list.remove(1);
	}
}
