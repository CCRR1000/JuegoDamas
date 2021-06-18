package src.jugadores;

import java.util.Scanner;

public class TablaJugadores {
 
    private final int MAXIMO_JUGADORES = 5;
    private Jugador[] jugadores = new Jugador[MAXIMO_JUGADORES];
    Scanner scan = new Scanner(System.in);
    private int ultimoJugador=0;

    public TablaJugadores(){
    }

    public void ingresarJugador() {

        if (ultimoJugador < MAXIMO_JUGADORES) {
            System.out.print("\n  Escriba el nombre del jugador: ");
            jugadores[ultimoJugador] = new Jugador(scan.nextLine());
            ultimoJugador++;

        } else {
            System.out.println("\n    No se puede ingresar, ya ha alcanzado el numero maximo de jugadores.");
        }    

    }

    public void mostrarJugadores() {

        if (ultimoJugador == 0) {
            System.out.println("\n    No hay jugadores ingresados.");
        } else {
            System.out.println("\n   - Lista de Jugadores -");
            for (int i = 0; i < ultimoJugador; i++) {
                System.out.println("  "+(i+1)+". "+jugadores[i].getNombre());
            }
        }

    }

    public void mostrarTablaPosiciones () {

        if (ultimoJugador == 0) {
            System.out.println("\n    No hay jugadores ingresados.");
        } 
        else {
            ordenarTabla();
            System.out.println("\n   - Tabla de Posiciones -\n");
            for (int i = 0; i < ultimoJugador; i++) {
                System.out.println("  "+(i+1)+". "+jugadores[i].mostrarDatosJugador());
            }
        }
    }

    public void ordenarTabla() {

        for (int i = 0; i < ultimoJugador; i++) {
            for (int j = 0; j < ultimoJugador-1; j++) {

                if (jugadores[j].getPuntuacion() < jugadores[j+1].getPuntuacion()) {
                    Jugador aux = jugadores[j];
                    jugadores[j] = jugadores[j+1];
                    jugadores[j+1] = aux;
                }
            }
        }

    }

    public Jugador seleccionarJugador() {

        mostrarJugadores();

        System.out.print("\n  Escriba el numero del jugador: ");
        int num = Integer.parseInt(scan.nextLine());

        return jugadores[num-1];
    }

    public int getUltimoJugador() {
        return ultimoJugador;
    }

    public void setUltimoJugador(int ultimoJugador) {
        this.ultimoJugador = ultimoJugador;
    }

    

}
