package com.iiitb.service;

import com.iiitb.beans.JobApplication;
import com.iiitb.repository.JobApplicationRepository;

public class JobApplicationService {
	
	JobApplicationRepository jobApplicationRepository=new JobApplicationRepository();
	
	public void saveJobApplication(JobApplication jobApplication) {
		jobApplicationRepository.save(jobApplication);
	}
}
