package com.iiitb.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.iiitb.beans.Course;
import com.iiitb.beans.Domain;
import com.iiitb.beans.JobApplication;
import com.iiitb.beans.JobOffer;
import com.iiitb.beans.Organization;
import com.iiitb.beans.Specialization;
import com.iiitb.beans.Student;

public class DBUtils {
    private static final SessionFactory sessionFactory;
    
    static {
        Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
        try {
            Configuration configuration = new Configuration();
            
			configuration.addAnnotatedClass(Student.class).addAnnotatedClass(Course.class)
					.addAnnotatedClass(Specialization.class).addAnnotatedClass(Domain.class)
					.addAnnotatedClass(JobOffer.class).addAnnotatedClass(JobApplication.class)
					.addAnnotatedClass(Organization.class);
            
            sessionFactory = configuration.buildSessionFactory();
        }
        catch (HibernateException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}
