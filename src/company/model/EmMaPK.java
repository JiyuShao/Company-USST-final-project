//The primary key is EmployeeID&ManagerID
package company.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable//Only use the variables that we defined in this class
public class EmMaPK implements Serializable{
	@Column(name="EMPLOYEE_ID",length=9,unique=true,nullable=false)
	private Integer employeeId;
	@Column(name="MANAGER_ID",length=9,unique=true,nullable=false)
	private Integer managerId;
	
	public EmMaPK() {}
	
	public EmMaPK(Integer employeeId, Integer managerId) {
		this.employeeId = employeeId;
		this.managerId = managerId;
	}
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result
				+ ((managerId == null) ? 0 : managerId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmMaPK other = (EmMaPK) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (managerId == null) {
			if (other.managerId != null)
				return false;
		} else if (!managerId.equals(other.managerId))
			return false;
		return true;
	}
	
	
}
