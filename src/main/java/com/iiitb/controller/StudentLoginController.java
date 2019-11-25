package com.iiitb.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

@Path("/student")
public class StudentLoginController {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
	public String loginStudent(String requestJson) {
		JSONObject responseJson=new JSONObject();
		
		responseJson.put("status", 200);
		
		return responseJson.toString();
	}
	
}
