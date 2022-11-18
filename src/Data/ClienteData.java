package Data;

import Modelo.Cliente;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ClienteData {
    private Connection con;

    public ClienteData(Connection con) {
        this.con = con;
    }
    
    public void agregarCliente(Cliente cliente){
        String query = "INSERT INTO cliente (dni, nombreCompleto, domicilio, telefono, activo) VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombreCompleto());
            ps.setString(3, cliente.getDomicilio());
            ps.setString(4, cliente.getTelefono());
            ps.setBoolean(5, cliente.isActivo());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente Agregado");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido agregar al cliente");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-AgregarCliente");
        }
    }
    
    public Cliente buscarClienteDNI(String dni){
        Cliente cliente = null;
        String query = "SELECT * FROM cliente WHERE dni=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombreCompleto(rs.getString("nombreCompleto"));
                cliente.setDomicilio(rs.getString("domicilio"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setActivo(rs.getBoolean("activo"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-buscarClienteDNI");
        }
        return cliente;
    }
    
    public Cliente buscarClienteNombre(String nombre){
        Cliente cliente  =  null;
        String query = "SELECT * FROM cliente WHERE nombreCompleto LIKE '?%' ";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombreCompleto(rs.getString("nombreCompleto"));
                cliente.setDomicilio(rs.getString("domicilio"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setActivo(rs.getBoolean("activo"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-buscarClienteNombre");
        }
        return cliente;
    }
    
    public void borrarCliente(String dni){
        String query = "UPDATE cliente SET activo = 0 WHERE dni = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, dni);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente Eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-borrarCliente");
        }
        
    }
}
