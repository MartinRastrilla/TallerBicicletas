package Modelo;

public class Repuesto {

    private String num_serie;
    private String descripcion;
    private float precio;
    private boolean activo;

    public Repuesto(String num_serie, String descripcion, float precio, boolean activo) {
        this.num_serie = num_serie;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activo = activo;
    }

    public Repuesto(String num_serie, String descripcion, float precio) {
        this.num_serie = num_serie;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Repuesto() {
    }

    public String getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(String num_serie) {
        this.num_serie = num_serie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Repuesto:" + "N° Serie: " + num_serie + " | Descripción: " + descripcion.toUpperCase() + " | Precio: " + precio + " | Stock: " + activo;
    }
}
