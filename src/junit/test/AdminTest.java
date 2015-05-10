package junit.test;


import java.util.Date;
import java.util.Set;
import java.text.DateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import company.model.Admin;
import company.model.Employee;
import company.model.Manager;
import company.model.Signin;
import company.model.util.ManagedAdminBean;
import company.model.util.ManagedManagerBean;

public class AdminTest {
	
	@Test
	public void getDate(){
		Date date = new Date();
		DateFormat df = DateFormat.getDateTimeInstance();
		System.out.print(df.format(date)+"\n");
	}
	
	@Test
	public void initial(){
		addAdmin();
		addManager();
		addEmployee();
		addSignIn();
	}
	//clean up
	@Test
	public void cleanUp() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
	    em.getTransaction().begin();
	    em.createNativeQuery("drop table signin").executeUpdate();
	    em.createNativeQuery("drop table employee").executeUpdate();
	    em.createNativeQuery("drop table manager").executeUpdate();
	    em.createNativeQuery("drop table admin").executeUpdate();
	    em.getTransaction().commit();
	}
	//Functions for admin
	@Test
	public void addAdmin() {
		Admin admin = new Admin();
		admin.setName("sjy");
		admin.setPassword("sjy1125");
		ManagedAdminBean.createNewAdmin(admin);
	}
	
	@Test
	public void getAdminName() {
		Admin admin = ManagedAdminBean.getById(3);
		System.out.print(admin.getName());
	}
	
	@Test
	public void updateAdminName() {
		ManagedAdminBean.update(3, "shao", "sjy1125");
	}
	
	@Test
	public void deleteAdmin() {
		ManagedAdminBean.removeById(2);
	}
	//wrong
	@Test
	public void searchAdminQuery() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		Query query=em.createQuery("select o from Admin o where o.adminId=?1");
		query.setParameter(1, 11111111);
		Admin admin=(Admin)query.getSingleResult();//List<Admin> admins=query.getResultList()
		System.out.print(admin.getEmail()+"\n");//for(Admin admin:admins)
		em.close();
		factory.close();
	}
	
	//Functions for manager
	@Test
	public void addManager() {
		
		Admin admin = ManagedAdminBean.getById(3);
		
		Manager manager1 = new Manager();
		manager1.setName("meng");
		manager1.setStatus("YES");
		manager1.setPassword("zmf1220");
		manager1.setAdmin(admin);
		
		ManagedManagerBean.createNewManager(3, manager1);
	}
	
	@Test
	public void searchManagerQuery() {
		Manager manager = ManagedManagerBean.getById(4);
		System.out.print(manager.getName());
	}
	//wrong
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
		ManagedManagerBean.removeById(4);
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
	
	@Test
	public void searchEmployee() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		Query query=em.createQuery("select o from Employee o where o.employeeId=?1");
		query.setParameter(1, 888888888);
		Employee employee=(Employee)query.getSingleResult();
		System.out.print(employee.getName()+"\n");
		em.close();
		factory.close();
		
	}
	
	//SignIn functions
	@Test
	public void addSignIn() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("company");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Query query=em.createQuery("select o from Employee o where o.employeeId=?1");
		query.setParameter(1, 888888888);
		Employee employee=(Employee)query.getSingleResult();
		
		Signin signin1 = new Signin();
		Date date = new Date();
		DateFormat df = DateFormat.getDateTimeInstance();		
		signin1.setTime(df.format(date));
		signin1.setStatus("YES");
		signin1.setEmployee(employee);
		
		employee.getSignins().add(signin1);
		
		em.merge(signin1);
		em.merge(employee);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
