package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/", "/signIn", "/signUp"})
public class AuthFilter implements Filter {
    public void init(FilterConfig config) {}

    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        Integer isAuthorized = (Integer) httpReq.getSession().getAttribute("authenticated");

        if (isAuthorized == null
                && !httpReq.getRequestURL().toString().endsWith("signIn")
                && !httpReq.getRequestURL().toString().endsWith("signUp"))
            ((HttpServletResponse) response).sendRedirect("/signIn");
        else if (isAuthorized != null)
            ((HttpServletResponse) response).sendRedirect("/profile");
        else
            chain.doFilter(request,response);

    }
}
