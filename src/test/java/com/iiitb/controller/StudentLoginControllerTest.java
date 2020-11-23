package com.iiitb.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.iiitb.beans.Student;
import com.iiitb.service.StudentService;

public class StudentLoginControllerTest {
	
	@InjectMocks
	StudentLoginController studentLoginController;
	
	@Mock
	StudentService studentService;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testLoginStudent() throws Exception {
		String requestJson="{\"rollNumber\":\"MT2019126\",\"password\":\"tiwariutsav\"}";
		Student student=new Student();
		student.setPassword("tiwariutsav");
		student.setRollNumber("MT2019126");
		when(studentService.findByRollNumber(Mockito.anyString())).thenReturn(student);
		String expected="{\"password\":\"tiwariutsav\",\"rollNumber\":\"MT2019126\",\"status\":200}";
		String actual=studentLoginController.loginStudent(requestJson);
		assertEquals(expected, actual);
	}
}
