package src.tablero;

import src.jugadores.Jugador;

public class Tablero {
    
    private Casilla casillas[][] = new Casilla[8][8];
    private char[] letras = {'a','b','c','d','e','f','g','h'};

    public Tablero() {

    }

    public void inicializarTablero(Jugador jugador1, Jugador jugador2) {

        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas.length; j++) {

                if ((i+j)%2==0) {
                    casillas[i][j] = new Casilla(true, ' ');
                } 
                else {
                    casillas[i][j] = new Casilla(false, ' ');
                }

                if (i<3 && casillas[i][j].isOscuro()) {
                    casillas[i][j].setCaracter(jugador1.getSimbolo());
                } 

                if (i>4 && casillas[i][j].isOscuro()) {
                    casillas[i][j].setCaracter(jugador2.getSimbolo());
                }
                
            }
        }
    } 

    public void mostrarTablero() {

        System.out.println();

        imprimirFilaLetras();

        for (int i = 0; i < casillas.length; i++) {
            System.out.print("     " + (i+1)+" ");
            for (int j = 0; j < casillas.length; j++) {
                System.out.print(casillas[i][j].getCasilla());
            }
            System.out.print(" " + (i+1));
            System.out.println();
        }

        imprimirFilaLetras();
      
    }

    private void imprimirFilaLetras() {

        System.out.print("       ");
        for (int i = 0; i < letras.length; i++) {
            System.out.print(" "+letras[i]+" ");
        }
        System.out.println();

    }

    public int buscarIndiceLetras(char letra) {
        int indice = -1;
        for (int i = 0; i < letras.length; i++) {
            if (letra == letras[i]) {
                indice = i;
                break;
            }
        }
        return indice;
    }

    public void contadorFichas(Jugador jugador1, Jugador jugador2) {
        jugador1.setFichasTablero(0);
        jugador2.setFichasTablero(0);
        
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas.length; j++) {

                if (casillas[i][j].getCaracter()==jugador1.getSimbolo()) {
                    jugador1.setFichasTablero(jugador1.getFichasTablero()+1);
                } 
                
                if (casillas[i][j].getCaracter()==jugador2.getSimbolo()) {
                    jugador2.setFichasTablero(jugador2.getFichasTablero()+1);
                }
                
            }
        }
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }

    public int[] rectificarCelda(String ficha, Jugador jugador, boolean esInicial) {

        int columna = buscarIndiceLetras(ficha.charAt(0));
        int fila = Character.getNumericValue(ficha.charAt(1)) - 1;
        int[] indices = {fila, columna}, wrong = {-1, -1};

        if (columna < 0 || fila < 0 || fila > 7) {
            System.out.println("\n  La celda se sale del tablero");
            return wrong;
        }

        if (esInicial) {

            if (casillas[fila][columna].getCaracter() == ' ') {
                System.out.println("\n  La celda inicial esta vacia");
                return wrong;
            } else if (casillas[fila][columna].getCaracter() != jugador.getSimbolo()) {
                System.out.println("\n  La ficha que elegiste no es tuya");
                return wrong;
            } else {
                return indices;
            }

        }
        else {

            if (casillas[fila][columna].getCaracter() != ' ') {
                System.out.println("\n  La celda ya esta ocupada");
                return wrong;
            } else {
                return indices;
            }

        }
        
    }



}
