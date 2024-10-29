package br.com.fintech.fintechweb.tests;

import br.com.fintech.fintechweb.dao.MoneyOutDao;
import br.com.fintech.fintechweb.exception.EntityNotFoundException;
import br.com.fintech.fintechweb.model.MoneyOut;
import br.com.fintech.fintechweb.model.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TestMoneyOut
{
    public static void main( String[] args )
    {
        try {
            MoneyOutDao dao = insertOut();
            // MoneyOutDao dao = new MoneyOutDao();
//            MoneyOut moneyOutSearched =  dao.search(10);
//            System.out.println("Label: " + moneyOutSearched.getDescription() + "\n Valor: " + moneyOutSearched.getValue());


            List<MoneyOut> moneyOuts = dao.getAll(1);
            System.out.println("Listando Despesas!");
            for (MoneyOut moneyOut: moneyOuts) {
                System.out.println("Label: " + moneyOut.getDescription() + "\n Category: " + moneyOut.getCategory());
                System.out.println("-------------");
            }
//            moneyOutSearched.setDescription("Perdi no Tigrinho");
//            moneyOutSearched.setValue(35.85);
//            moneyOutSearched.setCreatedAt("20/04/2006");
//            moneyOutSearched.setFintechUserId(23);
//            dao.update(moneyOutSearched);
//            System.out.println("Despesa atualizado!\n");
//            dao.remove(5);
//            System.out.println("Despesa Removida!\n");
            dao.closeConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static MoneyOutDao insertOut() throws SQLException {
        MoneyOutDao dao = new MoneyOutDao();
        Transaction moneyOutOne = new MoneyOut(64.84, "25/08/2024", "Cinema", 1, "diversão");
        Transaction moneyOutTwo = new MoneyOut(100.4, "25/08/2024", "Uber", 1,"transporte");
        Transaction moneyOutThree = new MoneyOut(32.00, "12/03/2023", "Pipoca", 1, "alimentação");
        Transaction moneyOutFour = new MoneyOut(60.48, "01/05/2012", "Lanche", 1, "alimentacao");
        Transaction moneyOutFive = new MoneyOut( 12.75, "08/08/2008", "Suco de Uva", 1, "alimentacao");
        dao.add(moneyOutOne);
        dao.add(moneyOutTwo);
        dao.add(moneyOutThree);
        dao.add(moneyOutFour);
        dao.add(moneyOutFive);
        return dao;
    }
}
