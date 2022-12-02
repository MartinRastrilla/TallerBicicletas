package Data;


import Modelo.ItemRepuesto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ItemRepuestoData {

    private Connection con;
    private ReparacionData repData;
    private RepuestoData rData;

    public ItemRepuestoData() {
        this.con = Conexion.getConexion();
        this.repData = new ReparacionData();
        this.rData = new RepuestoData();
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

    public ItemRepuesto obtenerItem(String numSerie) {
        ItemRepuesto i = null;
        String sql = "SELECT * FROM itemrepuesto WHERE num_serie = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, numSerie);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                i = new ItemRepuesto();
                i.setNum_serie(rData.obtenerRepuesto(rs.getString("num_serie")));
                i.setId_reparacion(repData.buscarReparacion(rs.getInt("id_reparacion")));
                i.setCantidad(rs.getInt("cantidad"));
                i.setActivo(rs.getBoolean("activo"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-ObtenerItem");
        }
        return i;
    }

    public void borrarItem(String numSerie) {
        String query = "UPDATE itemrepuesto SET activo = 0 WHERE itemrepuesto.num_serie = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, numSerie);

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

    public void actualizarItem(ItemRepuesto iR) {
        String sql = "UPDATE itemrepuesto SET num_serie=?, id_reparacion=?, cantidad=?, activo=? WHERE num_serie = ? AND id_reparacion = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, iR.getNum_serie().getNum_serie());
            ps.setInt(2, iR.getId_reparacion().getId_reparacion());
            ps.setInt(3, iR.getCantidad());
            ps.setBoolean(4, iR.isActivo());
            ps.setString(5, iR.getNum_serie().getNum_serie());
            ps.setInt(6, iR.getId_reparacion().getId_reparacion());
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Item actualizado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar el Item");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-actualizarItem ");
        }
    }
    
    //MÉTODO NO APLICABLE
//    public void eliminarItemsDeReparacion(){
//        String sql = "SELECT i.num_serie, i.id_reparacion, i.cantidad, i.activo FROM itemrepuesto AS i, reparacion AS r WHERE i.id_reparacion = r.id_reparacion AND r.estado=0;";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ItemRepuesto item = new ItemRepuesto();
//                item.setNum_serie(rData.obtenerRepuesto(rs.getString("num_serie")));
//                item.setId_reparacion(repData.buscarReparacion(rs.getInt("id_reparacion")));
//                item.setCantidad(rs.getInt("cantidad"));
//                item.setActivo(rs.getBoolean("activo"));
//                borrarItem(item.getNum_serie().getNum_serie());
//            }
//            ps.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-borrarItem | "+ex.getMessage());
//        }
//    }
    
    public ArrayList<ItemRepuesto> obtenerRepuestosDeReparacion(int id){
        String sql = "SELECT * FROM itemrepuesto WHERE id_reparacion = ? AND activo=true;";
        ArrayList<ItemRepuesto> itemsReparacion = new ArrayList();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemRepuesto item = new ItemRepuesto();
                item.setNum_serie(rData.obtenerRepuesto(rs.getString("num_serie")));
                item.setId_reparacion(repData.buscarReparacion(rs.getInt("id_reparacion")));
                item.setCantidad(rs.getInt("cantidad"));
                item.setActivo(rs.getBoolean("activo"));
                itemsReparacion.add(item);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-obtenerRepuestosDeReparacion | "+ex.getMessage());
        }
        return itemsReparacion;
    }
    
    public ArrayList<ItemRepuesto> obtenerRepuestos(){
        String sql = "SELECT * FROM itemrepuesto WHERE activo=true;";
        ArrayList<ItemRepuesto> itemsReparacion = new ArrayList();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemRepuesto item = new ItemRepuesto();
                item.setNum_serie(rData.obtenerRepuesto(rs.getString("num_serie")));
                item.setId_reparacion(repData.buscarReparacion(rs.getInt("id_reparacion")));
                item.setCantidad(rs.getInt("cantidad"));
                item.setActivo(rs.getBoolean("activo"));
                itemsReparacion.add(item);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-obtenerRepuestos | "+ex.getMessage());
        }
        return itemsReparacion;
    }
}
