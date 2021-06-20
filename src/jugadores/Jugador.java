package src.jugadores;

public class Jugador {
    
    private String nombre;
    private int partidasGanadas, partidasPerdidas, puntuacion, fichasTablero;
    


    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String mostrarDatosJugador() {
        return " Nombre: " + nombre + ", Partidas ganadas: " + partidasGanadas + ", Partidas perdidas: " + partidasPerdidas + ", Puntuacion: " + puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getFichasTablero() {
        return fichasTablero;
    }

    public void setFichasTablero(int fichasTablero) {
        this.fichasTablero = fichasTablero;
    }

    

}
