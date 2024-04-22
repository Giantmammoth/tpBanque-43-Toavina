/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.toavina.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import mg.itu.toavina.tpbanque.entity.CompteBancaire;
import mg.itu.toavina.tpbanque.entity.OperationBancaire;
import mg.itu.toavina.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author toavi
 */
@Named(value = "operation")
@RequestScoped
public class Operation {

    private long id;
    private CompteBancaire compte;
    
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }
    
    

    /**
     * Creates a new instance of OperationBancaire
     */
    public Operation() {
    }
    
    public void loadCompte() {
        this.compte = gestionnaireCompte.findById(id);
    }
    
    public List<OperationBancaire> getOperationBancaire(){
        return compte.getOperations();
    }
}
