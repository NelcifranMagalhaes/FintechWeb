package br.com.fintech.fintechweb.controller;

import br.com.fintech.fintechweb.dao.FintechUserDao;
import br.com.fintech.fintechweb.dao.MoneyOutDao;
import br.com.fintech.fintechweb.exception.EntityNotFoundException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/index")
public class indexServlet extends HttpServlet {
    private MoneyOutDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {

        try {
            dao = new MoneyOutDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Object> moneyOutsList = null;
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
            moneyOutsList = dao.getCountMoneyOut(userId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("moneyOuts", moneyOutsList);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
        return;
    }

    private int findUserId(String email) throws Exception {
        FintechUserDao daoFinder = new FintechUserDao();
        return daoFinder.findByEmail(email).getId();
    }
}
