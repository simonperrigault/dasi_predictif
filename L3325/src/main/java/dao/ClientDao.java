/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import metier.modele.Client;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author slouvetdem
 */
public class ClientDao {

    public void create(Client client) {
        JPAutil.obtenirContextePersistance().persist(client);
    }

    public void delete(Client client) {
        JPAutil.obtenirContextePersistance().remove(client);
    }

    public Client update(Client client) {
        return JPAutil.obtenirContextePersistance().merge(client);
    }

    public Client findById(Long id) {
        Client client;
        try 
        {
            client = JPAutil.obtenirContextePersistance().find(Client.class, id);
            
        } catch (NoResultException e) {
            client = null;
        }
        return client;
    }

    public List<Client> findAll() {
        List<Client> clients;
        
        String s = "select c from Client c order by c.nom asc";
        TypedQuery query = JPAutil.obtenirContextePersistance().createQuery(s, Client.class);
        
        try
        {
            clients = query.getResultList();
        } catch (NoResultException e)
        {
            clients = null;
        }
        
        return clients;

    }
    
    public Client findByMailID(String mail, String motDePasse) {
        Client client;
        String s = "select c from Client c where c.mail = :mail AND c.motDePasse = :motDePasse";
        TypedQuery<Client> query = JPAutil.obtenirContextePersistance().createQuery(s,Client.class);
        query.setParameter("mail", mail);
        query.setParameter("motDePasse", motDePasse);
        try {
            client = query.getSingleResult();
        }
        catch (NoResultException e){
            client = null;
        }
        return client;
        
    }
}
