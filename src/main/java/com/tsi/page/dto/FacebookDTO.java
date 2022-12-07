package com.tsi.page.dto;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tsi.page.enity.Content;

@Generated("jsonschema2pojo")
public class FacebookDTO {

	@SerializedName("version")
	@Expose
	private String version;
	@SerializedName("content")
	@Expose
	private Content content;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public FacebookDTO withVersion(String version) {
		this.version = version;
		return this;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public FacebookDTO withContent(Content content) {
		this.content = content;
		return this;
	}

}
