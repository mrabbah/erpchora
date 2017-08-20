
package com.choranet.commun;

import com.choranet.gesticom.util.DateUtil;

/**
 * PaternCompteurService Service pour la gestion des opérations
 * transactionnelles pour l'objet PaternCompteur
 */
class PaternCompteurService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(PaternCompteur.class)
    }
        
    def getProchainNumBcEtIncrementer() {
        def pc = PaternCompteur.findByType("BON_COMMANDE");
        def result = constituerPattern(pc);
        incrementerPattern(pc);
        return result;
    }
    def getProchainNumBrEtIncrementer() {
        def pc = PaternCompteur.findByType("BON_RETOUR");
        def result = constituerPattern(pc);
        incrementerPattern(pc);
        return result;
    }
    def getProchainNumBlEtIncrementer() {
        def pc = PaternCompteur.findByType("BON_LIVRAISON");
        def result = constituerPattern(pc);
        incrementerPattern(pc);
        return result;
    }
    def getProchainNumBpEtIncrementer() {
        def pc = PaternCompteur.findByType("BON_PRET");
        def result = constituerPattern(pc);
        incrementerPattern(pc);
        return result;
    }
    def getProchainNumDevisEtIncrementer() {
        def pc = PaternCompteur.findByType("DEVIS");
        def result = constituerPattern(pc);
        incrementerPattern(pc);
        return result;
    }
    def getProchainNumFactureEtIncrementer() {
        def pc = PaternCompteur.findByType("FACTURE");
        def result = constituerPattern(pc);
        incrementerPattern(pc);
        return result;
    }
    def getProchainNumAvoirEtIncrementer() {
        def pc = PaternCompteur.findByType("AVOIR");
        def result = constituerPattern(pc);
        incrementerPattern(pc);
        return result;
    }
    
    def getProchainNumLivraisonEtIncrementer() {
        def pc = PaternCompteur.findByType("LIVRAISON");
        def result = constituerPattern(pc);
        incrementerPattern(pc);
        return result;
    }
    
    def getProchainNumLivraison() {
        def pc = PaternCompteur.findByType("LIVRAISON");
        return constituerPattern(pc);
    }
    
    def getProchainNumBc() {
        def pc = PaternCompteur.findByType("BON_COMMANDE");
        return constituerPattern(pc);
    }
    def getProchainNumBr() {
        def pc = PaternCompteur.findByType("BON_RETOUR");
        return constituerPattern(pc);
    }
    def getProchainNumBl() {
        def pc = PaternCompteur.findByType("BON_LIVRAISON");
        return constituerPattern(pc);
    }
    def getProchainNumBp() {
        def pc = PaternCompteur.findByType("BON_PRET");
        return constituerPattern(pc);
    }
    def getProchainNumDevis() {
        def pc = PaternCompteur.findByType("DEVIS");
        return constituerPattern(pc);
    }
    def getProchainNumFacture() {
        def pc = PaternCompteur.findByType("FACTURE");
        return constituerPattern(pc);
    }
    def getProchainNumAvoir() {
        def pc = PaternCompteur.findByType("AVOIR");
        return constituerPattern(pc);
    }
        
    private String constituerPattern(PaternCompteur pc) throws Exception {
        Date now = new Date()
        def result = pc.prefixe
        String ns = pc.numeroSuivant
        def diff = pc.remplissage - ns.length()
        while(diff > 0) {
            ns = "0" + ns;
            diff--;
        }
        result += ns; 
        result += pc.suffixe
        //Remplacer les variables
        result = result.replaceAll("\\{annee\\}", DateUtil.getDateTime("yyyy", now));
        result = result.replaceAll("\\{minannee\\}", DateUtil.getDateTime("yy", now));
        result = result.replaceAll("\\{mois\\}", DateUtil.getDateTime("MM", now));
        result = result.replaceAll("\\{jour\\}", DateUtil.getDateTime("dd", now));
        return result
    }
    
    private void incrementerPattern(PaternCompteur pc) throws Exception {
        pc.numeroSuivant += pc.pas;
        update(pc);
    }
}