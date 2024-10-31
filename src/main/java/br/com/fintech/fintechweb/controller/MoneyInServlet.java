package br.com.fintech.fintechweb.controller;

import br.com.fintech.fintechweb.dao.BalanceDao;
import br.com.fintech.fintechweb.dao.FintechUserDao;
import br.com.fintech.fintechweb.dao.MoneyInDao;
import br.com.fintech.fintechweb.exception.EntityNotFoundException;
import br.com.fintech.fintechweb.model.Balance;
import br.com.fintech.fintechweb.model.MoneyIn;
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

@WebServlet("/moneyIns")
public class MoneyInServlet extends HttpServlet {
    private MoneyInDao dao;
    private BalanceDao balanceDao;


    @Override
    public void init(ServletConfig config) throws ServletException {

        try {
            dao = new MoneyInDao();
            balanceDao = new BalanceDao();
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
        String label = req.getParameter("description");
        double value = Double.parseDouble(req.getParameter("value"));
        String createdAt = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));
        MoneyIn moneyIn = new MoneyIn(id, value, createdAt, label);
        try {
            dao.update(moneyIn);
            req.setAttribute("message", "Receita Atualizada");
        } catch (SQLException e) {
            req.setAttribute("error", "Erro ao Atualizar");
            throw new RuntimeException(e);
        }
        listMoneyIns(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("deleteCode"));
        try {
            dao.remove(id);
            req.setAttribute("message", "Receita removida com sucesso!");
        } catch (SQLException | EntityNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("error", "Erro ao remover");
        }
        listMoneyIns(req, resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        try{
            String label = req.getParameter("description");
            double value = Double.parseDouble(req.getParameter("value"));
            HttpSession session = req.getSession();
            int userId = findUserId(session.getAttribute("userEmail").toString());
            String createdAt = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));

            MoneyIn moneyIn = new MoneyIn(value, createdAt, label, userId);
            dao.add(moneyIn);
            Balance balance = new Balance(value, createdAt, userId);
            balanceDao.add(balance);
            req.setAttribute("message", "Receita cadastrada!");

        } catch(Exception e){
            e.printStackTrace();
            req.setAttribute("error","Por favor, valide os dados");
        }
        req.getRequestDispatcher("money-in-registration.jsp").forward(req, resp);
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
            case "list-moneyIns":
                try {
                    listMoneyIns(req, resp);
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
        MoneyIn moneyIn = null;
        try {
            moneyIn = dao.search(id);
        } catch (SQLException | EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("moneyIn", moneyIn);
        req.getRequestDispatcher("edit-money-in.jsp").forward(req, resp);
    }

    private void listMoneyIns(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<MoneyIn> moneyInsList = null;
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
            moneyInsList = dao.getAll(userId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("moneyIns", moneyInsList);
        req.getRequestDispatcher("list-money-ins.jsp").forward(req, resp);
        return;
    }
}
