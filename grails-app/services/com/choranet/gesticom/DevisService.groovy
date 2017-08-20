
package com.choranet.gesticom;

import com.choranet.commun.SuperService

/**
 * DevisService Service pour la gestion des opérations
 * transactionnelles pour l'objet Devis
 */
class DevisService extends SuperService implements Comparator {

    static transactional = true

    def paternCompteurService
    
    def ligneProduitService
    
    int compare(lineA, lineB) {
        if (lineA.sequenceLigne < lineB.sequenceLigne) return -1
        if (lineA.sequenceLigne == lineB.sequenceLigne) return 0
        if (lineA.sequenceLigne > lineB.sequenceLigne) return 1
    }
    
    def list() throws Exception {
        return super.list(Devis.class)
    }
        
    def save(objet) throws Exception {
        objet.numDevis = paternCompteurService.getProchainNumDevisEtIncrementer()
        super.save(objet)
    }
    
    def trierParSequenceLigne(objet) {
        return objet.ligneProduits.sort(this)
    }
    
    def getSequenceSuivante(objet) {
        def resultat = 0;
        for(LigneProduit lp : objet.ligneProduits) {
            if(lp.sequenceLigne > resultat) {
                resultat = lp.sequenceLigne;
            }   
        }
        resultat++;
        return resultat;
    }
    
    def getDevisNonLiesBcLiesPartenaire(type, partner) {
        def bcs = BonCommande.list()
        def ids = bcs*.deviss*.id
        def idds = []
        ids.each {
            idds.addAll(it)
        }
        def criteria = Devis.createCriteria()
        def devisNonAssociesBc = criteria.list {
            if(idds.size() > 0) {
                not{"in"("id", idds)}
            }
            eq("type", type)
            partenaire {
                eq("code", partner.code)
            }
        }
        return devisNonAssociesBc
    }
    
    def getDevisNonLiesBcEtLies(bc) {
        def bcs = BonCommande.findAllByNumBCNotEqual(bc.numBC)
        def ids = bcs*.deviss*.id
        def idds = []
        ids.each {
            idds.addAll(it)
        }
        def criteria = Devis.createCriteria()
        def devisNonAssociesBc = criteria.list {
            if(idds.size() > 0) {
                not{"in"("id", idds)}
            }
            eq("type", bc.type)
            partenaire {
                eq("code", bc.partenaire.code)
            }
        }
        return devisNonAssociesBc
    }
}