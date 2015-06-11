package company.dao;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.model.Admin;
import company.model.Message;
import company.model.util.JPAResourceBean;
import company.model.util.ManagedMessageBean;

/**
 * Servlet implementation class MessageDetail
 */
@WebServlet("/messageDetail")
public class MessageDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageDetail() {
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
		String messageId = request.getParameter("messageId");
	    try{
	        ManagedMessageBean.updateStatusByMessageId(Integer.parseInt(messageId), "NO");
	    }catch(Exception e){}
		response.sendRedirect(request.getContextPath() + "/private/messageDetail.jsp?messageId="+messageId);
	}

}
