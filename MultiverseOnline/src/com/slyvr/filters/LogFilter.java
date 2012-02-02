package com.slyvr.filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {
	protected FilterConfig config;
	private ServletContext context;
	private String filterName;

	public void init(FilterConfig config) throws ServletException {
		// In case it is needed by subclass.
		this.config = config;
		context = config.getServletContext();
		filterName = config.getFilterName();
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		context.log(req.getRemoteHost() + " tried to access "
				+ req.getRequestURL() + " on " + new Date() + ". "
				+ "(Reported by " + filterName + ".)");
		chain.doFilter(request, response);
	}
	@Override
	public void destroy() {
	}
}