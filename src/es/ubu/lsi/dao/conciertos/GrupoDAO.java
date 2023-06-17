package es.ubu.lsi.dao.conciertos;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.conciertos.Grupo;

import java.util.List;

import javax.persistence.EntityManager;

public class GrupoDAO extends JpaDAO<Grupo, Integer> {

    public GrupoDAO(EntityManager em) {
        super(em);
    }

    @Override
	public List<Grupo> findAll() {
		return getEntityManager()
                .createQuery("SELECT g FROM Grupo g", Grupo.class)
                .getResultList();
    }
}
