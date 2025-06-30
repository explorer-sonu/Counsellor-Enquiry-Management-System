package in.sonu.dto;

public class ViewEnqsFilterRequest {

	private String courseName;
	private String classMode;
	private String enqStatus;

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getClassMode() {
		return classMode;
	}
	public void setClassMode(String calssMode) {
		this.classMode = calssMode;
	}
	public String getEnqStatus() {
		return enqStatus;
	}
	public void setEnqStatus(String enqStatus) {
		this.enqStatus = enqStatus;
	}

}
