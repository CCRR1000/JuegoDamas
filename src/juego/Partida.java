package src.juego;

import java.util.Scanner;

import src.jugadores.Jugador;
import src.jugadores.TablaJugadores;
import src.tablero.Casilla;
import src.tablero.Tablero;

public class Partida {

    private Jugador jugador1, jugador2;
    private TablaJugadores tablaJ;
    private Tablero tablero;
    Casilla tableroPartida[][];
    Scanner scan = new Scanner(System.in);

    public Partida(TablaJugadores tablaJ) {

        this.tablaJ = tablaJ;
        inicializarPartida();

    }

    public void inicializarPartida() {

        boolean mismoJugador = true;

        if (tablaJ.getUltimoJugador() < 2) {
            System.out.println("\n  (!) Debe ingresar jugadores para poder iniciar una partida.");
            
        } else {
            
            do {
                System.out.println("\n  ELIJA AL JUGADOR 1");
                jugador1 = tablaJ.seleccionarJugador();
    
                System.out.println("\n  ELIJA AL JUGADOR 2");
                jugador2 = tablaJ.seleccionarJugador();
    
                if (jugador1.equals(jugador2)) {
                    System.out.println("\n  Error. El jugador 1 y el jugador 2 son el mismo.");
                } else {
                    crearPartida(jugador1, jugador2);
                    mismoJugador = false;
                }
    
            } while (mismoJugador == true);

        }
        

    }

    public void crearPartida(Jugador jugador1, Jugador jugador2) {

        tablero = new Tablero();
        System.out.println();
        tablero.inicializarTablero();
        tablero.mostrarTablero();
        System.out.println();


        tableroPartida = tablero.getCasillas();

        manejarTurnos(jugador1, jugador2);

       /*  realizarMovimiento(tableroPartida[5][1], tableroPartida[4][2]);
        tablero.mostrarTablero();

        realizarMovimiento(tableroPartida[2][2], tableroPartida[3][3]);
        tablero.mostrarTablero(); */
    }

    public void turno(Jugador jugador) {

        // primero elige la casilla inicial y la final

        int colIn = 0, filaIn = 0, colFn = 0, filaFn=0;

        System.out.println("\n  Turno de "+jugador.getNombre()+":");
        System.out.print("  Elige la ficha a mover (a1): ");
        String ficha = scan.nextLine();
        colIn = tablero.buscarIndiceLetras(ficha.charAt(0));
        filaIn = Character.getNumericValue(ficha.charAt(1))-1;
        System.out.print("  Elige la casilla a la que moveras: ");
        ficha = scan.nextLine();
        colFn = tablero.buscarIndiceLetras(ficha.charAt(0));
        filaFn = Character.getNumericValue(ficha.charAt(1))-1;


        realizarMovimiento(tableroPartida[filaIn][colIn], tableroPartida[filaFn][colFn]);
        tablero.mostrarTablero();

        // revisar si la casilla inicial tiene letra

        // revisar si es x ^ o

        // revisar si la casilla final está vacia

        // comprobar si se puede hacer el movimiento


    }


    public void manejarTurnos(Jugador jug1, Jugador jug2) {
        //hacer condicion
        boolean ganador = false;
        do {
            turno(jug1);
            turno(jug2);
        } while (ganador==false);
    }

    public void realizarMovimiento(Casilla actual, Casilla finall) {

        finall.setCaracter(actual.getCaracter());
        actual.setCaracter(' ');
        
    }

}
