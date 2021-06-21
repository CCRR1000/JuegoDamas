package src.tablero;

public class Tablero {
    
    private Casilla casillas[][] = new Casilla[8][8];
    private char[] letras = {'a','b','c','d','e','f','g','h'};

    public Tablero() {

    }

    public void inicializarTablero() {

        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas.length; j++) {

                if ((i+j)%2==0) {
                    casillas[i][j] = new Casilla(true, ' ');
                } 
                else {
                    casillas[i][j] = new Casilla(false, ' ');
                }

                if (i<3 && casillas[i][j].isOscuro()) {
                    casillas[i][j].setCaracter('X');
                } 

                if (i>4 && casillas[i][j].isOscuro()) {
                    casillas[i][j].setCaracter('O');
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

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }

    
}
