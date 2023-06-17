package es.ubu.lsi.model.conciertos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the GRUPO database table.
 * 
 */
@Entity
@NamedEntityGraph(
    name = "grupo-conciertos-compras",
    attributeNodes = {
        @NamedAttributeNode(value = "conciertos", subgraph = "conciertos-compras")
    },
    subgraphs = {
        @NamedSubgraph(
            name = "conciertos-compras",
            attributeNodes = {
                @NamedAttributeNode("compras")
            }
        )
    }
)
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idgrupo;

	private BigDecimal activo;

	private String estilo;

	private String nombre;

	//bi-directional many-to-one association to Concierto
	@OneToMany(mappedBy="grupo")
	private Set<Concierto> conciertos;

	public Grupo() {
	}

	public long getIdgrupo() {
		return this.idgrupo;
	}

	public void setIdgrupo(long idgrupo) {
		this.idgrupo = idgrupo;
	}

	public BigDecimal getActivo() {
		return this.activo;
	}

	public void setActivo(BigDecimal activo) {
		this.activo = activo;
	}

	public String getEstilo() {
		return this.estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Concierto> getConciertos() {
		return this.conciertos;
	}

	public void setConciertos(Set<Concierto> conciertos) {
		this.conciertos = conciertos;
	}

	public Concierto addConcierto(Concierto concierto) {
		getConciertos().add(concierto);
		concierto.setGrupo(this);

		return concierto;
	}

	public Concierto removeConcierto(Concierto concierto) {
		getConciertos().remove(concierto);
		concierto.setGrupo(null);

		return concierto;
	}

	@Override
	public String toString() {
		return "Grupo [idgrupo=" + idgrupo + ", activo=" + activo + ", estilo=" + estilo + ", nombre=" + nombre + "]";
	}
	
	

}