
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
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo
import com.choranet.stock.CategorieProduit
import com.choranet.stock.Produit

/**
 * ReglePrix Window Object
 **/
class ReglePrixWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet ReglePrix
     **/
    def reglePrixService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class ReglePrixWindow
     **/
    private Log logger = LogFactory.getLog(ReglePrixWindow.class)
    
    /**
     * liste des categories de partenaires
     **/
    def categoriePartenaires
    
    /**
     * categories de partenaires selectionnées
     **/
    def categoriePartenairesSelected = []
    
    /**
     * liste de partenaires
     **/
    def partenaires
    /**
     * partenaires selectionnés
     **/
    def partenairesSelected = []
     
    /**
     * liste des categories de produits
     **/
    def categorieProduits
    
    /**
     * categories de produits selectionnées
     **/
    def categorieProduitsSelected = []
    
    /**
     * liste de produits
     **/
    def produits
    
    /**
     * produits selectionn�
     **/
    def produitsSelected = []
    
    /**
     *
     **/
    def baseCtgrPrtSelected = []
    
    /**
     *
     **/
    def baseCtgrPrtAbnSelected = []
    
    /*
     * Partenaires sélectionnés 
     **/
    def basePrtSelected = []
    
    /*
     * Partenaires sélectionnés, qui sont abonnés pour la règle courante 
     **/
    def basePrtAbnSelected = []
    
    /**
     *
     **/
    def baseCtgrPrdSelected = []
    
    /**
     *
     **/
    def baseCtgrPrdAbnSelected = []
    
    /*
     * Produits sélectionnés 
     **/
    def basePrdSelected = []
    
    /*
     * Produits sélectionnés, qui sont abonnés pour la règle courante 
     **/
    def basePrdAbnSelected = []
        
    
    /**
     * Constructeur
     **/
    public ReglePrixWindow (reglePrixService) {
        super(ReglePrix.class)
        this.reglePrixService = reglePrixService
            
        categoriePartenaires = CategoriePartenaire.list()
        categoriePartenairesSelected = []
        
        partenaires = Partenaire.list()
        partenairesSelected = []
                            
        categorieProduits = CategorieProduit.list()
        categorieProduitsSelected = []
                
        produits = Produit.list()
        produitsSelected = []
        
        if (objet != null) {
            categoriePartenairesSelected = reglePrixService.getCategoriePartenairesForCurrentRegle(objet)
            partenairesSelected = reglePrixService.getPartenairesForCurrentRegle(objet)
            categorieProduitsSelected = reglePrixService.getCategorieProduitsForCurrentRegle(objet)
            produitsSelected = reglePrixService.getProduitsForCurrentRegle(objet)
        }
    }  

    protected SuperService getService() {
        return this.reglePrixService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des ReglePrixs"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_ReglePrixs.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_ReglePrixs.pdf"
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
        String titrerapport = "Rapport des ReglePrixs"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_ReglePrixs.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_ReglePrixs.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    /**
     * Activer ou dï¿½sactiver les boutons d'ajout, suppression, modfication
     **/
    def activerBoutons(visible) {
        this.getFellow("btnUpdate").visible = visible
        this.getFellow("btnDelete").visible = visible
        this.getFellow("btnPdf").visible = !visible
        this.getFellow("btnExcel").visible = !visible
        this.getFellow("btnSave").visible = !visible
        this.getFellow("btnNew").visible = !visible
        this.getFellow("westPanel").open = visible   
        actualiserListes(1)
        actualiserListes(2)
        actualiserListes(3)
        actualiserListes(4)
    }
    
    def binderAfterCRUD() {
        
        def binderCategoriePartenaires = new AnnotateDataBinder(this.getFellow("lstcategoriePartenaires"))
        def binderCategoriePartenairesSelected = new AnnotateDataBinder(this.getFellow("lstcategoriePartenairesAbonnesRegle"))
        categoriePartenairesSelected = []
        categoriePartenaires = CategoriePartenaire.list()
        binderCategoriePartenaires.loadAll()
        binderCategoriePartenairesSelected.loadAll()
        
        def binderPartenaires = new AnnotateDataBinder(this.getFellow("lstpartenaires"))
        def binderPartenairesSelected = new AnnotateDataBinder(this.getFellow("lstpartenairesAbonnesRegle"))
        partenairesSelected = []
        partenaires = Partenaire.list()
        binderPartenaires.loadAll()
        binderPartenairesSelected.loadAll()
        
        def binderCategorieProduits = new AnnotateDataBinder(this.getFellow("lstcategorieProduits"))
        def binderCategorieProduitsSelected = new AnnotateDataBinder(this.getFellow("lstcategorieProduitsAbonnesRegle"))
        categorieProduitsSelected = []
        categorieProduits = CategorieProduit.list()
        binderCategorieProduits.loadAll()
        binderCategorieProduitsSelected.loadAll()
        
        def binderProduits = new AnnotateDataBinder(this.getFellow("lstproduits"))
        def binderProduitsSelected = new AnnotateDataBinder(this.getFellow("lstproduitsAbonnesRegle"))
        produitsSelected = []
        produits = Produit.list()
        binderProduits.loadAll()
        binderProduitsSelected.loadAll()
    }
    
    def add() {
	super.add()
        binderAfterCRUD()
    }
    
    def update() {
	super.update()
        binderAfterCRUD()
    }
    
    /**
     * Pour annuler la modification ou la supression et pour basculer en mode ajout d'un nouveau ï¿½lï¿½ment
     **/
    def cancel() {    

        super.cancel()
        binderAfterCRUD()
    }
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
        categoriePartenaires = CategoriePartenaire.list()
        categoriePartenairesSelected = []
        
        partenaires = Partenaire.list()
        partenairesSelected = []
                            
        categorieProduits = CategorieProduit.list()
        categorieProduitsSelected = []
                
        produits = Produit.list()
        produitsSelected = []
        
        objet.operateurComparaison = null
        objet.senseArrondissement = null
        objet.typePrixdeBase = null
        objet.typeRegle = null
       
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        	
        if(del) {
            
            categoriePartenaires = CategoriePartenaire.list()
            partenaires = Partenaire.list()
            categorieProduits = CategorieProduit.list()
            produits = Produit.list()
        }
        
        this.getFellow("lstcategoriePartenaires").clearSelection()
        categoriePartenairesSelected = []
        
        this.getFellow("lstpartenaires").clearSelection()
        partenairesSelected = []// = new ArrayList()
                    	
        this.getFellow("lstcategorieProduits").clearSelection()
        categorieProduitsSelected = []
        
        this.getFellow("lstproduits").clearSelection()
        produitsSelected = []// = new ArrayList()
        
        objet.operateurComparaison = null
        objet.senseArrondissement = null
        objet.typePrixdeBase = null
        objet.typeRegle = null
                    
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        	
        objet.categoriePartenaires = categoriePartenairesSelected
        this.getFellow("lstcategoriePartenaires").clearSelection()
        categoriePartenairesSelected = []
        
        objet.partenaires = partenairesSelected
        this.getFellow("lstpartenaires").clearSelection()
        partenairesSelected = []// = new ArrayList()
                   
        objet.categorieProduits = categorieProduitsSelected
        this.getFellow("lstcategorieProduits").clearSelection()
        categorieProduitsSelected = []
        
        objet.produits = produitsSelected
        this.getFellow("lstproduits").clearSelection()
        produitsSelected = []// = new ArrayList()
       
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        
        def binderCategoriePartenaires = new AnnotateDataBinder(this.getFellow("lstcategoriePartenaires"))
        categoriePartenairesSelected = objetSelected.categoriePartenaires.toList()
        
        if (categoriePartenaires != null && categoriePartenairesSelected != null) {
            categoriePartenaires = categoriePartenaires.toList()
            CategoriePartenaire.removeAll(categoriePartenaires, categoriePartenairesSelected)
        }
        binderCategoriePartenaires.loadAll()
        
        def binderpartenaires = new AnnotateDataBinder(this.getFellow("lstpartenaires"))
        partenairesSelected = objetSelected.partenaires.toList()
        if (partenaires != null && partenairesSelected != null) {
            //partenaires = partenaires.toList()
            Partenaire.removeAll(partenaires, partenairesSelected)
            
        }
        binderpartenaires.loadAll()		
                    
        def binderCategorieProduits = new AnnotateDataBinder(this.getFellow("lstcategorieProduits"))
        categorieProduitsSelected = objetSelected.categorieProduits.toList()
        if (categorieProduits != null && categorieProduitsSelected != null) {
            categorieProduits = categorieProduits.toList()
            CategorieProduit.removeAll(categorieProduits, categorieProduitsSelected)
        }
        binderCategorieProduits.loadAll()
        
        def binderproduits = new AnnotateDataBinder(this.getFellow("lstproduits"))
        produitsSelected = objetSelected.produits.toList()
        if (produits != null && produitsSelected != null) {
            //produits = produits.toList()
            Produit.removeAll(produits, produitsSelected)
        }
        binderproduits.loadAll()		
    }
    
