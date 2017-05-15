package smarthomepanel.notify;

public class Data {

	private String date;
	private String noticePlace;
	private String title;
	private String contents;

	public Data(String date, String noticePlace, String title, String contents) {
		this.date = date;
		this.noticePlace = noticePlace;
		this.title = title;
		this.contents = contents;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNoticePlace() {
		return noticePlace;
	}

	public void setNoticePlace(String noticePlace) {
		this.noticePlace = noticePlace;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
