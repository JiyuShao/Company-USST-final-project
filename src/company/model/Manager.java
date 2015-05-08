package company.model;

import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="manager")
public class Manager {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	@Column(name="MANAGER_ID",length=9,unique=true,nullable=false)
	private Integer managerId;
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
	@Column(name="SITE",length=20, nullable=false)
	private String site;
	@Enumerated(EnumType.STRING) @Column(name="STATUS",length=4,nullable=false)
	private Status status =Status.NULL;
	@ManyToOne(fetch = LAZY,cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,optional=false,targetEntity=Admin.class)
	@JoinColumn(name = "ADMIN_ID",nullable=false)
	private Admin admin;
	
	@OneToMany(mappedBy="manager")
	private List<Employee> employees = new ArrayList<Employee>();
	
	public Manager(){
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
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

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public void addEmployee(Employee employee){
		getEmployees().add(employee);
		employee.setManager(this);
	}
	public void removeEmployee(Employee employee){
		getEmployees().remove(employee);
		employee.setManager(null);
	}
}
