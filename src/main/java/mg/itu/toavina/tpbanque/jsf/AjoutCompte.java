/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.toavina.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import mg.itu.toavina.tpbanque.entity.CompteBancaire;
import mg.itu.toavina.tpbanque.service.GestionnaireCompte;
import mg.itu.toavina.tpbanque.jsf.util.Util;

/**
 *
 * @author toavi
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    private String nom;

    private int solde;

    /**
     * Get the value of solde
     *
     * @return the value of solde
     */
    public int getSolde() {
        return solde;
    }

    /**
     * Set the value of solde
     *
     * @param solde new value of solde
     */
    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public AjoutCompte() {
    }
    
    public String action() {
        boolean erreur = false;
        CompteBancaire newCompte = new CompteBancaire(nom, solde);
        if(erreur) {
            return null;
        }
        gestionnaireCompte.creerCompte(newCompte);
        Util.addFlashInfoMessage("création de compte correctement effectué");
        return "listeComptes?faces-redirect=true";

    }

}
