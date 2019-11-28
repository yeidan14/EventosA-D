/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "sitio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sitio.findAll", query = "SELECT s FROM Sitio s")
    , @NamedQuery(name = "Sitio.findByIdSitio", query = "SELECT s FROM Sitio s WHERE s.idSitio = :idSitio")
    , @NamedQuery(name = "Sitio.findByNombre", query = "SELECT s FROM Sitio s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Sitio.findByCapacidad", query = "SELECT s FROM Sitio s WHERE s.capacidad = :capacidad")
    , @NamedQuery(name = "Sitio.findByFechaIni", query = "SELECT s FROM Sitio s WHERE s.fechaIni = :fechaIni")
    , @NamedQuery(name = "Sitio.findByUbicacion", query = "SELECT s FROM Sitio s WHERE s.ubicacion = :ubicacion")
    , @NamedQuery(name = "Sitio.findByEstado", query = "SELECT s FROM Sitio s WHERE s.estado = :estado")})
public class Sitio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdSitio")
    private Integer idSitio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Capacidad")
    private int capacidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaIni")
    @Temporal(TemporalType.DATE)
    private Date fechaIni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Ubicacion")
    private String ubicacion;
    @Size(max = 45)
    @Column(name = "Estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sitioIdSitio")
    private List<Solicitud> solicitudList;

    public Sitio() {
    }

    public Sitio(Integer idSitio) {
        this.idSitio = idSitio;
    }

    public Sitio(Integer idSitio, String nombre, int capacidad, Date fechaIni, String ubicacion) {
        this.idSitio = idSitio;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.fechaIni = fechaIni;
        this.ubicacion = ubicacion;
    }

    public Integer getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(Integer idSitio) {
        this.idSitio = idSitio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSitio != null ? idSitio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sitio)) {
            return false;
        }
        Sitio other = (Sitio) object;
        if ((this.idSitio == null && other.idSitio != null) || (this.idSitio != null && !this.idSitio.equals(other.idSitio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dto.Sitio[ idSitio=" + idSitio + " ]";
    }
    
}
