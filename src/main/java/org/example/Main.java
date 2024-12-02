package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int indice=0;
        do {
            System.out.println("¿Que accion quiere realizar?");
            System.out.println("1. Crear Juguete");
            System.out.println("2. Meter Juguetes Aleatorios");
            System.out.println("3. Listar todos los juguetes");
            System.out.println("4. Buscar Juguete");
            System.out.println("5. Actualizar Juguete");
            System.out.println("6. Eliminar Juguete");
            System.out.println("7. Eliminar todos los juguetes");
            System.out.println("8. Salir");
            System.out.print("Ingrese una opcion: ");
            if (scanner.hasNextInt()) {
                indice = scanner.nextInt();
            } else {
                indice=0;
            }
            scanner.nextLine();
            realizarAccion(indice);
        }while(indice!=8);

    }
    public static void realizarAccion(int indice) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DAOJuguete dao = new DAOJuguete();
        String nombreJuguete;
        int idJuguete=0;
        boolean entradaValida;
        switch (indice){
            case 1:
                System.out.print("Introduzca el nombre del juguete a introducir: ");
                nombreJuguete=scanner.nextLine();
                dao.addJuguete(new Juguete(dao.getLastId()+1,nombreJuguete));
                System.out.println("Juguete Creado.\n");
                break;

            case 2:
                dao.meterJuguetesPruebas();
                break;

            case 3:
                entradaValida = false;
                int numeroOrdenar=0;
                while (!entradaValida) {
                    System.out.println("¿Como quieres ordenar los juguetes?");
                    System.out.println("1. Por Id");
                    System.out.println("2. Por Nombre");

                    if (scanner.hasNextInt()) {  // Verifica si el siguiente token es un entero
                        numeroOrdenar=scanner.nextInt(); // Lee el número entero
                        if(numeroOrdenar==1 || numeroOrdenar==2)
                            entradaValida = true;  // Si es un entero, salimos del bucle
                    } else {
                        // Si no es un número entero, mostramos un mensaje y continuamos solicitando
                        System.out.println("¡Eso no es un número entero! Intenta de nuevo.");
                        scanner.next();  // Limpiar el buffer de entrada
                    }
                }
                for (Juguete juguete: dao.getAllJuguetes(numeroOrdenar)) {
                    System.out.println(juguete.toString());
                }
                break;

            case 4:
                System.out.print("Introduzca el nombre del juguete a buscar: ");
                nombreJuguete=scanner.nextLine();
                Juguete juguete = dao.getJugueteByNombre(nombreJuguete);
                if(juguete!=null)
                    System.out.println(juguete.toString()+"\n");
                else
                    System.out.println("No existe el juguete.\n");
                break;

            case 5:
                // Repetir hasta que el usuario ingrese un número entero válido
                entradaValida = false;
                while (!entradaValida) {
                    System.out.print("Introduzca el id del juguete a modificar: ");

                    if (scanner.hasNextInt()) {  // Verifica si el siguiente token es un entero
                        idJuguete = scanner.nextInt();  // Lee el número entero
                        entradaValida = true;  // Si es un entero, salimos del bucle
                    } else {
                        // Si no es un número entero, mostramos un mensaje y continuamos solicitando
                        System.out.println("¡Eso no es un número entero! Intenta de nuevo.");
                        scanner.next();  // Limpiar el buffer de entrada
                    }
                }
                scanner.nextLine();
                System.out.print("Introduzca el nuevo nombre del juguete: ");
                nombreJuguete=scanner.nextLine();
                dao.updateJuguete(new Juguete(idJuguete,nombreJuguete));
                System.out.println("Juguete Modificado.\n");
                break;

            case 6:
                // Repetir hasta que el usuario ingrese un número entero válido
                entradaValida = false;
                while (!entradaValida) {
                    System.out.print("Introduzca el id del juguete a eliminar: ");

                    if (scanner.hasNextInt()) {  // Verifica si el siguiente token es un entero
                        idJuguete = scanner.nextInt();  // Lee el número entero
                        entradaValida = true;  // Si es un entero, salimos del bucle
                    } else {
                        // Si no es un número entero, mostramos un mensaje y continuamos solicitando
                        System.out.println("¡Eso no es un número entero! Intenta de nuevo.");
                        scanner.next();  // Limpiar el buffer de entrada
                    }
                }
                dao.deleteJuguete(new Juguete(idJuguete,""));
                System.out.println("Juguete Eliminado.\n");
                break;

            case 7:
                dao.deleteAllJuguetes();
                System.out.println("Se han eliminado todos los juguetes de la base de datos");
                break;

            case 8:
                System.out.println("Ha salido de la consola");
                break;

            default:
                System.out.println("Ha ocurrido un error a la hora de introducir la opción, introduzcala otra vez porfavor.");
                break;

        }
    }

}
