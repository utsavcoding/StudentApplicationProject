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
	StudentLoginController studentLoginController=Mockito.spy(new StudentLoginController());
	
	@Mock
	StudentService studentService;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testLoginStudentSuccess() throws Exception {
		String requestJson="{\"rollNumber\":\"MT2019126\",\"password\":\"tiwariutsav\"}";
		Student student=new Student();
		student.setPassword("tiwariutsav");
		student.setRollNumber("MT2019126");
		when(studentService.findByRollNumber(Mockito.anyString())).thenReturn(student);
		String expected="{\"password\":\"tiwariutsav\",\"rollNumber\":\"MT2019126\",\"status\":200}";
		String actual=studentLoginController.loginStudent(requestJson);
		assertEquals(expected, actual);
	}

	@Test
	public void testLoginStudentFailureOne() throws Exception {// to kill greater than
		String requestJson="{\"rollNumber\":\"MT2019126\",\"password\":\"abcd1234\"}";
		Student student=new Student();
		student.setPassword("tiwariutsav");
		student.setRollNumber("MT2019126");
		when(studentService.findByRollNumber(Mockito.anyString())).thenReturn(student);
		String expected="{\"password\":\"abcd1234\",\"rollNumber\":\"MT2019126\",\"status\":201}";
		String actual=studentLoginController.loginStudent(requestJson);
		assertEquals(expected, actual);
	}

	@Test
	public void testLoginStudentFailureTwo() throws Exception { //to kill less than or equal
		String requestJson="{\"rollNumber\":\"MT2019126\",\"password\":\"zahikj\"}";
		Student student=new Student();
		student.setPassword("tiwariutsav");
		student.setRollNumber("MT2019126");
		when(studentService.findByRollNumber(Mockito.anyString())).thenReturn(student);
		String expected="{\"password\":\"zahikj\",\"rollNumber\":\"MT2019126\",\"status\":201}";
		String actual=studentLoginController.loginStudent(requestJson);
		assertEquals(expected, actual);
	}

	@Test
	public void testLoginStudentForException() throws Exception {
		String requestJson="{\"rollNumber\":\"MT2019126\",\"password\":\"abcd1234\"}";
		Student student=new Student();
		student.setPassword("tiwariutsav");
		student.setRollNumber("MT2019126");
		when(studentService.findByRollNumber(Mockito.anyString())).thenThrow(new Exception());
		String expected="{\"password\":\"abcd1234\",\"rollNumber\":\"MT2019126\",\"status\":201}";
		String actual=studentLoginController.loginStudent(requestJson);
		assertEquals(expected, actual);
	}
}
