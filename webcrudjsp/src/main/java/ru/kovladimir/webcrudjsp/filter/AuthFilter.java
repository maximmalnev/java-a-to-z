package ru.kovladimir.webcrudjsp.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Auth Filter.
 */
public class AuthFilter implements Filter {

    /**
     * {@inheritDoc}
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * {@inheritDoc}
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getRequestURI().contains("/signin")) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = httpRequest.getSession();
            if (session.getAttribute("user") == null) {
                ((HttpServletResponse) response).sendRedirect(String.format("%s/signin", httpRequest.getContextPath()));
                return;
            }
            chain.doFilter(request, response);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {

    }
}
