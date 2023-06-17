package es.ubu.lsi.dao.conciertos;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.conciertos.Concierto;
import es.ubu.lsi.model.conciertos.Grupo;

import javax.persistence.EntityManager;
import java.util.List;

public class ConciertoDAO extends JpaDAO<Concierto, Long> {

    public ConciertoDAO(EntityManager em) {
        super(em);
    }

    public List<Concierto> findAllByGrupo(Grupo grupo) {
        return getEntityManager()
                .createQuery("SELECT c FROM Concierto c WHERE c.grupo = :grupo", Concierto.class)
                .setParameter("grupo", grupo)
                .getResultList();
    }
    
    public Concierto findById(long id) {
    	List<Concierto> resultados = getEntityManager()
		        .createQuery("SELECT c FROM Concierto c WHERE c.idconcierto = :idconcierto", Concierto.class)
		        .setParameter("idconcierto", id)
		        .getResultList();

	    if (resultados.isEmpty()) {
	        return null;
	    } else {
	        return resultados.get(0);
	    }
    }
    
    @Override
	public List<Concierto> findAll() {
		return getEntityManager()
                .createQuery("SELECT c FROM Concierto c", Concierto.class)
                .getResultList();
    }
}
