package com.uestc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
@WebFilter(filterName="forceNoCacheFilter",urlPatterns="/*")
public class ForceNoCacheFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		((HttpServletResponse) response).setHeader("Cache-Control", "no-cache");
		((HttpServletResponse) response).setHeader("Pragma", "no-cache");
		((HttpServletResponse) response).setDateHeader("Expires", -1);
		filterChain.doFilter(request, response);
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}