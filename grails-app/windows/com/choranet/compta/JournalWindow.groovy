
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
 * Journal Window Object
 **/
class JournalWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Journal
     **/
    def journalService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class JournalWindow
     **/
    private Log logger = LogFactory.getLog(JournalWindow.class)
    
    /**
     * liste de compteComptables
     **/
    def compteComptables	
    /**
     * compteComptables  selectionn�
     **/
    def compteComptablesSelected
                
    /**
     * Constructeur
     **/
    public JournalWindow () {
        super(Journal.class)
    }  

    protected SuperService getService() {
        return this.journalService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Journals"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Journals.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Journals.pdf"
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
        String titrerapport = "Rapport des Journals"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Journals.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Journals.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
        compteComptables = CompteComptable.list()
        compteComptablesSelected = null// = new ArrayList()
                    
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        
        if(del) {
            compteComptables = CompteComptable.list()
        }
        this.getFellow("lstcompteComptables").clearSelection()
        compteComptablesSelected = null// = new ArrayList()
                    
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        
        objet.compteComptables = compteComptablesSelected
        this.getFellow("lstcompteComptables").clearSelection()
        compteComptablesSelected = null// = new ArrayList()
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        
        def bindercompteComptables = new AnnotateDataBinder(this.getFellow("lstcompteComptables"))
        compteComptablesSelected = objetSelected.compteComptables
        bindercompteComptables.loadAll()
                    
    }
}

