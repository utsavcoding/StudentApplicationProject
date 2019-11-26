package com.iiitb.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.iiitb.beans.JobOffer;	
import com.iiitb.service.JobOfferService;

@Path("/offers")
public class JobOfferController {
	private JobOfferService jobOfferService = new JobOfferService();
    
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOffers(@QueryParam (value="rollNo") String rollNumber) {
        List<JobOffer> offers;
		try {
			offers = jobOfferService.findAllJobOfferByRollNumber(rollNumber);
			if (offers.isEmpty() && offers == null)
	            return Response.noContent().build();
		} catch (Exception e) {
			return Response.noContent().build();
		}
        Gson gson=new Gson();
        return Response.ok(gson.toJson(offers),MediaType.APPLICATION_JSON).build();
    }
}
