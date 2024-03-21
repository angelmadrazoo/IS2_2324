package es.unican.is2.FranquiciasUCCommon;



import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa una tienda de la franquicia.
 * Almacena sus datos y su lista de empleados
 * El nombre de la tienda es unico en la franquicia
 */
@SuppressWarnings("serial")
public class Tienda implements Serializable {
	
	// Primary key en BBDD (autogenerada)
	private long id;
	private String nombre;
	private String direccion;
	private List<Empleado> empleados = new LinkedList<Empleado>();
	
	public Tienda() {}
	
	/**
	 * Constructor de tienda con nombre y direccion
	 * @param nombre Nombre de la tienda
	 * @param direccion Direccion de la tienda
	 */
	public Tienda(String nombre, String direccion) {
		this.nombre=nombre;
		this.direccion= direccion;
	}
	

	/**
	 * Retorna el gasto mensual de la tienda en 
	 * pagar sueldos de sus empleados.
	 * @return Total mensual sueldos 
	 */
	public double gastoMensualSueldos() {
		double total = 0.0;
		for (Empleado e:empleados) {
			total+=e.sueldoBruto();
		}
		return total;
	}

	/**
	 * Retorna el empleado con el dni indicado
	 * @param dni DNI del empleado
	 * @return Empleado con ese dni
	 *         null si no existe
	 */
	public Empleado buscaEmpleado(String dni) {
		for (Empleado e : empleados) {
			if (e.getDNI().equals(dni)) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Retorna la direccion de la tienda
	 * @return Direccion de la tienda
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Retorna la lista de Empleadoes actuales de la tienda
	 * @return La lista de empleados
	 */
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
