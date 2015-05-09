package company.model;

import static javax.persistence.FetchType.LAZY;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="signin")
public class SignIn {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;

	@Column(name="DATE",nullable=false)
	private String date="null";
	@Enumerated(EnumType.STRING) @Column(name="STATUS",length=4,nullable=false)
	private Status status =Status.NULL;
	
	
	@ManyToOne(fetch = LAZY,cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
	,optional=false,targetEntity=Admin.class)
	@JoinColumn(name = "EMPLOYEE_ID",nullable=false)
	private Employee employee;

	public SignIn(){
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
