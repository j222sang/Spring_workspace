package www.dream.com.party.model;

public enum ContactPointType {
	addr("gg"), phoneNum("dsf"), mobileNum("");
	
	private String validatingRegEx; // 검사 유효성 검사용도 정규식
	
	private ContactPointType(String validatingRegEx) {
		this.validatingRegEx = validatingRegEx;
	}
}
