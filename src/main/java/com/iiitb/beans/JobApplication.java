package com.iiitb.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class JobApplication {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotBlank
	private Integer id;
	
	@ManyToOne
	@NotBlank
	private Student student;
	
	@ManyToOne
	@NotBlank
	private JobOffer jobOffer;
	
	@NotBlank
	private double grade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public JobOffer getJobOffer() {
		return jobOffer;
	}

	public void setJobOffer(JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
}
