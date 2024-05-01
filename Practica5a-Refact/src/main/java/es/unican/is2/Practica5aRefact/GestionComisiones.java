package es.unican.is2.Practica5aRefact;


import java.util.Collections; 
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import fundamentos.Menu;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Clase para gestionar las comisiones de vendedores de una tienda
 */
public class GestionComisiones {

    private static Tienda tienda;

    /**
     * Programa principal basado en menu
     */
    public static void main(String[] args) { //WMC + 1
        tienda = new Tienda("D:\\Desktop\\IS2\\Practica3\\IS2_2324\\Practica5a-Refact\\datosTienda.txt");
        Menu menu = new Menu("Comisiones tienda");
        menu.insertaOpcion("Añadir venta", 0);
        menu.insertaOpcion("Vendedor del mes", 1);
        menu.insertaOpcion("Vendedores por ventas", 2);

        while (true) { //WMC + 1 // CCOG + 1
            int opcion = menu.leeOpcion();
            switch (opcion) {
                case 0: // WMC + 1
                    nuevaVenta();
                    break;
                case 1: // WMC + 1
                    vendedorDelMes();
                    break;
                case 2: // WMC + 1
                    vendedoresPorVentas();
                    break;
            }
        }
    }

    /**
     * Extraemos la funcionalidad de nuevaVenta
     */
    private static void nuevaVenta() { // WMC + 1
        Lectura lect = new Lectura("Datos Venta");
        lect.creaEntrada("ID Vendedor", "");
        lect.creaEntrada("Importe", "");
        lect.esperaYCierra();
        String dni = lect.leeString("ID Vendedor");
        double importe = lect.leeDouble("Importe");
        try {
            if (!tienda.anhadeVenta(dni, importe)) { // WMC + 1 // CCOG + 1
                mensaje("ERROR", "El vendedor no existe");
            }
        } catch (DataAccessException e) { // WMC + 1
            mensaje("ERROR", "No se pudo guardar el cambio");
        }
    }

    /**
     * Extraemos la funcionalidad de vendedorDelMes
     */
    private static void vendedorDelMes() { // WMC + 1
        try {
            List<Vendedor> vendedores = tienda.vendedores();
            List<Vendedor> resultado = new LinkedList<>();
            double maxVentas = 0.0;
            for (Vendedor v : vendedores) { // WMC + 1 // CCOG + 1
                if (v.getTotalVentas() > maxVentas) { // WMC + 1 // CCOG + 2
                    maxVentas = v.getTotalVentas();
                    resultado.clear();
                    resultado.add(v);
                } else if (v.getTotalVentas() == maxVentas) { // WMC + 1 // CCOG + 2
                    resultado.add(v);
                }
            }

            String msj = "";
            for (Vendedor vn : resultado) { // WMC + 1 // CCOG + 1
                msj += vn.getNombre() + "\n";
            }
            mensaje("VENDEDORES DEL MES", msj);

        } catch (DataAccessException e) { // WMC + 1
            mensaje("ERROR", "No se pudo acceder a los datos");
        }
    }

   /**
    * Extraemos la funcionalidad de vendedoresPorVentas
    */
    private static void vendedoresPorVentas() { // WMC + 1
		try {
			List<Vendedor> vendedores = tienda.vendedores();
			System.out.println(vendedores.size());
			Collections.sort(vendedores, new Comparator<Vendedor>() {
				public int compare(Vendedor o1, Vendedor o2) { //WMC + 1 // CCOG + 1
					if (o1.getTotalVentas() > o2.getTotalVentas()) //WMC + 1 // CCOG + 2
						return -1;
					else if (o1.getTotalVentas() < o2.getTotalVentas()) //WMC + 1 // CCOG + 2
						return 1;
					return 0;
				}
			});
			String msj = "";
			for (Vendedor vn : vendedores) { //WMC + 1 // CCOG + 2
				msj += vn.getNombre() + " (" + vn.getId()+ ") "+vn.getTotalVentas() + "\n";
			}
			mensaje("VENDEDORES", msj);
		} catch (DataAccessException e) { //WMC + 1 
			mensaje("ERROR", "No se pudo acceder a los datos");
		}
    }

    /**
     * Método auxiliar para mostrar un mensaje
     */
    private static void mensaje(String titulo, String txt) { // WMC + 1
        Mensaje msj = new Mensaje(titulo);
        msj.escribe(txt);
    }
}
