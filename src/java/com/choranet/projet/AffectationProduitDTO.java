/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.choranet.projet;

import com.choranet.stock.Produit;

/**
 *
 * @author rabbah
 */
public class AffectationProduitDTO {
    private Produit produit;
    private Double quantite;
    private Double total;

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
}
