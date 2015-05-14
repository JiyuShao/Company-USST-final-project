package company.model.util;

import javax.persistence.EntityManager;

import company.model.Admin;
import company.model.Employee;
import company.model.Manager;

public class ManagedEmployeeBean {
	public static void  createNewEmployee(Integer managerId, Employee employee){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        em.persist(employee);
	        ManagedManagerBean.addEmployee(managerId, employee);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	public static Employee getById(Integer employeeId){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        return em.find(Employee.class, employeeId);
	    }finally{
	        em.close();
	    }
	}
	
	public static void  removeById(Integer employeeId){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        Employee employee = em.find(Employee.class, employeeId);
	        Manager manager = employee.getManager();
	        manager.getEmployees().remove(employee);
	        em.remove(employee);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
}
