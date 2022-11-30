package tallerbicicletas;

import Data.BicicletaData;
import Data.ClienteData;
import Data.Conexion;
import Data.RepuestoData;
import Data.ServicioData;
import Modelo.Bicicleta;
import Modelo.Cliente;
import Modelo.Repuesto;
import Modelo.Servicio;
import java.sql.Connection;


public class TallerBicicletas {

    public static void main(String[] args) {
        Connection con  = Conexion.getConexion();
        ClienteData cData = new ClienteData(con);
        BicicletaData bData = new BicicletaData();
        RepuestoData repuestoData = new RepuestoData();
        ServicioData servicioData = new ServicioData();
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
        
        
        //PRUEBAS BICICLETA-DATA seleccionar lineas comentadas y luego CTRL+SHIFT+C para quitar comentado
        
//        Cliente c = new Cliente("41911000", "Gustavo", "Orozco", "Calle A veces Viva 123", "2664119944", true);
//        Bicicleta b = new Bicicleta("a434w98", "Mountain", "Rosa", "Loud", c, true);
//        bData.agregarBicicleta(b);
//        for (Bicicleta aux : bData.obtenerBiciCliente("41911000")) {
//            System.out.println(aux.toString());
//        }
//        bData.borrarBicicleta("a434w98");
//        bData.actualizarBicicleta("a434w98", b);
//        System.out.println(bData.buscarBiciNumSerie("13"));
        

        //PRUEBAS REPUESTO-DATA
        
//        Repuesto rep = new Repuesto("afs844", "Cubierta DiaVlo", 5500, true);
//        repuestoData.agregarRepuesto(rep);
//        repuestoData.eliminarRepuestoPorNumSerie("afs844");
//        System.out.println(repuestoData.obtenerRepuesto("zxy123"));
//        repuestoData.modificarRepuesto(rep, "afs844");
//        for (Repuesto aux : repuestoData.listadoRepuesto()) {
//            System.out.println(aux.toString());
//        }


        //PRUEBAS SERVICIO-DATA
        
        Servicio servicio = new Servicio(2000, "Calibrado de Manillar", true);
//        servicioData.agregarServicio(servicio);
//        servicioData.eliminarServicioPorCodigo(1);
//        System.out.println(servicioData.obtenerServicio(3));
//        for (Servicio aux : servicioData.listarServicios()) {
//            System.out.println(aux.toString());
//        }
        servicioData.modificarServicio(servicio, 3);
        
        
    }

}
