
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

/**
 * Paiement Window Object
 **/
class PaiementWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Paiement
     **/
    def paiementService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    def bonCommandeService
    /**
     * Logger de la class PaiementWindow
     **/
    private Log logger = LogFactory.getLog(PaiementWindow.class)
    
    /**
     * liste de modeReglement
     **/	
    def modeReglements	
    /**
     * modeReglement  selectionn�
     **/
    def modeReglementSelected
                
    /**
     * liste de bonCommande
     **/
    def bonCommandes	
    /**
     * bonCommande  selectionn�
     **/
    def bonCommandeSelected	
              
    def partenaires
    def partenaireSelected
    /**
     * liste de compteBancairePartenaire
     **/	
    def compteBancairePartenaires
    
    def bonsCommandesFilter
    /**
     * compteBancairePartenaire  selectionn�
     **/
    def compteBancairePartenaireSelected
                
    /**
     * liste de compteBancaire
     **/	
    def compteBancaires	
    /**
     * compteBancaire  selectionn�
     **/
    def compteBancaireSelected
      
    def type
    
    def estAvoir
    /**
     * Constructeur
     **/
    public PaiementWindow (paiementService) {
        super(Paiement.class, true, false)
        this.type = Executions.getCurrent().getParameter("type")
        logger.debug(Executions.getCurrent().getParameter("type"))
        this.estAvoir = Executions.getCurrent().getParameter("estAvoir").equals("true")
        logger.debug(Executions.getCurrent().getParameter("estAvoir"))
        this.paiementService = paiementService
        specialeInitialisation()
    }  

    protected SuperService getService() {
        return this.paiementService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Paiements"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Paiements.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Paiements.pdf"
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
        String titrerapport = "Rapport des Paiements"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Paiements.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Paiements.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    def add() {
        setDateEncaissement()
	super.add()
        bonCommandes = BonCommande.findAllByPayeAndType(false, type)	
        if(type.equals("ACHAT")) {
            if(estAvoir) {
                objet.credit = true 
                filtre.credit = true
            } else {
                objet.credit = false                    
                filtre.credit = true
            }
        } else {
            if(estAvoir) {
                objet.credit = false
                filtre.credit = true
            } else {
                objet.credit = true 
                filtre.credit = true
            }
        }
        new AnnotateDataBinder(getFellow("cobonCommandes")).loadAll()
    }
    def update() {
        setDateEncaissement()
        super.update()
        bonCommandes = BonCommande.findAllByPayeAndType(false, type)	
        if(type.equals("ACHAT")) {
            if(estAvoir) {
                objet.credit = true 
                filtre.credit = true
            } else {
                objet.credit = false                    
                filtre.credit = true
            }
        } else {
            if(estAvoir) {
                objet.credit = false
                filtre.credit = true
            } else {
                objet.credit = true 
                filtre.credit = true
            }
        }
        new AnnotateDataBinder(getFellow("cobonCommandes")).loadAll()
    }
    
    def setDateEncaissement (){
        println "++ nt " + objet.numTransaction
        println "++ date : " + objet.dateEncaissement
        objet.statut = "Non Réglé"
        
        if (objet.dateEncaissement){
            objet.statut = "Réglé"
        }
        println "+++ statut : " + objet.statut
    }
    
         
    //    def filtrer() {
    //        try {
    //            def map
    //            map = getService().filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, maxNb, this.type)
    //            tailleListe = map["tailleListe"]
    //            listeObjets = map["listeObjets"]
    //            new AnnotateDataBinder(this.getFellow("lstObjet")).loadAll()
    //        } catch (Exception ex) {
    //            logger.error(ex)
    //        }        
    //    }
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def specialeInitialisation() {
        logger.debug("Special init paiement window : " + new Date())
        modeReglements = ModeReglement.list()		
        //modeReglementSelected = modeReglements.get(0)
        modeReglementSelected = null
        logger.debug("type = " + type + " : " + new Date())
        if(type) {
            logger.debug("Recherche bons commandes par type : " + new Date())
            bonsCommandesFilter = BonCommande.findAllByType(type)
            logger.debug("Recherche bons commandes par type qui sont payé : " + new Date())
            bonCommandes = BonCommande.findAllByPayeAndType(false, type)	
            if(type.equals("ACHAT")) {
                if(estAvoir) {
                    objet.credit = true 
                    filtre.credit = true
                } else {
                    objet.credit = false                    
                    filtre.credit = true
                }
            } else {
                if(estAvoir) {
                    objet.credit = false
                    filtre.credit = true
                } else {
                    objet.credit = true 
                    filtre.credit = true
                }
            }
        } else {
            logger.debug("Liste des bons de commandes : " + new Date())
            bonsCommandesFilter = BonCommande.list()
            logger.debug("Recherche bons commandes payé : " + new Date())
            bonCommandes = BonCommande.findAllByPaye(false)	
        }
        logger.debug("Recherche Liste des partenaire : " + new Date())
        partenaires = Partenaire.list().sort{it.raisonSociale}
        partenaireSelected = null          
        
        //        compteBancairePartenaires = CompteBancaire.list()		
        //        if(compteBancairePartenaires.size() > 0)
        //        compteBancairePartenaireSelected = compteBancairePartenaires.get(0)
        //        else
        //        compteBancairePartenaireSelected = null
        logger.debug("Recherche les comptes bancaire : " + new Date())
        compteBancaires = CompteBancaireChoraClientInfo.list()		
        //        if(compteBancaires.size() > 0)
        //        compteBancaireSelected = compteBancaires.get(0)
        //        else
        compteBancaireSelected = null
        logger.debug("Liste des paiements par type : " + new Date())
        listeObjets = paiementService.getPaiements(type, estAvoir)
        logger.debug("Fin initialisation du Paiement window : " + new Date())
        
        def map
        if(attributsAFiltrer == null) {
            map = getService().filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, maxNb)
        } else {
            map = getService().filtrer(clazz, filtre, attributsAFiltrer, sortedHeader, sortedDirection, ofs, maxNb)
        }
        tailleListe = map["tailleListe"]
        listeObjets = map["listeObjets"]
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        if(type.equals("ACHAT")) {
            if(estAvoir) {
                objet.credit = true 
                filtre.credit = true
            } else {
                objet.credit = false                    
                filtre.credit = true
            }
        } else {
            if(estAvoir) {
                objet.credit = false
                filtre.credit = true
            } else {
                objet.credit = true 
                filtre.credit = true
            }
        }
        if(partenaireSelected) {
            bonCommandes = bonCommandeService.getBonCommandesNonPayePartenaire(partenaireSelected, type)
        } else {
            if(type) {
                bonCommandes = BonCommande.findAllByPayeAndType(false,type)                
            } else {
                bonCommandes = BonCommande.findAllByPaye(false)
            }
        }
        new AnnotateDataBinder(getFellow("cobonCommandes")).loadAll()                   
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        		
        objet.modeReglement = modeReglementSelected
        def bindermodeReglement = new AnnotateDataBinder(this.getFellow("comodeReglements"))
        modeReglementSelected = null   
        bindermodeReglement.loadAll()
        
        objet.bonCommande = bonCommandeSelected  
        def binderbonCommande = new AnnotateDataBinder(this.getFellow("cobonCommandes"))
        bonCommandeSelected = null
        binderbonCommande.loadAll()
                    		
        //        objet.compteBancairePartenaire = compteBancairePartenaireSelected
        //        if(compteBancairePartenaires.size() > 0) {
        //            def bindercompteBancairePartenaire = new AnnotateDataBinder(this.getFellow("cocompteBancairePartenaires"))
        //            compteBancairePartenaireSelected = compteBancairePartenaires.get(0)
        //            bindercompteBancairePartenaire.loadAll()
        //        }
        //        else
        //        compteBancairePartenaireSelected = null
                    		
        objet.compteBancaire = compteBancaireSelected
        def bindercompteBancaire = new AnnotateDataBinder(this.getFellow("cocompteBancaires"))
        compteBancaireSelected = null
        bindercompteBancaire.loadAll()
        
        objet.partenaire = partenaireSelected
        def binderpartenaire = new AnnotateDataBinder(this.getFellow("copartenaires"))
        partenaireSelected = null
        binderpartenaire.loadAll()
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
  
        def bindermodeReglement = new AnnotateDataBinder(this.getFellow("comodeReglements"))
        logger.debug("Recherche du mode de reglements selectionne")
        modeReglements = ModeReglement.list()
        modeReglementSelected = modeReglements.find{ it.id == objet.modeReglement.id }
        logger.debug("Affichage du mode de règlement du paiement selectionné")
        bindermodeReglement.loadAll()
                    						
        def binderbonCommande = new AnnotateDataBinder(this.getFellow("cobonCommandes"))
        logger.debug("Selection du bon de commande du paiement en cours")        
        bonCommandeSelected = BonCommande.findByNumBC(objet.bonCommande.numBC)
        bonCommandes = [bonCommandeSelected]
        logger.debug("affichege du mode de règlement du paiement selectionné")
        binderbonCommande.loadAll()
                    		
        //        def bindercompteBancairePartenaire = new AnnotateDataBinder(this.getFellow("cocompteBancairePartenaires"))
        //        compteBancairePartenaireSelected = compteBancairePartenaires.find{ it.id == Paiement.findById(objet.id).compteBancairePartenaire.id }
        //        bindercompteBancairePartenaire.loadAll()
                    		
        def bindercompteBancaire = new AnnotateDataBinder(this.getFellow("cocompteBancaires"))
        logger.debug("recheche du compte bancaire du paiement selectionné")
        if(objet.compteBancaire)
        compteBancaireSelected = compteBancaires.find{ it.id == objet.compteBancaire.id }
        else
        compteBancaireSelected = null
        logger.debug("affichage du compte bancaire du paiement selectionné")
        bindercompteBancaire.loadAll()
        
        if(bonCommandeSelected) { 
            logger.debug("bon command != null recherche du montant paye du bon de commande credit")
            this.getFellow("fieldMpc").value = paiementService.getMontantPayeBonCommande(bonCommandeSelected, true)
            logger.debug("bon command != null recherche du montant paye du bon de commande debit")
            this.getFellow("fieldMpd").value = paiementService.getMontantPayeBonCommande(bonCommandeSelected, false)
            logger.debug("bon command != null affichage du montant total de la bon de commande")            
            this.getFellow("fieldMbc").value = bonCommandeSelected.trans_totalttc
        }
        
       
        
        def cop = this.getFellow("copartenaires")
        def binderp = new AnnotateDataBinder(cop)
        logger.debug("recherche du partenaire du paiement selectionné")
        partenaireSelected = partenaires.find{ it.id == objet.partenaire.id }
        logger.debug("affichage du partenaire du paiement selectionné")
        binderp.loadAll()
        
        cop.readonly = true
        this.getFellow("cobonCommandes").readonly = true
        this.getFellow("fieldMontantPaye").readonly = true
        this.getFellow("cocompteBancaires").readonly = true
        logger.debug("fin actualisation des association du paiement")
    }
    
    def actualiserBonCommandes() {
        if(partenaireSelected) {
            bonCommandes = bonCommandeService.getBonCommandesNonPayePartenaire(partenaireSelected, type)
        } else {
            if(type) {
                bonCommandes = BonCommande.findAllByPayeAndType(false,type)                
            } else {
                bonCommandes = BonCommande.findAllByPaye(false)
            }
        }
        new AnnotateDataBinder(this.getFellow("cobonCommandes")).loadAll()
    }
    
    def actualiserPartenaireSelected() {
        if(!partenaireSelected && bonCommandeSelected) {
            def binderp = new AnnotateDataBinder(this.getFellow("copartenaires"))
            partenaireSelected = partenaires.find{ it.id == bonCommandeSelected.partenaire.id }
            binderp.loadAll()
        }
        if(bonCommandeSelected) {
            this.getFellow("fieldMbc").value = bonCommandeSelected.trans_totalttc
            def mpbcc = paiementService.getMontantPayeBonCommande(bonCommandeSelected, true)
            def mpbcd = paiementService.getMontantPayeBonCommande(bonCommandeSelected, false)
            this.getFellow("fieldMpc").value = mpbcc
            this.getFellow("fieldMpd").value = mpbcd
            if((type.equals("ACHAT") && estAvoir) || (type.equals("VENTE") && !estAvoir)) {
                this.getFellow("fieldMontantPaye").value = bonCommandeSelected.trans_totalttc - mpbcc
            } else {
                this.getFellow("fieldMontantPaye").value = bonCommandeSelected.trans_totalttc - mpbcd                
            }
        }
    }
    
    //    def select(){
    //        try{
    //            def bindermodeReglement = new AnnotateDataBinder(this.getFellow("comodeReglements"))
    //            modeReglements = ModeReglement.list()
    //            //modeReglementSelected = modeReglements.find{ it.id == objet.modeReglement.id }
    //            bindermodeReglement.loadAll()
    //            
    //            def binderp = new AnnotateDataBinder(this.getFellow("copartenaires"))
    //            partenaires = Partenaire.list()
    //            binderp.loadAll()
    //            super.select()
    //        } catch (Exception ex) {
    //            logger.error(ex)
    //        } 
    //        
    //    }
    
    def activerBoutons(test) {
        super.activerBoutons(test)
        if(!test) {
            this.getFellow("copartenaires").readonly = false
            this.getFellow("cobonCommandes").readonly = false
            this.getFellow("fieldMontantPaye").readonly = false
            this.getFellow("cocompteBancaires").readonly = false            
        }
    }       
}

