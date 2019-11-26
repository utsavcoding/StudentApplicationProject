package com.iiitb.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.iiitb.beans.JobApplication;
import com.iiitb.beans.JobOffer;
import com.iiitb.beans.Student;
import com.iiitb.service.JobApplicationService;
import com.iiitb.service.StudentService;

@Path("/application")
public class StudentJobApplicationController {
	
	@POST
	@Path("/apply")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String applyForJob(String requestJson) {
		JSONObject applicationJson=new JSONObject(requestJson);
		JSONObject responseJson=new JSONObject();
		JobApplication jobApplication = new JobApplication();
		try {
			StudentService studentService = new StudentService();
			jobApplication.setGrade(applicationJson.getDouble("grade"));
			jobApplication.setCvPath(applicationJson.getString("path"));
			Student student = studentService.findByRollNumber(applicationJson.getString("rollNumber"));
			jobApplication.setStudent(student);
			JobOffer jobOffer = new JobOffer();
			jobOffer.setId(applicationJson.getInt("offerId"));
			jobApplication.setJobOffer(jobOffer);
		} catch (Exception e) {
			responseJson.put("status", 201);
			return responseJson.toString();
		}
		
		JobApplicationService jobApplicationService=new JobApplicationService();
		jobApplicationService.saveJobApplication(jobApplication);
		responseJson.put("status", 200);
		return responseJson.toString();
	}
}

