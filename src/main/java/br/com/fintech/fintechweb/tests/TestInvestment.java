package br.com.fintech.fintechweb.tests;

import br.com.fintech.fintechweb.dao.InvestmentDao;
import br.com.fintech.fintechweb.model.Investment;
import java.sql.SQLException;
import java.util.List;

public class TestInvestment {
    public static void main( String[] args )
    {
        try {
            InvestmentDao dao = insertInvestment();
            //InvestmentDao dao = new InvestmentDao();
//            Investment investmentSearched =  dao.search(4);
//            System.out.println("Label: " + investmentSearched.getDescription() + "\n Valor: " + investmentSearched.getValue());
//
            List<Investment> investments = dao.getAll(1);
            System.out.println("Listando Investimentos!");
            for (Investment investment: investments) {
                System.out.println("Label: " + investment.getDescription() + "\n Valor: " + investment.getValue());
                System.out.println("-------------");
            }
//            investmentSearched.setDescription("Comprar uma Waifu!");
//            investmentSearched.setValue(300.85);
//            investmentSearched.setCreatedAt("20/04/2006");
//            investmentSearched.setFintechUserId(23);
//            dao.update(investmentSearched);
//            System.out.println("Investimento atualizado!\n");
//            dao.remove(3);
//            System.out.println("Investimento Removida!\n");
//            dao.closeConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static InvestmentDao insertInvestment() throws SQLException {
        InvestmentDao dao = new InvestmentDao();
        Investment investmentDaoOne = new Investment(64.84, "25/08/2024", "Comprar Carro", 1);
        Investment investmentDaoTwo = new Investment(100.4, "25/08/2024", "Comprar Casa", 2);
        Investment investmentDaoThree = new Investment(32.00, "12/03/2023", "Comprar PS5", 3);
        Investment investmentDaoFour = new Investment(60.48, "01/05/2012", "Comprar o Suco", 4);
        Investment investmentDaoFive = new Investment( 12.75, "08/08/2008", "Viajar para o Japão", 5);
        dao.add(investmentDaoOne);
        dao.add(investmentDaoTwo);
        dao.add(investmentDaoThree);
        dao.add(investmentDaoFour);
        dao.add(investmentDaoFive);
        return dao;
    }
}
