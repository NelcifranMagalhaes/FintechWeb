package br.com.fintech.fintechweb.controller;

import br.com.fintech.fintechweb.dao.FintechUserDao;
import br.com.fintech.fintechweb.dao.InvestmentDao;
import br.com.fintech.fintechweb.exception.EntityNotFoundException;
import br.com.fintech.fintechweb.model.Investment;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/investments")
public class InvestmentServlet extends HttpServlet {
    private InvestmentDao dao;
    @Override
    public void init(ServletConfig config) throws ServletException {

        try {
            dao = new InvestmentDao();
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
                try {
                    create(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                try {
                    update(req, resp);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            case "delete":
                try {
                    delete(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        String description = req.getParameter("description");
        double value = Double.parseDouble(req.getParameter("value"));
        String createdAt = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));
        Investment investment = new Investment(id, value, createdAt, description);
        try {
            dao.update(investment);
            req.setAttribute("message", "Investimento Atualizada");
        } catch (SQLException e) {
            req.setAttribute("error", "Erro ao Atualizar");
            throw new RuntimeException(e);
        }
        listInvestments(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("deleteCode"));
        try {
            dao.remove(id);
            req.setAttribute("message", "Investimento removido com sucesso!");
        } catch (SQLException | EntityNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("error", "Erro ao remover");
        }
        listInvestments(req, resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        try{
            String description = req.getParameter("description");
            double value = Double.parseDouble(req.getParameter("value"));
            String category = req.getParameter("category");
            HttpSession session = req.getSession();
            int userId = findUserId(session.getAttribute("userEmail").toString());
            String createdAt = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));

            Investment investment = new Investment(value, createdAt, description, userId);
            dao.add(investment);
            req.setAttribute("message", "Investimento cadastrado!");

        } catch(Exception e){
            e.printStackTrace();
            req.setAttribute("error","Por favor, valide os dados");
        }
        req.getRequestDispatcher("investment-registration.jsp").forward(req, resp);
    }

    private int findUserId(String email) throws Exception {
        FintechUserDao daoFinder = new FintechUserDao();
        return daoFinder.findByEmail(email).getId();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "list-investments":
                try {
                    listInvestments(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "open-edit-form":
                openForm(req, resp);
                break;
        }
    }

    private void openForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Investment investment = null;
        try {
            investment = dao.search(id);
        } catch (SQLException | EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("investment", investment);
        req.getRequestDispatcher("edit-investment.jsp").forward(req, resp);
    }

    private void listInvestments(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Investment> investmentsList = null;
        HttpSession session = req.getSession();
        int userId = 0;
        try {
            userId = findUserId(session.getAttribute("userEmail").toString());
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            investmentsList = dao.getAll(userId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("investments", investmentsList);
        req.getRequestDispatcher("list-investments.jsp").forward(req, resp);
        return;
    }
}
