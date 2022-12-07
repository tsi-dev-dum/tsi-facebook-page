package com.tsi.page.enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Applicant {
	
	@Id
	@GeneratedValue(generator = "applicantId")
	private long applicantId;
	@Column
	private String fName;
	@Column
	private String lName;
	@Column
	private String email;
	@Column
	private String status;
	@Column
	private String facebookId;
	@Column
	private String googleId;
	@Column
	private String timeStamp;
	
	public Applicant() {}
	
	public Applicant(long applicantId, String fName, String lName, String email, String status, String facebookId,
			String googleId, String timeStamp) {
		super();
		this.applicantId = applicantId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.status = status;
		this.facebookId = facebookId;
		this.googleId = googleId;
		this.timeStamp = timeStamp;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public long getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
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
