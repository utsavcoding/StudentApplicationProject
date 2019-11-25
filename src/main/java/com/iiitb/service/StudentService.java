package com.iiitb.service;

import com.iiitb.beans.Student;
import com.iiitb.repository.StudentRepository;

public class StudentService {
	
	StudentRepository studentRepository=new StudentRepository();

	public Student findByRollNumber(String rollNumber) throws Exception{
		return studentRepository.findByRollNumber(rollNumber);
	}
}
