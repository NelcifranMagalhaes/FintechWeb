package br.com.fintech.fintechweb.controller;

import br.com.fintech.fintechweb.dao.FintechUserDao;
import br.com.fintech.fintechweb.model.FintechUser;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/users")
public class FintechUserServlet extends HttpServlet {

    private FintechUserDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {

        try {
            dao = new FintechUserDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try{
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String gender = req.getParameter("gender");
            String birthdate = LocalDate.parse(req.getParameter("birthdate")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String password = req.getParameter("password");
            String createdAt = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));
            FintechUser user = new FintechUser(
                    name,
                    email,
                    gender,
                    birthdate,
                    password,
                    createdAt
            );

            dao.add(user);

            req.setAttribute("message", "Usuário cadastrado!");

        } catch(Exception e){
            e.printStackTrace();
            req.setAttribute("error","Por favor, valide os dados");
        }
        req.getRequestDispatcher("user-registration.jsp").forward(req, resp);
    }

}

