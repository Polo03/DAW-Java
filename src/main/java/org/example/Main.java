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
        int idJuguete;
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
                for (Juguete juguete: dao.getAllJuguetes()){
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
                System.out.print("Introduzca el id del juguete a modificar: ");
                idJuguete=scanner.nextInt();
                scanner.nextLine();
                System.out.print("Introduzca el nuevo nombre del juguete: ");
                nombreJuguete=scanner.nextLine();
                dao.updateJuguete(new Juguete(idJuguete,nombreJuguete));
                System.out.println("Juguete Modificado.\n");
                break;

            case 6:
                System.out.print("Introduzca el id del juguete a eliminar: ");
                idJuguete=scanner.nextInt();
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
