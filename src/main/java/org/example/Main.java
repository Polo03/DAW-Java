package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DAOJuguete dao = new DAOJuguete();
        Juguete juguete = new Juguete();
        for(Juguete jugueteARecorrer:dao.getAllJuguetes()){
            System.out.println(jugueteARecorrer.toString());
        }
//        System.out.println(dao.getJugueteById(1).toString());
        juguete.setId(4);
        juguete.setNombre("Mordedor");
//        dao.addJuguete(juguete);
        juguete.setNombre("Huesito");
//        dao.updateJuguete(juguete);
//        dao.deleteJuguete(juguete);
    }

}
