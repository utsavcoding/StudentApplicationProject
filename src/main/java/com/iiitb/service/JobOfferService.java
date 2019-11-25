package com.iiitb.service;

import java.util.List;

import com.iiitb.beans.JobOffer;
import com.iiitb.repository.JobOfferRepository;

public class JobOfferService {
	
	JobOfferRepository jobOfferRepository = new JobOfferRepository();

	public List<JobOffer> findAll(){
		return jobOfferRepository.findAll();
	};
}
