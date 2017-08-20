
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
import java.util.Date;
import org.zkoss.zk.ui.Executions
import com.choranet.gesticom.util.*
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo
import com.choranet.stock.Produit
import com.choranet.stock.CategorieProduit
import com.choranet.stock.UniteMesure
import com.choranet.stock.Entrepot
import com.choranet.compta.RegimeTVA

/**
 * Devis Window Object
 **/
class DevisWindow extends SuperWindow{
    
    def messageDocumentService
    
    def securitestockService
    
    def paternCompteurService
    
    def reglePrixService
    
    def partenaireService
    def produitService
    /**
     * Service pour la gestion de l'objet Devis
     **/
    def devisService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class DevisWindow
     **/
    private Log logger = LogFactory.getLog(DevisWindow.class)
    
    /**
     * liste de partenaire
     **/	
    def partenaires	
    /**
     * partenaire  selectionn�
     **/
    
    def quantiteProduitEnStock = 0
                
    /**
     * liste de objet.ligneProduits
     **/
   
    def ligneProduit
    
    def ligneProduitSelected
    
    def compteurSequence = 1
    
    def produits
    
    def nouveauPartenaire
    
    def type
    
    def valideLigneProduit = false
    
    def produitsRecherche
    def produitRechercheSelected
    def codeRecherche = ""
    def designationRecherche = ""
    def categorieRecherche
    def categoriesProduitRecherche
    def newPdt 
    def categorieProduits
    def regimeTVAs
    def uniteMesures
    def empReceptions
    /**
     * Constructeur
     **/
    public DevisWindow (partenaireService, paternCompteurService, devisService) {
        super(Devis.class, true, false)
        this.type = Executions.getCurrent().getParameter("type")
        this.partenaireService = partenaireService
        this.paternCompteurService = paternCompteurService
        this.devisService = devisService
        specialInitialiserAssociation()
    }  

    protected SuperService getService() {
        return this.devisService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Deviss"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Deviss.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Deviss.pdf"
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
        String titrerapport = "Rapport des Deviss"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Deviss.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Deviss.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def specialInitialiserAssociation() {
        
        produitsRecherche = Produit.list().sort{it.designation}
        categoriesProduitRecherche = CategorieProduit.list()
        categorieProduits = CategorieProduit.list()
        regimeTVAs = RegimeTVA.list()
        uniteMesures = UniteMesure.list()
        newPdt = new Produit()
        empReceptions = Entrepot.list()
        produits = Produit.list().sort{it.designation}
        ligneProduit = new LigneProduit()
        objet.type = this.type
        objet.numDevis = paternCompteurService.getProchainNumDevis()
        objet.date = new Date()
        if(type.equals("VENTE")) {
            partenaires = partenaireService.getClients()    
        } else {
            partenaires = partenaireService.getFournisseurs()
        }
        objet.ligneProduits = new ArrayList<LigneProduit>();
        //objet.objet.ligneProduits = objet.ligneProduits
        ligneProduit.type= this.type;
        ligneProduit.sequenceLigne = compteurSequence++;
                    
        nouveauPartenaire = new Partenaire();
        if(type.equals("VENTE")) {
            nouveauPartenaire.estClient = true;
            nouveauPartenaire.estFournisseur = false;
        } else {
            nouveauPartenaire.estClient = false;
            nouveauPartenaire.estFournisseur = true;
        }
        nouveauPartenaire.estParticulier = false;
        filtre.type = type
        def map
        if(attributsAFiltrer == null) {
            map = getService().filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, maxNb)
        } else {
            map = getService().filtrer(clazz, filtre, attributsAFiltrer, sortedHeader, sortedDirection, ofs, maxNb)
        }
        tailleListe = map["tailleListe"]
        listeObjets = map["listeObjets"]
    }
    
