package ch18.exam15;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BufferedExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        byte[] data = "가나다".getBytes();
        
        OutputStream os = new FileOutputStream("src/ch18/exam15/test.txt");
        BufferedOutputStream bos = new BufferedOutputStream(os);
        
        long startTime = System.nanoTime();
        
        for(int i=0; i<1000; i++) {
            bos.write(data);        
        }
        
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);
        
        bos.flush();
        bos.close();        
    }
}
