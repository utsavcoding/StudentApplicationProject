package com.iiitb.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iiitb.beans.JobApplication;
import com.iiitb.utils.DBUtils;

public class JobApplicationRepository {
	
	public void save(JobApplication jobApplication) {
		Session session = DBUtils.getSession();
        Transaction transaction = session.beginTransaction();
        
        session.save(jobApplication);
        
        transaction.commit();
        session.close();
	}
}
