package Data;

import Modelo.Repuesto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class RepuestoData {

    private Connection conn;

    public RepuestoData() {

        this.conn = Conexion.getConexion();
    }

    public void agregarRepuesto(Repuesto repuesto) {
        try {
            String sql = "INSERT INTO `repuesto`(`num_serie`, `descripcion`, `precio`, `activo`) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, repuesto.getNum_serie());
            ps.setString(2, repuesto.getDescripcion());
            ps.setFloat(3, repuesto.getPrecio());
            ps.setBoolean(4, repuesto.isActivo());

            String mensaje;

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                mensaje = "Ingreso de Repuesto exitoso";
            } else {
                mensaje = "No se pudo realizar el ingreso del Repuesto";
            }

            JOptionPane.showMessageDialog(null, mensaje);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void eliminarRepuestoPorNumSerie(int num_serie) {
        try {
            int resultado;
            String sql = "SELECT * FROM `repuesto` WHERE num_serie= ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            resultado = ps.executeUpdate();
            String mensaje;
            if (resultado > 0) {
                mensaje = "Registro de Repuesto Eliminado";
            } else {
                mensaje = "No se pudo realizar el ingreso";
            }
            JOptionPane.showMessageDialog(null, mensaje);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void eliminarRepuestoPorNombre(String nombre) {
        try {
            int resultado;
            String sql = "SELECT * FROM `repuesto` WHERE descripcion= ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            resultado = ps.executeUpdate();
            String mensaje;
            if (resultado > 0) {
                mensaje = "Registro de Repuesto Eliminado";
            } else {
                mensaje = "No se pudo realizar el ingreso";
            }
            JOptionPane.showMessageDialog(null, mensaje);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void obtenerRepuesto(String num_serie) {

        String sql = "SELECT * FROM `repuesto` WHERE num_serie= ?";
        Repuesto repuesto = new Repuesto();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, num_serie);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                repuesto.setNum_serie(num_serie);
                repuesto.setDescripcion(rs.getString("descripcion"));
                repuesto.setPrecio(rs.getFloat("precio"));
                repuesto.setActivo(rs.getBoolean("activo"));
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void modificarRepuesto(Repuesto repuesto) {
        String mensaje;
        try {
            String sql = "UPDATE `repuesto` SET `descripcion`= ?,`precio`= ?,`activo`= ? WHERE num_serie= ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, repuesto.getDescripcion());
            ps.setFloat(2, repuesto.getPrecio());
            ps.setBoolean(3, repuesto.isActivo());
            ps.setString(4, repuesto.getNum_serie());
            int modificacion = ps.executeUpdate();
            if (modificacion > 0) {
                mensaje = "Modificacion exitosa";

            } else {
                mensaje = "No se pudo realizar la modificacion";
            }

            JOptionPane.showMessageDialog(null, mensaje);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public ArrayList<Repuesto> listadoRepuesto() {

        ArrayList<Repuesto> lista = new ArrayList<>();
        Repuesto rp = new Repuesto();

        String sql = "SELECT * FROM repuesto";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rp = new Repuesto();
                rp.setNum_serie(rs.getString("num_serie"));
                rp.setDescripcion(rs.getString("descripcion"));
                rp.setPrecio(rs.getFloat("precio"));
                rp.setActivo(rs.getBoolean("activo"));
                lista.add(rp);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return lista;
    }

}
