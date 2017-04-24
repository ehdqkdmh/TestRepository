package ch18.exam11;

import java.io.File;
import java.io.IOException;

public class FileExample {
    public static void main(String[] args) throws IOException {
        //파일(디렉토리)의 정보 얻기
        File file = new File("src/ch18/exam11/FileExample.java");
        System.out.println(file.exists());
        System.out.println(file.length());
        System.out.println(file.isDirectory());
        
        File dir = new File("C:/Windows");
        System.out.println(dir.exists());
        System.out.println(dir.length());
        System.out.println(dir.isDirectory());
        
        //String[] contents1 = dir.list();
        File[] contents2 = dir.listFiles();
        
        for(File f : contents2) {
            String info = "";
            info += f.getName();
            info += "\t\t";
            info += (f.isDirectory())?"<dir>":"";
            info += "\t\t";
            info += f.length();
            System.out.println(info);
        }
        
        //파일(디렉토리)의 생성 및 삭제
        File file2 = new File("C:/Temp/test.txt");
        file2.createNewFile();
        file2.delete();
        
        File dir2 = new File("C:/Temp/dir2");
        dir2.mkdir();
        
        File dir3 = new File("C:/Temp/dir3/dir4/dir5");
        dir3.mkdirs();
    }
}
