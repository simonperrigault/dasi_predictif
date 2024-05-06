/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import metier.modele.ProfilAstral;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author slouvetdem
 */
public class ProfilAstralDao {

    public void create(ProfilAstral profil) {
        JPAutil.obtenirContextePersistance().persist(profil);
    }

    public void delete(ProfilAstral profil) {
        JPAutil.obtenirContextePersistance().remove(profil);
    }

    public ProfilAstral update(ProfilAstral profil) {
        return JPAutil.obtenirContextePersistance().merge(profil);
    }

    public ProfilAstral findById(Long id) {
        ProfilAstral profil;
        try 
        {
            profil = JPAutil.obtenirContextePersistance().find(ProfilAstral.class, id);
            
        } catch (NoResultException e) {
            profil = null;
        }
        return profil;
    }

    public List<ProfilAstral> findAll() {
        List<ProfilAstral> profils;
        
        String s = "select c from ProfilAstral c ";
        TypedQuery query = JPAutil.obtenirContextePersistance().createQuery(s, ProfilAstral.class);
        
        try
        {
            profils = query.getResultList();
        } catch (NoResultException e)
        {
            profils = null;
        }
        
        return profils;

    }
    
 
}
