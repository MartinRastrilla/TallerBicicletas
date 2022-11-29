package tallerbicicletas;

import Data.BicicletaData;
import Data.Conexion;
import Data.ItemRepuestoData;
import Data.ReparacionData;

import Modelo.Reparacion;
import java.sql.Connection;

public class TallerBicicletas {

    public static void main(String[] args) {
        Connection con = Conexion.getConexion();
        ReparacionData rData = new ReparacionData(con);
        //rData.BorrarReparacion(1);
//        System.out.println(rData.buscarReparacion(2));
//        for (Reparacion aux : rData.obtenerReparaciones()) {
//            System.out.println(aux.toString());
//        }
        ItemRepuestoData iR = new ItemRepuestoData(con);
        System.out.println(iR.obtenerItem("00639"));

    }

}
