package company.model.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAResourceBean {
	protected static EntityManagerFactory emf;
	
	@SuppressWarnings("rawtypes")
	public static EntityManagerFactory getEMF (){
	    if (emf == null){
	        emf = Persistence.createEntityManagerFactory("company", new java.util.HashMap());
	    }
	    return emf;
	}
}