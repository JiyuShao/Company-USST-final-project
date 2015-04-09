package junit.test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import company.model.Admin;
import company.model.Employee;
import company.model.Manager;

public class AdminTest {
	@Test
	public void initial(){
		addAdmin();
		addManager();
		addEmployee();
	}
	//clean up
	@Test
	public void cleanUp() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
	    em.getTransaction().begin();
	    em.createNativeQuery("drop table employee").executeUpdate();
	    em.createNativeQuery("drop table manager").executeUpdate();
	    em.createNativeQuery("drop table admin").executeUpdate();
	    em.getTransaction().commit();
	}
	//Functions for admin
	@Test
	public void addAdmin() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Admin admin =new Admin();
		admin.setAdminId(111111111);
		admin.setName("sjy");
		admin.setPassword("sjy1125");
		em.persist(admin);
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
	public void updateAdminQuery() {
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
	public void searchAdminQuery() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		Query query=em.createQuery("select o from Admin o where o.name=?1");
		query.setParameter(1, "sjy");
		Admin admin=(Admin)query.getSingleResult();//List<Admin> admins=query.getResultList()
		System.out.print(admin.getId()+"\n");//for(Admin admin:admins)
		em.close();
		factory.close();
	}
	
	//Functions for manager
	@Test
	public void addManager() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Query query=em.createQuery("select o from Admin o where o.name=?1");
		query.setParameter(1, "sjy");
		Admin admin=(Admin)query.getSingleResult();
		
		Manager manager1 = new Manager();
		manager1.setManagerId(666666666);
		manager1.setName("meng");
		manager1.setPassword("zmf1220");
		manager1.setSite("Vancouver");
		manager1.setAdmin(admin);
		admin.getManagers().add(manager1);
		
		em.merge(manager1);
		em.merge(admin);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void searchManagerQuery() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		Query query=em.createQuery("select o from Manager o where o.managerId=?1");
		query.setParameter(1, 666666666);
		Manager manager=(Manager)query.getSingleResult();//List<Admin> admins=query.getResultList()
		System.out.print(manager.getId()+"\n");//for(Admin admin:admins)
		em.close();
		factory.close();
	}
	
	@Test
	public void updateManagerQuery() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Query query=em.createQuery("update Manager o set o.name=:name where o.managerId=:id");
		query.setParameter("name", "fei");
		query.setParameter("id", 666666666);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void deleteManager() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Query query=em.createQuery("select o from Manager o where o.managerId=?1");
		query.setParameter(1, 666666666);
		Manager manager=(Manager)query.getSingleResult();
		manager.getAdmin().getManagers().remove(manager);
		em.remove(manager);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	//Employee functions
	@Test
	public void addEmployee() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Query query=em.createQuery("select o from Manager o where o.managerId=?1");
		query.setParameter(1, 666666666);
		Manager manager=(Manager)query.getSingleResult();
		
		Employee employee1 = new Employee();
		employee1.setEmployeeId(888888888);
		employee1.setName("duan");
		employee1.setPassword("12345678");
		employee1.setManager(manager);		
		manager.getEmployees().add(employee1);
		
		em.merge(employee1);
		em.merge(manager);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
