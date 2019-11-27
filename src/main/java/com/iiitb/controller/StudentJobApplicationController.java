package com.iiitb.controller;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONObject;

import com.iiitb.beans.JobApplication;
import com.iiitb.beans.JobOffer;
import com.iiitb.beans.Student;
import com.iiitb.service.FileService;
import com.iiitb.service.JobApplicationService;
import com.iiitb.service.JobOfferService;
import com.iiitb.service.StudentService;

@Path("/application")
public class StudentJobApplicationController {
	
	@POST
	@Path("/apply")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response applyForJob(@FormDataParam("minGrade") double minGrade,
							  @FormDataParam("id") Integer id,
							  @FormDataParam("rollNumber") String rollNumber,
							  @FormDataParam("file") InputStream fileInputStream,
            				  @FormDataParam("file") FormDataContentDisposition fileMetaData){
			JSONObject responseJson=new JSONObject();
			JobApplication jobApplication = new JobApplication();
	    	String fileName=fileMetaData.getFileName();
	    	FileService fileService=new FileService();
	    	fileService.upload(fileInputStream, fileName);
	    	JobOfferService jobOfferService = new JobOfferService();
	    	JobOffer jobOffer;
			try {
				jobOffer = jobOfferService.findById(id);
				System.out.print(jobOffer.getId());
			
		    	StudentService studentService = new StudentService();
				jobApplication.setGrade(minGrade);
				jobApplication.setCvPath("/home/vinay/eclipse-workspace/StudentApplicationProject/src/main/webapp/CV"+fileName);
				Student student = studentService.findByRollNumber(rollNumber);
				jobApplication.setStudent(student);
				jobApplication.setJobOffer(jobOffer);
			
			JobApplicationService jobApplicationService=new JobApplicationService();
			jobApplicationService.saveJobApplication(jobApplication);
			responseJson.put("status", 200);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				responseJson.put("status", 201);
			} 
		    java.net.URI uri=UriBuilder.fromUri("http://localhost:8080/alumni-placement/offers.html").build();
		    return Response.status(301).location(uri).build();
	}
}

