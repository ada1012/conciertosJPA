package es.ubu.lsi.service.conciertos;

import java.util.Date;
import java.util.List;

import es.ubu.lsi.model.conciertos.Grupo;
import es.ubu.lsi.service.PersistenceException;

/**
 * Transaction service.
 * 
 * @author <a href="mailto:jmaudes@ubu.es">Jesús Maudes</a>
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author <a href="mailto:mmabad@ubu.es">Mario Martínez</a>
 * @author <a href="mailto:pgdiaz@ubu.es">Pablo García</a> 
 * @since 1.0
 *
 */
public interface Service {
	

	/**
	 * Alta de una nueva compra sobre un concierto y un cliente.
	 * 
	 * @param fecha fecha
	 * @param nif nif
	 * @param grupo grupo
	 * @throws PersistenceException si se produce un error
	 */
	public void comprar(Date fecha, String nif, int grupo, int tickets) throws PersistenceException;

	
	/**
	 * Elimina un cliente y los registros asociados
	 * 
	 * @param nif nif
	 * @throws PersistenceException si se produce un error
	 */
	public void eliminarCliente(String nif) throws PersistenceException;
	
	/**
	 * Consulta gupos. En este caso en particular es importante recuperar 
	 * toda la información vinculada a los grupos, conciertos, compras y anulaciones
	 * 
	 * @return grupos
	 * @throws PersistenceException si se produce un error
	 */
	public List<Grupo> consultarGrupos() throws PersistenceException;

}
