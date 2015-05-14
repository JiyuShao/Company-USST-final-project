package company.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import company.model.*;
import company.model.util.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String user = request.getParameter("user");
		String pwd = request.getParameter("password");
		String accountType = request.getParameter("accountType");
		
		String password="";
				
		if(accountType.equals("Admin")){
			Admin admin = ManagedAdminBean.getById(Integer.parseInt(user));
			password = admin.getPassword();
		}
		
		if(!user.isEmpty() && password.equals(pwd)) {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(600);
			
			String forwardpath = (String) session.getAttribute("forwardpath");
			
			if(forwardpath!=null) {
				response.sendRedirect(forwardpath);
				session.removeAttribute("forwardpath");
			} else {
				response.sendRedirect(request.getContextPath() + "/private/admin.jsp");
			}					
			
		} else {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('Login Error!!');window.location.href='"+request.getContextPath()+"/login.html';</script>");
		}
		
	}

}
