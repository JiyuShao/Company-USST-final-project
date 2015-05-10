package company.model.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAResourceBean {
	protected static EntityManagerFactory emf;
	
	public static EntityManagerFactory getEMF (){
	    if (emf == null){
	        emf = Persistence.createEntityManagerFactory("company");
	    }
	    return emf;
	}
}
