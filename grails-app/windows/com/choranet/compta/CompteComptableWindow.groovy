
package com.choranet.compta
    
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
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
 * CompteComptable Window Object
 **/
class CompteComptableWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet CompteComptable
     **/
    def compteComptableService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class CompteComptableWindow
     **/
    private Log logger = LogFactory.getLog(CompteComptableWindow.class)
    
    /**
     * liste de parent
     **/	
    def parents	
    /**
     * parent  selectionn�
     **/
    def parentSelected
                
    /**
     * Constructeur
     **/
    public CompteComptableWindow () {
        super(CompteComptable.class)
    }  

    protected SuperService getService() {
        return this.compteComptableService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des CompteComptables"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_CompteComptables.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_CompteComptables.pdf"
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
        String titrerapport = "Rapport des CompteComptables"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_CompteComptables.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_CompteComptables.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
        parents = CompteComptable.list()		
        if(parents.size() > 0)
        parentSelected = parents.get(0)
        else
        parentSelected = null
                    
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        	
        if(del) {
            parents = CompteComptable.list()
        }	
        if(parents.size() > 0)
        parentSelected = parents.get(0)
        else
        parentSelected = null
                    
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        		
        objet.parent = parentSelected
        if(parents.size() > 0) {
            def binderparent = new AnnotateDataBinder(this.getFellow("coparents"))
            parentSelected = parents.get(0)
            binderparent.loadAll()
        }
        else
        parentSelected = null
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        		
        def binderparent = new AnnotateDataBinder(this.getFellow("coparents"))
        parentSelected = parents.find{ it.id == CompteComptable.findById(objet.id).parent.id }
        binderparent.loadAll()
                    
    }
}

