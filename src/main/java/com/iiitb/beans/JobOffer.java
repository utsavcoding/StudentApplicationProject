package com.iiitb.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class JobOffer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotBlank
	private Integer id;
	
	/**
	 * This is the minimum CGPA required to apply for this  jobOffer
	 * Average of all the credit for all courses of a student;
	 */
	@NotBlank
	private double minGrade;
	
	@NotBlank
	@ManyToOne
	private Organization organization;
	
	@ManyToOne
	private Specialization specialization;
	
	@ManyToOne
	@NotBlank
	private Domain domain;
	
	/*@OneToMany(mappedBy = "jobOffer")
	private List<JobApplication> jobApplications;*/

	@NotBlank
	private String jobPosition;
	
	@NotBlank
	private Integer compensation;

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public Integer getCompensation() {
		return compensation;
	}

	public void setCompensation(Integer compensation) {
		this.compensation = compensation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getMinGrade() {
		return minGrade;
	}

	public void setMinGrade(double minGrade) {
		this.minGrade = minGrade;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}
}
