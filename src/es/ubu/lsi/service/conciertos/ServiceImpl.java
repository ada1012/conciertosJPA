package es.ubu.lsi.service.conciertos;

import es.ubu.lsi.dao.conciertos.GrupoDAO;
import es.ubu.lsi.dao.conciertos.CompraDAO;
import es.ubu.lsi.dao.conciertos.ConciertoDAO;
import es.ubu.lsi.dao.conciertos.ClienteDAO;
import es.ubu.lsi.model.conciertos.Compra;
import es.ubu.lsi.model.conciertos.Concierto;
import es.ubu.lsi.model.conciertos.Grupo;
import es.ubu.lsi.model.conciertos.Cliente;
import es.ubu.lsi.service.PersistenceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private ConciertoDAO conciertoDAO;
    private GrupoDAO grupoDAO;
    private ClienteDAO clienteDAO;
    private CompraDAO compraDAO;

    public ServiceImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Conciertos");
        entityManager = entityManagerFactory.createEntityManager();
        conciertoDAO = new ConciertoDAO(entityManager);
        grupoDAO = new GrupoDAO(entityManager);
        clienteDAO = new ClienteDAO(entityManager);
        compraDAO = new CompraDAO(entityManager);
    }

    public void comprar(Date fecha, String nif, int grupo, int tickets) throws IncidentException {
        entityManager.getTransaction().begin();
        
        Cliente cliente = clienteDAO.findById(nif);
        
        if (cliente == null) {
        	entityManager.getTransaction().rollback();
            throw new IncidentException(IncidentError.NOT_EXIST_CLIENT.getText(), IncidentError.NOT_EXIST_CLIENT);
        }

        Concierto concierto = conciertoDAO.findById(grupo);
        
        if (concierto == null) {
            entityManager.getTransaction().rollback();
            throw new IncidentException(IncidentError.NOT_EXIST_MUSIC_GROUP.getText(), IncidentError.NOT_EXIST_MUSIC_GROUP);
        }
        
        if (concierto.getTickets() < tickets) {
            entityManager.getTransaction().rollback();
            throw new IncidentException(IncidentError.NOT_AVAILABLE_TICKETS.getText(), IncidentError.NOT_AVAILABLE_TICKETS);
        }

        Compra compra = new Compra();
        compra.setIdcompra(compraDAO.getMaxCompraId() + 1);
        compra.setConcierto(concierto);
        compra.setCliente(cliente);
        compra.setNTickets(tickets);

        compraDAO.persist(compra);

        concierto.setTickets(concierto.getTickets() - tickets);

        entityManager.getTransaction().commit();
    }

    public void eliminarCliente(String nif) throws IncidentException {
        entityManager.getTransaction().begin();

        Cliente cliente = clienteDAO.findById(nif);
        if (cliente == null) {
            entityManager.getTransaction().rollback();
            throw new IncidentException(IncidentError.NOT_EXIST_CLIENT.getText(), IncidentError.NOT_EXIST_CLIENT);
        }

        // Eliminar las compras asociadas al cliente
        List<Compra> compras = compraDAO.findAllByCliente(cliente);
        for (Compra compra : compras) {
            compraDAO.remove(compra);
        }

        clienteDAO.remove(cliente);

        entityManager.getTransaction().commit();
    }

    public List<Grupo> consultarGrupos() throws PersistenceException {
        try {
            entityManager.getTransaction().begin();

            List<Grupo> grupos = grupoDAO.findAll();

            for (Grupo grupo : grupos) {
                // Cargar los conciertos asociados al grupo
                grupo.setConciertos(conciertoDAO.findAllByGrupo(grupo));

                for (Concierto concierto : grupo.getConciertos()) {
                    // Cargar las compras asociadas al concierto
                    concierto.setCompras(compraDAO.findAllByConcierto(concierto));
                }
            }

            entityManager.getTransaction().commit();

            return grupos;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException("Error buscando los grupos", e);
        }
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}