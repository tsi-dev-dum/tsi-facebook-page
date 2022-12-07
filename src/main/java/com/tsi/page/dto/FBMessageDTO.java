package com.tsi.page.dto;

import com.tsi.page.enity.MessageFB;
import com.tsi.page.enity.Recipient;

public class FBMessageDTO {
//	public String messaging_type;
    public Recipient recipient;
    public MessageFB message;
    
	public FBMessageDTO () {}
    
	public FBMessageDTO(Recipient recipient, MessageFB message) {
		super();
		this.recipient = recipient;
		this.message = message;
	}
	public Recipient getRecipient() {
		return recipient;
	}
	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}
	public MessageFB getMessage() {
		return message;
	}
	public void setMessage(MessageFB message) {
		this.message = message;
	}
    
    
}
