
package com.choranet.stock;

import com.choranet.commun.SuperService
/**
 * AlertsSecuriteStockService Service pour la gestion des opérations
 * transactionnelles pour l'objet AlertsSecuriteStock
 */
class AlertsSecuriteStockService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(AlertsSecuriteStock.class)
    }
    def getListAlertsActives() {
        return AlertsSecuriteStock.findAllByTjsValide(true)
    }
        
    def mettreAjourAlertSecuriteStockApresMouvement(mouvement, securiteStockSource, securiteStockDestination) {
        if(securiteStockSource != null) {
            if(mouvement.empSource != null) {
                if(securiteStockSource.stockReel < securiteStockSource.stockMin || securiteStockSource.stockReel > securiteStockSource.stockMax) {
                    def nouvelleAlerte = new AlertsSecuriteStock()
                    nouvelleAlerte.raisonDeclanchement = mouvement
                    nouvelleAlerte.stockMin = securiteStockSource.stockMin
                    nouvelleAlerte.stockMax = securiteStockSource.stockMax
                    nouvelleAlerte.stockReel = securiteStockSource.stockReel
                    nouvelleAlerte.details = "Stock du produit : " + mouvement.produit
                    if(securiteStockSource.stockReel < securiteStockSource.stockMin) {
                        nouvelleAlerte.details  += " Inférieur à la quantité permise " + securiteStockSource.stockMin
                    
                    } else if(securiteStockSource.stockReel > securiteStockSource.stockMax) {
                        nouvelleAlerte.details  += " Supérieur à la quantité permise " + securiteStockSource.stockMax
                    }
                    nouvelleAlerte.details += " dans l'entrepôt " + mouvement.empSource + " Suite à un mouvement type " + mouvement.typeMouvement
                    save(nouvelleAlerte)
                } else {
                    def c = AlertsSecuriteStock.createCriteria() 
                    def anciennesAlertes = c.list {
                        eq("tjsValide", true)
                        raisonDeclanchement {
                            empSource {
                                eq("code", mouvement.empSource.code)
                            }
                            produit {
                                eq("code", mouvement.produit.code)
                            }
                        }
                    }
                    if(anciennesAlertes!= null && anciennesAlertes.size() > 0) {
                        for(ancienneAlerte in anciennesAlertes) {
                            ancienneAlerte.tjsValide = false
                            ancienneAlerte.raisonRelachement = mouvement
                            ancienneAlerte.dateFinAlerte = new Date()
                            update(ancienneAlerte)
                        }
                    } 
                }
            }
        }
        if(securiteStockDestination != null) {
            if(mouvement.empDestination != null) {
                if(securiteStockDestination.stockReel < securiteStockDestination.stockMin || securiteStockDestination.stockReel > securiteStockDestination.stockMax) {
                    def nouvelleAlerte = new AlertsSecuriteStock()
                    nouvelleAlerte.raisonDeclanchement = mouvement
                    nouvelleAlerte.stockMin = securiteStockDestination.stockMin
                    nouvelleAlerte.stockMax = securiteStockDestination.stockMax
                    nouvelleAlerte.stockReel = securiteStockDestination.stockReel
                    nouvelleAlerte.details = "Stock du produit : " + mouvement.produit
                    if(securiteStockDestination.stockReel < securiteStockDestination.stockMin) {
                        nouvelleAlerte.details  += " Inférieur à la quantité permise " + securiteStockDestination.stockMin
                    
                    } else if(securiteStockDestination.stockReel > securiteStockDestination.stockMax) {
                        nouvelleAlerte.details  += " Supérieur à la quantité permise " + securiteStockDestination.stockMax
                    }
                    nouvelleAlerte.details += " dans l'entrepôt " + mouvement.empDestination + " Suite à un mouvement type " + mouvement.typeMouvement
                    save(nouvelleAlerte)
                } else {
                    def c = AlertsSecuriteStock.createCriteria() 
                    def anciennesAlertes = c.list {
                        eq("tjsValide", true)
                        raisonDeclanchement {
                            empDestination {
                                eq("code", mouvement.empDestination.code)
                            }
                            produit {
                                eq("code", mouvement.produit.code)
                            }
                        }
                    }
                    if(anciennesAlertes!= null && anciennesAlertes.size() > 0) {
                        for(ancienneAlerte in anciennesAlertes) {
                            ancienneAlerte.tjsValide = false
                            ancienneAlerte.raisonRelachement = mouvement
                            ancienneAlerte.dateFinAlerte = new Date()
                            update(ancienneAlerte)
                        }
                    } 
                }
            }
        }
    }
}