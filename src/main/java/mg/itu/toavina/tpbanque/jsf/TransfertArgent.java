/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.toavina.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mg.itu.toavina.tpbanque.entity.CompteBancaire;
import mg.itu.toavina.tpbanque.jsf.util.Util;
import mg.itu.toavina.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author toavi
 */
@Named(value = "transfert")
@RequestScoped
public class TransfertArgent {

    private int montant;

    private Long idSource;

    private Long idDestination;

    /**
     * Get the value of idDestination
     *
     * @return the value of idDestination
     */
    public Long getIdDestination() {
        return idDestination;
    }

    /**
     * Set the value of idDestination
     *
     * @param idDestination new value of idDestination
     */
    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

    /**
     * Get the value of idSource
     *
     * @return the value of idSource
     */
    public Long getIdSource() {
        return idSource;
    }

    /**
     * Set the value of idSource
     *
     * @param idSource new value of idSource
     */
    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    /**
     * Get the value of montant
     *
     * @return the value of montant
     */
    public int getMontant() {
        return montant;
    }

    /**
     * Set the value of montant
     *
     * @param montant new value of montant
     */
    public void setMontant(int montant) {
        this.montant = montant;
    }

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of TransfertArgent
     */
    public TransfertArgent() {
    }

    public String transfert() {
        boolean erreur = false;
        CompteBancaire compteSource = gestionnaireCompte.findById(idSource);
        CompteBancaire compteDestination = gestionnaireCompte.findById(idDestination);
        
        if (compteSource == null) {
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else if (compteDestination == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } 
        else {
            if (compteSource.getSolde() < montant) { // à compléter pour le cas où le solde du compte source est insuffisant...
                
                Util.messageErreur("Votre solde est insuffisant !", "Votre solde est insuffisant !", "form:source");
                erreur = true;
            }
        }
        if(erreur) {
            return null;
        }
        gestionnaireCompte.transferer(compteSource, compteDestination, montant);
        Util.addFlashInfoMessage("Transfert correctement effectué");
        return "listeComptes?faces-redirect=true";

    }

}