//    def removeAll(listResult, listObject) {
//        if (listResult == null || listResult.size()<1 || listObject == null || listObject.size()<1 )
//            return false
//            
//        def listResultfinal = listResult.toList()
//        listResultfinal.each { objBasic ->
//            listObject.each {
//                if (objBasic.code == it.code) {
//                    listResult.remove(objBasic)
//                }
//            }
//        }
//        if (listResultfinal.size() > listResult.size()) {
//            listResult = listResultfinal
//            return true
//        }
//    }
    
    def actualiserListes(id) {
        
        
        switch(id) {
            case 1 : 
            baseCtgrPrtSelected = []
            baseCtgrPrtAbnSelected = []
            def l1 = getFellow("lstcategoriePartenaires")
            def binder1 = new AnnotateDataBinder(l1)
            binder1.loadAll()
            l1.clearSelection()
            def l11 = getFellow("lstcategoriePartenairesAbonnesRegle")
            def binder11 = new AnnotateDataBinder(l11)
            binder11.loadAll()
            l11.clearSelection()
            break
            
            case 2 :
            basePrtSelected = []
            basePrtAbnSelected = []
            def l2 = getFellow("lstpartenaires")
            def binder2 = new AnnotateDataBinder(l2)
            binder2.loadAll()
            l2.clearSelection()
            def l22 = getFellow("lstpartenairesAbonnesRegle")
            def binder22 = new AnnotateDataBinder(l22)
            binder22.loadAll()
            l22.clearSelection()
            break
            
            case 3 :
            baseCtgrPrdSelected = []
            baseCtgrPrdAbnSelected = []
            def l3 = getFellow("lstcategorieProduits")
            def binder3 = new AnnotateDataBinder(l3)
            binder3.loadAll()
            l3.clearSelection()
            def l33 = getFellow("lstcategorieProduitsAbonnesRegle")
            def binder33 = new AnnotateDataBinder(l33)
            binder33.loadAll()
            l33.clearSelection()
            break
            
            case 4 :
            basePrdSelected = []
            basePrdAbnSelected = []
            def l4 = getFellow("lstproduits")
            def binder4 = new AnnotateDataBinder(l4)
            binder4.loadAll()
            l4.clearSelection()
            def l44 = getFellow("lstproduitsAbonnesRegle")
            def binder44 = new AnnotateDataBinder(l44)
            binder44.loadAll()
            l44.clearSelection()
            break
        }
    }
        
    def choisirTout(id) {
        if(id == 1 && categoriePartenaires.size() == 0)
        return
        if(id == 2 && partenaires.size() == 0)
        return
        if(id == 3 && categorieProduits.size() == 0)
        return
        if(id == 4 && produits.size() == 0)
        return
                   
        switch(id) {
            
            case 1 : 
            categoriePartenairesSelected.addAll(categoriePartenaires)
            categoriePartenaires = []
            break
            
            case 2 : 
            partenairesSelected.addAll(partenaires)
            partenaires = []
            break
            
            case 3 :
            categorieProduitsSelected.addAll(categorieProduits)
            categorieProduits = []
            break
            
            case 4 :
            produitsSelected.addAll(produits)
            produits = []
            break
        }
        actualiserListes(id)
    }
    
    def choisirDesElements(id) {
        if(id == 1 && baseCtgrPrtSelected.size() == 0)
        return
        if(id == 2 && basePrtSelected.size() == 0)
        return
        if(id == 3 && baseCtgrPrdSelected.size() == 0)
        return
        if(id == 4 && basePrdSelected.size() == 0)
        return
                   
        switch(id) {
            case 1 : 
            categoriePartenairesSelected.addAll(baseCtgrPrtSelected)
            CategoriePartenaire.removeAll(categoriePartenaires, baseCtgrPrtSelected)
            break
            case 2 : 
            partenairesSelected.addAll(basePrtSelected)
            Partenaire.removeAll(partenaires, basePrtSelected)
            break
            case 3 : 
            categorieProduitsSelected.addAll(baseCtgrPrdSelected)
            CategorieProduit.removeAll(categorieProduits, baseCtgrPrdSelected)
            break
            case 4 :
            produitsSelected.addAll(basePrdSelected)
            Produit.removeAll(produits, basePrdSelected)
            break
        }
        actualiserListes(id)
    }
    
    def enleverDesElements(id) {
        if(id == 1 && baseCtgrPrtAbnSelected.size() == 0)
        return
        if(id == 2 && basePrtAbnSelected.size() == 0)
        return
        if(id == 3 && baseCtgrPrdAbnSelected.size() == 0)
        return
        if(id == 4 && basePrdAbnSelected.size() == 0)
        return
                   
        switch(id) {
            case 1 : 
            categoriePartenaires.addAll(baseCtgrPrtAbnSelected)
            CategoriePartenaire.removeAll(categoriePartenairesSelected, baseCtgrPrtAbnSelected)
            break
            case 2 : 
            partenaires.addAll(basePrtAbnSelected )
            Partenaire.removeAll(partenairesSelected, basePrtAbnSelected)
            break
            case 3 : 
            categorieProduits.addAll(baseCtgrPrdAbnSelected)
            CategorieProduit.removeAll(categorieProduitsSelected, baseCtgrPrdAbnSelected)
            break
            case 4 :
            produits.addAll(basePrdAbnSelected)
            Produit.removeAll(produitsSelected, basePrdAbnSelected)
            break
        }
        actualiserListes(id)
    }
    
    def enleverTout(id) {
        if(id == 1 && categoriePartenairesSelected.size() == 0)
        return
        if(id == 2 && partenairesSelected.size() == 0)
        return
        if(id == 3 && categorieProduitsSelected.size() == 0)
        return
        if(id == 4 && produitsSelected.size() == 0)
        return
                         
        switch(id) {
            case 1 : 
            categoriePartenaires.addAll(categoriePartenairesSelected)
            categoriePartenairesSelected = []
            break
            case 2 : 
            partenaires.addAll(partenairesSelected)
            partenairesSelected = []
            break
            case 3 : 
            categorieProduits.addAll(categorieProduitsSelected)
            categorieProduitsSelected = []
            break
            case 4 :
            produits.addAll(produitsSelected)
            produitsSelected = []
            break           
        }
        actualiserListes(id)
    }
    
}

