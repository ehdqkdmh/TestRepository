package ch09.exam08;

public class ButtonExample {
	public static void main(String[] args) {
		Button button = new Button();
		button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick() {
				
			}
		});
		button.touch();
	}
}
