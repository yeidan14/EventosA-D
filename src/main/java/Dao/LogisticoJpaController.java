/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.exceptions.IllegalOrphanException;
import Dao.exceptions.NonexistentEntityException;
import Dto.Logistico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Dto.Solicitud;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Daniel
 */
public class LogisticoJpaController implements Serializable {

    public LogisticoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Logistico logistico) {
        if (logistico.getSolicitudCollection() == null) {
            logistico.setSolicitudCollection(new ArrayList<Solicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Solicitud> attachedSolicitudCollection = new ArrayList<Solicitud>();
            for (Solicitud solicitudCollectionSolicitudToAttach : logistico.getSolicitudCollection()) {
                solicitudCollectionSolicitudToAttach = em.getReference(solicitudCollectionSolicitudToAttach.getClass(), solicitudCollectionSolicitudToAttach.getIdPeticion());
                attachedSolicitudCollection.add(solicitudCollectionSolicitudToAttach);
            }
            logistico.setSolicitudCollection(attachedSolicitudCollection);
            em.persist(logistico);
            for (Solicitud solicitudCollectionSolicitud : logistico.getSolicitudCollection()) {
                Logistico oldLogisticoIdLogOfSolicitudCollectionSolicitud = solicitudCollectionSolicitud.getLogisticoIdLog();
                solicitudCollectionSolicitud.setLogisticoIdLog(logistico);
                solicitudCollectionSolicitud = em.merge(solicitudCollectionSolicitud);
                if (oldLogisticoIdLogOfSolicitudCollectionSolicitud != null) {
                    oldLogisticoIdLogOfSolicitudCollectionSolicitud.getSolicitudCollection().remove(solicitudCollectionSolicitud);
                    oldLogisticoIdLogOfSolicitudCollectionSolicitud = em.merge(oldLogisticoIdLogOfSolicitudCollectionSolicitud);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Logistico logistico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Logistico persistentLogistico = em.find(Logistico.class, logistico.getIdLog());
            Collection<Solicitud> solicitudCollectionOld = persistentLogistico.getSolicitudCollection();
            Collection<Solicitud> solicitudCollectionNew = logistico.getSolicitudCollection();
            List<String> illegalOrphanMessages = null;
            for (Solicitud solicitudCollectionOldSolicitud : solicitudCollectionOld) {
                if (!solicitudCollectionNew.contains(solicitudCollectionOldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudCollectionOldSolicitud + " since its logisticoIdLog field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Solicitud> attachedSolicitudCollectionNew = new ArrayList<Solicitud>();
            for (Solicitud solicitudCollectionNewSolicitudToAttach : solicitudCollectionNew) {
                solicitudCollectionNewSolicitudToAttach = em.getReference(solicitudCollectionNewSolicitudToAttach.getClass(), solicitudCollectionNewSolicitudToAttach.getIdPeticion());
                attachedSolicitudCollectionNew.add(solicitudCollectionNewSolicitudToAttach);
            }
            solicitudCollectionNew = attachedSolicitudCollectionNew;
            logistico.setSolicitudCollection(solicitudCollectionNew);
            logistico = em.merge(logistico);
            for (Solicitud solicitudCollectionNewSolicitud : solicitudCollectionNew) {
                if (!solicitudCollectionOld.contains(solicitudCollectionNewSolicitud)) {
                    Logistico oldLogisticoIdLogOfSolicitudCollectionNewSolicitud = solicitudCollectionNewSolicitud.getLogisticoIdLog();
                    solicitudCollectionNewSolicitud.setLogisticoIdLog(logistico);
                    solicitudCollectionNewSolicitud = em.merge(solicitudCollectionNewSolicitud);
                    if (oldLogisticoIdLogOfSolicitudCollectionNewSolicitud != null && !oldLogisticoIdLogOfSolicitudCollectionNewSolicitud.equals(logistico)) {
                        oldLogisticoIdLogOfSolicitudCollectionNewSolicitud.getSolicitudCollection().remove(solicitudCollectionNewSolicitud);
                        oldLogisticoIdLogOfSolicitudCollectionNewSolicitud = em.merge(oldLogisticoIdLogOfSolicitudCollectionNewSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = logistico.getIdLog();
                if (findLogistico(id) == null) {
                    throw new NonexistentEntityException("The logistico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Logistico logistico;
            try {
                logistico = em.getReference(Logistico.class, id);
                logistico.getIdLog();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The logistico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Solicitud> solicitudCollectionOrphanCheck = logistico.getSolicitudCollection();
            for (Solicitud solicitudCollectionOrphanCheckSolicitud : solicitudCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Logistico (" + logistico + ") cannot be destroyed since the Solicitud " + solicitudCollectionOrphanCheckSolicitud + " in its solicitudCollection field has a non-nullable logisticoIdLog field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(logistico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Logistico> findLogisticoEntities() {
        return findLogisticoEntities(true, -1, -1);
    }

    public List<Logistico> findLogisticoEntities(int maxResults, int firstResult) {
        return findLogisticoEntities(false, maxResults, firstResult);
    }

    private List<Logistico> findLogisticoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Logistico.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Logistico findLogistico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Logistico.class, id);
        } finally {
            em.close();
        }
    }

    public int getLogisticoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Logistico> rt = cq.from(Logistico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
