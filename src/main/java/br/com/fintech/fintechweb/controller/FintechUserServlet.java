package br.com.fintech.fintechweb.controller;

import br.com.fintech.fintechweb.dao.FintechUserDao;
import br.com.fintech.fintechweb.exception.EntityNotFoundException;
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
import java.util.List;

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
        String action = req.getParameter("action");
        switch (action) {
            case "create":
                create(req, resp);
                break;
            case "update":
                update(req, resp);

        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String birthdate = LocalDate.parse(req.getParameter("birthdate")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String password = req.getParameter("password");
        String createdAt = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));
        FintechUser fintechUser = new FintechUser(id, name, email, gender, birthdate, password, createdAt);
        try {
            dao.update(fintechUser);
            req.setAttribute("message", "Usuário Atualizado");
        } catch (SQLException e) {
            req.setAttribute("error", "Erro ao Atualizar");
            throw new RuntimeException(e);
        }
        listUsers(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "list-users":
                listUsers(req, resp);
                break;
            case "open-edit-form":
                openForm(req, resp);
                break;
        }
    }

    private void openForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        FintechUser fintechUser = null;
        try {
            fintechUser = dao.search(id);
        } catch (SQLException | EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("fintechUser", fintechUser);
        req.getRequestDispatcher("edit-user.jsp").forward(req, resp);
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FintechUser> usersList = null;
        try {
            usersList = dao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("fintechUsers", usersList);
        req.getRequestDispatcher("list-users.jsp").forward(req, resp);
        return;
    }

}

