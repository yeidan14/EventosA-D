package Dto;

import Dto.Solicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-11-29T14:51:22")
@StaticMetamodel(Logistico.class)
public class Logistico_ { 

    public static volatile SingularAttribute<Logistico, String> descripcion;
    public static volatile SingularAttribute<Logistico, Integer> cant;
    public static volatile ListAttribute<Logistico, Solicitud> solicitudList;
    public static volatile SingularAttribute<Logistico, Integer> idLog;

}