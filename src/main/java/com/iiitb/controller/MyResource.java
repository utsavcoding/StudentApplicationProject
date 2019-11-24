package com.iiitb.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iiitb.beans.Course;
import com.iiitb.beans.Specialization;
import com.iiitb.beans.Student;
import com.iiitb.utils.DBUtils;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	DBUtils obj=new DBUtils();
    	Session sess=obj.getSession();
    	
    	/*Specialization specialization=new Specialization();
    	
    	specialization.setName("Data Science");
    	specialization.setMinCredit(20);
    	specialization.setCode("DS");
    	
    	Transaction txn=sess.beginTransaction();
    	
    	
    	sess.save(specialization);
    	
    	txn.commit();*/
    	sess.close();
    	
    	
        return "Got it!";
    }
}
