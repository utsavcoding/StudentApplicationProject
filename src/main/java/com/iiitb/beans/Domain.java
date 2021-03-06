package com.iiitb.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Domain {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotBlank
	private Integer id;
	
	@NotBlank
	private String domainName;
	
	@NotBlank
	private String disciplineCode;
	
	@NotBlank
	private String branchCode;
	
	/*@OneToMany(mappedBy = "domain")
	private List<Student> students;*/
	
	@NotBlank
	@Column(unique=true)
	private String domainCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	public String getDisciplineCode() {
		return disciplineCode;
	}

	public void setDisciplineCode(String disciplineCode) {
		this.disciplineCode = disciplineCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getDomainCode() {
		return domainCode;
	}

	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}

}
