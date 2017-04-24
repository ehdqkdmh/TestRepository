package ch18.exam09;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class CopyExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Reader reader = new FileReader("src/ch18/exam09/test.txt");
        Writer writer = new FileWriter("src/ch18/exam09/test2.txt");
        
        char[] data = new char[3];
        int readChars = -1;
        while(true) {
            readChars = reader.read(data);
            if(readChars == -1) break;
            writer.write(data, 0, readChars);
        }
        
        writer.flush();
        writer.close();
        reader.close();
    }
}
