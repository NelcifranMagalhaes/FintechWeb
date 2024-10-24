package br.com.fintech.fintechweb.tests;

import br.com.fintech.fintechweb.dao.BalanceDao;
import br.com.fintech.fintechweb.exception.EntityNotFoundException;
import br.com.fintech.fintechweb.model.Balance;

import java.sql.SQLException;

public class TestBalance {
    public static void main( String[] args )
    {
        try {
            BalanceDao dao = insertBalance();
            // BalanceDao dao = new BalanceDao();
            Balance balanceSearched =  dao.search(4);
            System.out.println("Valor: " + balanceSearched.getValue() + "\n User: " + balanceSearched.getFintechUserId());

//            List<Balance> balances = dao.getAll();
//            System.out.println("Listando Saldo!");
//            for (Balance balance: balances) {
//                System.out.println("Valor: " + balance.getValue() + "\n User: " + balance.getFintechUserId());
//                System.out.println("-------------");
//            }
//            balanceSearched.setValue(300.85);
//            balanceSearched.setCreatedAt("20/04/2006");
//            balanceSearched.setFintechUserId(23);
//            dao.update(balanceSearched);
//            System.out.println("Saldo atualizado!\n");
//            dao.remove(3);
//            System.out.println("Saldo Removida!\n");
            dao.closeConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static BalanceDao insertBalance() throws SQLException {
        BalanceDao dao = new BalanceDao();
        Balance balanceDaoOne = new Balance(64.84, "25/08/2024", 1);
        Balance balanceDaoTwo = new Balance(100.4, "25/08/2024", 2);
        Balance balanceDaoThree = new Balance(32.00, "12/03/2023", 3);
        Balance balanceDaoFour = new Balance(60.48, "01/05/2012", 4);
        Balance balanceDaoFive = new Balance( 12.75, "08/08/2008", 5);
        dao.add(balanceDaoOne);
        dao.add(balanceDaoTwo);
        dao.add(balanceDaoThree);
        dao.add(balanceDaoFour);
        dao.add(balanceDaoFive);
        return dao;
    }
}
