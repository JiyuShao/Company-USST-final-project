package company.model.util;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import company.model.Admin;
import company.model.Message;
import company.model.Signin;


public class ManagedMessageBean {
	public static void  createNewMessage(Message message){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        em.persist(message);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	@SuppressWarnings("unchecked")
	public static List<Message> getByToTypeIdStatus(String ttype, Integer tid, String status){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	    	em.getTransaction().begin();
	 	    Query query=em.createQuery("select o from Message o where o.ttype=?1 and o.tid=?2 and o.status=?3");
	 	    query.setParameter(1, ttype);
	 	    query.setParameter(2, tid);
	 	    query.setParameter(3, status);
	 	   return query.getResultList();
	 	   
	    }finally{
	        em.close();
	    }
	    
	}
	
	@SuppressWarnings("unchecked")
	public static List<Message> getByToTypeId(String ttype, Integer tid){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	    	em.getTransaction().begin();
	 	    Query query=em.createQuery("select o from Message o where o.ttype=?1 and o.tid=?2");
	 	    query.setParameter(1, ttype);
	 	    query.setParameter(2, tid);
	 	   return query.getResultList();
	 	   
	    }finally{
	        em.close();
	    }
	    
	}
	
	@SuppressWarnings("unchecked")
	public static List<Message> getByFromTypeIdStatus(String ftype, Integer fid, String status){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	    	em.getTransaction().begin();
	 	    Query query=em.createQuery("select o from Message o where o.ftype=?1 and o.fid=?2 and o.status=?3");
	 	    query.setParameter(1, ftype);
	 	    query.setParameter(2, fid);
	 	    query.setParameter(3, status);
	 	   return query.getResultList();
	 	   
	    }finally{
	        em.close();
	    }
	    
	}
	
	public static Message getMessageById(Integer id){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	    	return em.find(Message.class, id);
	    }finally{
	        em.close();
	    }
	}
	
	public static void updateStatusByMessageId(Integer messageId, String status){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	    	 em.getTransaction().begin();
		        Message message = em.find(Message.class, messageId);
		        message.setStatus(status);
		        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
}
