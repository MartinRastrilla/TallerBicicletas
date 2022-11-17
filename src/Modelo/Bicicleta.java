package Modelo;


public class Bicicleta {
    private int numSerie;
    private String tipo;
    private String color;
    private Cliente dniDuenio;
    private boolean activo;

    public Bicicleta(int numSerie, String tipo, String color, Cliente dniDuenio, boolean activo) {
        this.numSerie = numSerie;
        this.tipo = tipo;
        this.color = color;
        this.dniDuenio = dniDuenio;
        this.activo = activo;
    }

    public int getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Cliente getDniDuenio() {
        return dniDuenio;
    }

    public void setDniDuenio(Cliente dniDuenio) {
        this.dniDuenio = dniDuenio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
}
