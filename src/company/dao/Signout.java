package company.dao;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import company.model.Employee;
import company.model.Signin;
import company.model.util.ManagedEmployeeBean;
import company.model.util.ManagedSigninBean;

/**
 * Servlet implementation class Signout
 */
@WebServlet("/signout")
public class Signout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signout() {
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
		HttpSession session = request.getSession(true);
		String employeeId = (String) session.getAttribute("user");
		Date date = new Date();
		DateFormat df1 = DateFormat.getDateInstance();
		DateFormat df2 = DateFormat.getDateTimeInstance();	
		
		Signin signin=null;
		try{
			signin = ManagedSigninBean.getByIdDate(Integer.parseInt(employeeId), df1.format(date),"YES");
			try{
				signin = ManagedSigninBean.getByIdDate(Integer.parseInt(employeeId), df1.format(date),"NO");
			}catch(Exception e){
				Employee employee = ManagedEmployeeBean.getById(Integer.parseInt(employeeId));
				Signin signin1 = new Signin();
				signin1.setDate(df1.format(date));
				signin1.setTime(df2.format(date));
				signin1.setStatus("NO");
				signin1.setEmployee(employee);
				ManagedSigninBean.createNewSignin(Integer.parseInt(employeeId), signin1);
			}
		}catch(Exception e){
			Employee employee = ManagedEmployeeBean.getById(Integer.parseInt(employeeId));
			Signin signin1 = new Signin();
			signin1.setDate(df1.format(date));
			signin1.setTime(df2.format(date));
			signin1.setStatus("YES");
			signin1.setEmployee(employee);
			ManagedSigninBean.createNewSignin(Integer.parseInt(employeeId), signin1);
		}
		response.sendRedirect(request.getContextPath() + "/private/employee.jsp");
	}
}
