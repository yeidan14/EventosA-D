/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.exceptions.IllegalOrphanException;
import Dao.exceptions.NonexistentEntityException;
import Dto.Sitio;
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
public class SitioJpaController implements Serializable {

    public SitioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sitio sitio) {
        if (sitio.getSolicitudCollection() == null) {
            sitio.setSolicitudCollection(new ArrayList<Solicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Solicitud> attachedSolicitudCollection = new ArrayList<Solicitud>();
            for (Solicitud solicitudCollectionSolicitudToAttach : sitio.getSolicitudCollection()) {
                solicitudCollectionSolicitudToAttach = em.getReference(solicitudCollectionSolicitudToAttach.getClass(), solicitudCollectionSolicitudToAttach.getIdPeticion());
                attachedSolicitudCollection.add(solicitudCollectionSolicitudToAttach);
            }
            sitio.setSolicitudCollection(attachedSolicitudCollection);
            em.persist(sitio);
            for (Solicitud solicitudCollectionSolicitud : sitio.getSolicitudCollection()) {
                Sitio oldSitioIdSitioOfSolicitudCollectionSolicitud = solicitudCollectionSolicitud.getSitioIdSitio();
                solicitudCollectionSolicitud.setSitioIdSitio(sitio);
                solicitudCollectionSolicitud = em.merge(solicitudCollectionSolicitud);
                if (oldSitioIdSitioOfSolicitudCollectionSolicitud != null) {
                    oldSitioIdSitioOfSolicitudCollectionSolicitud.getSolicitudCollection().remove(solicitudCollectionSolicitud);
                    oldSitioIdSitioOfSolicitudCollectionSolicitud = em.merge(oldSitioIdSitioOfSolicitudCollectionSolicitud);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sitio sitio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sitio persistentSitio = em.find(Sitio.class, sitio.getIdSitio());
            Collection<Solicitud> solicitudCollectionOld = persistentSitio.getSolicitudCollection();
            Collection<Solicitud> solicitudCollectionNew = sitio.getSolicitudCollection();
            List<String> illegalOrphanMessages = null;
            for (Solicitud solicitudCollectionOldSolicitud : solicitudCollectionOld) {
                if (!solicitudCollectionNew.contains(solicitudCollectionOldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudCollectionOldSolicitud + " since its sitioIdSitio field is not nullable.");
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
            sitio.setSolicitudCollection(solicitudCollectionNew);
            sitio = em.merge(sitio);
            for (Solicitud solicitudCollectionNewSolicitud : solicitudCollectionNew) {
                if (!solicitudCollectionOld.contains(solicitudCollectionNewSolicitud)) {
                    Sitio oldSitioIdSitioOfSolicitudCollectionNewSolicitud = solicitudCollectionNewSolicitud.getSitioIdSitio();
                    solicitudCollectionNewSolicitud.setSitioIdSitio(sitio);
                    solicitudCollectionNewSolicitud = em.merge(solicitudCollectionNewSolicitud);
                    if (oldSitioIdSitioOfSolicitudCollectionNewSolicitud != null && !oldSitioIdSitioOfSolicitudCollectionNewSolicitud.equals(sitio)) {
                        oldSitioIdSitioOfSolicitudCollectionNewSolicitud.getSolicitudCollection().remove(solicitudCollectionNewSolicitud);
                        oldSitioIdSitioOfSolicitudCollectionNewSolicitud = em.merge(oldSitioIdSitioOfSolicitudCollectionNewSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sitio.getIdSitio();
                if (findSitio(id) == null) {
                    throw new NonexistentEntityException("The sitio with id " + id + " no longer exists.");
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
            Sitio sitio;
            try {
                sitio = em.getReference(Sitio.class, id);
                sitio.getIdSitio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sitio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Solicitud> solicitudCollectionOrphanCheck = sitio.getSolicitudCollection();
            for (Solicitud solicitudCollectionOrphanCheckSolicitud : solicitudCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sitio (" + sitio + ") cannot be destroyed since the Solicitud " + solicitudCollectionOrphanCheckSolicitud + " in its solicitudCollection field has a non-nullable sitioIdSitio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sitio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sitio> findSitioEntities() {
        return findSitioEntities(true, -1, -1);
    }

    public List<Sitio> findSitioEntities(int maxResults, int firstResult) {
        return findSitioEntities(false, maxResults, firstResult);
    }

    private List<Sitio> findSitioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sitio.class));
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

    public Sitio findSitio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sitio.class, id);
        } finally {
            em.close();
        }
    }

    public int getSitioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sitio> rt = cq.from(Sitio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
