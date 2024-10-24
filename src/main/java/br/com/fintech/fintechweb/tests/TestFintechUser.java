package br.com.fintech.fintechweb.tests;

import br.com.fintech.fintechweb.dao.FintechUserDao;
import br.com.fintech.fintechweb.exception.EntityNotFoundException;
import br.com.fintech.fintechweb.model.FintechUser;
import java.sql.SQLException;
import java.util.List;

public class TestFintechUser
{
    public static void main( String[] args )
    {
        try {
            //FintechUserDao dao = getFintechUserDao();
            FintechUserDao dao = new FintechUserDao();
            //FintechUserDao dao = getFintechUserDao();
            List<FintechUser> fintechUsers = dao.getAll();
            for (FintechUser fintechUser: fintechUsers) {
                System.out.println(fintechUser.getName()+ "\n" + fintechUser.getEmail());
                System.out.println("-------------");
            }
//            FintechUser fintechUser =  dao.search(21);
//            fintechUser.setName("Katsuki Bakugo");
//            fintechUser.setEmail("bakugo@gmail.com");
//            fintechUser.setBirthDate("20/04/2006");
//            fintechUser.setCreatedAt("22/04/2024");
//            fintechUser.setPasswordHash("ad154ds2ffd87as6#$");
//            dao.update(fintechUser);
//            System.out.println("Usuário atualizado!");
//            dao.remove(22);
//            System.out.println("Usuário Removido!");
            dao.closeConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static FintechUserDao getFintechUserDao() throws SQLException, EntityNotFoundException {
        FintechUserDao dao = new FintechUserDao();
        FintechUser first_user = new FintechUser("Midoria Izuku","midoria@gmail.com", "M", "18/11/1999",
                "$2y$10$vHFK3x3TwOJZ2E67IxX2LOAYKmFUh", "30/09/2024" );
        dao.add(first_user);
        FintechUser second_user = new FintechUser("Shota Aizawa","aizawa@gmail.com", "M", "08/11/1999",
                "$2y$10$vHFK3x3TwOJZ2E67IxX2LOAYKmFUh", "30/09/2024" );
        dao.add(second_user);
        FintechUser third_user = new FintechUser("Toshinori Yagi","yagi@gmail.com", "M", "10/06/1989",
                "$2y$10$vHFK3x3TwOJZ2E67IxX2LOAYKmFUh", "30/09/2024" );
        dao.add(third_user);
        FintechUser fourth_user = new FintechUser("Rumi Usagiyama","mirko@gmail.com", "F", "01/03/1998",
                "$2y$10$vHFK3x3TwOJZ2E67IxX2LOAYKmFUh", "30/09/2024" );
        dao.add(fourth_user);
        FintechUser fifth_user = new FintechUser("Kaina Tsutsumi ","kaina@gmail.com", "F", "10/10/2000",
                "$2y$10$vHFK3x3TwOJZ2E67IxX2LOAYKmFUh", "30/09/2024" );
        dao.add(fifth_user);
        return dao;
    }
}
