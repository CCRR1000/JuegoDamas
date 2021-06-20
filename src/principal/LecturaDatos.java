package src.principal;

import java.util.Scanner;

public class LecturaDatos {
    
    static Scanner scan = new Scanner(System.in);

    private static void imprimir(String mensaje) {

        System.out.print(mensaje);

    }

    public static String leerTexto(String mensaje) {

        imprimir(mensaje);
        String txt = scan.nextLine();
        return txt;

    }

    public static int leerEntero(String mensaje) {

        imprimir(mensaje);
        int ent = Integer.parseInt(scan.nextLine());
        return ent;

    }


    
}
