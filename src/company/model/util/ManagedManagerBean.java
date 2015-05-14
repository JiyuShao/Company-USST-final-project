package company.model.util;

import javax.persistence.EntityManager;

import company.model.Admin;
import company.model.Employee;
import company.model.Manager;


public class ManagedManagerBean {
	public static void  createNewManager(Integer adminId, Manager manager){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        em.persist(manager);
	        ManagedAdminBean.addManager(adminId, manager);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	public static Integer getAdminId(Integer managerId){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        return em.find(Manager.class, managerId).getAdmin().getAdminId();
	    }finally{
	        em.close();
	    }
	}
	
	public static String getAdminName(Integer managerId){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        return em.find(Manager.class, managerId).getAdmin().getName();
	    }finally{
	        em.close();
	    }
	}
	
	
	public static Manager getById(Integer managerId){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        return em.find(Manager.class, managerId);
	    }finally{
	        em.close();
	    }
	}

	
	public static void  removeById(Integer managerId){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        Manager manager = em.find(Manager.class, managerId);
	        Admin admin = manager.getAdmin();
	        admin.getManagers().remove(manager);
	        em.remove(manager);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	public static void addEmployee(Integer managerId, Employee employee){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        Manager manager = em.find(Manager.class, managerId);
	        manager.getEmployees().add(employee);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	public static void update(Integer managerId, String name, String password, String status){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        Manager manager = em.find(Manager.class, managerId);
	        manager.setName(name);
	        manager.setPassword(password);
	        manager.setStatus(status);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	public static void updateFull(Integer managerId, String name,Integer birthday, String email, String gender, String phone, String status){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        Manager manager = em.find(Manager.class, managerId);
	        manager.setName(name);
	        manager.setBirthday(birthday);
	        manager.setEmail(email);
	        manager.setGender(gender);
	        manager.setPhone(phone);
	        manager.setStatus(status);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
}
