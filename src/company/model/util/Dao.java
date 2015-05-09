package company.model.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import company.session.ThreadLocalEntityManagerFactory;

public class Dao {
	
	public static void beginTransaction() {
		EntityManager em = ThreadLocalEntityManagerFactory.get();
		if ( em != null )
			em.getTransaction().begin();
	}
	
	public static void commitTransaction() {
		EntityManager em = ThreadLocalEntityManagerFactory.get();
		if ( em != null )
			em.getTransaction().commit();
	}
	
	public static <T> T find(Class<T> clazz, int id) {
		
		EntityManager em = ThreadLocalEntityManagerFactory.get();
		if ( em == null )
			return null;
		
		return em.find(clazz, id);
	}
	
	public static <T> T findByName(Class<T> clazz, String name) {
		
		EntityManager em = ThreadLocalEntityManagerFactory.get();
		if ( em == null )
			return null;
		
		try {

			@SuppressWarnings("unchecked")
			TypedQuery<T> query = (TypedQuery<T>) em.createQuery("select o from "+clazz.getSimpleName()+" o where o.name=?1");
			return query.setParameter(1, name).getSingleResult();
			
		} catch (Exception e) {
			
			System.err.println(e);
			return null;
			
		}
	}
	
	public static <T> T findById(Class<T> clazz, Integer num) {
		
		EntityManager em = ThreadLocalEntityManagerFactory.get();
		if ( em == null )
			return null;
		
		String text="";
		try {
			if(clazz.getSimpleName().equals("Admin")){
				text="select o from Admin o where o.adminId=?1";
			}else if(clazz.getSimpleName().equals("Manager")){
				text="select o from Manager o where o.managerId=?1";
			}else{
				text="select o from Employee o where o.employeeId=?1";
			}
			@SuppressWarnings("unchecked")
			TypedQuery<T> query = (TypedQuery<T>) em.createQuery(text);
			return query.setParameter(1, num).getSingleResult();
			
		} catch (Exception e) {
			
			System.err.println(e);
			return null;
			
		}
	}



	public static <T> List<T> findAll(Class<T> clazz) {
		
		EntityManager em = ThreadLocalEntityManagerFactory.get();
		if ( em == null )
			return null;
		
		try {

			TypedQuery<T> query = em.createNamedQuery(clazz.getSimpleName() + ".findAll", clazz);
			return query.getResultList();
			
		} catch (Exception e) {
			
			System.err.println(e);
			return null;
			
		}
	}
}
