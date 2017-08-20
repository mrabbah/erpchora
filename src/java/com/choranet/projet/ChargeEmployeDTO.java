/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.choranet.projet;

import com.choranet.rh.Employe;
/**
 *
 * @author rabbah
 */
public class ChargeEmployeDTO {
    private Employe employe;
    private Double heuresTravail;
    private Double heuresAbscence;
    private Double total;

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Double getHeuresAbscence() {
        return heuresAbscence;
    }

    public void setHeuresAbscence(Double heuresAbscence) {
        this.heuresAbscence = heuresAbscence;
    }

    public Double getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(Double heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
}
