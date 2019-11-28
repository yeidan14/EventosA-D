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
        if (logistico.getSolicitudList() == null) {
            logistico.setSolicitudList(new ArrayList<Solicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Solicitud> attachedSolicitudList = new ArrayList<Solicitud>();
            for (Solicitud solicitudListSolicitudToAttach : logistico.getSolicitudList()) {
                solicitudListSolicitudToAttach = em.getReference(solicitudListSolicitudToAttach.getClass(), solicitudListSolicitudToAttach.getIdPeticion());
                attachedSolicitudList.add(solicitudListSolicitudToAttach);
            }
            logistico.setSolicitudList(attachedSolicitudList);
            em.persist(logistico);
            for (Solicitud solicitudListSolicitud : logistico.getSolicitudList()) {
                Logistico oldLogisticoIdLogOfSolicitudListSolicitud = solicitudListSolicitud.getLogisticoIdLog();
                solicitudListSolicitud.setLogisticoIdLog(logistico);
                solicitudListSolicitud = em.merge(solicitudListSolicitud);
                if (oldLogisticoIdLogOfSolicitudListSolicitud != null) {
                    oldLogisticoIdLogOfSolicitudListSolicitud.getSolicitudList().remove(solicitudListSolicitud);
                    oldLogisticoIdLogOfSolicitudListSolicitud = em.merge(oldLogisticoIdLogOfSolicitudListSolicitud);
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
            List<Solicitud> solicitudListOld = persistentLogistico.getSolicitudList();
            List<Solicitud> solicitudListNew = logistico.getSolicitudList();
            List<String> illegalOrphanMessages = null;
            for (Solicitud solicitudListOldSolicitud : solicitudListOld) {
                if (!solicitudListNew.contains(solicitudListOldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudListOldSolicitud + " since its logisticoIdLog field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Solicitud> attachedSolicitudListNew = new ArrayList<Solicitud>();
            for (Solicitud solicitudListNewSolicitudToAttach : solicitudListNew) {
                solicitudListNewSolicitudToAttach = em.getReference(solicitudListNewSolicitudToAttach.getClass(), solicitudListNewSolicitudToAttach.getIdPeticion());
                attachedSolicitudListNew.add(solicitudListNewSolicitudToAttach);
            }
            solicitudListNew = attachedSolicitudListNew;
            logistico.setSolicitudList(solicitudListNew);
            logistico = em.merge(logistico);
            for (Solicitud solicitudListNewSolicitud : solicitudListNew) {
                if (!solicitudListOld.contains(solicitudListNewSolicitud)) {
                    Logistico oldLogisticoIdLogOfSolicitudListNewSolicitud = solicitudListNewSolicitud.getLogisticoIdLog();
                    solicitudListNewSolicitud.setLogisticoIdLog(logistico);
                    solicitudListNewSolicitud = em.merge(solicitudListNewSolicitud);
                    if (oldLogisticoIdLogOfSolicitudListNewSolicitud != null && !oldLogisticoIdLogOfSolicitudListNewSolicitud.equals(logistico)) {
                        oldLogisticoIdLogOfSolicitudListNewSolicitud.getSolicitudList().remove(solicitudListNewSolicitud);
                        oldLogisticoIdLogOfSolicitudListNewSolicitud = em.merge(oldLogisticoIdLogOfSolicitudListNewSolicitud);
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
            List<Solicitud> solicitudListOrphanCheck = logistico.getSolicitudList();
            for (Solicitud solicitudListOrphanCheckSolicitud : solicitudListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Logistico (" + logistico + ") cannot be destroyed since the Solicitud " + solicitudListOrphanCheckSolicitud + " in its solicitudList field has a non-nullable logisticoIdLog field.");
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
