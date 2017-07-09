package ru.kovladimir.webcrudjsp.filter;

import ru.kovladimir.webcrudhtml.model.Role;
import ru.kovladimir.webcrudhtml.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Scanner;

/**
 * Access filter.
 */
public class RoleFilter implements Filter {

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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = httpRequest.getRequestURI();
        if (!url.contains("/signin")) {
            HttpSession session = httpRequest.getSession();
            User user = (User) session.getAttribute("user");
            if (user.getRole() == Role.USER) {
                if (url.contains("/add") || url.contains("/delete")) {
                    ((HttpServletResponse) response).sendRedirect(String.format("%s/accesdenied", httpRequest.getContextPath()));
                    return;
                } else {
                    if (url.contains("edit")) {
                        String idFromReq = httpRequest.getParameter("id");
                        if (idFromReq != null && Integer.parseInt(idFromReq) != user.getId()) {
                            ((HttpServletResponse) response).sendRedirect(String.format("%s/accesdenied", httpRequest.getContextPath()));
                            return;
                        }
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {

    }

}
