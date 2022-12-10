package Modelo;

public class Servicio {

    private int codigo;
    private float precio;
    private String descripcion;
    private boolean activo;

    public Servicio(int codigo, float precio, String descripcion, boolean activo) {
        this.codigo = codigo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Servicio(float precio, String descripcion, boolean activo) {
        this.precio = precio;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Servicio() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Servicio: " + "Codigo: " + codigo + " | Precio=" + precio + " | Descripcion: " + descripcion + " | Servicio Activo: " + activo;
    }
}
