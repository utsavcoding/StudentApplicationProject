package com.iiitb.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.iiitb.beans.Student;
import com.iiitb.service.StudentService;

@Path("/student")
public class StudentLoginController {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
	public String loginStudent(String requestJson) {
		JSONObject responseJson=new JSONObject(requestJson);
		StudentService studentService = new StudentService();
		try {
			Student student = studentService.findByRollNumber(responseJson.getString("rollNumber"));
			if(student.getPassword().equals(responseJson.getString("password"))){
				responseJson.put("status", 200);
				responseJson.put("rollNumber",responseJson.get("rollNumber"));
			}
			else
				responseJson.put("status", 201);
		} catch (Exception e) {
			responseJson.put("status", 201);
		}
		return responseJson.toString();
	}
}
