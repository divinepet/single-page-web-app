package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/profile"})
public class ProfileFilter implements Filter {
    public void init(FilterConfig config) {}

    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        Integer isAuthorized = (Integer) httpReq.getSession().getAttribute("authenticated");

        if (isAuthorized == null) {
            httpResp.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else
            chain.doFilter(request,response);
    }
}
