package br.com.fintech.fintechweb.controller;

import br.com.fintech.fintechweb.dao.BalanceDao;
import br.com.fintech.fintechweb.dao.FintechUserDao;
import br.com.fintech.fintechweb.dao.MoneyOutDao;
import br.com.fintech.fintechweb.exception.EntityNotFoundException;
import br.com.fintech.fintechweb.model.Balance;
import br.com.fintech.fintechweb.model.MoneyOut;
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

@WebServlet("/moneyOuts")
public class MoneyOutServlet extends HttpServlet {
    private MoneyOutDao dao;
    private BalanceDao balanceDao;
    @Override
    public void init(ServletConfig config) throws ServletException {

        try {
            dao = new MoneyOutDao();
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
        String category = req.getParameter("category");
        double value = Double.parseDouble(req.getParameter("value"));
        String createdAt = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));
        MoneyOut moneyOut = new MoneyOut(id, value, createdAt, label, category);
        try {
            dao.update(moneyOut);
            req.setAttribute("message", "Despesa Atualizada");
        } catch (SQLException e) {
            req.setAttribute("error", "Erro ao Atualizar");
            throw new RuntimeException(e);
        }
        listMoneyOuts(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("deleteCode"));
        try {
            dao.remove(id);
            req.setAttribute("message", "Despesa removida com sucesso!");
        } catch (SQLException | EntityNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("error", "Erro ao remover");
        }
        listMoneyOuts(req, resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        try{
            String label = req.getParameter("description");
            double value = Double.parseDouble(req.getParameter("value"));
            String category = req.getParameter("category");
            HttpSession session = req.getSession();
            int userId = findUserId(session.getAttribute("userEmail").toString());
            String createdAt = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));

            MoneyOut moneyOut = new MoneyOut(value, createdAt, label, userId, category);
            dao.add(moneyOut);
            Balance balance = new Balance(-value, createdAt, userId);
            balanceDao.add(balance);
            req.setAttribute("message", "Despesa cadastrada!");

        } catch(Exception e){
            e.printStackTrace();
            req.setAttribute("error","Por favor, valide os dados");
        }
        req.getRequestDispatcher("money-out-registration.jsp").forward(req, resp);
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
            case "list-moneyOuts":
                try {
                    listMoneyOuts(req, resp);
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
        MoneyOut moneyOut = null;
        try {
            moneyOut = dao.search(id);
        } catch (SQLException | EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("moneyOut", moneyOut);
        req.getRequestDispatcher("edit-money-out.jsp").forward(req, resp);
    }

    private void listMoneyOuts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<MoneyOut> moneyOutsList = null;
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
            moneyOutsList = dao.getAll(userId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("moneyOuts", moneyOutsList);
        req.getRequestDispatcher("list-money-outs.jsp").forward(req, resp);
        return;
    }
}
