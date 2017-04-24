package ch12.exam08;

public class ReadThread extends Thread {
	//Field
	private DataBox dataBox;
	//Constructor
	public ReadThread(DataBox dataBox) {
		this.dataBox = dataBox;
	}
	//Method
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			String data = dataBox.getData();
		}
	}
}
