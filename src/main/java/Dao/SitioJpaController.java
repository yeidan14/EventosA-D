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
        if (sitio.getSolicitudList() == null) {
            sitio.setSolicitudList(new ArrayList<Solicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Solicitud> attachedSolicitudList = new ArrayList<Solicitud>();
            for (Solicitud solicitudListSolicitudToAttach : sitio.getSolicitudList()) {
                solicitudListSolicitudToAttach = em.getReference(solicitudListSolicitudToAttach.getClass(), solicitudListSolicitudToAttach.getIdPeticion());
                attachedSolicitudList.add(solicitudListSolicitudToAttach);
            }
            sitio.setSolicitudList(attachedSolicitudList);
            em.persist(sitio);
            for (Solicitud solicitudListSolicitud : sitio.getSolicitudList()) {
                Sitio oldSitioIdSitioOfSolicitudListSolicitud = solicitudListSolicitud.getSitioIdSitio();
                solicitudListSolicitud.setSitioIdSitio(sitio);
                solicitudListSolicitud = em.merge(solicitudListSolicitud);
                if (oldSitioIdSitioOfSolicitudListSolicitud != null) {
                    oldSitioIdSitioOfSolicitudListSolicitud.getSolicitudList().remove(solicitudListSolicitud);
                    oldSitioIdSitioOfSolicitudListSolicitud = em.merge(oldSitioIdSitioOfSolicitudListSolicitud);
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
            List<Solicitud> solicitudListOld = persistentSitio.getSolicitudList();
            List<Solicitud> solicitudListNew = sitio.getSolicitudList();
            List<String> illegalOrphanMessages = null;
            for (Solicitud solicitudListOldSolicitud : solicitudListOld) {
                if (!solicitudListNew.contains(solicitudListOldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudListOldSolicitud + " since its sitioIdSitio field is not nullable.");
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
            sitio.setSolicitudList(solicitudListNew);
            sitio = em.merge(sitio);
            for (Solicitud solicitudListNewSolicitud : solicitudListNew) {
                if (!solicitudListOld.contains(solicitudListNewSolicitud)) {
                    Sitio oldSitioIdSitioOfSolicitudListNewSolicitud = solicitudListNewSolicitud.getSitioIdSitio();
                    solicitudListNewSolicitud.setSitioIdSitio(sitio);
                    solicitudListNewSolicitud = em.merge(solicitudListNewSolicitud);
                    if (oldSitioIdSitioOfSolicitudListNewSolicitud != null && !oldSitioIdSitioOfSolicitudListNewSolicitud.equals(sitio)) {
                        oldSitioIdSitioOfSolicitudListNewSolicitud.getSolicitudList().remove(solicitudListNewSolicitud);
                        oldSitioIdSitioOfSolicitudListNewSolicitud = em.merge(oldSitioIdSitioOfSolicitudListNewSolicitud);
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
            List<Solicitud> solicitudListOrphanCheck = sitio.getSolicitudList();
            for (Solicitud solicitudListOrphanCheckSolicitud : solicitudListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sitio (" + sitio + ") cannot be destroyed since the Solicitud " + solicitudListOrphanCheckSolicitud + " in its solicitudList field has a non-nullable sitioIdSitio field.");
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
