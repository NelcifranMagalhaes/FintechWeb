package br.com.fintech.fintechweb.controller;

import br.com.fintech.fintechweb.dao.FintechUserDao;
import br.com.fintech.fintechweb.model.FintechUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private FintechUserDao userDao;

    public LoginServlet() throws SQLException {
        userDao = new FintechUserDao();
    }

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        FintechUser fintechUser = null;
        try {
            fintechUser = new FintechUser(email, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            if (userDao.validateUser(fintechUser)) {
                HttpSession session = request.getSession();
                session.setAttribute("userEmail", email);
                request.setAttribute("message", "Logado com sucesso!");
                request.getRequestDispatcher("/index").forward(request, response);
            }else {
                request.setAttribute("error", "Usuário e/ou senha inválidos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("login.jsp").forward(request, response);

    }
}
