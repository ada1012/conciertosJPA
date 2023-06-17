package es.ubu.lsi.dao.conciertos;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.conciertos.Cliente;
import es.ubu.lsi.model.conciertos.Compra;
import es.ubu.lsi.model.conciertos.Concierto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

public class CompraDAO extends JpaDAO<Compra, Integer> {

    public CompraDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

    public List<Compra> findAllByCliente(Cliente cliente) {
        return getEntityManager()
                .createQuery("SELECT c FROM Compra c WHERE c.cliente = :cliente", Compra.class)
                .setParameter("cliente", cliente)
                .getResultList();
    }

    public List<Compra> findAllByConcierto(Concierto concierto) {
        return getEntityManager()
                .createQuery("SELECT c FROM Compra c WHERE c.concierto = :concierto", Compra.class)
                .setParameter("concierto", concierto)
                .getResultList();
    }
    
    public long getMaxCompraId() {
    	Long maxId = (Long) getEntityManager()
                .createQuery("SELECT MAX(c.idcompra) FROM Compra c")
                .getSingleResult();
    	
        return (maxId != null) ? maxId : 0;
    }

	@Override
	public List<Compra> findAll() {
		return getEntityManager()
                .createQuery("SELECT c FROM Compra c", Compra.class)
                .getResultList();
    }

}
