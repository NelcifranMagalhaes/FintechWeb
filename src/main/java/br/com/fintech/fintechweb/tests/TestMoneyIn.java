package br.com.fintech.fintechweb.tests;

import br.com.fintech.fintechweb.dao.MoneyInDao;
import br.com.fintech.fintechweb.exception.EntityNotFoundException;
import br.com.fintech.fintechweb.model.MoneyIn;
import br.com.fintech.fintechweb.model.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TestMoneyIn
{
    public static void main( String[] args )
    {
        try {
            MoneyInDao dao = insertMoney();
            //MoneyInDao dao = new MoneyInDao();
//            MoneyIn moneyInSearched =  dao.search(4);
//            System.out.println("Label: " + moneyInSearched.getDescription() + "\n Valor: " + moneyInSearched.getValue());


            List<MoneyIn> moneyIns = dao.getAll(4);
            System.out.println("Listando Receitas!");
            for (MoneyIn moneyIn: moneyIns) {
                System.out.println("Label: " + moneyIn.getDescription() + "\n Valor: " + moneyIn.getValue());
                System.out.println("-------------");
            }
//            moneyInSearched.setDescription("Ganhei no Tigrinho");
//            moneyInSearched.setValue(35.85);
//            moneyInSearched.setCreatedAt("20/04/2006");
//            moneyInSearched.setFintechUserId(23);
//            dao.update(moneyInSearched);
//            System.out.println("Receita atualizado!\n");
//            dao.remove(3);
//            System.out.println("Rceita Removida!\n");
            dao.closeConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static MoneyInDao insertMoney() throws SQLException {
        MoneyInDao dao = new MoneyInDao();
        Transaction moneyInOne = new MoneyIn(15500, "25/08/2024", "trampo", 1);
        Transaction moneyInTwo = new MoneyIn(200.22, "25/08/2024", "trampo 2", 2);
        Transaction moneyInThree = new MoneyIn(2000.54, "12/03/2023", "Venda do ps4", 3);
        Transaction moneyInFour = new MoneyIn(600.02, "01/05/2012", "Fiz um site", 4);
        Transaction moneyInFive = new MoneyIn( 200.22, "08/08/2008", "Emprestimo no BB", 5);
        dao.add(moneyInOne);
        dao.add(moneyInTwo);
        dao.add(moneyInThree);
        dao.add(moneyInFour);
        dao.add(moneyInFive);
        return dao;
    }
}
