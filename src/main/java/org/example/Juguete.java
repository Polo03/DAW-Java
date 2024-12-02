package org.example;

public class Juguete {
    private int id;
    private String nombre;

    public Juguete() {
        super();
    }

    public Juguete(String nombre) {
        this.nombre = nombre;
    }
    public Juguete(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
