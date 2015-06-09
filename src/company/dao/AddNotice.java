package company.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.model.Notice;
import company.model.util.ManagedNoticeBean;

/**
 * Servlet implementation class AddNotice
 */
@WebServlet("/addNotice")
public class AddNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNotice() {
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
		String content = request.getParameter("content");
		PrintWriter out = response.getWriter();
		try {
			Notice notice = new Notice();
			Date date = new Date();
			DateFormat df = DateFormat.getDateInstance();
			notice.setTitle("Notice");
			notice.setContent(content);
			notice.setDate(df.format(date));
			ManagedNoticeBean.createNewNotice(notice);
			out.print("<script>alert('Add Notice Succeed!!')</script>");
		} catch (Exception e) {
			// TODO: handle exception
			out.print("<script>alert('Add Notice Failed!!')</script>");
		}
		out.print("<script>window.location.href='" + request.getContextPath()
				+ "/private/addNotice.jsp';</script>");
	}

}
