package Dto;

import Dto.Solicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-12-03T04:37:02")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> pass;
    public static volatile CollectionAttribute<Persona, Solicitud> solicitudCollection;
    public static volatile SingularAttribute<Persona, String> apellido;
    public static volatile SingularAttribute<Persona, String> correo;
    public static volatile SingularAttribute<Persona, Integer> id;
    public static volatile SingularAttribute<Persona, String> cargo;
    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile SingularAttribute<Persona, Integer> edad;

}