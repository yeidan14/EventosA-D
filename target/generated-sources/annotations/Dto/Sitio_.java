package Dto;

import Dto.Solicitud;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-12-03T04:37:02")
@StaticMetamodel(Sitio.class)
public class Sitio_ { 

    public static volatile SingularAttribute<Sitio, String> ubicacion;
    public static volatile SingularAttribute<Sitio, String> estado;
    public static volatile SingularAttribute<Sitio, Date> fechaIni;
    public static volatile CollectionAttribute<Sitio, Solicitud> solicitudCollection;
    public static volatile SingularAttribute<Sitio, String> nombre;
    public static volatile SingularAttribute<Sitio, Integer> idSitio;
    public static volatile SingularAttribute<Sitio, Integer> capacidad;

}