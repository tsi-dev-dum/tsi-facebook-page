package com.tsi.page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsi.page.enity.Applicant;
import com.tsi.page.repository.ApplicantRepository;

@Service
public class ApplicantService {
	@Autowired
	private ApplicantRepository applicantRepository;
	
	public Applicant save(Applicant applicant) {
		applicant.setTimeStamp(java.time.LocalDate.now().toString());
		return applicantRepository.save(applicant);
	}
	
	public List<Applicant> getApplicantByFacebooId(String facebookId){
		return applicantRepository.getApplicantByFacebookId(facebookId);
	}
	
	public List<Applicant> getApplicantByDate(String date){
		if(date.isBlank() || date.isEmpty()) {
			date = java.time.LocalDate.now().toString();
		}
		return applicantRepository.getApplicantByDate(date);
	}
	
}
