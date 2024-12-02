package org.example;

public class Juguete {
    private int id;
    private String nombre;

    public Juguete() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Juguete{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
