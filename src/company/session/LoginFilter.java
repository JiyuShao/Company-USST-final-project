package company.session;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/private/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);

		if (session == null) {
			
			System.err.println("session cannot be created");
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
		} else if (session.isNew() || session.getAttribute("user") == null) {
			
			String uri = req.getRequestURI();
			uri = (uri == null || uri.isEmpty()) ? "" : uri;
			
			String query = req.getQueryString();
			query = (query == null || query.isEmpty()) ? "" : ("?" + query);
			
			session.setAttribute("forwardpath", uri + query);

			String ajaxRequest = req.getHeader("x-requested-with");
			if(ajaxRequest != null && ajaxRequest.equalsIgnoreCase("XMLHttpRequest")) {
				// Non-authorized Ajax request
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
			} else {
				// No logged-in user found, redirect to login page.
				res.sendRedirect(req.getContextPath() + "/login.html");
			}
			
		} else {
			// Logged-in user found, continue with the request.
			chain.doFilter(request, response);
		}		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
