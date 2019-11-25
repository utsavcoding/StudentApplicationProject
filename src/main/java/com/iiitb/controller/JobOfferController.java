package com.iiitb.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.iiitb.beans.JobOffer;
import com.iiitb.service.JobOfferService;
@Path("/offers")
public class JobOfferController {
	private JobOfferService jobOfferService = new JobOfferService();
    
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAllOffers() {
        List<JobOffer> offers = jobOfferService.findAll();
        System.out.print(offers.size()+"Contr");
        if (offers == null)
            return Response.noContent().build();
        return Response.status(200).entity(offers).build();
    }
}
