package ru.kovladimir.webcrudajax.filters;

import ru.kovladimir.webcrudhtml.model.Role;
import ru.kovladimir.webcrudhtml.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Check if user has access
 */
public class RoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = httpRequest.getRequestURI();
        String clearPath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (!clearPath.startsWith("/signin") && !clearPath.startsWith("/logout") && !clearPath.equals("/")
                && !clearPath.equals("/styles.css") && !clearPath.equals("/scripts.js")) {
            HttpSession session = httpRequest.getSession();
            User user = (User) session.getAttribute("client");
            if (user.getRole() == Role.USER) {
                if (clearPath.startsWith("/get")) {
                    chain.doFilter(request, response);
                } else if (clearPath.startsWith("/edit")) {
                    String idFromReq = httpRequest.getParameter("id");
                    if (idFromReq != null && Integer.parseInt(idFromReq) == user.getId()) {
                        chain.doFilter(request, response);
                    }
                }
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
