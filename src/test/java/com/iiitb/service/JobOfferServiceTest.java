package com.iiitb.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.iiitb.beans.*;
import com.iiitb.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class JobOfferServiceTest {

    Student student1=new Student();
    Domain domain1=new Domain();
    Domain domain2=new Domain();
    Specialization spcl1=new Specialization();
    Organization org1=new Organization();
    Organization org2=new Organization();
    Organization org3=new Organization();

    List<JobOffer> jobOffers=new ArrayList<JobOffer>();

    public JobOfferServiceTest(){
        domain1.setId(1);
        domain1.setBranchCode("CSE");
        domain1.setDomainCode("CSE");
        domain2.setId(2);
        domain2.setBranchCode("ECE");
        domain2.setDomainCode("ECE");

        spcl1.setName("Data Science");
        spcl1.setCode("DS");
        spcl1.setId(1);

        org1.setName("Google");
        org1.setId(1);
        org2.setName("Amazon");
        org2.setId(2);
        org3.setName("Nvidia");
        org3.setId(3);

        student1.setRollNumber("MT2019126");
        JobOffer jb1=new JobOffer();
        jb1.setSpecialization(spcl1);jb1.setDomain(domain1);
        jb1.setMinGrade(3.2);jb1.setOrganization(org1);jb1.setId(1);
        JobOffer jb2=new JobOffer();
        jb2.setDomain(domain1);jb2.setId(2);
        jb2.setMinGrade(3.0);jb2.setOrganization(org2);
        JobOffer jb3=new JobOffer();
        jb3.setDomain(domain2);
        jb3.setOrganization(org3);jb3.setMinGrade(3.2);jb3.setId(3);
        jobOffers.add(jb1);jobOffers.add(jb2);jobOffers.add(jb3);
    }

    @InjectMocks
    JobOfferService jobOfferService= Mockito.spy(new JobOfferService());

    @Mock
    StudentService studentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFilterJobOfferWhenCreditScoreNotSatisfied() throws Exception {
        List<JobOffer> expected=new ArrayList<JobOffer>();
        student1.setDomain(domain1);
        when(studentService.findByRollNumber(Mockito.anyString())).thenReturn(student1);
        when(studentService.fetchCreditByStudentId(Mockito.anyInt())).thenReturn(2.8);
        when(studentService.fetchSpecializationCodeByStudentId(Mockito.anyInt())).thenReturn("DS");
        List<JobOffer> actual=jobOfferService.filterJobOffer(jobOffers,"MT2019126");
        assertEquals(expected, actual);
    }

    @Test
    public void testFilterJobOfferForDomainCode() throws Exception {
        List<JobOffer> expected=new ArrayList<JobOffer>(jobOffers);
        expected.remove(0);
        expected.remove(0);
        student1.setDomain(domain2);
        student1.setJobApplications(new ArrayList<JobApplication>());
        when(studentService.findByRollNumber(Mockito.anyString())).thenReturn(student1);
        when(studentService.fetchCreditByStudentId(Mockito.anyInt())).thenReturn(3.5);
        when(studentService.fetchSpecializationCodeByStudentId(Mockito.anyInt())).thenReturn("DS");
        List<JobOffer> actual=jobOfferService.filterJobOffer(jobOffers,"MT2019126");
        assertEquals(expected, actual);
    }

    @Test
    public void testFilterJobOfferForSpecializationPresent() throws Exception {
        List<JobOffer> expected = new ArrayList<JobOffer>(jobOffers);
        expected.remove(2);
        student1.setDomain(domain1);
        student1.setJobApplications(new ArrayList<JobApplication>());
        when(studentService.findByRollNumber(Mockito.anyString())).thenReturn(student1);
        when(studentService.fetchCreditByStudentId(Mockito.anyInt())).thenReturn(3.5);
        when(studentService.fetchSpecializationCodeByStudentId(Mockito.anyInt())).thenReturn("DS");
        List<JobOffer> actual = jobOfferService.filterJobOffer(jobOffers, "MT2019126");
        assertEquals(expected, actual);
    }

    @Test
    public void testFilterJobOfferForSpecializationNotPresent() throws Exception {
        List<JobOffer> expected = new ArrayList<JobOffer>(jobOffers);
        expected.remove(0);
        expected.remove(1);
        student1.setDomain(domain1);
        student1.setJobApplications(new ArrayList<JobApplication>());
        when(studentService.findByRollNumber(Mockito.anyString())).thenReturn(student1);
        when(studentService.fetchCreditByStudentId(Mockito.anyInt())).thenReturn(3.5);
        when(studentService.fetchSpecializationCodeByStudentId(Mockito.anyInt())).thenReturn(null);
        List<JobOffer> actual = jobOfferService.filterJobOffer(jobOffers, "MT2019126");
        assertEquals(expected, actual);
    }

    @Test
    public void testFilterJobOfferForAlreadyAppliedJobOffer() throws Exception {
        List<JobOffer> expected = new ArrayList<JobOffer>(jobOffers);
        expected.remove(2);
        expected.remove(1);
        student1.setDomain(domain1);
        List<JobApplication> jobApplications=new ArrayList<JobApplication>();
        JobOffer jb1=new JobOffer();
        jb1.setSpecialization(spcl1);jb1.setDomain(domain1);
        jb1.setMinGrade(3.2);jb1.setOrganization(org1);jb1.setId(2);
        JobApplication ja1=new JobApplication();
        ja1.setJobOffer(jb1);
        jobApplications.add(ja1);
        student1.setJobApplications(jobApplications);
        when(studentService.findByRollNumber(Mockito.anyString())).thenReturn(student1);
        when(studentService.fetchCreditByStudentId(Mockito.anyInt())).thenReturn(3.5);
        when(studentService.fetchSpecializationCodeByStudentId(Mockito.anyInt())).thenReturn("DS");
        List<JobOffer> actual = jobOfferService.filterJobOffer(jobOffers, "MT2019126");
        assertEquals(expected, actual);
    }
}
