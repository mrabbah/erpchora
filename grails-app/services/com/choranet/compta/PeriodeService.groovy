
package com.choranet.compta;

import com.choranet.commun.SuperService
/**
 * P�riodeService Service pour la gestion des opérations
 * transactionnelles pour l'objet P�riode
 */
class PeriodeService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Periode.class)
        }
}