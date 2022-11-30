package Data;

import Modelo.Servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ServicioData {

    private Connection conn;

    public ServicioData() {
        this.conn = Conexion.getConexion();

    }

    public void agregarServicio(Servicio svc) {
        String sql = "INSERT INTO `servicio`(`precio`, `descripcion`, `activo`) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setFloat(1, svc.getPrecio());
            ps.setString(2, svc.getDescripcion());
            ps.setBoolean(3, svc.isActivo());
            
            String mensaje;

            int nuevoRegistro = ps.executeUpdate();

            if (nuevoRegistro > 0) {
                mensaje = "Servicio Añadido";
            } else {
                mensaje = "Error al añadir Servicio";
            }

            JOptionPane.showMessageDialog(null, mensaje);
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-AgregaServicio");
        }
    }

    public void eliminarServicioPorCodigo(int codigo) {
        try {
            int resultado;
            String sql = "UPDATE servicio SET activo = 0 WHERE codigo = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigo);
            resultado = ps.executeUpdate();
            String mensaje;
            if (resultado > 0) {
                mensaje = "Servicio Eliminado";
            } else {
                mensaje = "Servicio no encontrado";
            }
            JOptionPane.showMessageDialog(null, mensaje);
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-borrarServicio");
        }

    }

    public void eliminarServicioPorNombre(String nombre) {
        try {
            int resultado;
            String sql = "UPDATE servicio SET activo = 0 WHERE descripcion = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            resultado = ps.executeUpdate();
            String mensaje;
            if (resultado > 0) {
                mensaje = "Servicio Eliminado";
            } else {
                mensaje = "Servicio no encontrado";
            }
            JOptionPane.showMessageDialog(null, mensaje);
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-borrarServicio");
        }
    }

    public Servicio obtenerServicio(int codigo) {
        Servicio sv = null;
        try {
            String sql = "SELECT * FROM `servicio` WHERE codigo= ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, codigo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                sv = new Servicio();
                sv.setCodigo(codigo);
                sv.setDescripcion(rs.getString("descripcion"));
                sv.setPrecio(rs.getFloat("precio"));
                sv.setActivo(rs.getBoolean("activo"));
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-ObtenerServicio");
        }
        return sv;
    }

    public ArrayList<Servicio> listarServicios() {

        ArrayList<Servicio> lista = new ArrayList<>();
        

        String sql = "SELECT * FROM servicio";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Servicio sv = new Servicio();
                sv.setCodigo(rs.getInt("codigo"));
                sv.setPrecio(rs.getFloat("precio"));
                sv.setDescripcion(rs.getString("descripcion"));
                sv.setActivo(rs.getBoolean("activo"));

                lista.add(sv);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-ListaServicios");
        }

        return lista;
    }

    public void modificarServicio(Servicio sv, int codigo) {
        String sql = "UPDATE servicio SET precio= ?,descripcion= ?,activo= ? WHERE codigo= ?";
        String mensaje;
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, sv.getPrecio());
            ps.setString(2, sv.getDescripcion());
            ps.setBoolean(3, sv.isActivo());
            ps.setInt(4, codigo);

            int camb = ps.executeUpdate();

            if (camb > 0) {
                mensaje = "Modificacion exitosa";

            } else {
                mensaje = "No fue posible realizar la modificacion";

            }

            JOptionPane.showMessageDialog(null, mensaje);
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-ModificarServicio");
        }

    }

}
