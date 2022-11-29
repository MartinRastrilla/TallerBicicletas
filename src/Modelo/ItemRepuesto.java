package Modelo;

public class ItemRepuesto {

    private Repuesto num_serie;
    private Reparacion id_reparacion;
    private int cantidad;

    public ItemRepuesto() {
    }

    public ItemRepuesto(Repuesto num_serie, Reparacion id_reparacion, int cantidad) {
        this.num_serie = num_serie;
        this.id_reparacion = id_reparacion;
        this.cantidad = cantidad;
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
        return "ItemRepuesto: " + "N° Serie: " + num_serie + " | N° Reparación: " + id_reparacion + " | Cantidad: " + cantidad;
    }
}
