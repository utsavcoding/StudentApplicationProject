package com.iiitb.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.iiitb.beans.Student;
import com.iiitb.utils.DBUtils;

public class StudentRepository {

	public Student findByRollNumber(String rollNumber)throws Exception {
		Session session = DBUtils.getSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "FROM Student WHERE rollNumber = :roll_number";
        Query query = session.createQuery(hql);
        query.setParameter("roll_number", rollNumber);
        Student student = (Student) query.getSingleResult();
        
        transaction.commit();
        session.close();
        return student;
	}
}
