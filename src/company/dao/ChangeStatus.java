package company.dao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.model.Employee;
import company.model.util.JPAResourceBean;

/**
 * Servlet implementation class ChangeStatus
 */
@WebServlet("/changeStatus")
public class ChangeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String employeeId = request.getParameter("employeeId");
		EntityManager em = JPAResourceBean.getEMF().createEntityManager();
		try{
	        em.getTransaction().begin();
	        Employee employee = em.find(Employee.class, Integer.parseInt(employeeId));
	        if(employee!=null){
				if(employee.getStatus().equals("YES")){
					employee.setStatus("NO");
				}else if(employee.getStatus().equals("NO")){
					employee.setStatus("YES");
				}else{
					employee.setStatus("YES");
				}
			} else {
				PrintWriter out = response.getWriter();
				out.print("<script>alert('Error!!');</script>");
			}
	        em.getTransaction().commit();
	    }finally{
	        em.close();
	    }
		PrintWriter out = response.getWriter();
		out.print("<script>window.location.href='"+request.getContextPath()+"/private/employeeOrientation.jsp';</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
