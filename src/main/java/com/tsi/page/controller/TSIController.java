package com.tsi.page.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tsi.page.config.FacebookConfig;
import com.tsi.page.dto.ApplicantDTO;
import com.tsi.page.dto.FBMessageDTO;
import com.tsi.page.dto.FacebookDTO;
import com.tsi.page.dto.GmailDTO;
import com.tsi.page.enity.Applicant;
import com.tsi.page.enity.DefaultMessage;
import com.tsi.page.enity.FBSendResponse;
import com.tsi.page.enity.MessageFB;
import com.tsi.page.enity.Recipient;
import com.tsi.page.service.ApplicantService;
import com.tsi.page.utils.FacebookUtil;
import com.tsi.page.utils.GmailAPI;

@RestController
@RequestMapping("/api/v1")
public class TSIController {
	@Autowired
	private ApplicantService applicantService;
	@Autowired
	private GmailAPI gmailAPI;
	@Autowired
	private FacebookUtil facebookUtil;
	@Autowired
	private FacebookConfig facebookConfig;
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/applicants", method = RequestMethod.POST)
	public ResponseEntity<FacebookDTO> addApplicant(@RequestBody Applicant applicant) {
		if (!applicantService.getApplicantByFacebooId(applicant.getFacebookId()).isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		Applicant applicantResp = applicantService.save(applicant);
		ApplicantDTO applicantDTO = new ApplicantDTO();
		BeanUtils.copyProperties(applicantResp, applicantDTO);
		FacebookDTO fbDTO = facebookUtil.initFacebookDTO(facebookConfig.getContent_type(), applicantDTO,
				facebookConfig.getVersion());
		return ResponseEntity.ok(fbDTO);
	}

	@RequestMapping(value = "/applicants/{facebookId}", method = RequestMethod.GET)
	public ResponseEntity<FacebookDTO> getApplicantThreadId(@PathVariable String facebookId)
			throws IOException, GeneralSecurityException {
		FacebookDTO fbDTO = facebookUtil.initFacebookDTO(facebookConfig.getContent_type(), null,
				facebookConfig.getVersion());
		List<Applicant> applicants = applicantService.getApplicantByFacebooId(facebookId);
		if (applicants.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		GmailDTO gmailDTO = new GmailDTO();
		BeanUtils.copyProperties(applicants.get(0), gmailDTO);
		String threadId = gmailAPI.checkMessage(gmailAPI.getAuthorizedService(), gmailDTO.toString());
		if (threadId.length() <= 0) {
			return ResponseEntity.badRequest().build();
		} else {
			applicants.get(0).setGoogleId(threadId);
			Applicant applicant = applicantService.save(applicants.get(0));
			ApplicantDTO applicantDTO = new ApplicantDTO();
			BeanUtils.copyProperties(applicant, applicantDTO);
			fbDTO.getContent().getMessages().get(0).setText(applicantDTO);
			return ResponseEntity.ok(fbDTO);
		}
	}

	@RequestMapping(value = "/list/applicants", method = RequestMethod.GET)
	public ResponseEntity<List<Applicant>> getApplicantListBydate(@RequestParam(required = false) String date) {
		return ResponseEntity.ok(applicantService.getApplicantByDate(date));
	}

	@RequestMapping(value = "/applicants/stage/{facebookId}", method = RequestMethod.PUT)
	public ResponseEntity<ApplicantDTO> updateApplicantStatus(@PathVariable String facebookId,
			@RequestParam(required = true) String status) {
		List<Applicant> applicantList = applicantService.getApplicantByFacebooId(facebookId);
		if (applicantList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Applicant applicant = applicantList.get(0);
		applicant.setStatus(status);
		ApplicantDTO applicantDTO = new ApplicantDTO();
		BeanUtils.copyProperties(applicant, applicantDTO);
		applicantService.save(applicant);
		return ResponseEntity.ok(applicantDTO);
	}

	@RequestMapping(value = "/applicants/googldid/{facebookId}")
	public ResponseEntity<ApplicantDTO> setApplicantGoogleId(@PathVariable String facebookId,
			@RequestParam(required = true) String thread) {
		List<Applicant> applicantList = applicantService.getApplicantByFacebooId(facebookId);
		if (applicantList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Applicant applicant = applicantList.get(0);
		applicant.setGoogleId(thread);
		ApplicantDTO applicantDTO = new ApplicantDTO();
		BeanUtils.copyProperties(applicant, applicantDTO);
		applicantService.save(applicant);
		return ResponseEntity.ok(applicantDTO);
	}

	@RequestMapping(value = "/notifications/applicants/{facebookId}", method = RequestMethod.POST)
	public ResponseEntity<FBSendResponse> sendReadNotification(@PathVariable String facebookId) {
		List<Applicant> applicantList = applicantService.getApplicantByFacebooId(facebookId);
		if(applicantList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		FBMessageDTO fbMessageDTO = new FBMessageDTO();
		Recipient recipient = new Recipient(facebookId);
		MessageFB messageFB = new MessageFB(DefaultMessage.VIEWED_EMAIL);
		fbMessageDTO.setMessage(messageFB);
		fbMessageDTO.setRecipient(recipient);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<FBMessageDTO> entity = new HttpEntity<FBMessageDTO>(fbMessageDTO, headers);
		return ResponseEntity.ok(restTemplate.exchange(facebookConfig.getGraph_api(), HttpMethod.POST, entity, FBSendResponse.class).getBody());
	}

}
