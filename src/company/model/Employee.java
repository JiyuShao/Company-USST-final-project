package company.model;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="employee")
public class Employee {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	@Column(name="EMPLOYEE_ID",length=9,unique=true,nullable=false)
	private Integer employeeId;
	@Column(name="NAME",length=20,nullable=false)
	private String name;
	@Enumerated(EnumType.STRING) @Column(name="GENDER",length=5,nullable=false)
	private Gender gender = Gender.MAN;
	@Column(name="PASSWORD",length=20,nullable=false)
	private String password;
	@Column(name="PHONE",length=15,nullable=false)
	private String phone="0000000000";
	@Column(name="EMAIL",length=50,nullable=false)
	private String email="manager@gmail.com";
	@SuppressWarnings("deprecation")
	@Temporal(TemporalType.DATE)@Column(name="BIRTHDAY",nullable=false)
	private Date birthday=new Date(93,11,25);
	@Enumerated(EnumType.STRING) @Column(name="STATUS",length=4,nullable=false)
	private Status status =Status.NULL;
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,optional=false,targetEntity=Manager.class)
	@JoinColumn(name = "MANAGER_ID",nullable=false)
	private Manager manager;
	
	public Employee() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	

}