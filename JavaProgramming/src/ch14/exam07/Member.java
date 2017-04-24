package ch14.exam07;

public class Member {
	private String mid;
	private String mname;
	
	public Member(String mid, String mname) {
		this.mid = mid;
		this.mname = mname;
	}
	
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}	
}
