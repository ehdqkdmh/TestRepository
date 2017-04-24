package ch18.exam05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WriteExample {
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException  {
        InputStream is = new FileInputStream("src/ch18/exam05/test.txt");
        OutputStream os = new FileOutputStream("src/ch18/exam05/test2.txt");
       
        byte[] data = new byte[4];
        int readBytes = -1;
        while(true) {
            readBytes = is.read(data);
            if(readBytes == -1) break;
            os.write(data, 0, readBytes);
        }
        
        os.flush();
        os.close();
        is.close();
    }
}
