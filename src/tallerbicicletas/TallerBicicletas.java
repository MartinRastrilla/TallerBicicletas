package tallerbicicletas;

import Data.BicicletaData;
import Data.ClienteData;
import Data.Conexion;
import Data.ItemRepuestoData;
import Data.ReparacionData;
import Data.RepuestoData;
import Data.ServicioData;
import Modelo.Bicicleta;
import Modelo.Cliente;
import Modelo.ItemRepuesto;
import Modelo.Reparacion;
import Modelo.Repuesto;
import Modelo.Servicio;
import java.sql.Connection;
import java.time.LocalDate;


public class TallerBicicletas {

    public static void main(String[] args) {
        Connection con  = Conexion.getConexion();
        ClienteData cData = new ClienteData(con);
        BicicletaData bData = new BicicletaData();
        RepuestoData repuestoData = new RepuestoData();
        ServicioData servicioData = new ServicioData();
        ReparacionData repData = new ReparacionData();
        ItemRepuestoData itemData = new ItemRepuestoData();
        
        //PRUEBAS CLIENTE-DATA
        
//        Cliente c = new Cliente("17303438", "Julio", "Bersia", "Los Pochoclos 6328", "2665131944", true);
//        cData.agregarCliente(c);
//        cData.borrarCliente("43490178");
//        System.out.println(cData.buscarClienteDNI("44911000"));
//        for (Cliente aux : cData.buscarClienteApellido("orozco")) {
//            System.out.println(aux.toString());
//        }
//        
        
        
        //PRUEBAS BICICLETA-DATA seleccionar lineas comentadas y luego CTRL+SHIFT+C para quitar comentado
        
//        Cliente c = new Cliente("41911000", "Gustavo", "Orozco", "Calle A veces Viva 123", "2664119944", true);
//        Bicicleta b = new Bicicleta("C234C", "Urbana", "DoradaNegra", "Rocko", c, true);
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
        
//        Servicio servicio = servicioData.obtenerServicio(4);
//        servicioData.agregarServicio(servicio);
//        servicioData.eliminarServicioPorCodigo(1);
//        System.out.println(servicioData.obtenerServicio(3));
//        for (Servicio aux : servicioData.listarServicios()) {
//            System.out.println(aux.toString());
//        }
//        servicioData.modificarServicio(servicio, 3);
        
        
        //PRUEBAS REPARACI??N-DATA
//        Servicio servicio = new Servicio(2500, "Ajuste de Frenos", true);
//        Cliente c = new Cliente("17303438", "Julio", "Bersia", "Los Pochoclos 6328", "2665131944", true);
//        Bicicleta b = new Bicicleta("B932B", "Speed", "Blanca", "Spin", c, true);
//        Reparacion reparacion = new Reparacion(servicio, b, LocalDate.now(), 4100, true, true);
//        repData.ingresarReparacion(reparacion);
//        System.out.println(repData.buscarReparacion(10));
//        repData.BorrarReparacion(10);
//        repData.actualizarReparacion(reparacion, 10, 5);
//        for (Reparacion aux : repData.obtenerReparaciones()) {
//            System.out.println(aux.toString());
//        }
//        for (Reparacion aux : repData.filtrarReparacionPorDNI("17303438")) {
//            System.out.println(aux.toString());
//        }
//        System.out.println(repData.costoReparacion(23));
        
        
        //PRUEBAS ITEMREPUESTO-DATA
//        Repuesto rep = repuestoData.obtenerRepuesto("zxy123");
//        Reparacion reparacion = repData.buscarReparacion(23);
//        ItemRepuesto item = new ItemRepuesto(rep, reparacion, 1, true);
//        itemData.ingresarItems(item);
//        System.out.println(itemData.obtenerItem("1234"));
//        itemData.borrarItem("1234");
//        itemData.actualizarItem(item);
//        for (ItemRepuesto aux : itemData.obtenerRepuestosDeReparacion(10)) {
//            System.out.println(aux.toString());
//        }
//        for (ItemRepuesto aux : itemData.obtenerRepuestos()) {
//            System.out.println(aux.toString());
//        }
//        System.out.println(itemData.costoRepuestos(23));
//        float costoFinal =itemData.costoRepuestos(23)+repData.costoReparacion(23);
//        System.out.println(costoFinal);
//        for (ItemRepuesto aux : itemData.obtenerBicicletaXreparacion("14")) {
//            System.out.println(aux.toString());
//        }
    }

}
