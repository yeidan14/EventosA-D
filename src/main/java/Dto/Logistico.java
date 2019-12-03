/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "logistico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logistico.findAll", query = "SELECT l FROM Logistico l")
    , @NamedQuery(name = "Logistico.findByIdLog", query = "SELECT l FROM Logistico l WHERE l.idLog = :idLog")
    , @NamedQuery(name = "Logistico.findByDescripcion", query = "SELECT l FROM Logistico l WHERE l.descripcion = :descripcion")
    , @NamedQuery(name = "Logistico.findByCant", query = "SELECT l FROM Logistico l WHERE l.cant = :cant")})
public class Logistico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdLog")
    private Integer idLog;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cant")
    private int cant;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "logisticoIdLog")
    private Collection<Solicitud> solicitudCollection;

    public Logistico() {
    }

    public Logistico(Integer idLog) {
        this.idLog = idLog;
    }

    public Logistico(Integer idLog, String descripcion, int cant) {
        this.idLog = idLog;
        this.descripcion = descripcion;
        this.cant = cant;
    }

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    @XmlTransient
    public Collection<Solicitud> getSolicitudCollection() {
        return solicitudCollection;
    }

    public void setSolicitudCollection(Collection<Solicitud> solicitudCollection) {
        this.solicitudCollection = solicitudCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLog != null ? idLog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logistico)) {
            return false;
        }
        Logistico other = (Logistico) object;
        if ((this.idLog == null && other.idLog != null) || (this.idLog != null && !this.idLog.equals(other.idLog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dto.Logistico[ idLog=" + idLog + " ]";
    }
    
}
