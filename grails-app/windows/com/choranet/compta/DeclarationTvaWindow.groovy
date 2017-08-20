
    package com.choranet.compta
    

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
 * DeclarationTva Window Object
 **/
class DeclarationTvaWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet DeclarationTva
     **/
    def declarationTvaService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class DeclarationTvaWindow
     **/
    private Log logger = LogFactory.getLog(DeclarationTvaWindow.class)
    
                /**
                 * liste de paiements
                 **/
                def paiements	
                /**
                 * paiements  selectionn�
                 **/
                def paiementsSelected
                
                /**
                 * liste de factures
                 **/
                def factures	
                /**
                 * factures  selectionn�
                 **/
                def facturesSelected
                
                /**
                 * liste de regimeDeclarationTva
                 **/	
                def regimeDeclarationTvas	
                /**
                 * regimeDeclarationTva  selectionn�
                 **/
                def regimeDeclarationTvaSelected
                
    /**
     * Constructeur
     **/
    public DeclarationTvaWindow () {
        super(DeclarationTva.class)
    }  

    protected SuperService getService() {
        return this.declarationTvaService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des DeclarationTvas"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_DeclarationTvas.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_DeclarationTvas.pdf"
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
        String titrerapport = "Rapport des DeclarationTvas"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_DeclarationTvas.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_DeclarationTvas.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
                    paiements = Paiement.list()
                    paiementsSelected = null// = new ArrayList()
                    
                    factures = Facture.list()
                    facturesSelected = null// = new ArrayList()
                    
                    regimeDeclarationTvas = RegimeDeclarationTva.list()		
                    if(regimeDeclarationTvas.size() > 0)
                    regimeDeclarationTvaSelected = regimeDeclarationTvas.get(0)
                    else
                    regimeDeclarationTvaSelected = null
                    
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        
                    if(del) {
			paiements = Paiement.list()
                    }
                    this.getFellow("lstpaiements").clearSelection()
                    paiementsSelected = null// = new ArrayList()
                    
                    if(del) {
			factures = Facture.list()
                    }
                    this.getFellow("lstfactures").clearSelection()
                    facturesSelected = null// = new ArrayList()
                    	
                    if(del) {
			regimeDeclarationTvas = RegimeDeclarationTva.list()
                    }	
                    if(regimeDeclarationTvas.size() > 0)
                    regimeDeclarationTvaSelected = regimeDeclarationTvas.get(0)
                    else
                    regimeDeclarationTvaSelected = null
                    
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        
                    objet.paiements = paiementsSelected
                    this.getFellow("lstpaiements").clearSelection()
                    paiementsSelected = null// = new ArrayList()
                    
                    objet.factures = facturesSelected
                    this.getFellow("lstfactures").clearSelection()
                    facturesSelected = null// = new ArrayList()
                    		
                    objet.regimeDeclarationTva = regimeDeclarationTvaSelected
                    if(regimeDeclarationTvas.size() > 0) {
			def binderregimeDeclarationTva = new AnnotateDataBinder(this.getFellow("coregimeDeclarationTvas"))
			regimeDeclarationTvaSelected = regimeDeclarationTvas.get(0)
			binderregimeDeclarationTva.loadAll()
                    }
                    else
                    regimeDeclarationTvaSelected = null
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        
                    def binderpaiements = new AnnotateDataBinder(this.getFellow("lstpaiements"))
                    paiementsSelected = objetSelected.paiements
                    binderpaiements.loadAll()
                    
                    def binderfactures = new AnnotateDataBinder(this.getFellow("lstfactures"))
                    facturesSelected = objetSelected.factures
                    binderfactures.loadAll()
                    		
                    def binderregimeDeclarationTva = new AnnotateDataBinder(this.getFellow("coregimeDeclarationTvas"))
                    regimeDeclarationTvaSelected = regimeDeclarationTvas.find{ it.id == DeclarationTva.findById(objet.id).regimeDeclarationTva.id }
                    binderregimeDeclarationTva.loadAll()
                    
    }
}

