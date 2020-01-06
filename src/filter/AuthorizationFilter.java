package filter;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
//@WebFilter("/AuthorizationFilter")
@WebFilter("*.xhtml")
public class AuthorizationFilter implements Filter {

	private static final Logger LOGGER = LogManager.getLogger(AuthorizationFilter.class.getName());

	/**
	 * Default constructor.
	 */
	public AuthorizationFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		boolean isLogged;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			isLogged = context.getExternalContext().getSessionMap().get("isLogged") != null;
		} catch (NullPointerException e) {
			LOGGER.error(e);
			isLogged = false;
		}
		
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		if (!isLogged && !req.getRequestURI().endsWith("/index.xhtml")
				&& !req.getRequestURI().contains("/javax.faces.resource/")) {

			res.sendRedirect(req.getContextPath() + "/index.xhtml");
		} else {
			chain.doFilter(req, res);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
