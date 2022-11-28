package tallerbicicletas;

import Data.ClienteData;
import Data.Conexion;
import Modelo.Cliente;
import java.sql.Connection;


public class TallerBicicletas {

    public static void main(String[] args) {
        Connection con  = Conexion.getConexion();
        ClienteData cData = new ClienteData(con);
        /*
        //PRUEBAS CLIENTE-DATA
        Cliente c = new Cliente("41911000", "Gustavo", "Orozco", "Calle A veces Viva 123", "2664119944", true);
        cData.agregarCliente(c);
        cData.borrarCliente("43490178");
        System.out.println(cData.buscarClienteDNI("44911000"));
        for (Cliente aux : cData.buscarClienteApellido("orozco")) {
            System.out.println(aux.toString());
        }
        */
        Cliente c = new Cliente("41911123", "Lorenzo", "Tapia", "Calle A veces Viva 456", "2664229944", true);
        cData.agregarCliente(c);
        
    }

}
