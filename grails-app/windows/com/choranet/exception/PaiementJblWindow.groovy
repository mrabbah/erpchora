
package com.choranet.exception
    

import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef

import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import java.util.HashMap;
import java.util.Map;
import org.zkoss.zk.ui.Executions
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo
import com.choranet.gesticom.util.DateUtil
import com.choranet.rh.Employe

/**
 * PaiementJbl Window Object
 **/
class PaiementJblWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet PaiementJbl
     **/
    def paiementJblService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class PaiementJblWindow
     **/
    private Log logger = LogFactory.getLog(PaiementJblWindow.class)
    
    /**
     * liste de employe
     **/	
    def employes	
    /**
     * employe  selectionn�
     **/
    def employeSelected
                
    def moisAnnee = DateUtil.moisAnnee
    
    def fichePresenceService
    /**
     * Constructeur
     **/
    public PaiementJblWindow (fichePresenceService) {
        super(PaiementJbl.class)
        this.fichePresenceService = fichePresenceService
        initialiserObjetPaiement(false)
    }  

    def initialiserObjetPaiement(loaderAttribut) {
        if(objet.annee == null) {
            objet.annee = DateUtil.recupererAnneeDateActuelleInt()
        }
        if(objet.mois == null || objet.mois.equals("")) {
            objet.mois = DateUtil.recupererMoisDateActuelle()
        }
        if(employeSelected != null) {
            objet.salaire = employeSelected.salaire
            def date = DateUtil.convertStringToDate(01+"/"+DateUtil.recupererOrdreMois(objet.mois)+"/"+objet.annee)
            def dates = DateUtil.recupereDateDebutDateFinMois(date)
            objet.joursTravailles = fichePresenceService.getNbJoursTravailEmploye(employeSelected, dates[0], dates[1])
            objet.heuresSupp = fichePresenceService.getNbHeuresSuppEmploye(employeSelected, dates[0], dates[1])
            objet.joursAbscences = fichePresenceService.getNbJoursAbscenceEmploye(employeSelected, dates[0], dates[1])
        }
        if(loaderAttribut) {
            rafraichirField()
            new AnnotateDataBinder(this.getFellow("comois")).loadAll()
        }
    }
    
    def add() {
        super.add()
        initialiserObjetPaiement(true)
    }
    
    def delete() {
        super.delete()
        initialiserObjetPaiement(true)
    }
    
    def update() {
        super.update()
        initialiserObjetPaiement(true)
    }
    
    def cancel() {
        super.cancel()
        initialiserObjetPaiement(true)
    }
    protected SuperService getService() {
        return this.paiementJblService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Paiements"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_PaiementJbls.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_PaiementJbls.pdf"
        //Filedownload.save(bit, "application/pdf", nom_fichier);
        Media amedia = new AMedia(nom_fichier, "pdf", "application/pdf", bit);
        Map map = new HashMap();
        map.put("content", amedia);
        Executions.createComponents("/zul/util/pdfviewer.zul", null, map).doModal();
    }
    /**
     * Generation du rapport excel
     **/
    def genererRapportExcel() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Paiements"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_PaiementJbls.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_PaiementJbls.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
        employes = Employe.list()		
        if(employes.size() > 0)
        employeSelected = employes.get(0)
        else
        employeSelected = null
                    
    }
    
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        objet.employe = employeSelected
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        		
        def binderemploye = new AnnotateDataBinder(this.getFellow("coemployes"))
        employeSelected = employes.find{ it.id == PaiementJbl.findById(objet.id).employe.id }
        binderemploye.loadAll()
        
        new AnnotateDataBinder(this.getFellow("comois")).loadAll()
                    
    }
}

