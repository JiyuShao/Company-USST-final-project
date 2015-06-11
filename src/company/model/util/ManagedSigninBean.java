package company.model.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import company.model.Admin;
import company.model.Signin;

public class ManagedSigninBean {
	public static void  createNewSignin(Integer employeeId, Signin signin){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        em.getTransaction().begin();
	        em.persist(signin);
	        ManagedEmployeeBean.addSignin(employeeId, signin);
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
	}
	
	public static Signin getByIdDate(Integer employeeId, String date , String status){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    em.getTransaction().begin();
	    Query query=em.createQuery("select o from Signin o join o.employee e where e.employeeId=?1 and o.date=?2 and o.status=?3");
	    query.setParameter(1, employeeId);
	    query.setParameter(2, date);
	    query.setParameter(3, status);
	    return (Signin) query.getSingleResult();
	}
	
	public static String getEmployeeName(Integer signinId){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    try{
	        return em.find(Signin.class, signinId).getEmployee().getName();
	    }finally{
	        em.close();
	    }
	}
	
	@SuppressWarnings("unchecked")
	public static List<Signin> getTodayList(Integer managerId, String date){
	    EntityManager em = JPAResourceBean.getEMF().createEntityManager();
	    em.getTransaction().begin();
	    Query query=em.createQuery("select o from Signin o join o.employee.manager m where m.managerId=?1 and o.date=?2");
	    query.setParameter(1, managerId);
	    query.setParameter(2, date);
	    
	    return (List<Signin>) query.getResultList();
	}
	
	
}
