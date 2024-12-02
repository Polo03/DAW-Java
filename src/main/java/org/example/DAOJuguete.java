package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class DAOJuguete {
    Connection conexion;
    Juguete juguete;
    ArrayList<Juguete> listaJuguetes;
    ArrayList<String> listaNombres;

    //Constructor
    public DAOJuguete() {
        conexion = DB.getConexion();
        listaNombres=new ArrayList<>();
        listaNombres.add("Juguete 1");
        listaNombres.add("Juguete 2");
        listaNombres.add("Juguete 3");
        listaNombres.add("Juguete 4");
        listaNombres.add("Juguete 5");
    }

    public ArrayList<Juguete> getAllJuguetes() throws SQLException {
        listaJuguetes = new ArrayList<>();
        PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM juguetes");

        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            juguete = new Juguete();
            int id=resultado.getInt(1);
            String nombre=resultado.getString(2);
            juguete.setId(id);
            juguete.setNombre(nombre);
            listaJuguetes.add(juguete);
        }
        return listaJuguetes;
    }
    public Juguete getJugueteById(int id) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM juguetes where id = ?");
        sentencia.setInt(1, id);
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            juguete = new Juguete();
            juguete.setId(resultado.getInt(1));
            juguete.setNombre(resultado.getString(2));
        }
        return juguete;

    }

    public Juguete getJugueteByNombre(String nombre) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM juguetes where nombre = ?");
        sentencia.setString(1, nombre);
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            juguete = new Juguete();
            juguete.setId(resultado.getInt(1));
            juguete.setNombre(resultado.getString(2));
        }
        return juguete;

    }

    public void meterJuguetesPruebas() throws SQLException {
        Random random = new Random();
        for(int i=0;i<10;i++){
            String nombre = listaNombres.get(random.nextInt(listaNombres.size()));
            this.addJuguete(new Juguete(i, nombre));
        }
    }
    public int addJuguete(Juguete juguete) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO juguetes (id, nombre) VALUES (?, ?)");
        sentencia.setInt(1, juguete.getId());
        sentencia.setString(2, juguete.getNombre());
        return sentencia.executeUpdate();


    }
    public int updateJuguete(Juguete juguete) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("UPDATE juguetes SET nombre = ? WHERE id = ?");
        sentencia.setInt(2, juguete.getId());
        sentencia.setString(1, juguete.getNombre());
        return sentencia.executeUpdate();

    }

    public int deleteJuguete(Juguete juguete) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM juguetes WHERE id = ?");
        sentencia.setInt(1, juguete.getId());
        return sentencia.executeUpdate();

    }

    public int deleteAllJuguetes() throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM juguetes");
        return sentencia.executeUpdate();

    }

    public int getLastId() throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("SELECT MAX(id) FROM juguetes;");
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            return resultado.getInt(1);
        }
        return 0;
    }
}
