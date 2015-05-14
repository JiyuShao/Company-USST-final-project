package company.model.util;

import javax.persistence.EntityManager;

import company.model.Admin;
import company.model.Manager;


public class ManagedAdminBean {
	public static void  createNewAdmin(Admin admin){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        em.persist(admin);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	public static Admin getById(Integer adminId){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        return em.find(Admin.class, adminId);
	    }finally{
	        em.close();
	    }
	}
	
	public static void update(Integer adminId, String name, String password){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        Admin admin = em.find(Admin.class, adminId);
	        admin.setName(name);
	        admin.setPassword(password);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	public static void updateFull(Integer adminId, String name, Integer birthday, String email, String gender, String phone){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        Admin admin = em.find(Admin.class, adminId);
	        admin.setName(name);
	        admin.setBirthday(birthday);
	        admin.setEmail(email);
	        admin.setGender(gender);
	        admin.setPhone(phone);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	public static void addManager(Integer adminId, Manager manager){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        Admin admin = em.find(Admin.class, adminId);
	        admin.getManagers().add(manager);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	public static void  removeById(Integer adminId){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        Admin admin = em.find(Admin.class, adminId);
	        em.remove(admin);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	
}
