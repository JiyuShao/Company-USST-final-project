package junit.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import org.junit.Test;

import company.model.Admin;

public class AdminTest {
	
	@Test
	public void save() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(new Admin("sjy","sjy1125"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void getAdminName() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		Admin admin=em.find(Admin.class, 1);
		System.out.print(admin.getName());
		em.close();
		factory.close();
	}
	
	@Test
	public void getAdminName2() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		Admin admin=em.getReference(Admin.class, 1);
		System.out.print(admin.getName());
		em.close();
		factory.close();
	}
	
	@Test
	public void updateAdminName() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Admin admin=em.find(Admin.class, 1);
		admin.setName("jiyu");
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void updateAdminName2() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Admin admin=em.find(Admin.class, 1);
		em.clear();
		admin.setName("jiyu");
		em.merge(admin);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void deleteAdmin() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Admin admin=em.find(Admin.class, 1);
		em.remove(admin);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	@Test
	public void updateQuery() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Query query=em.createQuery("update Admin o set o.name=:name where o.id=:id");
		query.setParameter("name", "jiyu");
		query.setParameter("id", 1);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	@Test
	public void searchQuery() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		Query query=em.createQuery("select o from Admin o where o.name=?1");
		query.setParameter(1, "jiyu");
		Admin admin=(Admin)query.getSingleResult();//List<Admin> admins=query.getResultList()
		System.out.print(admin.getId());//for(Admin admin:admins)
		em.close();
		factory.close();
	}
}
