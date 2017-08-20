
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
import com.choranet.commun.Pays
import com.choranet.commun.Ville
import com.choranet.commun.Zone
import com.choranet.compta.CompteComptable
import com.choranet.rh.Employe
import com.choranet.commun.FormeJuridique
import com.choranet.stock.ModeLivraison

import org.zkoss.zk.ui.event.Events
import org.zkoss.zk.ui.event.Event

/**
 * Partenaire Window Object
 **/
class PartenaireWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Partenaire
     **/
    def partenaireService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class PartenaireWindow
     **/
    private Log logger = LogFactory.getLog(PartenaireWindow.class)
    
    /**
     * liste de categoriePartenaires
     **/
    def categoriePartenaires
    /**
     * categoriePartenaires selectionn?
     **/
    def categoriePartenairesSelected
                
    /**
     * liste de modeRelancePaiement
     **/	
            
    def type    
    
    /**
     * liste de formeJuridique
     **/	
    def formeJuridiques	
    /**
     * formeJuridique  selectionn?
     **/
    def formeJuridiqueSelected
                
    
//    def comptes
//    def comptecomSelected
        
    def excelImporterService
    
    def compteBancaireService
    def agenceBancaireService
    def contactService
    
    def listeComptes
    def compte
    def isUpdateCompte
    def compteSelected    
    //def oldSold
    def listeContacts
    def contact
    def isUpdateContact
    def contactSelected
    
    /**
     * Constructeur
     **/
   
    public PartenaireWindow (partenaireService, compteBancaireService, agenceBancaireService, contactService) {
        super(Partenaire.class, 11, true, false)
        this.type = Executions.getCurrent().getParameter("type")
        if(type == null && arg.containsKey("type")) {
            this.type = (String) arg.get("type")
        }
        this.partenaireService = partenaireService
        this.compteBancaireService = compteBancaireService
        this.agenceBancaireService = agenceBancaireService
        this.contactService = contactService
        specialeInitialisation()
    }  

    protected SuperService getService() {
        return this.partenaireService
    }
    
    def importation(media) {
        String resultat = excelImporterService.importerPartenaires(media)
        Messagebox.show(resultat, "Notification" ,  Messagebox.OK, Messagebox.INFORMATION)
        rafraichirList()
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Partenaires"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Partenaires.jasper',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Partenaires.pdf"
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
        String titrerapport = "Rapport des Partenaires"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Partenaires.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Partenaires.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    def specialeInitialisation() {
        objet.code = partenaireService.getNextCode()
        if(type.equals("CLIENT")) {
            filtre.estClient = true
            def map
            if(attributsAFiltrer == null) {
                map = getService().filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, maxNb)
            } else {
                map = getService().filtrer(clazz, filtre, attributsAFiltrer, sortedHeader, sortedDirection, ofs, maxNb)
            }
            tailleListe = map["tailleListe"]
            listeObjets = map["listeObjets"]
        } else if(type.equals("FOURNISSEUR")) {
            filtre.estFournisseur = true
            def map
            if(attributsAFiltrer == null) {
                map = getService().filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, maxNb)
            } else {
                map = getService().filtrer(clazz, filtre, attributsAFiltrer, sortedHeader, sortedDirection, ofs, maxNb)
            }
            tailleListe = map["tailleListe"]
            listeObjets = map["listeObjets"]
        }   else {
            def map
            if(attributsAFiltrer == null) {
                map = getService().filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, maxNb)
            } else {
                map = getService().filtrer(clazz, filtre, attributsAFiltrer, sortedHeader, sortedDirection, ofs, maxNb)
            }
            tailleListe = map["tailleListe"]
            listeObjets = map["listeObjets"]
        }
        initialiserAssociation()
    }
    /**
     * Fonction qui g?re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
        
        categoriePartenaires = CategoriePartenaire.list()
        categoriePartenairesSelected = null// = new ArrayList()
                    
        formeJuridiques = FormeJuridique.list()		
        formeJuridiqueSelected = null
                    
//        comptes = CompteComptable.list()
//        comptecomSelected = null
             
    }
    /**
     * Fonction qui permet de r?-initaliser l'association au niveau de l'interface
     * @param del si c'est une r?initionalisation apr?s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        	
        if(del) {
            categoriePartenaires = CategoriePartenaire.list()
        }
        this.getFellow("lstcategoriePartenaires").clearSelection()
        categoriePartenairesSelected = null// = new ArrayList()
                    	
        if(del) {
            formeJuridiques = FormeJuridique.list()
            modeLivraisons = ModeLivraison.list()
            
//            comptes = CompteComptable.list()
        }	
        formeJuridiqueSelected = null
//        comptecomSelected = null 
    }
    /**
     * Fonction qui copie la valeur de l'association ? l'?l?ment courant
     **/
    def actualiserValeurAssociation() {
        	
        objet.categoriePartenaires = categoriePartenairesSelected
        this.getFellow("lstcategoriePartenaires").clearSelection()
        categoriePartenairesSelected = null// = new ArrayList()

        objet.formeJuridique = formeJuridiqueSelected
        formeJuridiqueSelected = null
                    		     
//        objet.compte = comptecomSelected
//        comptecomSelected = null
    }
    /**
     * Fonction qui fait la liaison entre l'association l'?l?ment selectionn? et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        logger.debug("chargement categorie partenaire")
        def bindercategoriePartenaires = new AnnotateDataBinder(this.getFellow("lstcategoriePartenaires"))
        categoriePartenairesSelected = objetSelected.categoriePartenaires
        bindercategoriePartenaires.loadAll()		

        binderformeJuridique.loadAll()
    }
    
    def activerBoutons(visible) {
        this.getFellow("btnUpdate").visible = visible
        this.getFellow("btnDelete").visible = visible
        //this.getFellow("btnCancel").visible = visible
        this.getFellow("btnPdf").visible = !visible
        this.getFellow("btnExcel").visible = !visible
        this.getFellow("btnSave").visible = !visible
        this.getFellow("btnNew").visible = !visible
        this.getFellow("westPanel").open = visible        
    }
    
    def add() {
        actualiserValeurAssociation()
        try {     
            getService().save(objet)
            Messagebox.show("Partenaire ajouté avec succès")
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("Merci d'indiquer les bons valeurs pour les champs obligatoires\n", "Ajout non disponible", Messagebox.OK, Messagebox.ERROR)
        } finally {
            objet = clazz.newInstance()
            rafraichirField()
            rafraichirList()
            activerBoutons(false)
        }
        
    }
    
    def update() {
        actualiserValeurAssociation()
        try {
            getService().update(objet)
            Messagebox.show("Mise à jour effectuée avec succès")
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("L'un des deux valeurs partenaire : " + "(" + objet.code + "," + objet.raisonSociale + ") est déjà associée à un partenaire existant, Merci de choisir d'autre valeurs", "Mise à jour non disponible", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            activerBoutons(false)
            objet = clazz.newInstance()
            rafraichirField()
            rafraichirList()
        }  
    }
    
    def rafraichirField() {
        if(objet.code == null || objet.code.equals("")) {
            objet.code = partenaireService.getNextCode()
        }
        super.rafraichirField()
    }
    
    def delete() {
        try{
            super.delete()
        }
        catch(Exception e) {
            Messagebox.show("Ce partenaire est déjà subi à des opérations antérieurs\n", "Suppression non disponible", Messagebox.OK, Messagebox.ERROR)
        }
    }
    
    
    def selectCompte() {  
        isUpdateCompte = true
        compte = compteSelected
        //oldSold = compte.solde
        rafraichirField()
    }
    
    def addCompte() {
        try {    
            //compte.soldePrevu = compte.solde
//            Messagebox.show("compte.agenceBancaire : " + compte.agenceBancaire + " \n" + "compte.partenaire : " + compte.partenaire, 
//                            "Ajout", Messagebox.OK, Messagebox.ERROR)
            compteBancaireService.save(compte)
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("Veillez renseignez toutes les valeurs requises par le système\n", "Ajout non disponible", Messagebox.OK, Messagebox.ERROR)
        } finally {
            compte = new CompteBancaire(agenceBancaire : getDefaultAgenceBancaire(), partenaire : objet);
            rafraichirField()
            rafraichirListComptes()
        }
        
    }
    
    def addOrUpdateCompte(){
        compte.partenaire = objet
        if (isUpdateCompte) {
            this.updateCompte()
            isUpdateCompte = false
        } else {
            this.addCompte()
        }
    }
    
    def updateCompte() {
        try {            
            //            if(oldSold) {
            //                def diff = compte.solde - oldSold
            //                compte.soldePrevu += diff
            //            }
            compteBancaireService.update(compte)
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("La nouvelle valeur est dèjà mentionnée pour une agance existante", "Mise à jour non disponible", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            compte = new CompteBancaire(agenceBancaire : getDefaultAgenceBancaire(), partenaire : objet);
            rafraichirField()
            rafraichirListComptes()
        }
        
    }
   
    def deleteCompte() {
        try{
            compteBancaireService.delete(compte)
        }
        catch(Exception e) {
            Messagebox.show("La présente compte est déjà associée à un compte bancaire\n", "Suppression non disponible", Messagebox.OK, Messagebox.ERROR)
        }
        compte = new CompteBancaire(agenceBancaire : getDefaultAgenceBancaire(), partenaire : objet);
        rafraichirField()
        rafraichirListComptes()
    }
    
    def rafraichirListComptes() {
        listeComptes = compteBancaireService.getCompteBancairePartenaire(objet)
        compteSelected = null
        def lst = getFellow("lstComptes")
        lst.clearSelection()
        new AnnotateDataBinder(lst).loadAll() 
    }
    
    def chargerComptes() {    
        compte = new CompteBancaire(agenceBancaire : getDefaultAgenceBancaire(), partenaire : objet);
        isUpdateCompte = false
        rafraichirListComptes() 
    }
    
    def getDefaultAgenceBancaire(){
        if(type.equals("CLIENT")) 
        return new AgenceBancaire(nom : "Agence Client")
        else  return new AgenceBancaire(nom : "Agence Fournisseur")
    }
    
    def newCompte() {
        compte = new CompteBancaire(agenceBancaire : getDefaultAgenceBancaire(), partenaire : objet);
        rafraichirField();
        rafraichirList();
        isUpdateCompte = false;
    }
    
    def selectContact() {  
        isUpdateContact = true
        contact = contactSelected
        rafraichirField()
    }
    
    def addContact() {
        try {    
            contactService.save(contact)
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("Veillez renseignez toutes les valeurs requises par le système\n", "Ajout non disponible", Messagebox.OK, Messagebox.ERROR)
        } finally {
            contact = new Contact();
            rafraichirField()
            rafraichirListContacts()
        }
        
    }
    
    def addOrUpdateContact(){
        contact.partenaire = objet
        if (isUpdateContact) {
            this.updateContact()
            isUpdateContact = false
        } else {
            this.addContact()
        }
    }
    
    def updateContact() {
        try {
            contactService.update(contact)
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("La nouvelle valeur est dèjà mentionnée pour une agance existante", "Mise à jour non disponible", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            contact = new Contact();
            rafraichirField()
            rafraichirListContacts()
        }
        
    }
   
    def deleteContact() {
        try{
            contactService.delete(contact)
        }
        catch(Exception e) {
            Messagebox.show("La présente compte est déjà associée à un compte bancaire\n", "Suppression non disponible", Messagebox.OK, Messagebox.ERROR)
        }
        contact = new Contact();
        rafraichirField()
        rafraichirListContacts()
    }
    
    def rafraichirListContacts() {
        listeContacts = contactService.getContactPartenaire(objet)
        contactSelected = null
        def lst = getFellow("lstContacts")
        lst.clearSelection()
        new AnnotateDataBinder(lst).loadAll()  
    }
    
    def chargerContacts() {    
        contact = new Contact();
        isUpdateContact = false
        rafraichirListContacts()
    }
     
    def newContact() {
        contact = new Contact();
        rafraichirField();
        rafraichirList();
        isUpdateContact = false;
    }
}

