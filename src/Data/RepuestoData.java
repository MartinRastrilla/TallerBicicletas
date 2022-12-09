package Data;


import Modelo.Repuesto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

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
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                repuesto.setNum_serie(rs.getString(1));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void eliminarRepuestoPorNumSerie(String num_serie) {
        try {
            int resultado;
            String sql = "UPDATE repuesto SET activo = 0 WHERE num_serie = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, num_serie);
            resultado = ps.executeUpdate();
            
            String mensaje;
            if (resultado > 0) {
                mensaje = "Repuesto Eliminado";
            } else {
                mensaje = "No se pudo eliminar el repuesto";
            }
            JOptionPane.showMessageDialog(null, mensaje);
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-borrarRepuesto");
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
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public Repuesto obtenerRepuesto(String num_serie) {
        String sql = "SELECT * FROM repuesto WHERE num_serie = ?;";
        Repuesto repuesto = null;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, num_serie);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                repuesto = new Repuesto();
                repuesto.setNum_serie(rs.getString("num_serie"));
                repuesto.setDescripcion(rs.getString("descripcion"));
                repuesto.setPrecio(rs.getFloat("precio"));
                repuesto.setActivo(rs.getBoolean("activo"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-ObtenerRepuesto");
        }
        return repuesto;
    }

    public void modificarRepuesto(Repuesto repuesto, String numSerie) {
        String mensaje;
        try {
            String sql = "UPDATE `repuesto` SET num_serie = ?, `descripcion`= ?,`precio`= ?,`activo`= ? WHERE num_serie= ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, repuesto.getNum_serie());
            ps.setString(2, repuesto.getDescripcion());
            ps.setFloat(3, repuesto.getPrecio());
            ps.setBoolean(4, repuesto.isActivo());
            ps.setString(5, numSerie);
            int modificacion = ps.executeUpdate();
            if (modificacion > 0) {
                mensaje = "Repuesto Modificado";

            } else {
                mensaje = "No se pudo modificar el repuesto";
            }
            JOptionPane.showMessageDialog(null, mensaje);
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-ActualizarRepuesto");
        }
    }

    public ArrayList<Repuesto> listadoRepuesto() {

        ArrayList<Repuesto> lista = new ArrayList();
        String sql = "SELECT * FROM repuesto";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Repuesto rp = new Repuesto();
                rp.setNum_serie(rs.getString("num_serie"));
                rp.setDescripcion(rs.getString("descripcion"));
                rp.setPrecio(rs.getFloat("precio"));
                rp.setActivo(rs.getBoolean("activo"));
                lista.add(rp);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-obtenerListaRepuesto");
        }

        return lista;
    }
}
