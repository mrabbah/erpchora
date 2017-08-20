
package com.choranet.gesticom;

import com.choranet.commun.SuperService
/**
 * BonLivraisonService Service pour la gestion des opérations
 * transactionnelles pour l'objet BonLivraison
 */
class BonLivraisonService extends SuperService {

    static transactional = true

    def paternCompteurService
    def livraisonService
    def ligneProduitService
    def bonCommandeService
    def paiementService
    
    def list() throws Exception {
        return super.list(BonLivraison.class)
    }
    
    def getBonsLivraisonBonsRetourPartenaire(partner) {
        def criteria = BonLivraison.createCriteria()
        def bls = criteria.list {
            bonCommande {
                partenaire {
                    eq("code", partner.code)
                }
            }
        }
        return bls
    }
    
    def getBonsLivraisonPartenaire(type, partner) {
        def criteria = BonLivraison.createCriteria()
        def bls = criteria.list {
            bonCommande {
                partenaire {
                    eq("code", partner.code)
                }
            }
            eq("type", type)
            eq("estBonRetour", false)
        }
        return bls
    }
    
    def getBonsLivraisonBonCommande(bc) {
        def criteria = BonLivraison.createCriteria()
        def bls = criteria.list {
            eq("estBonRetour", false)
            bonCommande {
                eq("numBC", bc.numBC)
            }
        }
        return bls
    }
    
//    def getBonLivraisonBetweenWI(date1, date2, partner) {
//        def factures = Facture.list()
//        def ids = factures*.bonLivraisons*.id
//        def idds = []
//        ids.each {
//            idds.addAll(it)
//        }
//        def criteria = BonLivraison.createCriteria()
//        def bls = criteria.list {
//            eq("estBonRetour", false)
//            bonCommande {
//                partenaire {
//                    eq("code", partner.code)
//                }
//            }
//            if(idds.size() > 0) {
//                not{"in"("id", idds)}
//            }
//            eq("type", "VENTE")            
//            between("date", date1, date2)
//        }
//        return bls
//
//    }

    def save(objet) throws Exception {
        def bc = objet.bonCommande
        if(objet.estBonRetour) {
            objet.numBL = paternCompteurService.getProchainNumBrEtIncrementer()            
        } else {
            objet.numBL = paternCompteurService.getProchainNumBlEtIncrementer()            
        }
        objet = super.save(objet)
        if(objet.estBonRetour) {
            updateEtatBonCommandeLorsRetour(bc)
        }
        return objet
     }
    
    def sauverAvecLivraison(objet, livraison) {
        def bc = objet.bonCommande
        if(objet.estBonRetour) {
            objet.numBL = paternCompteurService.getProchainNumBrEtIncrementer()
        } else {
            objet.numBL = paternCompteurService.getProchainNumBlEtIncrementer()            
        }
        def numBL = objet.numBL
        objet.livraison = livraison
        livraison = livraisonService.sauverEtAjouterBl(livraison, objet)
        if(objet.estBonRetour) {
            updateEtatBonCommandeLorsRetour(bc)
        }
        return BonLivraison.findByNumBL(numBL)
    }
    
    def mettreAjourEtAjouterLivraison(objet, livraison) {
        def bc = objet.bonCommande
        objet.livraison = livraison
        def numBL = objet.numBL
        livraison = livraisonService.sauverEtAjouterBl(livraison, objet)
        if(objet.estBonRetour) {
            updateEtatBonCommandeLorsRetour(bc)
        }
        return BonLivraison.findByNumBL(numBL)
    }
    
    def delete(objet) throws Exception {
        def bc = objet.bonCommande
        def estBonRetour = objet.estBonRetour
        def codelivraison = null
        if(objet.livraison != null) {
            codelivraison = objet.livraison.numExpedition
        }
        super.delete(objet)
        if(codelivraison != null) {
            livraisonService.supprimerSiLieeAvecUnSeulBl(codelivraison)
        }
        if(estBonRetour) {
            updateEtatBonCommandeLorsRetour(bc)
        }
    }
    
    def update(objet)throws Exception {
        objet = super.update(objet)
        if(objet.estBonRetour) {
            updateEtatBonCommandeLorsRetour(bc)
        }
        return objet
    }
    
    def updateEtatBonCommandeLorsRetour(bc) {
        def c = BonLivraison.createCriteria()
        def brs = c.list{
            bonCommande {
                eq("numBC", bc.numBC)
            }
            eq("estBonRetour", true)
        }
        if(brs.size() > 0) {
            bc.avecRetour = true
        }
        def montantAretourner = 0d
        for(br in brs) {
            montantAretourner += br.trans_totalttc
        }
        bc.totalttcRetour = montantAretourner
        bc.retourPaye = false
        bc = bonCommandeService.update(bc)
        paiementService.updateEtatPaiementBonCommandeAprsRetour(bc)
    }
    
    def deleteBonsLivraisonsZambies(bl) {
        def bc = bl.bonCommande
        def c = BonLivraison.createCriteria()
        def r = c.list {
            eq("bonCommande", bc)
            isNull("livraison")
        }
        for(b in r) {
            delete(b)
        }
        //BonLivraison.executeUpdate("delete BonLivraison bl where bl.bonCommande = :bc and livraison = null", [bc:bc])
    }
    
    def bonlivraisonslivraison(livraison) {
        def c = BonLivraison.createCriteria()  
        def result = c.list {
            eq("livraison",livraison)
        }
        return result
    }
    
    def getLpsBonLivraisonEnDetails(lignesProduitsBL){
        return livraisonService.getLpsBLEnDetails(lignesProduitsBL)
    }
    
}