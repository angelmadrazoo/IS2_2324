package es.unican.is2.ListaOrdenadaAcotada;
/**
 * TDA ListaOrdenadaAcotada
 * Estructura de datos que almacena datos ordenados de acuerdo
 * a su orden natural y tiene capacidad limitada. No admite nulos.
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 */
public interface IListaOrdenadaAcotada<E extends Comparable<E>> {
	
	/**
	 * Retorna el elemento que ocupa la posicion indicada
	 * @param indice Indice del elemento
	 * @return Elemento que ocupa la posicion indice
	 * @throws IndexOutOfBoundsException si el indice es incorrecto
	 */
    public E get(int indice);
    
    /**
     * Inserta un elemento en la posicion que le corresponde
     * de acuerdo a su orden natural (usando el compareTo)
     * @param elemento a insertar
     * @throws Lanza IllegalStateException si el elemento no cabe
     * @throws Lanza NullPointerException si el elemento es nulo
     */
    public void add(E elemento);
    
    /**
     * Elimina el elemento que ocupa la posicion indicada
     * @param indice Posicion del elemento a eliminar
     * @return Elemento que ocupaba la posicion indicada
     * Lanza IndexOutOfBoundsException si el indice es incorrecto
     */
    public E remove(int indice);
    
    /**
     * Retorna el numero de elementos en la lista
     * @return Numero de elementos
     */
    public int size();
    
    /**
     * Vacia la lista
     */
     public void clear();
     
}

