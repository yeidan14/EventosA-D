/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s")
    , @NamedQuery(name = "Solicitud.findByIdPeticion", query = "SELECT s FROM Solicitud s WHERE s.idPeticion = :idPeticion")
    , @NamedQuery(name = "Solicitud.findByEstado", query = "SELECT s FROM Solicitud s WHERE s.estado = :estado")
    , @NamedQuery(name = "Solicitud.findByDescripcion", query = "SELECT s FROM Solicitud s WHERE s.descripcion = :descripcion")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPeticion")
    private Integer idPeticion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Descripcion")
    private String descripcion;
    @JoinColumn(name = "logistico_IdLog", referencedColumnName = "IdLog")
    @ManyToOne(optional = false)
    private Logistico logisticoIdLog;
    @JoinColumn(name = "persona_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Persona personaId;
    @JoinColumn(name = "sitio_IdSitio", referencedColumnName = "IdSitio")
    @ManyToOne(optional = false)
    private Sitio sitioIdSitio;

    public Solicitud() {
    }

    public Solicitud(Integer idPeticion) {
        this.idPeticion = idPeticion;
    }

    public Solicitud(Integer idPeticion, String estado, String descripcion) {
        this.idPeticion = idPeticion;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public Integer getIdPeticion() {
        return idPeticion;
    }

    public void setIdPeticion(Integer idPeticion) {
        this.idPeticion = idPeticion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Logistico getLogisticoIdLog() {
        return logisticoIdLog;
    }

    public void setLogisticoIdLog(Logistico logisticoIdLog) {
        this.logisticoIdLog = logisticoIdLog;
    }

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
    }

    public Sitio getSitioIdSitio() {
        return sitioIdSitio;
    }

    public void setSitioIdSitio(Sitio sitioIdSitio) {
        this.sitioIdSitio = sitioIdSitio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeticion != null ? idPeticion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.idPeticion == null && other.idPeticion != null) || (this.idPeticion != null && !this.idPeticion.equals(other.idPeticion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dto.Solicitud[ idPeticion=" + idPeticion + " ]";
    }
    
}
