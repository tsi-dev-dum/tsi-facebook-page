package com.tsi.page.enity;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tsi.page.dto.ApplicantDTO;

@Generated("jsonschema2pojo")
public class Message {

	@SerializedName("type")
	@Expose
	private String type;
	@SerializedName("text")
	@Expose
	private ApplicantDTO text;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Message withType(String type) {
		this.type = type;
		return this;
	}

	public ApplicantDTO getText() {
		return text;
	}

	public void setText(ApplicantDTO text) {
		this.text = text;
	}

	public Message withText(ApplicantDTO text) {
		this.text = text;
		return this;
	}

}
