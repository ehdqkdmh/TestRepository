package ch09.exam08;

public class Button {
	//Field
	private OnClickListener onClickListener;
	//Constructor
	//Method
	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}
	public void touch() {
		if(onClickListener != null) {
			onClickListener.onClick();
		}
	}
	//Nested Interface
	interface OnClickListener {
		void onClick();
	}
}
