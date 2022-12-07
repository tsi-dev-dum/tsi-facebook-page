package com.tsi.page.dto;

public class GmailDTO {
	private String email;
	private String lName;
	private String facebookId;
	
	public GmailDTO() {}
	
	public GmailDTO(String email, String lName, String facebookId) {
		super();
		this.email = email;
		this.lName = lName;
		this.facebookId = facebookId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	
	@Override
	public String toString() {
		return "from:" + this.email + " subject:" + "APPLICANT-"+lName+"-"+facebookId+" is:unread";
	}
}
