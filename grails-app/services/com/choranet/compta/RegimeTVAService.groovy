
package com.choranet.compta;

import com.choranet.commun.SuperService

/**
 * RegimeTVAService Service pour la gestion des opérations
 * transactionnelles pour l'objet RegimeTVA
 */
class RegimeTVAService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(RegimeTVA.class)
        }
        
        def getDefaultRegime() {
            return RegimeTVA.findByTaux(20)
        }
        
        def getRegimeByLibelle(rtva) {
            return RegimeTVA.findByLibelleIlike(rtva)
        }
}