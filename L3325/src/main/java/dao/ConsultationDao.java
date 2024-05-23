/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import metier.modele.Consultation;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import metier.modele.Employe;

/**
 *
 * @author slouvetdem
 */
public class ConsultationDao {

    public void create(Consultation consultation) {
        JPAutil.obtenirContextePersistance().persist(consultation);
    }

    public void delete(Consultation consultation) {
        JPAutil.obtenirContextePersistance().remove(consultation);
    }

    public Consultation update(Consultation consultation) {
        return JPAutil.obtenirContextePersistance().merge(consultation);
    }

    public Consultation findById(Long id) {
        Consultation consultation;
        try 
        {
            consultation = JPAutil.obtenirContextePersistance().find(Consultation.class, id);
            
        } catch (NoResultException e) {
            consultation = null;
        }
        return consultation;
    }
    
    
    public Consultation findCurrentEmployeConsult(Employe employe)
    {
        Consultation result;
        
        String s = "select c from Consultation c where c.duree is null AND c.employe.id = :id";
        TypedQuery<Consultation> query = JPAutil.obtenirContextePersistance().createQuery(s, Consultation.class);
        query.setParameter("id", employe.getId());
        

        
        try
        {
            result = query.getSingleResult();
        } catch (NoResultException e)
        {
            result = null;
        }
        
        return result;
    }
    

    public List<Consultation> findAll() {
        List<Consultation> clients;
        
        String s = "select c from Consultation c order by c.id asc";
        TypedQuery query = JPAutil.obtenirContextePersistance().createQuery(s, Consultation.class);
        
        try
        {
            clients = query.getResultList();
        } catch (NoResultException e)
        {
            clients = null;
        }
        
        return clients;

    }
    
    public List<Consultation> findConsultationByEmploye(Employe employe) {
    List<Consultation> consultations;
    
    String s = "select c from Consultation c where c.employe = :employe";
    TypedQuery<Consultation> query = JPAutil.obtenirContextePersistance().createQuery(s, Consultation.class);
    query.setParameter("employe", employe);
    
    try {
        consultations = query.getResultList();
    } catch (NoResultException e) {
        consultations = null;
    }
    
    return consultations;
}
}
