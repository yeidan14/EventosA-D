/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Dto.Logistico;
import Dto.Persona;
import Dto.Sitio;
import Dto.Solicitud;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Daniel
 */
public class SolicitudJpaController implements Serializable {

    public SolicitudJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitud solicitud) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Logistico logisticoIdLog = solicitud.getLogisticoIdLog();
            if (logisticoIdLog != null) {
                logisticoIdLog = em.getReference(logisticoIdLog.getClass(), logisticoIdLog.getIdLog());
                solicitud.setLogisticoIdLog(logisticoIdLog);
            }
            Persona personaId = solicitud.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                solicitud.setPersonaId(personaId);
            }
            Sitio sitioIdSitio = solicitud.getSitioIdSitio();
            if (sitioIdSitio != null) {
                sitioIdSitio = em.getReference(sitioIdSitio.getClass(), sitioIdSitio.getIdSitio());
                solicitud.setSitioIdSitio(sitioIdSitio);
            }
            em.persist(solicitud);
            if (logisticoIdLog != null) {
                logisticoIdLog.getSolicitudCollection().add(solicitud);
                logisticoIdLog = em.merge(logisticoIdLog);
            }
            if (personaId != null) {
                personaId.getSolicitudCollection().add(solicitud);
                personaId = em.merge(personaId);
            }
            if (sitioIdSitio != null) {
                sitioIdSitio.getSolicitudCollection().add(solicitud);
                sitioIdSitio = em.merge(sitioIdSitio);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitud solicitud) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitud persistentSolicitud = em.find(Solicitud.class, solicitud.getIdPeticion());
            Logistico logisticoIdLogOld = persistentSolicitud.getLogisticoIdLog();
            Logistico logisticoIdLogNew = solicitud.getLogisticoIdLog();
            Persona personaIdOld = persistentSolicitud.getPersonaId();
            Persona personaIdNew = solicitud.getPersonaId();
            Sitio sitioIdSitioOld = persistentSolicitud.getSitioIdSitio();
            Sitio sitioIdSitioNew = solicitud.getSitioIdSitio();
            if (logisticoIdLogNew != null) {
                logisticoIdLogNew = em.getReference(logisticoIdLogNew.getClass(), logisticoIdLogNew.getIdLog());
                solicitud.setLogisticoIdLog(logisticoIdLogNew);
            }
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                solicitud.setPersonaId(personaIdNew);
            }
            if (sitioIdSitioNew != null) {
                sitioIdSitioNew = em.getReference(sitioIdSitioNew.getClass(), sitioIdSitioNew.getIdSitio());
                solicitud.setSitioIdSitio(sitioIdSitioNew);
            }
            solicitud = em.merge(solicitud);
            if (logisticoIdLogOld != null && !logisticoIdLogOld.equals(logisticoIdLogNew)) {
                logisticoIdLogOld.getSolicitudCollection().remove(solicitud);
                logisticoIdLogOld = em.merge(logisticoIdLogOld);
            }
            if (logisticoIdLogNew != null && !logisticoIdLogNew.equals(logisticoIdLogOld)) {
                logisticoIdLogNew.getSolicitudCollection().add(solicitud);
                logisticoIdLogNew = em.merge(logisticoIdLogNew);
            }
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getSolicitudCollection().remove(solicitud);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getSolicitudCollection().add(solicitud);
                personaIdNew = em.merge(personaIdNew);
            }
            if (sitioIdSitioOld != null && !sitioIdSitioOld.equals(sitioIdSitioNew)) {
                sitioIdSitioOld.getSolicitudCollection().remove(solicitud);
                sitioIdSitioOld = em.merge(sitioIdSitioOld);
            }
            if (sitioIdSitioNew != null && !sitioIdSitioNew.equals(sitioIdSitioOld)) {
                sitioIdSitioNew.getSolicitudCollection().add(solicitud);
                sitioIdSitioNew = em.merge(sitioIdSitioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitud.getIdPeticion();
                if (findSolicitud(id) == null) {
                    throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitud solicitud;
            try {
                solicitud = em.getReference(Solicitud.class, id);
                solicitud.getIdPeticion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.", enfe);
            }
            Logistico logisticoIdLog = solicitud.getLogisticoIdLog();
            if (logisticoIdLog != null) {
                logisticoIdLog.getSolicitudCollection().remove(solicitud);
                logisticoIdLog = em.merge(logisticoIdLog);
            }
            Persona personaId = solicitud.getPersonaId();
            if (personaId != null) {
                personaId.getSolicitudCollection().remove(solicitud);
                personaId = em.merge(personaId);
            }
            Sitio sitioIdSitio = solicitud.getSitioIdSitio();
            if (sitioIdSitio != null) {
                sitioIdSitio.getSolicitudCollection().remove(solicitud);
                sitioIdSitio = em.merge(sitioIdSitio);
            }
            em.remove(solicitud);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitud> findSolicitudEntities() {
        return findSolicitudEntities(true, -1, -1);
    }

    public List<Solicitud> findSolicitudEntities(int maxResults, int firstResult) {
        return findSolicitudEntities(false, maxResults, firstResult);
    }

    private List<Solicitud> findSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitud.class));
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

    public Solicitud findSolicitud(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitud> rt = cq.from(Solicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
