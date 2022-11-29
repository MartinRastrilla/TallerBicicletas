package Data;

import Modelo.ItemRepuesto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ItemRepuestoData {

    private Connection con;
    private ReparacionData repData;
    private RepuestoData rData;

    public ItemRepuestoData(Connection con) {
        this.con = con;
    }

    public void ingresarItems(ItemRepuesto i) {
        String sql = "INSERT INTO itemrepuesto (num_serie, id_reparacion, cantidad, activo) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, i.getNum_serie().getNum_serie());
            ps.setInt(2, i.getId_reparacion().getId_reparacion());
            ps.setInt(3, i.getCantidad());
            ps.setBoolean(4, i.isActivo());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Item Agregado");

            } else {
                JOptionPane.showMessageDialog(null, "Item No Agregado");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-ingresarItems | " + e.getMessage());
        }
    }

    public ItemRepuesto obtenerItem(String idreparacion) {
        ItemRepuesto i = null;
        String sql = "SELECT * FROM itemrepuesto WHERE id_reparacion = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idreparacion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                i = new ItemRepuesto();
//                i.setNum_serie(rData.obtenerRepuesto(rs.getString("num_serie")));
//                i.setId_reparacion(repData.buscarReparacion(rs.getInt("id_reparacion")));
                i.setCantidad(rs.getInt("cantidad"));
                i.setActivo(rs.getBoolean("activo"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-ObtenerItem");
        }
        return i;
    }

    public void borrarItem(int id_reparacion) {
        String query = "UPDATE itemrepuesto SET activo = 0 WHERE itemrepuesto.num_serie = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id_reparacion);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Item Eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el Item");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-borrarItem");
        }
    }

    public void actualizarCantidadItem(ItemRepuesto iR) {
        String sql = "UPDATE itemrepuesto SET cantidad=? WHERE num_serie LIKE ? AND id_reparacion = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, iR.getCantidad());
//            ps.setString(2, iR.getNum_serie());
//            ps.setString(3, iR.getId_reparacion());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cantidad de Items actualizado¿a con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar la cantidad de Items");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-actualizarCantidadItem ");
        }
    }

    
      
}
