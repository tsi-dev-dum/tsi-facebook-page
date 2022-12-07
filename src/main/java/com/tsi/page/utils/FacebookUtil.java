package com.tsi.page.utils;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tsi.page.dto.ApplicantDTO;
import com.tsi.page.dto.FacebookDTO;
import com.tsi.page.enity.Content;
import com.tsi.page.enity.Message;

@Service
public class FacebookUtil {
	
	public FacebookDTO initFacebookDTO(String type, ApplicantDTO text, String version) {
		FacebookDTO facebookDTO = new FacebookDTO();
		Content content = new Content();
		Message message = new Message();
		message.setType(type);
		message.setText(text);
		List<Message> msgList = content.getMessages();
		msgList.add(message);
		content.setMessages(msgList);
		facebookDTO.setVersion(version);
		facebookDTO.setContent(content);
		return facebookDTO;
	}
	
	
}
