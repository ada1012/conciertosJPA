package es.ubu.lsi.dao.conciertos;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.conciertos.Cliente;

import java.util.List;

import javax.persistence.EntityManager;

public class ClienteDAO extends JpaDAO<Cliente, String> {

    public ClienteDAO(EntityManager em) {
        super(em);
    }
    
    @Override
	public List<Cliente> findAll() {
		return getEntityManager()
                .createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
    }
}
