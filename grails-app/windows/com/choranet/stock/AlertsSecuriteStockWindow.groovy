
package com.choranet.stock

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
 * AlertsSecuriteStock Window Object
 **/
class AlertsSecuriteStockWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet AlertsSecuriteStock
     **/
    def alertsSecuriteStockService
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class AlertsSecuriteStockWindow
     **/
    private Log logger = LogFactory.getLog(AlertsSecuriteStockWindow.class)
    /**
     * Constructeur
     **/
    public AlertsSecuriteStockWindow (alertsSecuriteStockService) {
        super(AlertsSecuriteStock.class)
        this.alertsSecuriteStockService = alertsSecuriteStockService
        //specialeInitialisation()
    }  
//    def specialeInitialisation() {
//        filtre.dateAlerte = null
//        filtre.tjsValide = null
//        listeObjets = alertsSecuriteStockService.getListAlertsActives()     
//    }
    protected SuperService getService() {
        return this.alertsSecuriteStockService
    }
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des AlertsSecuriteStocks"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_AlertsSecuriteStocks.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_AlertsSecuriteStocks.pdf"
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
        String titrerapport = "Rapport des AlertsSecuriteStocks"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_AlertsSecuriteStocks.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_AlertsSecuriteStocks.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    def activerBoutons(visible) {
        this.getFellow("btnPdf").visible = !visible
        this.getFellow("btnExcel").visible = !visible
    }
//    private void initialisation(clazz, maxNb, attributsAFiltrer) throws Exception {
//        try {
//            this.clazz = clazz
//            this.maxNb = maxNb
//            this.attributsAFiltrer = attributsAFiltrer
//            this.nomClassFille = clazz.getSimpleName()
//            ofs = 0
//            listeObjets = clazz.list(offset:ofs, max:maxNb)
//            def criteria = clazz.createCriteria()
//            tailleListe = criteria.count{} 
//            objetSelected = null
//            objet = clazz.newInstance()
//            filtre = clazz.newInstance()
//            initialiserAssociation()
//        } catch(Exception e) {
//            logger.error(e)
//            throw e
//        }
//    }
//    def filtrer() {
//        try {
//            def map
//            if(attributsAFiltrer == null) {
//                map = getService().filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, maxNb)
//            } else {
//                map = getService().filtrer(clazz, filtre, attributsAFiltrer, sortedHeader, sortedDirection, ofs, maxNb)
//            }
//            tailleListe = map["tailleListe"]
//            listeObjets = map["listeObjets"]
//            def binder = new AnnotateDataBinder(this.getFellow("lstObjet"))
//            binder.loadAll()
//        
//            def binder2 = new AnnotateDataBinder(this.getFellow("paging"))
//            binder2.loadAll()    
//        } catch (Exception ex) {
//            logger.error(ex)
//        }        
//    }
//    
//    def getObjetsToExport() {
//        try {
//            if(attributsAFiltrer == null) {
//                return getService().filtrer(clazz, filtre, sortedHeader, sortedDirection)
//            } else {
//                return getService().filtrer(clazz, filtre, attributsAFiltrer, sortedHeader, sortedDirection)
//            }
//        } catch (Exception ex) {
//            logger.error(ex)
//        }        
//    }
//    
//    def sort(event) {
//        sortedHeader = event.getTarget().getId()
//        def sortDirection =  event.getTarget().getSortDirection()
//        sortedDirection = "asc"
//
//        if(sortDirection == "natural" || sortDirection == "descending") {
//            sortedDirection = "desc"
//        }
//        
//        filtrer()
//    }
//    
//    def getNextElements(event) {
//        def pgno = event.getActivePage()
//        ofs = pgno * event.getPageable().getPageSize()
//        
//        filtrer()
//    }
}

