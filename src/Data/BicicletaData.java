package Data;

import Modelo.Bicicleta;
import Modelo.Cliente;
import com.mysql.jdbc.Statement;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class BicicletaData {
    private Connection con;
    private ClienteData cData;
    
    public BicicletaData() {
        this.con = Conexion.getConexion();
        this.cData = new ClienteData(con);
    }
    
    public void agregarBicicleta(Bicicleta bici){
        String query = "Insert INTO bicicleta (num_serie, tipo, color, marca, dni_duenio, activo) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bici.getNumSerie());
            ps.setString(2, bici.getTipo());
            ps.setString(3, bici.getColor());
            ps.setString(4, bici.getMarca());
            ps.setString(5, bici.getDniDuenio().getDni());
            ps.setBoolean(6, bici.isActivo());
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Bicicleta Agregada");

            } else {
                JOptionPane.showMessageDialog(null, "Bicicleta No Agregada");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                bici.setNumSerie(rs.getString(1));

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-AgregaBicicleta");
        }
    }
    
    public ArrayList<Bicicleta> obtenerBiciCliente(String dni){
        String query = "SELECT * FROM bicicleta WHERE dni_duenio = ?;"; 
        ArrayList<Bicicleta> bicicletasDuenio = new ArrayList();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bicicleta b = new Bicicleta();
                Cliente c = new Cliente();
                c.setDni(rs.getString("dni_duenio"));
                b.setNumSerie(rs.getString("num_serie"));
                b.setTipo(rs.getString("tipo"));
                b.setColor(rs.getString("color"));
                b.setMarca(rs.getString("marca"));
                b.setDniDuenio(c);
                b.setActivo(rs.getBoolean("activo"));
                bicicletasDuenio.add(b);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-obtenerBiciCliente");
        }
        return bicicletasDuenio;
        }
    
    public void borrarBicicleta(String numSerie){
        String query = "UPDATE bicicleta SET activo = 0 WHERE num_serie = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, numSerie);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Bicicleta Eliminada");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la Bicicleta");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-borrarBicicleta");
        }
        
    }
    
    public void actualizarBicicleta(String numSerie, Bicicleta bici){
        String query = "UPDATE bicicleta SET num_serie=?,tipo=?,color=?,marca=?, dni_duenio=?, activo=? WHERE num_serie=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, bici.getNumSerie());
            ps.setString(2, bici.getTipo());
            ps.setString(3, bici.getColor());
            ps.setString(4, bici.getMarca());
            ps.setString(5, bici.getDniDuenio().getDni());
            ps.setBoolean(6, bici.isActivo());
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Bicicleta Actualizada");

            } else {
                JOptionPane.showMessageDialog(null, "Bicicleta No Actualizada");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sentencia SQL errónea-ActualizarBicicleta");
        }
    }
}
