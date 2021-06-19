package src.juego;

import src.jugadores.Jugador;
import src.jugadores.TablaJugadores;
import src.tablero.Casilla;
import src.tablero.Tablero;

public class Partida {

    private Jugador jugador1, jugador2;
    private TablaJugadores tablaJ;
    private Tablero tablero;

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


        Casilla tableroPartida[][] = tablero.getCasillas();

        realizarMovimiento(tableroPartida[5][1], tableroPartida[4][2]);
        tablero.mostrarTablero();

        realizarMovimiento(tableroPartida[2][2], tableroPartida[3][3]);
        tablero.mostrarTablero();
    }

    public void realizarMovimiento(Casilla actual, Casilla finall) {

        finall.setCaracter(actual.getCaracter());
        actual.setCaracter(' ');
        
    }

}
