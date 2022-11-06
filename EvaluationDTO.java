package evaluation;

public class EvaluationDTO {
	int evaluationID;
	String userID;
	String lectureName;
	String professorName;
	int lectureYear;
	String semsterDivide;
	String lectureDivide;
	String evaluationTtitle;
	String evaluationContent;
	String totalScore;
	String creditScore;
	String comfortableScore;
	String lectureScore;
	int likeCount;
	
	public EvaluationDTO () {
		
	}
	public EvaluationDTO(int evaluationID, String userID, String lectureName, String professorName, int lectureYear,
			String semsterDivide, String lectureDivide, String evaluationTtitle, String evaluationContent,
			String totalScore, String creditScore, String comfortableScore, String lectureScore, int likeCount) {
		super();
		this.evaluationID = evaluationID;
		this.userID = userID;
		this.lectureName = lectureName;
		this.professorName = professorName;
		this.lectureYear = lectureYear;
		this.semsterDivide = semsterDivide;
		this.lectureDivide = lectureDivide;
		this.evaluationTtitle = evaluationTtitle;
		this.evaluationContent = evaluationContent;
		this.totalScore = totalScore;
		this.creditScore = creditScore;
		this.comfortableScore = comfortableScore;
		this.lectureScore = lectureScore;
		this.likeCount = likeCount;
	}
	
	public int getEvaluationID() {
		return evaluationID;
	}

	public void setEvaluationID(int evaluationID) {
		this.evaluationID = evaluationID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setlectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public int getLectureYear() {
		return lectureYear;
	}

	public void setLectureYear(int lectureYear) {
		this.lectureYear = lectureYear;
	}

	public String getSemsterDivide() {
		return semsterDivide;
	}

	public void setSemsterDivide(String semsterDivide) {
		this.semsterDivide = semsterDivide;
	}

	public String getLectureDivide() {
		return lectureDivide;
	}

	public void setLectureDivide(String lectureDivide) {
		this.lectureDivide = lectureDivide;
	}

	public String getEvaluationTtitle() {
		return evaluationTtitle;
	}

	public void setEvaluationTtitle(String evaluationTtitle) {
		this.evaluationTtitle = evaluationTtitle;
	}

	public String getEvaluationContent() {
		return evaluationContent;
	}

	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public String getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}

	public String getComfortableScore() {
		return comfortableScore;
	}

	public void setComfortableScore(String comfortableScore) {
		this.comfortableScore = comfortableScore;
	}

	public String getLectureScore() {
		return lectureScore;
	}

	public void setLectureScore(String lectureScore) {
		this.lectureScore = lectureScore;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
}
