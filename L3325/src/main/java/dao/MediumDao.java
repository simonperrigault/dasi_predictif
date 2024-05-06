/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import metier.modele.Medium;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author slouvetdem
 */
public class MediumDao {

    public void create(Medium medium) {
        JPAutil.obtenirContextePersistance().persist(medium);
    }

    public void delete(Medium medium) {
        JPAutil.obtenirContextePersistance().remove(medium);
    }

    public Medium update(Medium medium) {
        return JPAutil.obtenirContextePersistance().merge(medium);
    }

    public Medium findById(Long id) {
        Medium medium;
        try 
        {
            medium = JPAutil.obtenirContextePersistance().find(Medium.class, id);
            
        } catch (NoResultException e) {
            medium = null;
        }
        return medium;
    }

    public List<Medium> findAll() {
        List<Medium> mediums;
        
        String s = "select c from Medium c";
        TypedQuery query = JPAutil.obtenirContextePersistance().createQuery(s, Medium.class);
        
        try
        {
            mediums = query.getResultList();
        } catch (NoResultException e)
        {
            mediums = null;
        }
        
        return mediums;

    }
    
    public Medium findByDenomination(String denomination) {
        Medium medium;
        
        String s = "select c from Medium c where c.denomination = :denomination";
        TypedQuery<Medium> query = JPAutil.obtenirContextePersistance().createQuery(s, Medium.class);
        query.setParameter("denomination", denomination);
        
        try
        {
            medium = query.getSingleResult();
        } catch (NoResultException e)
        {
            medium = null;
        }
        
        return medium;

    }
    
    public List<Medium> findBestMediums() {
        List<Medium> mediums;
        
        
        String s = "select c from Medium c order by c.nombreChoisi desc";
        TypedQuery query = JPAutil.obtenirContextePersistance().createQuery(s, Medium.class);
        query.setMaxResults(5);
        
        try
        {
            mediums = query.getResultList();
        } catch (NoResultException e)
        {
            mediums = null;
        }
        
        return mediums;

    }

}
