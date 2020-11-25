package com.iiitb.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.iiitb.beans.Student;
import com.iiitb.utils.DBUtils;

public class StudentRepository {

    Query query;
    Session session = DBUtils.getSession();

	public Student findByRollNumber(String rollNumber)throws Exception {
        Transaction transaction = session.beginTransaction();
        
        String hql = "FROM Student WHERE rollNumber = :roll_number";
        Query query = session.createQuery(hql);
        query.setParameter("roll_number", rollNumber);
        Student student = (Student) query.getSingleResult();
        
        transaction.commit();
        session.close();
        return student;
	}

	public Double fetchCreditByStudentId(Integer id)throws Exception {
        StringBuilder sql=new StringBuilder();
        sql.append("select avg(credit) from StudentCourse where student_id= ").append(id); 
        query = session.createSQLQuery(sql.toString());
        
        Double credit=(double) query.list().get(0);
        
        session.close();
        return credit;
	}
	
	public String fetchStudentSpecializationDetail(Integer id){
        StringBuilder sql=new StringBuilder();
        sql.append("select student.firstName as name,sum(student_course.credit) as specialization_credit,")
        .append(" spcl.code as specialization_code,spcl.minCredit as min_credit ")
        .append(" from Student student,Course course,Specialization spcl,StudentCourse student_course ")
        .append(" where student_id=").append(id)
        .append(" and student.id=student_course.student_id and course.id=student_course.course_id ")
        .append("and course.specialization_id=spcl.id group by spcl.id");
        
        Query query = session.createSQLQuery(sql.toString());
        
        List<Object> objects=query.list();
        session.close();
        
        if(objects!=null) {
        	Iterator<Object> iterator= objects.iterator();
            while(iterator.hasNext()){
                Object[] tuple= (Object[]) iterator.next();
                double studentCredit=(double)tuple[1];
                double minCredit=(double)tuple[3];
                String code=(String)tuple[2];
                if(studentCredit>=minCredit) return code;
            }
        }
        
		return null;
	}
}
