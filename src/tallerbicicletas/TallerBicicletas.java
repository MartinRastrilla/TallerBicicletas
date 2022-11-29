package tallerbicicletas;

import Data.Conexion;
import Data.ReparacionData;
import java.sql.Connection;


public class TallerBicicletas {

    public static void main(String[] args) {
        Connection con=Conexion.getConexion();
        ReparacionData rData= new ReparacionData(con);
       
        rData.buscarReparacion(2);
    }

}
