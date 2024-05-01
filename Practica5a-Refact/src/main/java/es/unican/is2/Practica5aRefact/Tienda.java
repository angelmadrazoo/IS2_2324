package es.unican.is2.Practica5aRefact;
import java.io.FileNotFoundException; 
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;



/**
 * Clase que representa una tienda con un conjunto de vendedores. Gestiona las
 * ventas realizadas y las comisiones asignadas a cada vendedor. Los datos de la
 * tienda se almacenan en un fichero de texto que se pasa como parametro al
 * crear la tienda
 */
public class Tienda {

	private List<Vendedor> lista = new LinkedList<Vendedor>();
	private String direccion;
	private String nombre;

	private String rutaDatos;
	
	static final double comisionJunior = 0.005;
	static final double comisionSenior = 0.01;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) { //WMC + 1 (1)
		this.rutaDatos = datos;
	}

	/**
	 * Retorna la direccion de la tienda
	 * @return Direccion de la tienda
	 */
	public String getDireccion() { //WMC + 1 (2)
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String getNombre() { //WMC + 1 (3)
		return nombre;
	}

	/**
	 * Anhade un nuevo vendedor a la tienda
	 * @param nuevo El vendedor a anhadir
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya existe el vendedor
	 */
	public boolean anhade(Vendedor nuevo) throws DataAccessException { //WMC + 1
		Vendedor v = buscaVendedor(nuevo.getId());
		if (v != null) { //WMC + 1 // CCOG + 1
			return false;
		}
		lista.add(nuevo);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo id se pasa como argumento
	 * @param id
	 * @return true si se elimina el vendedor false si no existe el vendedor
	 */
	public boolean eliminaVendedor(String id) throws DataAccessException { //WMC + 1
		Vendedor v = buscaVendedor(id);
		if (v == null) { //WMC + 1 // CCOG + 1
			return false;
		}
		lista.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Anhade una venta a un vendedor
	 * @param id      Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se anhade la venta false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String id, double importe) throws DataAccessException { //WMC + 1
		Vendedor v = buscaVendedor(id);
		if (v == null) { //WMC + 1 // CCOG + 1
			return false;
		}
		double comision = 0;
		if (v instanceof VendedorEnPlantilla) { //WMC + 1 // CCOG + 1
			switch (((VendedorEnPlantilla) v).tipo()) { // CCOG + 2
			case Junior: //WMC + 1
				comision = importe * comisionJunior;
				break;
			case Senior: //WMC + 1
				comision = importe * comisionSenior;
				break;
			}
		}
		v.anhade(importe);
		v.setComision(v.getComision()+comision);
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese dni o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) throws DataAccessException { //WMC + 1

		lista = new LinkedList<Vendedor>();
		Scanner in = null;
		try {
			// abre el fichero
			in = new Scanner(new FileReader(rutaDatos));
			// configura el formato de numeros
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			// lee los vendedores senior
			buscaEnPlantilla(in, TipoVendedor.Senior, "Junior");
			// lee los vendedores junior
			buscaEnPlantilla(in, TipoVendedor.Junior, "Practicas");
			// lee los vendedores en practicas
			buscaEnPracticas(in);
			
		} catch (FileNotFoundException e) { //WMC + 1 
			throw new DataAccessException();
		} finally {
			if (in != null) { //WMC + 1 // CCOG + 1
				in.close();
			}
		} // try

		for (Vendedor v : lista) { //WMC + 1 // CCOG + 1
			if (v.getId().equals(id)) { //WMC + 1 // CCOG + 2
				return v;
			}
		}
		return null;
	}

	/**
	 * Busca el vendedor en Practicas
	 * @param in
	 */
	private void buscaEnPracticas(Scanner in) { // WMC + 1
		Vendedor ven;
		while (in.hasNext()) { //WMC + 1 // CCOG + 1
			in.next();
			String nombre = in.next();
			in.next();
			String idIn = in.next();
			in.next();
			String dni = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			ven = new VendedorEnPracticas(nombre, idIn, dni);
			ven.setTotalVentas(totalVentas);
			lista.add(ven);
		}
	}

	/**
	 * Busca el vendedor que esta en plantilla 
	 * @param in
	 */
	private void buscaEnPlantilla(Scanner in, TipoVendedor tipo, String vendedor) { // WMC + 1
		Vendedor ven;
		while (in.hasNext() && !in.next().equals(vendedor)) { //WMC + 1 + 1 // CCOG + 1

			String nombre = in.next();
			in.next();
			String idIn = in.next();
			in.next();
			String dni = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			in.next();
			double totalComision = in.nextDouble();
			ven = new VendedorEnPlantilla(nombre, idIn, dni, tipo);
			ven.setTotalVentas(totalVentas);
			ven.setComision(totalComision);
			lista.add(ven);
		}
	}
	
	

	/**
	 * Retorna la lista de vendedores actuales de la tienda
	 * 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> vendedores() throws DataAccessException { //WMC + 1
		lista = new LinkedList<Vendedor>();

		Scanner in = null;
		try {
			// abre el fichero
			in = new Scanner(new FileReader(rutaDatos));
			// configura el formato de numeros
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			buscaEnPlantilla(in, TipoVendedor.Senior, "Junior");
			// lee los vendedores junior
			buscaEnPlantilla(in, TipoVendedor.Junior, "Practicas");
			// lee los vendedores Practicas
			buscaEnPracticas(in);
			
		} catch (FileNotFoundException e) { //WMC + 1 (24)
			throw new DataAccessException();
		} finally {
			if (in != null) { //WMC + 1 // CCOG + 1
				in.close();
			}
		} // try

		return lista;

	}

	/**
	 * Actualiza el fichero datosTienda.txt con los datos actualizados de
	 * los vendedores
	 */
	private void vuelcaDatos() throws DataAccessException { //WMC + 1
		PrintWriter out = null;
		List<Vendedor> senior = new LinkedList<Vendedor>();
		List<Vendedor> junior = new LinkedList<Vendedor>();
		List<Vendedor> practicas = new LinkedList<Vendedor>();

		for (Vendedor v : lista) { //WMC + 1 // CCOG + 1
			if (v instanceof VendedorEnPracticas) { //WMC + 1 // CCOG + 2
				practicas.add(v);
			} else if (v instanceof VendedorEnPlantilla) { //WMC + 1 // CCOG + 2
				VendedorEnPlantilla vp = (VendedorEnPlantilla) v;
				if (vp.tipo().equals(TipoVendedor.Junior)) //WMC + 1 // CCOG + 3
					junior.add(vp);
				else // CCOG + 3
					senior.add(vp);
			}
		}

		try {

			out = new PrintWriter(new FileWriter(rutaDatos));

			out.println(nombre);
			out.println(direccion);
			
			out.println();
			out.println("Senior");
			pintaPlantilla(out, senior);
			
			out.println();
			out.println("Junior");
			pintaPlantilla(out, junior);
			
			out.println();
			out.println("Practicas");	
			for (Vendedor v : practicas) { //WMC + 1 // CCOG + 1
				VendedorEnPracticas v3 = (VendedorEnPracticas) v;
				out.println("  Nombre: " + v3.getNombre() + " Id: " + v3.getId() + " DNI: " + v3.getDni()
						+ " TotalVentasMes: " + v3.getTotalVentas());
			}
			
		} catch (IOException e) { //WMC + 1
			throw new DataAccessException();

		} finally {
			if (out != null) //WMC + 1 // CCOG + 1
				out.close();
		}
	}
   
	/**
	 * Separamos los casos en plantilla 
	 * @param out
	 * @param tipo
	 */
	private void pintaPlantilla(PrintWriter out, List<Vendedor> tipo) { // WMC + 1
		for (Vendedor v : tipo) { //WMC + 1 // CCOG + 1
			VendedorEnPlantilla v1 = (VendedorEnPlantilla) v;
			out.println("  Nombre: " + v1.getNombre() + " Id: " + v1.getId() + " DNI: " + v1.getDni()
					+ " TotalVentasMes: " + v1.getTotalVentas() + " TotalComision: "+ v1.getComision());
		}
	}

		
	
	
	
}
