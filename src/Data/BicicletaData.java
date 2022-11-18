package Data;

import Modelo.Bicicleta;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BicicletaData {
    private Connection con;
    private ClienteData cData;
    
    public BicicletaData() {
        this.con = Conexion.getConexion();
        this.cData = new ClienteData(con);
    }
    
    public void agregarBicicleta(Bicicleta bici){
        String query = "Insert INTO bicicleta (numSerie, tipo, color, dni_duenio, activo) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bici.getNumSerie());
            ps.setString(2, bici.getTipo());
            ps.setString(3, bici.getColor());
        } catch (SQLException ex) {
            Logger.getLogger(BicicletaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
