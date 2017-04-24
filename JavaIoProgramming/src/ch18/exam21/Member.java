package ch18.exam21;

import java.io.Serializable;

public class Member implements Serializable {
    //Field
    private String name;
    private int age;
    public static String nation = "한국";
    //Constructor
    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }    
    //Method
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
