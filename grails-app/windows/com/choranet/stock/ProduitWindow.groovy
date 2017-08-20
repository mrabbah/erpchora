
package com.choranet.stock
    

import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef

import org.zkoss.image.Image
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import java.util.HashMap;
import java.util.Map;
import org.zkoss.zk.ui.Executions
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo
import com.choranet.compta.RegimeTVA

/**
 * Produit Window Object
 **/
class ProduitWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Produit
     **/
    def produitService
    
    def excelImporterService
    
    def parametrageService
    
    def partenaireService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class ProduitWindow
     **/
    private Log logger = LogFactory.getLog(ProduitWindow.class)
    /**
     * liste de empReception
     **/	
    def empReceptions	
    /**
     * liste de nature
     **/	
    def natures	
    /**
     * liste de categorieProduit
     **/	
    def categorieProduits	
    /**
     * liste de uniteMesure
     **/	
    def uniteMesures	
    /**
     * liste de regimeTVA
     **/	
    def regimeTVAs	
    
    def produitPerissableManaged = false
       
    /**
     * Constructeur
     **/
    public ProduitWindow (parametrageService, partenaireService) {
        super(Produit.class, 11)
        this.parametrageService = parametrageService
        this.partenaireService = partenaireService       
        produitPerissableManaged = parametrageService.isProduitPerissableManaged() 
    }  

    protected SuperService getService() {
        return this.produitService
    }
    
    def importation(media) {
        String resultat = excelImporterService.importerProduits(media)
        Messagebox.show(resultat, "Notification" ,  Messagebox.OK, Messagebox.INFORMATION)
        rafraichirList()
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Produits"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Produits.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Produits.pdf"
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
        String titrerapport = "Rapport des Produits"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Produits.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Produits.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    def updateImageProduit(Object media){
        if (media != null) {                   
            if (media instanceof Image) {
                this.getFellow("appercuPhoto").setContent(media);
                objet.setTrans_imageProd((Image) media);
            } else {
                Messagebox.show("Le fichier choisi n'est pas une image: " + media, "Erreur",
                    Messagebox.OK, Messagebox.ERROR);
                //break; //not to show too many errors
            }

        } else {
            Messagebox.show("Impossible d'envoyer le fichier choisi: " + media, "Erreur",
                Messagebox.OK, Messagebox.ERROR);
        }
    }
    
    def zoomerImage(img) {
        def win = Executions.createComponents("/zul/util/zoomer.zul", null, null)
        win.getFellow("image").content = img
        win.doModal();
    }
}

