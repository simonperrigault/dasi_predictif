/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import metier.modele.Employe;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author slouvetdem
 */
public class EmployeDao {

    public void create(Employe employe) {
        JPAutil.obtenirContextePersistance().persist(employe);
    }

    public void delete(Employe employe) {
        JPAutil.obtenirContextePersistance().remove(employe);
    }

    public Employe update(Employe employe) {
        return JPAutil.obtenirContextePersistance().merge(employe);
    }

    public Employe findById(Long id) {
        Employe employe;
        try 
        {
            employe = JPAutil.obtenirContextePersistance().find(Employe.class, id);
            
        } catch (NoResultException e) {
            employe = null;
        }
        return employe;
    }

    public List<Employe> findAll() {
        List<Employe> employes;
        
        String s = "select c from Employe c order by c.nom asc";
        TypedQuery query = JPAutil.obtenirContextePersistance().createQuery(s, Employe.class);
        
        try
        {
            employes = query.getResultList();
        } catch (NoResultException e)
        {
            employes = null;
        }
        
        return employes;

    }
    public long nombreTotalConsultation() {
        long total;
        String s = "SELECT SUM(c.nombreConsult) FROM Employe c";
        TypedQuery<Long> query = JPAutil.obtenirContextePersistance().createQuery(s,Long.class);

        try {
            total = query.getSingleResult();
        }
        catch (NoResultException e){
            total = -1;
        }
        return total;
        
    }
    
    public Employe findByMailID(String mail, String motDePasse) {
        Employe employe;
        String s = "select c from Employe c where c.mail = :mail AND c.motDePasse = :motDePasse";
        TypedQuery<Employe> query = JPAutil.obtenirContextePersistance().createQuery(s,Employe.class);
        query.setParameter("mail", mail);
        query.setParameter("motDePasse", motDePasse);
        try {
            employe = query.getSingleResult();
        }
        catch (NoResultException e){
            employe = null;
        }
        return employe;
        
    }
    
    public Employe findByDispoAndGenre(String genre) {
        Employe employe;
        
        String s = "select c from Employe c where c.genre = :genre AND c.disponible = true order by c.nombreConsult asc";
        TypedQuery<Employe> query = JPAutil.obtenirContextePersistance().createQuery(s,Employe.class);
        query.setParameter("genre", genre);
        query.setMaxResults(1);
        
        try {
            employe = query.getSingleResult();
            
        }
        catch (NoResultException e){
            employe = null;
        }
        
        return employe;
        
    }
}
