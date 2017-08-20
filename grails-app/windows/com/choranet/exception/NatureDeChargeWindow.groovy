
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

/**
 * NatureDeCharge Window Object
 **/
class NatureDeChargeWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet NatureDeCharge
     **/
    def natureDeChargeService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class NatureDeChargeWindow
     **/
    private Log logger = LogFactory.getLog(NatureDeChargeWindow.class)
    
    def isUpdate
    
    def typeSelected
    
    /**
     * Constructeur
     **/
    public NatureDeChargeWindow () {
        super(NatureDeCharge.class)
    }  

    protected SuperService getService() {
        return this.natureDeChargeService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des NatureDeCharges"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_NatureDeCharges.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_NatureDeCharges.pdf"
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
        String titrerapport = "Rapport des NatureDeCharges"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_NatureDeCharges.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_NatureDeCharges.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        
    }
    
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        
        objet.type = typeSelected
        
        def bindertype = new AnnotateDataBinder(this.getFellow("cotype"))
        //categorieParenteSelected = categorieParentes.get(0)
        bindertype.loadAll()
       
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        def bindercotype = new AnnotateDataBinder(this.getFellow("cotype"))          
        typeSelected = objet.type
        bindercotype.loadAll() 
    }
    
    /**
     *  Cette fonction est appeliée lorsque un élement de la liste est selectionné
     **/
    def select() {  
        isUpdate = true
        objet = objetSelected
        afficherValeurAssociation()
        rafraichirField()
    }
    
    def addOrUpdate(){
        if (isUpdate) {
            this.update()
            isUpdate = false
        } else {
            this.add()
        }
    }
    
    def add() {
        actualiserValeurAssociation()
        try {     
            getService().save(objet)
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("Paramètres manquants, ou la nouvelle entré est déjà supportée par le système\n", "Ajout non disponible", Messagebox.OK, Messagebox.ERROR)
        } finally {
            objet = clazz.newInstance()
            rafraichirField()
            rafraichirList()
        }
        
    }
    
    def update() {
        actualiserValeurAssociation()
        try {
            getService().update(objet)
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("La nouvelle valeur est dèjà mentionnée pour une catégorie existante", "Mise à jour non disponible", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            objet = clazz.newInstance()
            rafraichirField()
            rafraichirList()
        }
        
    }
    
    def delete() {
         try{
            getService().delete(objet)
        }
        catch(Exception e) {
            Messagebox.show("Cette catégorie est déjà associée à une dépense\n", "Suppression non disponible", Messagebox.OK, Messagebox.ERROR)
        }
        objet = clazz.newInstance()
        rafraichirField()
        rafraichirList()
        reinitialiserAssociation(true)
    }
}

