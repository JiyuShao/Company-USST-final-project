package company.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="employee")
public class SignIn {
	@EmbeddedId
	@Column(name="ID")
	private EmMaPK id;
	@SuppressWarnings("deprecation")
	@Temporal(TemporalType.DATE)@Column(name="DATE",nullable=false)
	private Date date=new Date(93,11,25);
	@Enumerated(EnumType.STRING) @Column(name="STATUS",length=4,nullable=false)
	private Status status =Status.NULL;
	
	
	public SignIn() {}
	
	public SignIn(EmMaPK id, Date date, Status status) {
		this.id = id;
		this.date = date;
		this.status = status;
	}
	
	public SignIn(Integer employeeId, Integer managerId, Date date, Status status) {
		this.id = new EmMaPK(employeeId,managerId);
		this.date = date;
		this.status = status;
	}

	public EmMaPK getId() {
		return id;
	}

	public void setId(EmMaPK id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
		
}
