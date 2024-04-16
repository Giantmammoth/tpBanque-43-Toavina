/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.toavina.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.toavina.tpbanque.entity.CompteBancaire;
import mg.itu.toavina.tpbanque.jsf.util.Util;
import mg.itu.toavina.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author toavi
 */
@Named(value = "modifier")
@ViewScoped
public class Modifier implements Serializable {
    
    

    @Inject
    GestionnaireCompte gestionnaireCompte;
    
    private Long id;
    private CompteBancaire compte;
    
    private String nom;

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

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }
 
    public void loadCompte() {
        compte = gestionnaireCompte.findById(id);
        nom = compte.getNom();
    }
    
    public String update(){
        CompteBancaire compteBancaire = gestionnaireCompte.update(compte);
        Util.addFlashInfoMessage("Nom du possésseur : " + nom + " a été effectuer avec succès en " + compteBancaire.getNom());
        return "listeComptes?faces-redirect=true";
    }
    
}
