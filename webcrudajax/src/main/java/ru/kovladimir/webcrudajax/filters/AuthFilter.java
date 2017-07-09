package ru.kovladimir.webcrudajax.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Check if user is authorized.
 */
public class AuthFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String clearPath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (clearPath.startsWith("/signin") || clearPath.startsWith("/logout") || clearPath.equals("/")
                || clearPath.equals("/styles.css")|| clearPath.equals("/scripts.js")) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = httpRequest.getSession();
            if (session.getAttribute("client") != null) {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
