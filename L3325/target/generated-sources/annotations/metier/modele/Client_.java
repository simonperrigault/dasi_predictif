package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Consultation;
import metier.modele.ProfilAstral;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-13T14:19:37")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> motDePasse;
    public static volatile SingularAttribute<Client, String> mail;
    public static volatile SingularAttribute<Client, Date> dateNaissance;
    public static volatile SingularAttribute<Client, ProfilAstral> profil;
    public static volatile SingularAttribute<Client, Double> latitude;
    public static volatile SingularAttribute<Client, Long> id;
    public static volatile SingularAttribute<Client, String> adressePostale;
    public static volatile SingularAttribute<Client, String> nom;
    public static volatile SingularAttribute<Client, String> numTel;
    public static volatile ListAttribute<Client, Consultation> consultations;
    public static volatile SingularAttribute<Client, String> prenom;
    public static volatile SingularAttribute<Client, Double> longitude;

}