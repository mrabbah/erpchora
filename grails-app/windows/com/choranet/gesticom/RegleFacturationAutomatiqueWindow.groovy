
package com.choranet.gesticom
    

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
import org.zkoss.zk.ui.Executions;
import com.choranet.gesticom.util.DateUtil;
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo

/**
 * RegleFacturationAutomatique Window Object
 **/
class RegleFacturationAutomatiqueWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet RegleFacturationAutomatique
     **/
    def regleFacturationAutomatiqueService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class RegleFacturationAutomatiqueWindow
     **/
    private Log logger = LogFactory.getLog(RegleFacturationAutomatiqueWindow.class)
    
    /**
     * liste de partenaires
     **/
    def partenaires
    /**
     * partenaires selectionn�
     **/
    def partenairesSelected
                
    /**
     * Constructeur
     **/

    
    def bonLivraisonService
    def factureService
    
    public RegleFacturationAutomatiqueWindow () {
        super(RegleFacturationAutomatique.class)
    }  

    protected SuperService getService() {
        return this.regleFacturationAutomatiqueService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des RegleFacturationAutomatiques"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_RegleFacturationAutomatiques.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_RegleFacturationAutomatiques.pdf"
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
        String titrerapport = "Rapport des RegleFacturationAutomatiques"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_RegleFacturationAutomatiques.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_RegleFacturationAutomatiques.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
        partenaires = Partenaire.list()
        partenairesSelected = null// = new ArrayList()
                    
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        	
        if(del) {
            partenaires = Partenaire.list()
        }
        this.getFellow("lstpartenaires").clearSelection()
        partenairesSelected = null// = new ArrayList()
                    
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        	
        objet.partenaires = partenairesSelected
        this.getFellow("lstpartenaires").clearSelection()
        partenairesSelected = null// = new ArrayList()
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        
        def binderpartenaires = new AnnotateDataBinder(this.getFellow("lstpartenaires"))
        partenairesSelected = objetSelected.partenaires
        binderpartenaires.loadAll()		
                    
    }
    
    def newRecord(){
        super.newRecord()  
        getFellow("grcrud").visible = false
        getFellow("vbchoix").visible = visible
        getFellow("choixType").setSelectedIndex(-1)
    }
    def choixGenreRegleFacturationAutomatique(id) {
        getFellow("LigneType").visible = false
        getFellow("LigneBL").visible = false
        switch(id) {
            
            case "r1" :
            getFellow("vbchoix").visible = false
            getFellow("grcrud").visible = true

            break
            case "r2" : 
            getFellow("vbchoix").visible = false
            getFellow("grcrud").visible = true

            break
            case "r3" : 
            getFellow("vbchoix").visible = false
            getFellow("grcrud").visible = true

            break
            case "r4" : 
            getFellow("vbchoix").visible = false
            getFellow("grcrud").visible = true
            getFellow("LigneBL").visible = true
        }
    }
    
    def GenererRegleFacturationAutomatique(id, nombreregroupebl) {
        def bonLivraisons
        Date actuelle = new Date();
        def MoisPrecedent = DateUtil.recupererDateMoisPrecedent(actuelle)
        def dateMoisPrecedent = DateUtil.recupereDateDebutDateFinMois (MoisPrecedent)
        def dateDerniersTroisMois = DateUtil.recupererDateDebutDateFinDerniersTroisMois(actuelle);
        
        Date date1 
        Date date2
        def listFactures
        def NombreFactures = 0
        
        switch(id) {
            case "r1" :
            date1 = Date.parse("yyy-MM-dd", "1940-01-01")
            date2 = new Date()
            nombreregroupebl = -1
            break
            case "r2" :
            date1 = dateMoisPrecedent[0]
            date2 = dateMoisPrecedent[1]
            nombreregroupebl = -1 
            break
            case "r3" :
            date1 = dateDerniersTroisMois[0]
            date2 = dateDerniersTroisMois[1]
            nombreregroupebl = -1
            break
            case "r4" :
            date1 =  Date.parse("yyy-MM-dd", "1940-01-01")
            date2 = new Date()
            break
        }
              
       
                
        partenairesSelected.each { partenaire ->
            bonLivraisons = bonLivraisonService.getBonLivraisonBetweenWI(date1,date2,partenaire)
            if ( bonLivraisons != null && bonLivraisons.size() > 0 ){ 
                listFactures = factureService.genererFacture(partenaire,bonLivraisons,nombreregroupebl)
            }
            
            //            else {
            //                Messagebox.show("Liste BL vide", "Infos", Messagebox.OK, Messagebox.ERROR)
            //            }
        }
        
        if (listFactures != null && listFactures.size() > 0){
            NombreFactures = listFactures.size()
            String nomsociete = session.societe.raisonSociale
            String titrerapport = "Rapport des factures automatiques générées"
        
            def reportDef = new JasperReportDef(name:'rapport_des_Factures.jasper',
                fileFormat:JasperExportFormat.PDF_FORMAT,
                reportData : listFactures,
                parameters : ['nomsociete' : nomsociete,'titrerapport' : titrerapport]
            )
            def bit = jasperService.generateReport(reportDef).toByteArray()
            def nom_fichier = "rapport_Factures_generees"+".pdf"
            //Filedownload.save(bit, "application/file", nom_fichier);
            Media amedia = new AMedia(nom_fichier, "pdf", "application/pdf", bit);
            Map map = new HashMap();
            map.put("content", amedia);
            Executions.createComponents("/zul/util/pdfviewer.zul", null, map).doModal();
        }
                        
        Messagebox.show("Nombre des factures générées : "+NombreFactures, "Infos", Messagebox.OK, Messagebox.ERROR)
        this.getFellow("westPanel").open = false
        
        //        getFellow("vbchoix").visible = false
        //        getFellow("grcrud").visible = false
    }
}

