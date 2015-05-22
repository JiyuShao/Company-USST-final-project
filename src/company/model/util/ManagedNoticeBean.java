package company.model.util;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import company.model.Notice;


public class ManagedNoticeBean {
	public static void  createNewNotice(Notice notice){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        em.persist(notice);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	public static Notice  getByDate(String date){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	    	em.getTransaction().begin();
	 	    Query query=em.createQuery("select o from Notice o where o.date=?1");
	 	    query.setParameter(1, date);
	 	   return (Notice) query.getResultList().get(query.getResultList().size()-1);
	 	   
	    }finally{
	        em.close();
	    }
	    
	}
	
}
