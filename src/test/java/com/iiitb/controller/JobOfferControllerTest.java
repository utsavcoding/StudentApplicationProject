package com.iiitb.controller;

import com.google.gson.Gson;
import com.iiitb.beans.JobOffer;
import com.iiitb.service.JobOfferService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class JobOfferControllerTest {

    Gson gson=new Gson();

    Response response = Mockito.mock(Response.class);

    @InjectMocks
    JobOfferController jobOfferController= Mockito.spy(new JobOfferController());

    @Mock
    JobOfferService jobOfferService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetAllOffersWhenPresent() throws Exception {
        List<JobOffer> jobOffers=new ArrayList<JobOffer>();
        JobOffer j1=new JobOffer();
        j1.setId(1);
        JobOffer j2=new JobOffer();
        j2.setId(2);
        jobOffers.add(j1);
        jobOffers.add(j2);
        Response.ResponseBuilder responseBuilder = Response.ok(gson.toJson(jobOffers),MediaType.APPLICATION_JSON);
        String expected=responseBuilder.build().toString();
        System.out.println(expected);
        when(jobOfferService.findAllJobOfferByRollNumber(Mockito.any())).thenReturn(jobOffers);
        String actual=jobOfferController.getAllOffers("MT2019126").toString();
        assertEquals(expected,actual);
    }

    @Test
    public void testGetAllOffersWhenNotPresent() throws Exception {
        List<JobOffer> jobOffers=new ArrayList<JobOffer>();
        Response.ResponseBuilder responseBuilder = Response.noContent();
        String expected=responseBuilder.build().toString();
        System.out.println(expected);
        when(jobOfferService.findAllJobOfferByRollNumber(Mockito.any())).thenReturn(jobOffers);
        String actual=jobOfferController.getAllOffers("MT2019126").toString();
        assertEquals(expected,actual);
    }

    @Test
    public void testGetAllOffersWhenNull() throws Exception {
        List<JobOffer> jobOffers=null;
        Response.ResponseBuilder responseBuilder = Response.noContent();
        String expected=responseBuilder.build().toString();
        System.out.println(expected);
        when(jobOfferService.findAllJobOfferByRollNumber(Mockito.any())).thenReturn(jobOffers);
        String actual=jobOfferController.getAllOffers("MT2019126").toString();
        assertEquals(expected,actual);
    }

    @Test
    public void testGetAllOffersForException() throws Exception {
        List<JobOffer> jobOffers=null;
        Response.ResponseBuilder responseBuilder = Response.noContent();
        String expected=responseBuilder.build().toString();
        when(jobOfferService.findAllJobOfferByRollNumber(Mockito.any())).thenThrow(new Exception());
        String actual=jobOfferController.getAllOffers("MT2019126").toString();
        assertEquals(expected,actual);
    }
}
