
package com.choranet.rh;

import com.choranet.commun.SuperService

/**
 * FichePresenceService Service pour la gestion des opérations
 * transactionnelles pour l'objet FichePresence
 */
class FichePresenceService extends SuperService {

    static transactional = true

    def projetService
    
    def update(Object object) throws Exception {
        super.update(object);
        projetService.mettreAjourChargesReellesProjet(object.projet)
    }

    def save(Object object) throws Exception {
        super.save(object);
        projetService.mettreAjourChargesReellesProjet(object.projet)
    }

    def delete(Object object) throws Exception {
        super.delete(object);
        projetService.mettreAjourChargesReellesProjet(object.projet)
    }
    
    def list() throws Exception {
        return super.list(FichePresence.class)
    }
    
    def getNbHeuresTravilEmploye(emp, dateDebut, dateFin) {
        def c = FichePresence.createCriteria()
        def result = c.get {
            employe {
                eq("id", emp.id)
            }
            between("date", dateDebut, dateFin)
            projections {
                sum("heuresTravail")
            }
        }
        if(result == null) {
            result = 0
        }
        return result
    }
    
    def getNbHeuresAbscenceEmploye(emp, dateDebut, dateFin) {
        def c = FichePresence.createCriteria()
        def result = c.get {
            employe {
                eq("id", emp.id)
            }
            between("date", dateDebut, dateFin)
            projections {
                sum("heuresAbscence")
            }
        }
        if(result == null) {
            result = 0
        }
        return result        
    }
    
    def getNbHeuresSuppEmploye(emp, dateDebut, dateFin) {
        def calendrier = emp.calendrier
        def nbHeuresTravailParMois = calendrier.nbHeuresTravailParJour * calendrier.nbJoursTravailParMois
        def result = getNbHeuresTravilEmploye(emp, dateDebut, dateFin)
        result -= nbHeuresTravailParMois
        if(result < 0) {
            result = 0
        }
        return result
    }
    
    def getNbJoursTravailEmploye(emp, dateDebut, dateFin) {
        def calendrier = emp.calendrier
        def result = getNbHeuresTravilEmploye(emp, dateDebut, dateFin)
        result = (int) (result / calendrier.nbHeuresTravailParJour)
        return result    
            
    }
    
    def getNbJoursAbscenceEmploye(emp, dateDebut, dateFin) {
        def calendrier = emp.calendrier
        def result = getNbHeuresAbscenceEmploye(emp, dateDebut, dateFin)
        result = (int) (result / calendrier.nbHeuresTravailParJour)
        return result  
    }
}