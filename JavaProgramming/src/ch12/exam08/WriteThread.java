package ch12.exam08;

public class WriteThread extends Thread {
	//Field
	private DataBox dataBox;
	//Constructor
	public WriteThread(DataBox dataBox) {
		this.dataBox = dataBox;
	}	
	//Method
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			dataBox.setData(String.valueOf(i));
		}		
	}
}
