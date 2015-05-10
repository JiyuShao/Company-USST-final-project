package company.model.util;

import javax.persistence.EntityManager;

import company.model.Admin;
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
	
	public static Manager getById(Integer managerId){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        return em.find(Manager.class, managerId);
	    }finally{
	        em.close();
	    }
	}
//wrong	
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
	
	
}
