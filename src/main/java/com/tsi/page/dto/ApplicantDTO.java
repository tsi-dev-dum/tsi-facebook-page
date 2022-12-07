package com.tsi.page.dto;

public class ApplicantDTO {
	private String email;
	private String status;
	private String facebookId;
	private String googleId;
	
	public ApplicantDTO() {}
	
	public ApplicantDTO(String email, String status, String facebookId, String googleId) {
		super();
		this.email = email;
		this.status = status;
		this.facebookId = facebookId;
		this.googleId = googleId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	public String getGoogleId() {
		return googleId;
	}
	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}
	
	
}
