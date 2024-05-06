/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author sperrigaul
 */
public class TestUtilisateur {
    Long id;
    String prenom;
    String nom;
    String mail;

    public TestUtilisateur(Long id, String prenom, String nom, String mail) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "TestUtilisateur{" + "id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", mail=" + mail + '}';
    }
    
    
    
    
}
