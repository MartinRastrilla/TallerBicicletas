package tallerbicicletas;

import Data.BicicletaData;
import Data.ClienteData;
import Data.Conexion;
import Modelo.Bicicleta;
import Modelo.Cliente;
import java.sql.Connection;


public class TallerBicicletas {

    public static void main(String[] args) {
        Connection con  = Conexion.getConexion();
        ClienteData cData = new ClienteData(con);
        BicicletaData bData = new BicicletaData();
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
        
        
        Cliente c = new Cliente("41911000", "Gustavo", "Orozco", "Calle A veces Viva 123", "2664119944", true);
        Bicicleta b = new Bicicleta("a434w98", "BMX", "Negra", "Loud", c, true);
        //bData.agregarBicicleta(b);
        for (Bicicleta aux : bData.obtenerBiciCliente("41911000")) {
            System.out.println(aux.toString());
        }
        //bData.borrarBicicleta("a434w98");
        bData.actualizarBicicleta("a434w98", b);
    }

}
