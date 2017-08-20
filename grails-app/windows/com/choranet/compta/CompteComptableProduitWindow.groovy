
package com.choranet.compta

import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.stock.CategorieProduit
import com.choranet.commun.ChoraClientInfo
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


/**
 * CompteComptableProduit Window Object
 **/
class CompteComptableProduitWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet CompteComptableProduit
     **/
    def compteComptableProduitService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class CompteComptableProduitWindow
     **/
    private Log logger = LogFactory.getLog(CompteComptableProduitWindow.class)
    
    /**
     * liste de compteComptable
     **/	
    def compteComptables	
    /**
     * compteComptable  selectionn�
     **/
    def compteComptableSelected
                
    /**
     * liste de categorieProduit
     **/	
    def categorieProduits	
    /**
     * categorieProduit  selectionn�
     **/
    def categorieProduitSelected
                
    /**
     * Constructeur
     **/
    public CompteComptableProduitWindow () {
        super(CompteComptableProduit.class)
    }  

    protected SuperService getService() {
        return this.compteComptableProduitService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des CompteComptableProduits"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_CompteComptableProduits.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_CompteComptableProduits.pdf"
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
        String titrerapport = "Rapport des CompteComptableProduits"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_CompteComptableProduits.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_CompteComptableProduits.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
        compteComptables = CompteComptable.list()		
        if(compteComptables.size() > 0)
        compteComptableSelected = compteComptables.get(0)
        else
        compteComptableSelected = null
                    
        categorieProduits = CategorieProduit.list()		
        if(categorieProduits.size() > 0)
        categorieProduitSelected = categorieProduits.get(0)
        else
        categorieProduitSelected = null
                    
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        	
        if(del) {
            compteComptables = CompteComptable.list()
        }	
        if(compteComptables.size() > 0)
        compteComptableSelected = compteComptables.get(0)
        else
        compteComptableSelected = null
                    	
        if(del) {
            categorieProduits = CategorieProduit.list()
        }	
        if(categorieProduits.size() > 0)
        categorieProduitSelected = categorieProduits.get(0)
        else
        categorieProduitSelected = null
                    
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        		
        objet.compteComptable = compteComptableSelected
        if(compteComptables.size() > 0) {
            def bindercompteComptable = new AnnotateDataBinder(this.getFellow("cocompteComptables"))
            compteComptableSelected = compteComptables.get(0)
            bindercompteComptable.loadAll()
        }
        else
        compteComptableSelected = null
                    		
        objet.categorieProduit = categorieProduitSelected
        if(categorieProduits.size() > 0) {
            def bindercategorieProduit = new AnnotateDataBinder(this.getFellow("cocategorieProduits"))
            categorieProduitSelected = categorieProduits.get(0)
            bindercategorieProduit.loadAll()
        }
        else
        categorieProduitSelected = null
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        		
        def bindercompteComptable = new AnnotateDataBinder(this.getFellow("cocompteComptables"))
        compteComptableSelected = compteComptables.find{ it.id == CompteComptableProduit.findById(objet.id).compteComptable.id }
        bindercompteComptable.loadAll()
                    		
        def bindercategorieProduit = new AnnotateDataBinder(this.getFellow("cocategorieProduits"))
        categorieProduitSelected = categorieProduits.find{ it.id == CompteComptableProduit.findById(objet.id).categorieProduit.id }
        bindercategorieProduit.loadAll()
                    
    }
}

