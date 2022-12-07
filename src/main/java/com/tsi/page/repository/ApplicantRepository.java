package com.tsi.page.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tsi.page.enity.Applicant;

@Repository
public interface ApplicantRepository extends CrudRepository<Applicant, Long> {
	
	@Query("FROM Applicant WHERE facebookId = ?1")
	List<Applicant> getApplicantByFacebookId(String facebookId);
	
	@Query("FROM Applicant WHERE (timeStamp = ?1) AND (status = 'PENDING')")
	List<Applicant> getApplicantByDate(String date);
	
}
