package Data;

import Modelo.Bicicleta;
import Modelo.Reparacion;
import Modelo.Servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReparacionData {

    private Connection con;
    private BicicletaData bData;
    private ServicioData sData;
    
    public ReparacionData() {
        this.con = Conexion.getConexion();
        this.bData = new BicicletaData();
        this.sData = new ServicioData();
    }

    public void ingresarReparacion(Reparacion r) {
        String query = "INSERT INTO reparacion (id_servicio, id_bicicleta, fecha_entrada, costo, estado , activo) VALUES ( ? , ? , ? , ? , ? , ? )";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, r.getId_servicio().getCodigo());
            ps.setString(2, r.getId_bicicleta().getNumSerie());
            ps.setDate(3, java.sql.Date.valueOf(r.getFecha_entrada()));
            ps.setFloat(4, r.getCosto());
            ps.setBoolean(5, r.isEstado());
            ps.setBoolean(6, r.isActivo());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Reparación Agregada");

            } else {
                JOptionPane.showMessageDialog(null, "Reparación No Agregada");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                r.setId_reparacion(rs.getInt(1));
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-ingresarReparación | " + e.getMessage());
        }
    }

    public Reparacion buscarReparacion(int idRep) {
        Reparacion r = null;
        String sql = "SELECT * FROM reparacion WHERE id_reparacion = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idRep);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                r = new Reparacion();
                r.setId_reparacion(rs.getInt("id_reparacion"));
                r.setId_servicio(sData.obtenerServicio(rs.getInt("id_servicio")));
                r.setId_bicicleta(bData.buscarBiciNumSerie(rs.getString("id_bicicleta")));
                r.setFecha_entrada(rs.getDate("fecha_entrada").toLocalDate());
                r.setCosto(rs.getFloat("costo"));
                r.setActivo(rs.getBoolean("activo"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-buscarReparacion");
        }
        return r;
    }

    public void BorrarReparacion(int id) {
        String sql = "UPDATE reparacion SET activo = 0 WHERE id_reparacion = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Reparacion Eliminada");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la Reparacion");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-BorrarReparacion");
        }
    }

    public void actualizarReparacion(Reparacion r, int id, int idServ) {
        String sql = "UPDATE reparacion SET id_servicio=?, id_bicicleta=?, fecha_entrada=?, costo=?, estado=? WHERE id_reparacion=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idServ);
            ps.setString(2, r.getId_bicicleta().getNumSerie());
            ps.setDate(3, java.sql.Date.valueOf(r.getFecha_entrada()));
            ps.setFloat(4, r.getCosto());
            ps.setBoolean(5, r.isEstado());
            ps.setInt(6, id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Reparacion actualizada con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar la reparacion");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-ActualizarReparacion | " + ex.getMessage());
        }
    }

    public ArrayList<Reparacion> obtenerReparaciones() {
        ArrayList<Reparacion> listaReparacion = new ArrayList();
        String sql = "SELECT * FROM reparacion WHERE activo = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reparacion r = new Reparacion();
                r.setId_reparacion(rs.getInt("id_reparacion"));
                r.setId_servicio(sData.obtenerServicio(rs.getInt("id_servicio")));
                r.setId_bicicleta(bData.buscarBiciNumSerie(rs.getString("id_bicicleta")));
                r.setFecha_entrada(rs.getDate("fecha_entrada").toLocalDate());
                r.setCosto(rs.getFloat("costo"));
                r.setEstado(rs.getBoolean("estado"));
                r.setActivo(rs.getBoolean("activo"));
                listaReparacion.add(r);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-obtenerReparaciones ");
        }
        return listaReparacion;
    }
    
    public ArrayList<Reparacion> filtrarReparacionPorDNI(String dni){
        ArrayList<Reparacion> listaDuenios = new ArrayList();
        String sql = "SELECT r.id_reparacion, r.id_servicio, r.id_bicicleta, r.fecha_entrada, r.costo, r.estado, r.activo FROM cliente AS c, reparacion AS r, bicicleta AS b WHERE c.dni=? AND b.dni_duenio = c.dni AND b.num_serie = r.id_bicicleta;";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reparacion r = new Reparacion();
                r.setId_reparacion(rs.getInt("id_reparacion"));
                r.setId_servicio(sData.obtenerServicio(rs.getInt("id_servicio")));
                r.setId_bicicleta(bData.buscarBiciNumSerie(rs.getString("id_bicicleta")));
                r.setFecha_entrada(rs.getDate("fecha_entrada").toLocalDate());
                r.setCosto(rs.getFloat("costo"));
                r.setEstado(rs.getBoolean("estado"));
                r.setActivo(rs.getBoolean("activo"));
                listaDuenios.add(r);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-obtenerReparaciones | "+ ex.getMessage());
        }
        return listaDuenios;
    }
    
    public void costoTotal(){
        String sql="SELECT SUM(precio*itemrepuesto.cantidad)\n" +
                    "FROM repuesto, itemrepuesto\n" +
                    "WHERE repuesto.num_serie = itemrepuesto.num_serie\n" +
                    "AND itemrepuesto.id_reparacion = 20;"; 
    }

}