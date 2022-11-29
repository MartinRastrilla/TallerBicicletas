package Modelo;

import java.time.LocalDate;

public class Reparacion {

    private int id_reparacion;
    private Servicio id_servicio;
    private Bicicleta id_bicicleta;
    private LocalDate fecha_entrada;
    private float costo;
    private boolean estado;
    private boolean activo;

    public Reparacion() {
    }

    public Reparacion(int id_reparacion, Servicio id_servicio, Bicicleta id_bicicleta, LocalDate fecha_entrada, float costo, boolean estado) {
        this.id_reparacion = id_reparacion;
        this.id_servicio = id_servicio;
        this.id_bicicleta = id_bicicleta;
        this.fecha_entrada = fecha_entrada;
        this.costo = costo;
        this.estado = estado;
    }

    public Reparacion(Servicio id_servicio, Bicicleta id_bicicleta, LocalDate fecha_entrada, float costo, boolean estado) {
        this.id_servicio = id_servicio;
        this.id_bicicleta = id_bicicleta;
        this.fecha_entrada = fecha_entrada;
        this.costo = costo;
        this.estado = estado;
    }

    public int getId_reparacion() {
        return id_reparacion;
    }

    public void setId_reparacion(int id_reparacion) {
        this.id_reparacion = id_reparacion;
    }

    public Servicio getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Servicio id_servicio) {
        this.id_servicio = id_servicio;
    }

    public Bicicleta getId_bicicleta() {
        return id_bicicleta;
    }

    public void setId_bicicleta(Bicicleta id_bicicleta) {
        this.id_bicicleta = id_bicicleta;
    }

    public LocalDate getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(LocalDate fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public float getCosto() {
        return costo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Reparacion:" + " N° Reparación: " + id_reparacion + " | N° Servicio: " + id_servicio + " | N° Serie Bicicleta: " + id_bicicleta + " | Ingreso: " + fecha_entrada + " | Costo: " + costo + " | Estado: " + estado;
    }
}
