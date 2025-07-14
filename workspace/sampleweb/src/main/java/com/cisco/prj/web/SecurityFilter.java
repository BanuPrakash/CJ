package com.cisco.prj.web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("*.jsp")
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // get session
        String URI = req.getRequestURI();
        if(URI.endsWith("login.jsp")) {
            chain.doFilter(request, response);
            return;
        } else if(session == null || session.getAttribute("user") == null) {
            // invalid user
            resp.sendRedirect("login.jsp");
            return;
        }
        chain.doFilter(request, response);
    }
}
