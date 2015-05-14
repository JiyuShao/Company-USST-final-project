package company.dao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import company.model.util.ManagedManagerBean;

/**
 * Servlet implementation class UpdateAdmin
 */
@WebServlet("/updateManager")
public class UpdateManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateManager() {
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
		String user = (String) session.getAttribute("user");
		
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String status = request.getParameter("status");
		String pwd = request.getParameter("password");
		
		if(birthday.equals("null")){
			birthday= "19000101";
		}
		
		String password = ManagedManagerBean.getById(Integer.parseInt(user)).getPassword();
		PrintWriter out = response.getWriter();
		
		try {
			if (!pwd.isEmpty() && password.equals(pwd)) {
				ManagedManagerBean.updateFull(Integer.parseInt(user), name,
						Integer.parseInt(birthday), email, gender, phone, status);
				out.print("<script>alert('Update Succeed!!');window.location.href='"
						+ request.getContextPath()
						+ "/private/managerProfile.jsp';</script>");
			} else {
				
				out.print("<script>alert('Update Error!!');window.location.href='"
						+ request.getContextPath()
						+ "/private/managerProfile.jsp';</script>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			out.print("<script>alert('Update Error!!');window.location.href='"
					+ request.getContextPath()
					+ "/private/managerProfile.jsp';</script>");
		}
		
		
	}

}
