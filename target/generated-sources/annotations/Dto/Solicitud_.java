package Dto;

import Dto.Logistico;
import Dto.Persona;
import Dto.Sitio;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-12-03T04:37:02")
@StaticMetamodel(Solicitud.class)
public class Solicitud_ { 

    public static volatile SingularAttribute<Solicitud, String> descripcion;
    public static volatile SingularAttribute<Solicitud, Sitio> sitioIdSitio;
    public static volatile SingularAttribute<Solicitud, String> estado;
    public static volatile SingularAttribute<Solicitud, Logistico> logisticoIdLog;
    public static volatile SingularAttribute<Solicitud, Integer> idPeticion;
    public static volatile SingularAttribute<Solicitud, Persona> personaId;

}