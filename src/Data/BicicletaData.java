package Data;

import Modelo.Bicicleta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void agregarBicicleta(Bicicleta bici) {
        String query = "Insert INTO bicicleta (numSerie, tipo, color, marca, dni_duenio, activo) VALUES (?,?,?,?,?,?)";
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
            Logger.getLogger(BicicletaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Bicicleta obtenerBicicleta(String dni) {
        Bicicleta bici = null;
        String sql = "SELECT * FROM bicicleta WHERE dni_duenio = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bici = new Bicicleta();
                bici.setDniDuenio(cData.buscarClienteDNI(rs.getString("dni_duenio")));
                bici.setNumSerie("numSerie");
                bici.setTipo(rs.getString("tipo"));
                bici.setColor(rs.getString("color"));
                bici.setMarca(rs.getString("marca"));
                bici.setActivo(rs.getBoolean("activo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BicicletaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bici;
    }
}
