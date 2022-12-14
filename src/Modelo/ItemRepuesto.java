package Modelo;

public class ItemRepuesto {

    private Repuesto num_serie;
    private Reparacion id_reparacion;
    private int cantidad;
    private boolean activo;

    public ItemRepuesto() {
    }

    public ItemRepuesto(Repuesto num_serie, Reparacion id_reparacion, int cantidad, boolean activo) {
        this.num_serie = num_serie;
        this.id_reparacion = id_reparacion;
        this.cantidad = cantidad;
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Repuesto getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(Repuesto num_serie) {
        this.num_serie = num_serie;
    }

    public Reparacion getId_reparacion() {
        return id_reparacion;
    }

    public void setId_reparacion(Reparacion id_reparacion) {
        this.id_reparacion = id_reparacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ItemRepuesto: " + "N° Serie: " + num_serie.getNum_serie() + " | N° Reparación: " + id_reparacion.getId_reparacion() + " | Cantidad: " + cantidad;
    }
}
