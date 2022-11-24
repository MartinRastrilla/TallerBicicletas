package tallerbicicletas;

import Data.RepuestoData;
import Data.ServicioData;
import Modelo.Repuesto;
import Modelo.Servicio;


public class TallerBicicletas {

    public static void main(String[] args) {
        RepuestoData rD=new RepuestoData();
        //Repuesto nR=new Repuesto("312", "tacos de freno", (float) 325.3, true);
       // rD.agregarRepuesto(nR);
       // rD.obtenerRepuesto("2");
       /* for (Repuesto repuesto : rD.listadoRepuesto()) {
            System.out.println(repuesto);
        }*/
       
       
        //Verificamos SERVICIODATA Y SUS METODOS
        ServicioData sD=new ServicioData();
        //Servicio sv=new Servicio((float) 2400.55, "Ajustes", true);
        
        //sD.AgregarServicio(sv);   //Verificado
        //sD.obtenerServicio(2);    //Verificado
        
        
        //Verificado el enlistado de los SERVICIOS DESDE SERVICIODATA
        /*for (Servicio ser : sD.listarServicios()) {
            System.out.println(ser);
        }*/
       
       sD.EliminarServicioPorCodigo(9);
       
    }
    

}
