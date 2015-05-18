package company.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.model.Employee;
import company.model.Manager;
import company.model.util.JPAResourceBean;
import company.model.util.ManagedEmployeeBean;
import company.model.util.ManagedManagerBean;

/**
 * Servlet implementation class Orientation
 */
@WebServlet("/orientation")
public class Orientation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Orientation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String employeeId = request.getParameter("employees");
//		EntityManager em = JPAResourceBean.getEMF().createEntityManager();
//		try{
//	        em.getTransaction().begin();
//	        Employee employee = em.find(Employee.class, employeeId);
//	        if(employee!=null){
//				if(employee.getStatus().equals("YES")){
//					employee.setStatus("NO");
//				}else if(employee.getStatus().equals("NO")){
//					employee.setStatus("YES");
//				}else{
//					employee.setStatus("YES");
//				}
//			} else {
//				PrintWriter out = response.getWriter();
//				out.print("<script>alert('Login Error!!');</script>");
//			}
//	        em.getTransaction().commit();
//	    }finally{
//	        em.close();
//	    }
//		PrintWriter out = response.getWriter();
//		out.print("<script>window.location.href='"+request.getContextPath()+"/private/employeeOrientation.jsp';</script>");
	
	
		String managerId = request.getParameter("managerId");
		String userName = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("password");
		String pwd2 = request.getParameter("password2");
		
		if(managerId!=null&&pwd!=null&&pwd.equals(pwd2)){
			try {
				Manager manager = ManagedManagerBean.getById(Integer
						.parseInt(managerId));
				Employee employee1 = new Employee();
				employee1.setName(userName);
				employee1.setEmail(email);
				employee1.setPhone(phone);
				employee1.setPassword(pwd);
				employee1.setStatus("NULL");
				employee1.setManager(manager);
				ManagedEmployeeBean.createNewEmployee(
						Integer.parseInt(managerId), employee1);
				PrintWriter out = response.getWriter();
				out.print("<script>alert('Sign Up Succeed!! Please wait for confirmation!!');window.location.href='"+request.getContextPath()+"/index.html';</script>");
			} catch (Exception e) {
				// TODO: handle exception
				PrintWriter out = response.getWriter();
				out.print("<script>alert('Sign Up Error!!');window.location.href='"+request.getContextPath()+"/signup.html';</script>");
			}
		}else{
			PrintWriter out = response.getWriter();
			out.print("<script>alert('Information Error!!');window.location.href='"+request.getContextPath()+"/signup.html';</script>");
		}
	}

}
