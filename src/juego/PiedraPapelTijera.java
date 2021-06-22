package src.juego;

import java.util.Random;

import src.jugadores.Jugador;
import src.principal.LecturaDatos;

public class PiedraPapelTijera {

    private Jugador jugador1, jugador2, ganador, perdedor;
    private Random random = new Random();

    public PiedraPapelTijera(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public Jugador jugar() {
        int numGanador;
        System.out.println("\n\nJuguemos Piedra, Papel o Tijera, el ganador iniciara y podra elegir simbolo");
        do {
            int objeto1 = random.nextInt(3);
            String icono1 = getIcono(objeto1);
            int objeto2 = random.nextInt(3);
            String icono2 = getIcono(objeto2);
            numGanador = enfrentarJugadores(objeto1, objeto2);
            cuadrarNombre(this.jugador1);
            cuadrarNombre(this.jugador2);

            System.out.println("\n    " + jugador1.getNombre().substring(0, 8) + "             " + jugador2.getNombre().substring(0, 8));
            System.out.println("     " + getObjeto(objeto1) + "     < - >     " + getObjeto(objeto2));
            System.out.println("       " + icono1 + "                   " + icono2);

            if (numGanador != 0) {
                definirGanador(numGanador);
                System.out.println("\n  GANA: " + this.ganador.getNombre());
                elegirSimbolo();
            }

        } while (numGanador == 0);

        return ganador;
    }

    public void elegirSimbolo() {

        System.out.println("\n  Felicidades " + this.ganador.getNombre() + ", ahora elige el simbolo que quieres usar:");
        System.out.println("  1. X");
        System.out.println("  2. O");
        int simb = LecturaDatos.leerEntero("Escribe el numero del simbolo: ");

        if (simb == 2) {
            ganador.setSimbolo('O');
            perdedor.setSimbolo('X');
        } else {
            ganador.setSimbolo('X');
            perdedor.setSimbolo('O');
        }

    }

    public int enfrentarJugadores(int J1, int J2) {
        // 0 = PIEDRA , 1 = PAPEL , 2 = TIJERA
        if ((J1 == 0 && J2 == 2) || (J1 == 1 && J2 == 0) || (J1 == 2 && J2 == 1)) {
            return 1;
        } else if ((J2 == 0 && J1 == 2) || (J2 == 1 && J1 == 0) || (J2 == 2 && J1 == 1)) {
            return 2;
        } else {
            return 0;
        }
    }

    public void definirGanador(int num) {

        if (num == 1) {
            this.ganador = jugador1;
            this.perdedor = jugador2;
        } else if (num == 2) {
            this.ganador = jugador2;
            this.perdedor = jugador1;
        }

    }

    public String getObjeto(int objeto) {

        if (objeto == 0) {
            return "PIEDRA";
        } else if (objeto == 1) {
            return "PAPEL ";
        } else {
            return "TIJERA";
        }
    }

    public String getIcono(int objeto) {

        if (objeto == 0) {
            return "<>";
        } else if (objeto == 1) {
            return "[]";
        } else {
            return "8<";
        }
    }
   
    public void cuadrarNombre(Jugador jugador) {

        if (jugador.getNombre().length() < 8) {

            for (int i = 0; i < (8-jugador.getNombre().length()); i++) {
                jugador.setNombre(jugador.getNombre()+" ");
            }
        }

    }

}
