package src.juego;

import java.util.Scanner;

import src.jugadores.Jugador;
import src.jugadores.TablaJugadores;
import src.principal.LecturaDatos;
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

                    PiedraPapelTijera a = new PiedraPapelTijera(jugador1, jugador2);
                    Jugador ganador = a.jugar();

                    if (jugador1.equals(ganador)) {
                        crearPartida(jugador1, jugador2);
                    } else {
                        crearPartida(jugador2, jugador1);
                    }
                    
                    mismoJugador = false;

                }

            } while (mismoJugador == true);

        }

    }

    public void crearPartida(Jugador jugador1, Jugador jugador2) {

        tablero = new Tablero();
        System.out.println();
        tablero.inicializarTablero(jugador1, jugador2);
        tablero.mostrarTablero();
        System.out.println();

        tableroPartida = tablero.getCasillas();

        manejarTurnos(jugador1, jugador2);

    }

    public boolean turno(Jugador jugador, int numJugador, Jugador otroJugador) {

        boolean movimientoValido = false;

        do {
            int[] in = null, fn = null;
            System.out.println("\n  Turno de " + jugador.getNombre() + " ('"+jugador.getSimbolo()+"'):");

            System.out.println("\n  (Para finalizar la partida escribe 'no')");
            String ficha = LecturaDatos.leerTexto("  Elige la ficha a mover (a1): ");

            if (ficha.equalsIgnoreCase("no")) {
                //buscar un metodo para salir
                return false;
            }

            in = tablero.rectificarCelda(ficha, jugador, true);

            if (in[0] != -1) {
                ficha = LecturaDatos.leerTexto("  Elige la casilla a la que moveras: ");
                fn = tablero.rectificarCelda(ficha, jugador, false);
            }
            
            if (in[0] != -1 && fn[0] != -1) {
                movimientoValido = comprobarMovimiento(in[0], in[1], fn[0], fn[1], jugador, numJugador, otroJugador);
                if (movimientoValido) {
                    realizarMovimiento(tableroPartida[in[0]][in[1]], tableroPartida[fn[0]][fn[1]]);
                    tablero.mostrarTablero();
                }
            } 
            
        } while (movimientoValido == false);

        return true;

    }

    public boolean comprobarMovimiento(int filaIn, int colIn, int filaFin, int colFin, Jugador jug, int numJ, Jugador otro) {

        boolean movimientoValido = false;

        // Movimiento simple
        if (colFin == (colIn + 1) || colFin == (colIn - 1)) {
            if (numJ == 1 && filaFin == (filaIn + 1)) {
                movimientoValido = true;
            } else if (numJ == 2 && filaFin == (filaIn - 1)) {
                movimientoValido = true;
            } else {
                System.out.println("\n  Movimiento no valido");
            }
        }
        // Movimiento con salto
        else if (colFin == (colIn + 2) || colFin == (colIn - 2)) {
            if (numJ == 1 && filaFin == (filaIn + 2)) {
                // comprobar si hay algo para comer
                if (colFin == (colIn + 2) && tableroPartida[filaIn + 1][colIn + 1].getCaracter() == otro.getSimbolo()) {
                    tableroPartida[filaIn + 1][colIn + 1].setCaracter(' ');
                    movimientoValido = true;
                } else if (colFin == (colIn - 2) && tableroPartida[filaIn + 1][colIn - 1].getCaracter() == otro.getSimbolo()) {
                    tableroPartida[filaIn + 1][colIn - 1].setCaracter(' ');
                    movimientoValido = true;
                } else {
                    System.out.println("\n  Movimiento no valido");
                }

                movimientoValido = true;
            } else if (numJ == 2 && filaFin == (filaIn - 2)) {
                if (colFin == (colIn + 2) && tableroPartida[filaIn - 1][colIn + 1].getCaracter() == otro.getSimbolo()) {
                    tableroPartida[filaIn - 1][colIn + 1].setCaracter(' ');
                    movimientoValido = true;
                } else if (colFin == (colIn - 2) && tableroPartida[filaIn - 1][colIn - 1].getCaracter() == otro.getSimbolo()) {
                    tableroPartida[filaIn - 1][colIn - 1].setCaracter(' ');
                    movimientoValido = true;
                } else {
                    System.out.println("\n  Movimiento no valido");
                }
            } else {
                System.out.println("\n  Movimiento no valido");
            }
        } else {
            System.out.println("\n  Movimiento no valido");
        }

        return movimientoValido;
    }

    public void manejarTurnos(Jugador jugador1, Jugador jugador2) {

        boolean ganador = false, continuarJuego;

        do {
            continuarJuego = turno(jugador1, 1, jugador2);
            tablero.contadorFichas(jugador1, jugador2);

            if (continuarJuego) {                
                continuarJuego = turno(jugador2, 2, jugador1);
                tablero.contadorFichas(jugador1, jugador2);

                if (continuarJuego==false) {
                    jugador2.setFichasTablero(0);
                }

            } else {
                jugador1.setFichasTablero(0);
            }

            ganador = revisarGanador(jugador1, jugador2);

        } while (ganador == false);

    }

    public boolean revisarGanador(Jugador jugador1, Jugador jugador2) {

        if (jugador1.getFichasTablero() == 0 || jugador2.getFichasTablero() == 0) {

            if (jugador1.getFichasTablero() == 0) {
                jugador2.setPartidasGanadas(jugador2.getPartidasGanadas() + 1);
                jugador2.setPuntuacion(jugador2.getPuntuacion() + 3);
                jugador1.setPartidasPerdidas(jugador1.getPartidasPerdidas() + 1);

                System.out.println("\n  EL GANADOR ES: " + jugador2.getNombre());
                return true;

            } else if (jugador2.getFichasTablero() == 0) {
                jugador1.setPartidasGanadas(jugador1.getPartidasGanadas() + 1);
                jugador1.setPuntuacion(jugador1.getPuntuacion() + 3);
                jugador2.setPartidasPerdidas(jugador2.getPartidasPerdidas() + 1);

                System.out.println("\n  EL GANADOR ES: " + jugador1.getNombre());
                return true;
            } 

        }

        return false;

    }

    public void realizarMovimiento(Casilla actual, Casilla finall) {

        finall.setCaracter(actual.getCaracter());
        actual.setCaracter(' ');

    }

}
