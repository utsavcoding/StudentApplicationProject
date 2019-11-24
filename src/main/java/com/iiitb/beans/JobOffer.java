package com.iiitb.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class JobOffer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotBlank
	private Integer id;
	
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
	
	@OneToMany(mappedBy = "jobOffer")
	private List<JobApplication> jobApplications;

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

	public List<JobApplication> getJobApplications() {
		return jobApplications;
	}

	public void setJobApplications(List<JobApplication> jobApplications) {
		this.jobApplications = jobApplications;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}
}
