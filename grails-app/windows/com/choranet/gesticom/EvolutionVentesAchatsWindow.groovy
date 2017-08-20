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
import com.choranet.gesticom.util.DateUtil
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo

/**
 * Produit Window Object
 **/ 
class EvolutionVentesAchatsWindow extends Window {
    /**
     * Service pour la gestion de l'objet Produit
     **/
    def livraisonService
    
    def etatventesachats = new SimpleCategoryModel()
    def annee
    def listeannees 
   
    /**
     *
     * Logger de la class ProduitWindow
     **/
    private Log logger = LogFactory.getLog(ProduitsVendusAchetesWindow.class)
    
    /**
     * Constructeur
     **/
    
    public EvolutionVentesAchatsWindow(livraisonService){
        this.livraisonService = livraisonService
        listeannees = livraisonService.getlisteAnnees()
        annee = null
        //        new AnnotateDataBinder(this.getFellow("listeannees")).loadAll()
    }
        
    def genererExercice(){

        
        etatventesachats = new SimpleCategoryModel();
        def exerciceVentes = livraisonService.exercice(annee)
        if (exerciceVentes != null && exerciceVentes.size() > 0)
        {
            exerciceVentes.each {
                etatventesachats.setValue(it[0], DateUtil.getMonth(it[1]),it[2]);
            }
        }
        new AnnotateDataBinder(this.getFellow("sbarchart3d")).loadAll()
    } 

}

