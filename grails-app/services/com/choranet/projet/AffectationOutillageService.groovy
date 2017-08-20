
package com.choranet.projet;

import com.choranet.commun.SuperService

/**
 * AffectationOutillageService Service pour la gestion des opérations
 * transactionnelles pour l'objet AffectationOutillage
 */
class AffectationOutillageService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(AffectationOutillage.class)
    }
    
    def getOutillageNonAffecter() {
        def oa = getOutillageAffecter()
        def idds = oa*.id
        def c = OutillageCollectif.createCriteria()
        def result = c.list {
            isNull("dateFinUtilisation")
            if(idds.size() > 0) {
                not{"in"("id", idds)}
            }
        }
        return result
    }
    
    def getOutillageAffecter() {
        def c = AffectationOutillage.createCriteria()
        def result = c.list {
            isNull("dateFinUtilisationOutillage")            
        }
        return result*.outillageCollectif
    }
}