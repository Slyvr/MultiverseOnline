package com.slyvr.filters;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class BannedAccessFilter implements Filter {
	private HashSet<String> bannedSiteTable;
	private HttpServletRequest req;
	
	public void init(FilterConfig config) throws ServletException {
		bannedSiteTable = new HashSet<String>();
		String bannedSites = config.getInitParameter("bannedSites");
		StringTokenizer tok = new StringTokenizer(bannedSites);
		while (tok.hasMoreTokens()) {
			String bannedSite = tok.nextToken();
			bannedSiteTable.add(bannedSite);
			System.out.println("Banned " + bannedSite);
		}
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		req = (HttpServletRequest) request;
		String requestingHost = req.getRemoteHost();
		String referringHost = getReferringHost(req.getHeader("Referer"));
		String bannedSite = null;
		boolean isBanned = false;
		if (bannedSiteTable.contains(requestingHost)) {
			bannedSite = requestingHost;
			isBanned = true;
		} else if (bannedSiteTable.contains(referringHost)) {
			bannedSite = referringHost;
			isBanned = true;
		}
		if (isBanned) {
			showWarning(response, bannedSite);// Custom response
		} else {
			chain.doFilter(request, response);
		}
	}

	private String getReferringHost(String refererringURLString) {
		try {
			URL referringURL = new URL(refererringURLString);
			return (referringURL.getHost());
			// Malformed or null
		} catch (MalformedURLException mue) {
			return (null);
		}
	}

	private void showWarning(ServletResponse response, String bannedSite)
			throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher dispatcher = req.getRequestDispatcher("errors/accessdenied.jsp");
	    dispatcher.include(req, response);
	}
}