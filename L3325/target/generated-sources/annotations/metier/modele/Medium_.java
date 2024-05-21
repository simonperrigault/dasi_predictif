package metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Consultation;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-21T08:14:28")
@StaticMetamodel(Medium.class)
public class Medium_ { 

    public static volatile SingularAttribute<Medium, String> presentation;
    public static volatile SingularAttribute<Medium, String> cheminPhotoProfil;
    public static volatile SingularAttribute<Medium, String> genre;
    public static volatile SingularAttribute<Medium, Long> id;
    public static volatile SingularAttribute<Medium, Integer> nombreChoisi;
    public static volatile ListAttribute<Medium, Consultation> consultations;
    public static volatile SingularAttribute<Medium, String> denomination;

}