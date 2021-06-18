package src.tablero;

public class Casilla {
    
    private String fGris = "\u001b[47;1m", fRojo = "\033[41m", negro = "\033[30m", reset = "\u001B[0m";
    private char caracter;
    private boolean oscuro;

    public Casilla() {

    }

    public Casilla(boolean oscuro, char caracter) {
        this.oscuro = oscuro;
        this.caracter = caracter;
    }

    public String getCasilla() {
        if (oscuro) {
            return fRojo+negro+" "+caracter+" "+reset;
        } else {
            return fGris + negro+"   "+reset;
        }
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public boolean isOscuro() {
        return oscuro;
    }

    public void setOscuro(boolean oscuro) {
        this.oscuro = oscuro;
    }

    
/* 
    public String getCasillaRoja(char caracter) {
        return fRojo+negro+" "+caracter+" "+reset;
    }

    public String getCasillaGris(char caracter) {
        return fGris+negro+" "+caracter+" "+reset;
    }
   */  


}
