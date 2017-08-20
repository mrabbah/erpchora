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
import org.zkoss.zk.ui.Executions
import org.hibernate.ScrollableResults
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo

/**
 * Produit Window Object
 **/ 
class ProduitsVendusAchetesWindow extends Window {
    /**
     * Service pour la gestion de l'objet Produit
     **/
    def livraisonService
    def etatproduitsvendusqte = new SimplePieModel()
    def etatproduitsvendusvaleur = new SimplePieModel()
    def etatproduitsachetesqte = new SimplePieModel()
    def etatproduitsachetesvaleur = new SimplePieModel()
    def datedebut
    def datefin
    
    /**
     *
     * Logger de la class ProduitWindow
     **/
    private Log logger = LogFactory.getLog(ProduitsVendusAchetesWindow.class)
    
    /**
     * Constructeur
     **/
    
    public ProduitsVendusAchetesWindow() {
        
    }
        
    def genererProduitsvendusAchetes() {
        genererProduitVendus()
        genererProduitAchetes()
    }
    def genererProduitVendus(){ 
        etatproduitsvendusqte = new SimplePieModel()
        etatproduitsvendusvaleur = new SimplePieModel()
        def totalventesQ = 0
        def totalventesV = 0
        def typeTran = "VENTE"
        def produitsVendusQte = livraisonService.produitsLesPlusVendusAchetesQte(datedebut,datefin,typeTran)
        def produitsVendusValeur = livraisonService.produitsLesPlusVendusAchetesValeur(datedebut,datefin,typeTran)
        def totalVendu = livraisonService.getproduitsVendusAchetesTotal(datedebut,datefin,typeTran)

        def TQ = 0  
        def TV = 0
        
        if(totalVendu[0] != null && totalVendu[1] != null) {
            TQ = totalVendu[0]
            TV = totalVendu[1] 
        }
        
    
        if (produitsVendusQte != null && produitsVendusQte.size() > 0)
        {
            produitsVendusQte.each {
                etatproduitsvendusqte.setValue(it[0],it[1]);
                totalventesQ += it[1]
            }
        }
        
        if (produitsVendusValeur != null && produitsVendusValeur.size() > 0)
        {
            produitsVendusValeur.each {
                etatproduitsvendusvaleur.setValue(it[0],it[1]);
                totalventesV += it[1]
            }
        }  
        
        if(totalVendu[0] != null && totalVendu[1] != null) {
            totalventesQ = TQ - totalventesQ
            totalventesV = TV - totalventesV
            if(totalventesQ != 0) {
                etatproduitsvendusqte.setValue("Autres",totalventesQ);
            }
            if(totalventesV != 0) {
                etatproduitsvendusvaleur.setValue("Autres",totalventesV);
            }
        }
              
        new AnnotateDataBinder(this.getFellow("mychart2")).loadAll()
        new AnnotateDataBinder(this.getFellow("mychart4")).loadAll()
    }
    def genererProduitAchetes(){
        etatproduitsachetesqte =  new SimplePieModel()
        etatproduitsachetesvaleur  = new SimplePieModel()
        
        def totalachatsQ = 0
        def totalachatsV = 0
        def typeTran = "ACHAT"
        def TQ = 0  
        def TV = 0
        def produitsAchetesQte = livraisonService.produitsLesPlusVendusAchetesQte(datedebut,datefin,typeTran)
        def produitsAchetesValeur = livraisonService.produitsLesPlusVendusAchetesValeur(datedebut,datefin,typeTran)
        def totalAchete = livraisonService.getproduitsVendusAchetesTotal(datedebut,datefin,typeTran)

                        
        if(totalAchete[0] != null && totalAchete[1] != null) {
            TQ = totalAchete[0] 
            TV = totalAchete[1]
        }

        if (produitsAchetesQte != null && produitsAchetesQte.size() > 0)
        {
            produitsAchetesQte.each {
                etatproduitsachetesqte.setValue(it[0],it[1]);
                totalachatsQ += it[1]
            }
        }
        if (produitsAchetesValeur != null && produitsAchetesValeur.size() > 0)
        {
            produitsAchetesValeur.each {
                etatproduitsachetesvaleur.setValue(it[0],it[1]);
                totalachatsV += it[1]
            }
        }
        if(totalAchete[0] != null && totalAchete[1] != null) {
            totalachatsQ =  totalAchete[0] - totalachatsQ
            totalachatsV = totalAchete[1] - totalachatsV
            if(totalachatsQ != 0) {
                etatproduitsachetesqte.setValue("Autres",totalachatsQ);
            }
            if(totalachatsV != 0) {
                etatproduitsachetesvaleur.setValue("Autres",totalachatsV);
            }
        }
            
        new AnnotateDataBinder(this.getFellow("mychart1")).loadAll()
        new AnnotateDataBinder(this.getFellow("mychart3")).loadAll()
    }

}

