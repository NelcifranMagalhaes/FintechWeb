package br.com.fintech.fintechweb.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class loginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        String url = req.getRequestURI();

        if (session.getAttribute("userEmail") == null && !url.endsWith("login") && !url.contains("resources") && !url.contains("user-registration")
        && !url.contains("users")) {
            req.setAttribute("error", "Entre com o usuário e senha!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else {
            filterChain.doFilter(req, resp);
        }
    }
}