    def addLigneProduit() {
        valideLigneProduit = false
        ligneProduit.mettreAjourEntrepot()
        objet.ligneProduits.add(ligneProduit);
        ligneProduit = new LigneProduit();
        ligneProduit.type= this.type;
        //
        ligneProduit.sequenceLigne = compteurSequence++;
        def binderligneProduits = new AnnotateDataBinder(this.getFellow("lstligneProduits"))
        binderligneProduits.loadAll()
        new AnnotateDataBinder(this.getFellow("fieldsequenceLignebis")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldquantitebis")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldprixDeduitbis")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldprixbis")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldremisebis")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldsoustotal")).loadAll()
        new AnnotateDataBinder(this.getFellow("conewproduits")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTotalHt")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTva")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTtc")).loadAll()
    }
    
    def deleteLigneProduit(lignePdt) {
        objet.ligneProduits.remove(lignePdt)
        def binderligneProduits = new AnnotateDataBinder(this.getFellow("lstligneProduits"))
        binderligneProduits.loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTotalHt")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTva")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTtc")).loadAll()
    }
    
    def updateLigneProduit() {
        new AnnotateDataBinder(this.getFellow("fieldTotalHt")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTva")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTtc")).loadAll()
    }
                                                                        
    def calculerPrixDeduit() {
        
        ligneProduit.prixDeduit = reglePrixService.getPrixDeduit(objet.partenaire, ligneProduit)
        ligneProduit.remise = 0d
        valideLigneProduit = true
        
        //new AnnotateDataBinder(this.getFellow("lstligneProduits")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldremisebis")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldprixDeduitbis")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldsoustotal")).loadAll()
    }
    
    def calculerPrixDeduitForCurrentLigne(currentLigneprod, qty) {
        currentLigneprod.quantite = qty
        currentLigneprod.prixDeduit = reglePrixService.getPrixDeduit(objet.partenaire, currentLigneprod)
        currentLigneprod.remise = 0d
        valideLigneProduit = true
        new AnnotateDataBinder(this.getFellow("lstligneProduits")).loadAll()
        updateLigneProduit()
    }
    
    def calculerPrixDeduitWithGR(givenRemise) {
        ligneProduit.prixDeduit = reglePrixService.calculerPrixDeduit(objet.partenaire, ligneProduit, givenRemise)
      
        new AnnotateDataBinder(this.getFellow("fieldremisebis")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldprixDeduitbis")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldsoustotal")).loadAll()
    }
    
    def calculerPrixDeduitWithGR_(currentLigneprod, givenRemise) {
        ligneProduit = currentLigneprod
        ligneProduit.prixDeduit = reglePrixService.calculerPrixDeduit(objet.partenaire, ligneProduit, givenRemise)
        new AnnotateDataBinder(this.getFellow("lstligneProduits")).loadAll()
    }
    
    def updateRemiseBis(prixdeduit) {
        if (ligneProduit.prix < prixdeduit) 
        ligneProduit.remise = 0;
        else 
        ligneProduit.remise = 100 * (1 - prixdeduit / ligneProduit.prix);
        new AnnotateDataBinder(this.getFellow("fieldremisebis")).loadAll()
    }
    
    def updateRemise(prixdeduit, currentLigneprod) {
        ligneProduit = currentLigneprod
        if (ligneProduit.prix < prixdeduit) 
        ligneProduit.remise = 0;
        else ligneProduit.remise = 100 * (1 - prixdeduit / ligneProduit.prix);
        ligneProduit.prixDeduit = prixdeduit    
        new AnnotateDataBinder(this.getFellow("lstligneProduits")).loadAll()
    }
    
    def trierParSequenceLigne(listproduits) {
        def binderligneProduits = new AnnotateDataBinder(this.getFellow("lstligneProduits"))
        listproduits = devisService.trierParSequenceLigne(objet)
        binderligneProduits.loadAll()
    }
    
    def ajouterPartenaire() {
        partenaireService.save(nouveauPartenaire)
        if(type.equals("VENTE")) {
            partenaires = partenaireService.getClients()            
        } else {
            partenaires = partenaireService.getFournisseurs()
        }
        objet.partenaire = nouveauPartenaire
        new AnnotateDataBinder(this.getFellow("copartenaires")).loadAll()
        def band = this.getFellow("band")
        band.value = nouveauPartenaire.raisonSociale
        band.close()
        band.disabled = true;
        new AnnotateDataBinder(band).loadAll()
        this.getFellow("gridAjout").visible = true
        this.getFellow("btnSave").visible = true
    }
    
    def calculerQuantiteProduitEnStock() {
        if(ligneProduit.produit != null) {
            quantiteProduitEnStock = securitestockService.getQuantiteProduitEnStock(ligneProduit.produit);            
        } else {
            quantiteProduitEnStock = 0;
        }
        getFellow("lbStock").value = "la quantité actuelle du produit en stock est " + quantiteProduitEnStock;
    }
        
    def activerBoutons(visible) {
        this.getFellow("btnUpdate").visible = visible
        this.getFellow("btnDocument").visible = visible
        this.getFellow("btnDelete").visible = visible
        //            this.getFellow("btnCancel").visible = visible
        this.getFellow("btnPdf").visible = !visible
        this.getFellow("btnExcel").visible = !visible
        this.getFellow("btnSave").visible = false
        this.getFellow("btnNew").visible = !visible
        this.getFellow("westPanel").open = true        
    }
    
    def fermer() {
        cancel()
        this.getFellow("westPanel").open = false
    }
    
    //    def getLigneProduitSelected(event) {
    //        isUpdateOp = true
    //        def item = event.getReference()        
    //        if(item.isSelected())
    //        ligneProduit = item.getValue()
    //    }
    
    def cancel() {        
        objet = clazz.newInstance()
        
        ligneProduit = new LigneProduit()
        objet.type = this.type
        objet.numDevis = paternCompteurService.getProchainNumDevis()
        objet.date = new Date()
        objet.ligneProduits = new ArrayList<LigneProduit>();
        ligneProduit.type= this.type;
        compteurSequence = 1;
        ligneProduit.sequenceLigne = compteurSequence++;
                    
        nouveauPartenaire = new Partenaire();
        if(type.equals("VENTE")) {
            nouveauPartenaire.estFournisseur = false;
            nouveauPartenaire.estClient = true;            
        } else {
            nouveauPartenaire.estFournisseur = true;
            nouveauPartenaire.estClient = false;            
        }
        nouveauPartenaire.estParticulier = false;
        
        this.getFellow("gridAjout").visible = false
        this.getFellow("btnSave").visible = false
        def band = this.getFellow("band")
        band.value = null
        band.disabled = false;
        new AnnotateDataBinder(band).loadAll()
        
        objet.partenaire = null
        new AnnotateDataBinder(this.getFellow("lstligneProduits")).loadAll()
        new AnnotateDataBinder(this.getFellow("copartenaires")).loadAll()
        
        rafraichirField()
        activerBoutons(false)
        annulerSelection()
    }
    
    def add() {
        try {  
            objet.partenaire = objet.partenaire;
            getService().save(objet)
            Messagebox.show("Enregistrement effectué avec succès", "", Messagebox.OK, Messagebox.INFORMATION)
            activerBoutons(true)
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        } finally {
            rafraichirList()          
            //cancel()
        }
    }
    
    def select() {          
        objet = objetSelected	
        compteurSequence = devisService.getSequenceSuivante(objet);
        ligneProduit = new LigneProduit()
        ligneProduit.type= this.type;
        ligneProduit.sequenceLigne = compteurSequence++;
        
        this.getFellow("gridAjout").visible = true
        new AnnotateDataBinder(this.getFellow("copartenaires")).loadAll()
        def band = this.getFellow("band")
        band.value = objet.partenaire.raisonSociale
        band.close()
        band.disabled = true;
        new AnnotateDataBinder(band).loadAll()
        new AnnotateDataBinder(this.getFellow("lstligneProduits")).loadAll()

        rafraichirField()
        activerBoutons(true)
    }
    
    def update() {
        try {
            getService().update(objet)
            new AnnotateDataBinder(this.getFellow("lstObjet")).loadAll()
            Messagebox.show("Modification effectuée avec succès", "", Messagebox.OK, Messagebox.INFORMATION)
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("Problème lors de la mise à jour", "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            rafraichirList()          
            //cancel()
        }
    }
    
    def delete() {
        getService().delete(objet)
        rafraichirList()          
        cancel()
    }
    
    def genererDocument(Boolean defaut,Boolean avecEntetePied,Boolean sansEntetePied, Boolean avecCachet, Boolean avecCopie, Boolean avecDestinataire, String destinataire) {
                
        def typedocument = messageDocumentService.getMessageParType("DEVIS", type);
        def DocumentSubReport = Utilitaire.getDocumentSubReport(getClass())
        def document = objet.ligneProduits
        def societe = session.societe;
        def nomsociete = societe.raisonSociale
        def image = societe.trans_logo.getStreamData()       
        
        def imageEntetePied = societe.trans_entetepied.getStreamData()
        def imageCachet = societe.trans_cachet.getStreamData()
        def imageCopie = societe.trans_copie.getStreamData()
        
        Boolean avecDetails = true
        Boolean avecHeader  
        Boolean avecFooter 
        
        if(defaut){
            avecHeader = true   
            avecFooter = true   
        }
        if (avecEntetePied){
            avecHeader = false   
            avecFooter = false
        }
        if (sansEntetePied){
            avecHeader = false   
            avecFooter = false
        }
        
        def titrerapport = "Devis"
        def adressesociete = societe.adresse ;
        def codePostalesociete = societe.codePostale;
        def villesociete = societe.ville;
        def payssociete = societe.pays;
        def telephonesociete = societe.telephone;
        def emailsociete = societe.email;
        def faxsociete = societe.fax;
        def rcsociete = societe.rc;
        def idfsociete = societe.idF; 
        def sitesociete = societe.site; 
        def cnsssociete = societe.cnss;
        def patentesociete = societe.patente;
        
        def client = objet.partenaire;
        def nomclient = client.raisonSociale;
        def telclient = client.telephone;
        def faxclient = client.fax ;
        def adresseclient = client.adresseFacturation;
        def villeclient = (client.ville != null)?client.ville.intitule: "";
        def paysclient = (client.ville != null)?client.ville.pays.intitule: "";
        def JasperReportname
        
        JasperReportname ="rapport_des_Documents.jasper"
        
        def slogan ;
                          
        String champ1Nom = "N° Devis"
        String champ1Valeur  = objet.numDevis
        String champ1Date = DateUtil.convertDateToStringByPattern("dd-MM-yyyy",objet.date)
        
        def champ2Nom 
        def champ2Valeur  
        def champ2Date 
        
        def champ3Nom 
        def champ3Valeur  
        def champ3Date 
        
        String entete = typedocument.entete
        String pied = typedocument.pied
        
        Double autresfrais = 0;
        Double totalht = objet.trans_totalht
        Double totaltva = objet.trans_totaltva 
        Double totalttc = objet.trans_totalttc
        
        double TotalDocument = totalttc + autresfrais
        
        Integer apresLaVergule = Utilitaire.getNbApresLaVirgule(TotalDocument)
        
        String TotalDocumentEncaractere = FrenchNumberToWords.convert((int)TotalDocument)                      
       
        String arretermessage = "Arrêté le présent devis à la somme de : <br /> " + TotalDocumentEncaractere + " dirhams";
        if(apresLaVergule != 0) {
            String apresLaVerguleEncarac = FrenchNumberToWords.convert(apresLaVergule)
            arretermessage += " " + apresLaVerguleEncarac + " centime(s)"             
        }
        
        //        if (bonLivraisons !=null && bonLivraisons.size() > 1) {
        //            Chmap1Valeur = "Plusieurs BL";
        //        }
        //            else if (bonLivraisons.size() == 1) {
        //            Chmap1Valeur = bonLivraisons(0).numBL;
        //            Chmap1Date = bonLivraisons(0).date;
        //            }
     
        def reportDef = new JasperReportDef(name:JasperReportname,
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : [objet],
            parameters : ['nomsociete' : nomsociete,'titrerapport' : titrerapport , 'adressesociete' : adressesociete,'codePostalesociete' : codePostalesociete,'villesociete' : villesociete, 'payssociete' : payssociete, 'telephonesociete':telephonesociete,'emailsociete': emailsociete,'faxsociete' : faxsociete,'rcsociete' :rcsociete ,'idfsociete':idfsociete,'sitesociete':sitesociete ,'cnsssociete':cnsssociete,'patentesociete':patentesociete,'nomclient' :nomclient,'telclient':telclient,'faxclient':faxclient,'adresseclient':adresseclient,'villeclient':villeclient,'paysclient':paysclient,'slogan':slogan,'arretermessage':arretermessage,'autresfrais':autresfrais,'champ1Nom':champ1Nom,'champ1Valeur':champ1Valeur,'champ1Date':champ1Date,'champ2Nom':champ2Nom,'champ2Valeur':champ2Valeur,'champ2Date':champ2Date,'champ3Nom':champ3Nom,'champ3Valeur':champ3Valeur,'champ3Date':champ3Date,'entete':entete,'pied':pied,'document':document,'DocumentSubReport':DocumentSubReport,'Image' : image,'totalht' :totalht,'totaltva' :totaltva,'totalttc' :totalttc, 'ImageEntetePied':imageEntetePied,'ImageCachet': imageCachet, 'ImageCopie':imageCopie,'avecHeader':avecHeader, 'avecFooter' : avecFooter,'avecDetails':avecDetails,'avecEntetePied':avecEntetePied,'avecCachet':avecCachet,'avecCopie':avecCopie, 'avecDestinataire' : avecDestinataire, 'destinataire': destinataire]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Documents_"+objet.numDevis+"_"+nomclient+".pdf"
        //Filedownload.save(bit, "application/file", nom_fichier);
        Media amedia = new AMedia(nom_fichier, "pdf", "application/pdf", bit);
        Map map = new HashMap();
        map.put("content", amedia);
        Executions.createComponents("/zul/util/pdfviewer.zul", null, map).doModal();
    }
    
    
    def doModalDialogChoix(){
        this.getFellow("modalDialogChoix").visible = true
        this.getFellow("modalDialogChoix").doModal() 
    }
    def doModalDialogPdt(){
        this.getFellow("winProduit").visible = true
        this.getFellow("winProduit").doModal() 
    }
    
    def rechercher() {
        produitsRecherche = produitService.getProduitByCodeDesignationCategorie(codeRecherche, designationRecherche, categorieRecherche)
        new AnnotateDataBinder(this.getFellow("winProduit")).loadAll()
    }
    
    def utiliserProduitRecherche() {
      
        if(produitRechercheSelected) {
            if(objet.partenaire) {
                this.getFellow("winProduit").visible = false
                ligneProduit.produit = produits.find{ it.id == produitRechercheSelected.id };
                def cpdt = this.getFellow("conewproduits")
                new AnnotateDataBinder(this.getFellow("lblUm")).loadAll()
                new AnnotateDataBinder(this.getFellow("fieldprixbis")).loadAll()
                new AnnotateDataBinder(cpdt).loadAll()
                calculerPrixDeduit();
                calculerQuantiteProduitEnStock();
                this.getFellow("informationsporduit").open(cpdt, "after_start")
            } else {
                Messagebox.show("Veillez choisir déjà un partenaire", "Partenaire non sélectionné", Messagebox.OK, Messagebox.ERROR)
            }
            
        } else {
            Messagebox.show("Vous n'avez pas choisi de produit", "Produit non sélectionné", Messagebox.OK, Messagebox.ERROR)
        }
    }
    def ajouterNouveauProduit() {
        try {
            produitService.save(newPdt)
            codeRecherche = newPdt.code
            produits = Produit.list().sort{it.designation}
            new AnnotateDataBinder(this.getFellow("conewproduits")).loadAll()
            newPdt = new Produit()
            rechercher()
            Messagebox.show("Produit ajouté avec succès", "", Messagebox.OK, Messagebox.INFORMATION)
        } catch(Exception e) {
            log.error(e)
            Messagebox.show("Impossible d'ajouter le nouveau produit, vérifier les données saisies", "", Messagebox.OK, Messagebox.ERROR)
        }
    }
}

