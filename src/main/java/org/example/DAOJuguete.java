package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOJuguete {
    Connection conexion;
    Juguete juguete;
    ArrayList<Juguete> listaJuguetes;

    //Constructor
    public DAOJuguete() {
        conexion = DB.getConexion();
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
}
