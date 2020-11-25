package com.iiitb.controller;

import com.iiitb.beans.JobOffer;
import com.iiitb.beans.Student;
import com.iiitb.service.FileService;
import com.iiitb.service.JobApplicationService;
import com.iiitb.service.JobOfferService;
import com.iiitb.service.StudentService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;

import java.io.FileInputStream;
import java.net.URI;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StudentJobApplicationControllerTest {

    Response response = Mockito.mock(Response.class);
    FileInputStream inputStream = Mockito.mock(FileInputStream.class);


    @InjectMocks
    StudentJobApplicationController studentJobApplicationController= Mockito.spy(new StudentJobApplicationController());

    @Mock
    FileService fileService;

    @Mock
    JobOfferService jobOfferService;

    @Mock
    StudentService studentService;

    @Mock
    JobApplicationService jobApplicationService;

    public StudentJobApplicationControllerTest() throws ParseException {
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /*
    @Test
    public void testApplyForJob() throws Exception {
        when(fileService.upload(Mockito.any(), Mockito.any())).thenReturn(true);
        when(jobOfferService.findById(1)).thenReturn(new JobOffer());
        when(studentService.findByRollNumber(Mockito.anyString())).thenReturn(new Student());
        doNothing().when(jobApplicationService).saveJobApplication(Mockito.any());
        Response.ResponseBuilder responseBuilder = Response.seeOther(new URI("/alumni-placement/offers.html"));
        String expected=responseBuilder.build().toString();
        String actual=studentJobApplicationController.applyForJob(3.2,1,"MT2019126",inputStream,Mockito.anyObject()).toString();
        assertEquals(expected,actual);
    }*/
}
