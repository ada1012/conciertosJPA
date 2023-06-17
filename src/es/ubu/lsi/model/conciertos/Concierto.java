package es.ubu.lsi.model.conciertos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the CONCIERTO database table.
 * 
 */
@Entity
@NamedQuery(name="Concierto.findAll", query="SELECT c FROM Concierto c")
public class Concierto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idconcierto;

	private String ciudad;

	private Timestamp fecha;

	private String nombre;

	private double precio;

	private int tickets;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="concierto")
	private List<Compra> compras;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="IDGRUPO")
	private Grupo grupo;

	public Concierto() {
	}

	public long getIdconcierto() {
		return this.idconcierto;
	}

	public void setIdconcierto(long idconcierto) {
		this.idconcierto = idconcierto;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getTickets() {
		return this.tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setConcierto(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setConcierto(null);

		return compra;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "Concierto [idconcierto=" + idconcierto + ", ciudad=" + ciudad + ", fecha=" + fecha + ", nombre="
				+ nombre + ", precio=" + precio + ", tickets=" + tickets + ", grupo=" + grupo.getIdgrupo()
				+ "]";
	}
	
	

}