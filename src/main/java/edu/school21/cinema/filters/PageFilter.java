package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class PageFilter implements Filter {
    public void init(FilterConfig config) {}

    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse httpResp = (HttpServletResponse) response;

        if (httpResp.getStatus() == 404)
            httpResp.sendError(HttpServletResponse.SC_NOT_FOUND);
        chain.doFilter(request, response);
    }
}
