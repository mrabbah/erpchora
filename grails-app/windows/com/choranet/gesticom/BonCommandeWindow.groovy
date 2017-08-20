
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
 * BonCommande Window Object
 **/
class BonCommandeWindow extends SuperWindow {
    
    def messageDocumentService
    def securitestockService
    def paternCompteurService
    def reglePrixService
    def partenaireService
    def produitService
    def devisService
    def bonCommandeService
    def bonPretService
    def jasperService
    def paiementService
    def parametrageService
    /**
     * Logger de la class BonCommandeWindow
     **/
    private Log logger = LogFactory.getLog(BonCommandeWindow.class)
    
    /**
     * liste de bonCommandes
     **/
    def bonCommandes	
    /**
     * bonCommandes  selectionn�
     **/
    def bonCommandesSelected
                
    /**
     * liste de partenaire
     **/	
    def partenaires	
    
    def quantiteProduitEnStock = 0
                
    def ligneProduit
    
    def compteurSequence = 1
    
    def produits
    
    def nouveauPartenaire
    
    def type
    
    def bonPret
    
    def listedevis  
    
    def listeBonPret
    
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
    def produitPerissableManaged = false
    
    def sortedLigneProduits
    
    /**
     * Constructeur
     **/
    public BonCommandeWindow (partenaireService, paternCompteurService, bonCommandeService, devisService, parametrageService) {
        super(BonCommande.class, 12, true, false)
        this.type = Executions.getCurrent().getParameter("type")
        this.bonPret = Executions.getCurrent().getParameter("bonPret").equals("true")
        this.partenaireService = partenaireService
        this.paternCompteurService = paternCompteurService
        this.bonCommandeService = bonCommandeService
        this.devisService = devisService
        this.parametrageService = parametrageService
        specialInitialiserAssociation()
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
        produitPerissableManaged = parametrageService.isProduitPerissableManaged()
        
        produits = Produit.list().sort{it.designation}
        ligneProduit = new LigneProduit()
        objet.type = this.type
        objet.bonPret = this.bonPret
        if(bonPret) {
            objet.numBC = paternCompteurService.getProchainNumBp()
        } else {
            objet.numBC = paternCompteurService.getProchainNumBc()
        }        
        objet.date = new Date()
        if(type.equals("VENTE")) {
            partenaires = partenaireService.getClients()    
        } else {
            partenaires = partenaireService.getFournisseurs()
        }
        sortedLigneProduits = []
        objet.ligneProduits = []
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
        if(bonPret) {
            listeBonPret = BonPret.list()
        }
        filtre.bonPret = bonPret
        filtre.type = type
        filtre.livree = null
        filtre.paye = null
        filtre.retourPaye = null
        filtre.avecRetour = null
        filtre.totalttcRetour = null
        def map
        if(attributsAFiltrer == null) {
            map = getService().filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, maxNb)
        } else {
            map = getService().filtrer(clazz, filtre, attributsAFiltrer, sortedHeader, sortedDirection, ofs, maxNb)
        }
        tailleListe = map["tailleListe"]
        listeObjets = map["listeObjets"]
    }
    
    
    protected SuperService getService() {
        return this.bonCommandeService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Bon Commandes"
        if(bonPret) {
            titrerapport = "Rapport des Bons Prêts"
        }
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_BonCommandes.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_BonCommandes.pdf"
        if(bonPret) {
            nom_fichier = "rapport_des_BonsPrets.pdf"
        }
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
        String titrerapport = "Rapport des Bon Commandes"
        if(bonPret) {
            titrerapport = "Rapport des Bons Prêts"
        }
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_BonCommandes.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_BonCommandes.xls"
        if(bonPret) {
            nom_fichier = "rapport_des_BonsPrets.xls"
        }
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
    
    def partenaireSelectionner() {
        listedevis = devisService.getDevisNonLiesBcLiesPartenaire(type, objet.partenaire)   
        def binderligneProduits = new AnnotateDataBinder(this.getFellow("lstdevis"))
        binderligneProduits.loadAll()   
    }
    def devisSelectionner(event) {
        def item = event.getReference()        
        if(item.isSelected() && !getFellow("btnUpdate").visible) {
            def devis = item.getValue()            
            for(lp in devis.ligneProduits) {
                def newlp = new LigneProduit()
                newlp.quantite = lp.quantite
                newlp.remise = lp.remise
                newlp.prix = lp.prix
                newlp.prixDeduit = lp.prixDeduit
                newlp.type = this.type
                newlp.sequenceLigne = compteurSequence - 1
                compteurSequence++
                newlp.produit = lp.produit
                newlp.entrepot = lp.entrepot
                objet.ligneProduits.add(newlp);
            }
            ligneProduit.sequenceLigne = compteurSequence; 
            new AnnotateDataBinder(this.getFellow("fieldsequenceLignebis")).loadAll()
            def binderligneProduits = new AnnotateDataBinder(this.getFellow("lstligneProduits"))
            //objet.ligneProduits = objet.ligneProduits.sort{it.sequenceLigne}
            binderligneProduits.loadAll()            
        } 
    }
    def addLigneProduit() {
        valideLigneProduit = false
        ligneProduit.mettreAjourEntrepot()
        objet.ligneProduits.add(ligneProduit)
        ligneProduit = new LigneProduit();
        ligneProduit.type= this.type;
        //
        ligneProduit.sequenceLigne = compteurSequence++
        sortedLigneProduits = objet.ligneProduits.sort{it.sequenceLigne};
        new AnnotateDataBinder(this.getFellow("lstligneProduits")).loadAll()
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
        sortedLigneProduits = objet.ligneProduits.sort{it.sequenceLigne};
        new AnnotateDataBinder(this.getFellow("lstligneProduits")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTotalHt")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTva")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTtc")).loadAll()
    }
    
    def updateLigneProduit() {
        sortedLigneProduits = objet.ligneProduits.sort{it.sequenceLigne};
        new AnnotateDataBinder(this.getFellow("lstligneProduits")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTotalHt")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTva")).loadAll()
        new AnnotateDataBinder(this.getFellow("fieldTtc")).loadAll()
    }
    
    def calculerPrixDeduit() {
        //        if(type.equals("VENTE")) {
        //            ligneProduit.prix = ligneProduit.produit.prixVenteStandard
        //        } else {
        //            ligneProduit.prix = ligneProduit.produit.prixAchat
        //        }
        
        //def regUsed = reglePrixService.getUsedRegle(objet.partenaire, ligneProduit)
        ligneProduit.prixDeduit = reglePrixService.getPrixDeduit(objet.partenaire, ligneProduit)
        //, regUsed)
        
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
    
    def updateRemise(prixdeduit, currentLigneprod) {
        ligneProduit = currentLigneprod
        if (ligneProduit.prix < prixdeduit) 
        ligneProduit.remise = 0;
        else ligneProduit.remise = 100 * (1 - prixdeduit / ligneProduit.prix);
        ligneProduit.prixDeduit = prixdeduit    
        new AnnotateDataBinder(this.getFellow("lstligneProduits")).loadAll()
    }
    
    def updateRemiseBis(prixdeduit) {
        if (ligneProduit.prix < prixdeduit) 
        ligneProduit.remise = 0;
        else 
        ligneProduit.remise = 100 * (1 - prixdeduit / ligneProduit.prix);
        new AnnotateDataBinder(this.getFellow("fieldremisebis")).loadAll()
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
        if(bonPret) {
            this.getFellow("btnValider").visible = visible
        }
    }
    
    def fermer() {
        cancel()
        this.getFellow("westPanel").open = false
    }
    
    def cancel() {        
        objet = clazz.newInstance()
        
        ligneProduit = new LigneProduit()
        objet.type = this.type
        objet.bonPret = this.bonPret
        if(bonPret) {
            objet.numBC = paternCompteurService.getProchainNumBp()
        } else {
            objet.numBC = paternCompteurService.getProchainNumBc()
        }        
        objet.date = new Date()
        sortedLigneProduits = []
        objet.ligneProduits = []
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
        
        listedevis = null
        new AnnotateDataBinder(this.getFellow("lstdevis")).loadAll()
        
        rafraichirField()
        activerBoutons(false)
        annulerSelection()
    }
    
    def add() {
        try {  
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
        compteurSequence = bonCommandeService.getSequenceSuivante(objet);
        ligneProduit = new LigneProduit()
        ligneProduit.type= this.type;
        ligneProduit.sequenceLigne = compteurSequence++;
        
        this.getFellow("gridAjout").visible = true
        objet.partenaire = Partenaire.findById(objet.partenaire.id)
        new AnnotateDataBinder(this.getFellow("copartenaires")).loadAll()
        def band = this.getFellow("band")
        band.value = objet.partenaire.raisonSociale
        band.close()
        band.disabled = true;
        new AnnotateDataBinder(band).loadAll()
        sortedLigneProduits = objet.ligneProduits.sort{it.sequenceLigne};
        new AnnotateDataBinder(this.getFellow("lstligneProduits")).loadAll()
        
        listedevis = devisService.getDevisNonLiesBcEtLies(objet)
        new AnnotateDataBinder(this.getFellow("lstdevis")).loadAll()

        rafraichirField()
        activerBoutons(true)
    }
    
    def update() {
        try {
            objet.livree = objet.trans_livree
            objet = getService().update(objet)
            if(!this.bonPret) {
                if(type.equals("VENTE")) {
                    objet = paiementService.updateEtatPaiementBonCommande(objet, true)
                } else if(type.equals("ACHAT")) {
                    objet = paiementService.updateEtatPaiementBonCommande(objet, false)
                }
            }
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
    
    def validerBp() {
        def bp = new BonPret()    
        bp.numBP = objet.numBC
        bp.type = objet.type
        bp.date = objet.date
        bp.reference = objet.reference
        bp.partenaire = objet.partenaire
        bp.bonCommande = objet
        bp.utilisateur = objet.utilisateur
        bp.deviss = objet.deviss
        for(lp in objet.ligneProduits) {
            def newLp = new LigneProduit()
            newLp.sequenceLigne = lp.sequenceLigne
            newLp.quantite = lp.quantite
            newLp.quantiteLivree = lp.quantiteLivree
            newLp.remise = lp.remise
            newLp.prix = lp.prix
            newLp.prixDeduit = lp.prixDeduit
            newLp.type = lp.type
            newLp.produit = lp.produit
            newLp.entrepot = lp.entrepot
            //lp.quantiteLivree = lp.quantite
            bp.addToLigneProduits(newLp)    
        }
        //objet.ligneProduits = objet.ligneProduits.sort{it.sequenceLigne}
        bonPretService.save(bp)
        def numeroBc = paternCompteurService.getProchainNumBcEtIncrementer()
        objet.numBC = numeroBc
        objet.bonPret = false
        bonCommandeService.update(objet)
        this.rafraichirList()          
        this.cancel()
        Messagebox.show("Le bon de commande numéro " + numeroBc + " a été créer avec succès", "Info", Messagebox.OK, Messagebox.INFORMATION)
    }
    
    def genererDocument(Boolean defaut,Boolean avecEntetePied,Boolean sansEntetePied, Boolean avecCachet, Boolean avecCopie, Boolean avecDestinataire, String destinataire) {
        
        def typedocument
        if(!bonPret) {
            typedocument = messageDocumentService.getMessageParType("BON_COMMANDE",type);
        }  else {
            typedocument = messageDocumentService.getMessageParType("BON_PRET",type);
        }      
        def DocumentSubReport = Utilitaire.getDocumentSubReport(getClass())
        def document = sortedLigneProduits
        def societe = session.societe;
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
                
        def nomsociete = societe.raisonSociale ;
        def titrerapport = "Bon de commande"
        if(bonPret) {
            titrerapport = "Bon de Prêt"
        }
        def adressesociete = (societe.adresse == null)?"":societe.adresse ;
        def codePostalesociete = societe.codePostale;
        def villesociete = (societe.ville == null)?"":societe.ville;
        def payssociete = (societe.pays == null)?"":societe.pays;
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
        
        String champ1Nom = "N° BC"
        if(bonPret) {
            champ1Nom = "N° BP"
        }
        String champ1Valeur  = objet.numBC
        String champ1Date = DateUtil.convertDateToStringByPattern("dd-MM-yyyy",objet.date)
        
        String champ2Nom = "N° Devis"
        String champ2Valeur  
        String champ2Date 
        
        String champ3Nom 
        String champ3Valeur  
        String champ3Date 
        
        String entete = typedocument.entete
        String pied = typedocument.pied
        
        double autresfrais = 0
        double totalht = objet.trans_totalht
        double totaltva = objet.trans_totaltva 
        double totalttc = objet.trans_totalttc
        
        double TotalDocument = totalttc + autresfrais
        
        Integer apresLaVergule = Utilitaire.getNbApresLaVirgule(TotalDocument)
        
        String TotalDocumentEncaractere = FrenchNumberToWords.convert((int)TotalDocument)                         
       
        String arretermessage = "<b>Arrêté le présent bon de commande à la somme de :</b> <br /> " + TotalDocumentEncaractere + " dirhams";        
        if(apresLaVergule != 0) {
            String apresLaVerguleEncarac = FrenchNumberToWords.convert(apresLaVergule)
            arretermessage += " " + apresLaVerguleEncarac + " centime(s)"             
        }

        if(bonPret) {
            arretermessage = ""
        }
        
        if (objet.deviss != null && objet.deviss.size() > 1) {

            champ2Valeur = "Plusieurs Devis";
        }
        else if (objet.deviss != null && objet.deviss.size() == 1) {
            
            //            def devis = objet.deviss[0]
            //            champ2Valeur = devis.numDevis
            //            champ2Date = DateUtil.convertDateToStringByPattern("dd-MM-yyyy",devis.date)
            
            objet.deviss.each {
                champ2Valeur = it.numDevis
                champ2Date = DateUtil.convertDateToStringByPattern("dd-MM-yyyy",it.date)
            }
            
            
        } else {
            champ2Nom = ""
        }


     
        def reportDef = new JasperReportDef(name:JasperReportname,
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : [objet],
            parameters : ['nomsociete' : nomsociete,'titrerapport' : titrerapport , 'adressesociete' : adressesociete,'codePostalesociete' : codePostalesociete,'villesociete' : villesociete, 'payssociete' : payssociete, 'telephonesociete':telephonesociete,'emailsociete': emailsociete,'faxsociete' : faxsociete,'rcsociete' :rcsociete ,'idfsociete':idfsociete,'sitesociete':sitesociete ,'cnsssociete':cnsssociete,'patentesociete':patentesociete,'nomclient' :nomclient,'telclient':telclient,'faxclient':faxclient,'adresseclient':adresseclient,'villeclient':villeclient,'paysclient':paysclient,'slogan':slogan,'arretermessage':arretermessage,'autresfrais':autresfrais,'champ1Nom':champ1Nom,'champ1Valeur':champ1Valeur,'champ1Date':champ1Date,'champ2Nom':champ2Nom,'champ2Valeur':champ2Valeur,'champ2Date':champ2Date,'champ3Nom':champ3Nom,'champ3Valeur':champ3Valeur,'champ3Date':champ3Date,'entete':entete,'pied':pied,'document':document,'DocumentSubReport':DocumentSubReport,'Image' : image,'totalht' :totalht,'totaltva' :totaltva,'totalttc' :totalttc, 'ImageEntetePied':imageEntetePied,'ImageCachet': imageCachet, 'ImageCopie':imageCopie,'avecHeader':avecHeader, 'avecFooter' : avecFooter,'avecDetails':avecDetails,'avecEntetePied':avecEntetePied,'avecCachet':avecCachet,'avecCopie':avecCopie, 'avecDestinataire' : avecDestinataire, 'destinataire': destinataire]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Documents_"+objet.numBC+"_"+nomclient+".pdf"
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
        new AnnotateDataBinder(this.getFellow("winProduit").getFellow("lstProduitRecherche")).loadAll()
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
            Messagebox.show("Impossible d'ajouter le nouveau produit, vérifier les données saisies", "", Messagebox.OK, Messagebox.ERROR)
        }
    }
    
}

