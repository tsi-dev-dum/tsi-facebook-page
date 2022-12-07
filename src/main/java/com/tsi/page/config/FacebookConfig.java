package com.tsi.page.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fb")
@ConfigurationPropertiesScan
public class FacebookConfig {
	private String version;
	private String content_type;
	private String page_id;
	private String graph_api;
	private String page_access_token;
	
	public FacebookConfig() {}

	public FacebookConfig(String version, String content_type, String page_id, String graph_api,
			String page_access_token) {
		super();
		this.version = version;
		this.content_type = content_type;
		this.page_id = page_id;
		this.graph_api = graph_api;
		this.page_access_token = page_access_token;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}

	public String getGraph_api() {
		return graph_api;
	}

	public void setGraph_api(String graph_api) {
		this.graph_api = graph_api;
	}

	public String getPage_access_token() {
		return page_access_token;
	}

	public void setPage_access_token(String page_access_token) {
		this.page_access_token = page_access_token;
	}
}
