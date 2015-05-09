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

			TypedQuery<T> query = em.createNamedQuery(clazz.getSimpleName() + ".findByName", clazz);
			return query.setParameter("name", name).getSingleResult();
			
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
