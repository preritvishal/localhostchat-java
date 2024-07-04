package localhostchat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class IPFilter implements Filter {

	private Set<String> allowedIPs;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Initialize the set of allowed IPs
		allowedIPs = new HashSet<>();
		
		allowedIPs.add("127.0.0.1"); // localhost
		allowedIPs.add("0:0:0:0:0:0:0:1"); // localhost
		allowedIPs.add("192.168.1.149"); // charvi
		allowedIPs.add("192.168.1.57"); // charvi
		allowedIPs.add("192.168.1.162"); // my phone
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String clientIP = httpRequest.getRemoteAddr();
		String requestURI = httpRequest.getRequestURI();
		
		if (requestURI.endsWith("/access-denied.html")) {
			// Proceed without filtering
			chain.doFilter(request, response);
			return;
		}

		if (allowedIPs.contains(clientIP)) {
			// If the IP is allowed, proceed with the request
			chain.doFilter(request, response);
		} else {
			// If the IP is not allowed, redirect it so many times that it feels it's a genuine issue
			httpResponse.sendRedirect("./not-found.html");
		}
	}

}
