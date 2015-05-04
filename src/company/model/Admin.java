package company.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="admin")
public class Admin {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	@Column(name="ADMIN_ID",length=9,unique=true,nullable=false)
	private Integer adminId;
	@Column(name="NAME",length=20,nullable=false)
	private String name;
	@Enumerated(EnumType.STRING) @Column(name="GENDER",length=5,nullable=false) 
	private Gender gender = Gender.MAN;
	@Column(name="PASSWORD",length=20,nullable=false)
	private String password;
	@Column(name="PHONE",length=15,nullable=false)
	private String phone="0000000000";
	@Column(name="EMAIL",length=50,nullable=false)
	private String email="employee@gmail.com";
	@SuppressWarnings("deprecation")
	@Temporal(TemporalType.DATE)@Column(name="BIRTHDAY",nullable=false)
	private Date birthday=new Date(93,11,25);
	@OneToMany(mappedBy="admin")
	private List<Manager> managers = new ArrayList<Manager>();
	
	public Admin(){
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
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

	public List<Manager> getManagers() {
		return managers;
	}

	public void setManagers(List<Manager> managers) {
		this.managers = managers;
	}
	
	public void addManager(Manager manager){
		getManagers().add(manager);
		manager.setAdmin(this);
	}
	public void removeManager(Manager manager){
		getManagers().remove(manager);
		manager.setAdmin(null);
	}
	
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Admin))
            return false;
 
        final Admin admin = (Admin)object;
 
        if (id != null && admin.getId() != null) {
            return id.equals(admin.getId());
        }
        return false;
    }
}