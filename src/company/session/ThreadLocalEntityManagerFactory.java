package company.session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ThreadLocalEntityManagerFactory implements ServletRequestListener {
	
	private static final EntityManagerFactory emfInstance =
			Persistence.createEntityManagerFactory("company");

	private static final ThreadLocal<EntityManager> perThreadEntityManager =
			new ThreadLocal<EntityManager>();
	
	public static EntityManager get() {
		if( perThreadEntityManager.get() == null )
			perThreadEntityManager.set(emfInstance.createEntityManager());
		
		return perThreadEntityManager.get();
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		EntityManager em = perThreadEntityManager.get();
		if( em != null ) {
			if( em.isOpen() ) {

				if(em.getTransaction().isActive())
					em.getTransaction().rollback();
				
				em.close();
			}
			perThreadEntityManager.set(null);
		}
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {}
}
