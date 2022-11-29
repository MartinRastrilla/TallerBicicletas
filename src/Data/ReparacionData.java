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
    
    public ReparacionData(Connection con) {
        this.con = con;
    }

    public void ingresarReparacion(Bicicleta b, Servicio s, Reparacion r) {
        String query = "INSERT INTO reparacion (id_servicio, id_bicicleta, fecha_entrada, costo, estado , activo) VALUES ( ? , ? , ? , ? , ? , ? )";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, s.getCodigo());
            ps.setString(2, b.getDniDuenio().getDni());
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

    public Reparacion buscarReparacion(int dni) {
        Reparacion r = null;
        String sql = "SELECT * FROM reparacion WHERE id_bicicleta = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ServicioData sD = new ServicioData();
                r = new Reparacion();
                r.setId_reparacion(rs.getInt("id_reparacion"));
//                r.setId_servicio(sData.obtenerServicio());
//                r.setId_bicicleta(bData.obtenerBicicleta(rs.getString("dni_duenio")));
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
        String sql = "UPDATE reparacion SET activo = 0 WHERE reparacion.id_reparacion = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Reparacion Eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la Reparacion");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-BorrarReparacion");
        }
    }

    public void actualizarReparacion(Reparacion r) {
        String sql = "UPDATE reparacion SET fecha_entrada=?, costo=?, estado=? WHERE id_reparacion=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(r.getFecha_entrada()));
            ps.setFloat(2, r.getCosto());
            ps.setBoolean(3, r.isEstado());
            ps.setInt(4, r.getId_reparacion());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Lista de reparacion actualizada con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar la lista de reparacion");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-ActualizarReparacion ");
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
//                r.setId_servicio(sData.obtenerServicio(rs.getInt("codigo")));
//                r.setId_bicicleta(bData.obtenerBicicleta(rs.getString("dni_duenio")));
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

}
