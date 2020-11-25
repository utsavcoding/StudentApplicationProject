package com.iiitb.controller;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.iiitb.beans.JobApplication;
import com.iiitb.beans.JobOffer;
import com.iiitb.beans.Student;
import com.iiitb.service.FileService;
import com.iiitb.service.JobApplicationService;
import com.iiitb.service.JobOfferService;
import com.iiitb.service.StudentService;

@Path("/application")
public class StudentJobApplicationController {

	FileService fileService=new FileService();
	JobOfferService jobOfferService = new JobOfferService();
	StudentService studentService = new StudentService();
	JobApplicationService jobApplicationService=new JobApplicationService();

	@POST
	@Path("/apply")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response applyForJob(@FormDataParam("minGrade") double minGrade,
							  @FormDataParam("id") Integer id,
							  @FormDataParam("rollNumber") String rollNumber,
							  @FormDataParam("file") InputStream fileInputStream,
            				  @FormDataParam("file") FormDataContentDisposition fileMetaData) throws URISyntaxException{
			JobApplication jobApplication = new JobApplication();
	    	String fileName=fileMetaData.getFileName();
	    	fileService.upload(fileInputStream, fileName);
	    	
	    	InputStream propertyFileStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            Properties properties = new Properties();
	    	JobOffer jobOffer;
			try {
				jobOffer = jobOfferService.findById(id);
				properties.load(propertyFileStream);
	            String uploadPath = properties.getProperty("upload.path");
				jobApplication.setGrade(minGrade);
				jobApplication.setCvPath(uploadPath+fileName);
				Student student = studentService.findByRollNumber(rollNumber);
				jobApplication.setStudent(student);
				jobApplication.setJobOffer(jobOffer);

			jobApplicationService.saveJobApplication(jobApplication);
			} catch (Exception e) {
				Response.seeOther(new URI("/alumni-placement/offers.html")).build();
			} 
		    return Response.seeOther(new URI("/alumni-placement/offers.html")).build();
	}
}
