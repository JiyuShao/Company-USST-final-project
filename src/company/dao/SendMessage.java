package company.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import company.model.Message;
import company.model.util.ManagedMessageBean;

/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/sendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessage() {
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
		String fType = session.getAttribute("type").toString();
		String fId = session.getAttribute("user").toString();
		String tType = request.getParameter("tType");
		String tId = request.getParameter("tId");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		PrintWriter out = response.getWriter();
		
		if(tType.equals("null")||tId.equals("null")){
			out.print("<script>alert('Send Message Error!!')</script>");
			out.print("<script>window.location.href='" + request.getContextPath()
					+ "/private/sendMessage.jsp';</script>");
		}else{
			try {
				Message message = new Message();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				
				message.setTitle(title);
				message.setContent(content);
				message.setDate(date);
				message.setFid(Integer.parseInt(fId));
				message.setFtype(fType);
				message.setTid(Integer.parseInt(tId));
				message.setTtype(tType);
				message.setStatus("YES");
				
				ManagedMessageBean.createNewMessage(message);
				out.print("<script>alert('Send Message Sucesseed!!')</script>");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				out.print("<script>alert('Send Message Error!!')</script>");
			}
			out.print("<script>window.location.href='" + request.getContextPath()
					+ "/private/sendMessage.jsp';</script>");
		}
		
	}

}
