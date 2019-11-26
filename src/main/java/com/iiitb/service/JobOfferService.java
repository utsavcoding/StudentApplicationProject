package com.iiitb.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.iiitb.beans.JobApplication;
import com.iiitb.beans.JobOffer;
import com.iiitb.beans.Student;
import com.iiitb.repository.JobOfferRepository;

public class JobOfferService {
	
	JobOfferRepository jobOfferRepository = new JobOfferRepository();

	/**
	 * This method filters the job offer as per the student's eligibility
	 * 
	 * @param jobOffers
	 * @param rollNumber
	 * @return
	 * @throws Exception 
	 */
	private List<JobOffer> filterJobOffer(List<JobOffer> jobOffers,String rollNumber) throws Exception{
		StudentService studentService=new StudentService();
		
		Student student=studentService.findByRollNumber(rollNumber);
		
		double studentCredit=studentService.fetchCreditByStudentId(student.getId());
		String spclCode=studentService.fetchSpecializationCodeByStudentId(student.getId());
		
		
		List<JobOffer> filteredJobOffers=new ArrayList<JobOffer>();
		
		for (Iterator<JobOffer> itr = jobOffers.iterator(); itr.hasNext();) {
			JobOffer jobOffer = itr.next();
			if (jobOffer.getDomain().getDomainCode().equals(student.getDomain().getDomainCode())
					&& studentCredit >= jobOffer.getMinGrade()) {
				if (jobOffer.getSpecialization().getCode() != null
						&& jobOffer.getSpecialization().getCode().equals(spclCode))
					filteredJobOffers.add(jobOffer);
				else if (jobOffer.getSpecialization() == null)
					filteredJobOffers.add(jobOffer);
			}
		}
		
		for(Iterator<JobOffer> itr1=filteredJobOffers.iterator();itr1.hasNext();) {
			JobOffer currentJobOffer=itr1.next();
			for(Iterator<JobApplication> itr2=student.getJobApplications().iterator();itr2.hasNext();) {
				JobOffer alreadyApplied=((JobApplication) itr2.next()).getJobOffer();
				if(alreadyApplied.getId()==currentJobOffer.getId())
					itr1.remove();
			}
		}
		
		return filteredJobOffers;
	}
	
	public List<JobOffer> findAllJobOfferByRollNumber(String rollNumber) throws Exception{
		List<JobOffer> jobOffers=filterJobOffer(jobOfferRepository.findAll(),rollNumber);
		return jobOffers;
	};
}
