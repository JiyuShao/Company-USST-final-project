package company.model.util;

import javax.persistence.EntityManager;

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
	
	public static String getManagerName(Integer employeeId){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        return em.find(Employee.class, employeeId).getManager().getName();
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
	
	public static void updateFull(Integer employeeId, String name,Integer birthday, String email, String gender, String phone){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        Employee employee = em.find(Employee.class, employeeId);
	        employee.setName(name);
	        employee.setBirthday(birthday);
	        employee.setEmail(email);
	        employee.setGender(gender);
	        employee.setPhone(phone);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
}
