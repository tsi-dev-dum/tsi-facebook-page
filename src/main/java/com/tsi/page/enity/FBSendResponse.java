package com.tsi.page.enity;

public class FBSendResponse {
	private String recipient_id;
	private String message_id;
	
	public FBSendResponse() {}
	
	public FBSendResponse(String recipient_id, String message_id) {
		super();
		this.recipient_id = recipient_id;
		this.message_id = message_id;
	}
	public String getRecipient_id() {
		return recipient_id;
	}
	public void setRecipient_id(String recipient_id) {
		this.recipient_id = recipient_id;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
}
