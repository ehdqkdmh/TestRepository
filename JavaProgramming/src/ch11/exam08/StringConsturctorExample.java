package ch11.exam08;

public class StringConsturctorExample {
	public static void main(String[] args)  throws Exception {
		String s1 = "abc";
		String s2 = new String("abc");
		
		char[] charArray = { 'a', 'b', 'c' };
		String s3 = new String(charArray);
		
		byte[] byteArray = { 49, 50, 51 };
		String s4 = new String(byteArray);
		System.out.println(s4);
		
		/*
		byte[] inputData = new byte[100];
		int readByteNo = System.in.read(inputData);
		String strData = new String(inputData, 0, readByteNo-2);
		*/
		
		byte[] byteArray2 = { 49, 50, 51, 52, 53, 54, 55};
		String strData2 = new String(byteArray2, 3, 3);
		System.out.println(strData2);
	}
}
