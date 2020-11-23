package com.iiitb.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.iiitb.beans.Domain;
import com.iiitb.beans.Student;
import com.iiitb.repository.StudentRepository;

public class StudentServiceTest {
	
	@InjectMocks
	StudentService studentService;
	
	@Mock
	StudentRepository studentRepository;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testFindByRollNumber() throws Exception {
		Domain domain=new Domain();
		domain.setId(1);
		Student expected=new Student();
		expected.setEmail("vaibhav.sharma@gmail.com");
		expected.setFirstName("Vaibhav");
		expected.setLastName("Sharma");
		expected.setMiddleName("Bhuwankumar");
		expected.setPassword("sharmavaibhav");
		expected.setRollNumber("MT2019130");
		expected.setDomain(domain);
		when(studentRepository.findByRollNumber("MT2019130")).thenReturn(expected);
		Student actual=studentService.findByRollNumber("MT2019130");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFetchCreditByStudentId() throws Exception {
		Double expected = 3.2;
		when(studentRepository.fetchCreditByStudentId(1)).thenReturn(3.2);
		Double actual=studentService.fetchCreditByStudentId(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFetchSpecializationCodeByStudentId() throws Exception {
		String expected = "DS";
		when(studentRepository.fetchStudentSpecializationDetail(1)).thenReturn("DS");
		String actual=studentService.fetchSpecializationCodeByStudentId(1);
		assertEquals(expected, actual);
	}
}
