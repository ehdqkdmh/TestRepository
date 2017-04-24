package ch18.exam10;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleExample {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;

        //how1
        byte[] keycodes = new byte[10];
        int readBytes = is.read(keycodes);
        System.out.println(Arrays.toString(keycodes));
        String str = new String(keycodes, 0, readBytes-2);
        System.out.println(str);
        
        //how2
        Scanner scanner = new Scanner(System.in);
        String str2 = scanner.nextLine();
        System.out.println(str2);
        
        //how3
        Console console = System.console();
        String str3 = console.readLine();
        System.out.println(str3);
    }
}
