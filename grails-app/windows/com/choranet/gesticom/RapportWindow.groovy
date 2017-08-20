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
import com.choranet.gesticom.util.*
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo
import org.springframework.web.context.request.RequestContextHolder

/**
 * Rapport Window Object
 **/
class RapportWindow extends Window {
    def bonCommandeService
    def paiementService
    def partenaireService
    def jasperService 

    def fournisseurs
    def clients
    def partenaireC
    def partenaireF
    
    def partenaireFacture
    
    Date datedebutCAFournisseurs
    Date datefinCAFournisseurs
    Date datedebutCAFournisseur
    Date datefinCAFournisseur
    Date datedebutCAClients
    Date datefinCAClients
    Date datedebutCAClient
    Date datefinCAClient
    Date datedebutVAFournisseurs
    Date datefinVAFournisseurs
    Date datedebutVAFournisseur
    Date datefinVAFournisseur
    Date datedebutVAClients
    Date datefinVAClients
    Date datedebutVAClient
    Date datefinVAClient
    Date datedebutFacture
    Date datefinFacture
    Date datedebutFacturePartenaire
    Date datefinFacturePartenaire
    def type
    def typePaiements = ["Tout", "Paiements reglés", "Paiements non reglés"] 
    def typePaiementSelected 
    
    def session 
    /**
     * Logger de la class RapportWindow
     **/
    private Log logger = LogFactory.getLog(RapportWindow.class)
	
    /**
     * Constructeur
     **/
    
    public RapportWindow (bonCommandeService, paiementService, partenaireService) {
        this.bonCommandeService = bonCommandeService
        this.paiementService = paiementService 
        this.partenaireService = partenaireService
        fournisseurs = partenaireService.getFournisseurs()
        clients = partenaireService.getClients()
        partenaireC = null
        partenaireF = null
               
    }
        
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    //    def initialiserAssociation() {
    //        
    //        partenaires = Partenaire.list()
    //        partenaire = null// = new ArrayList()
    //                    
    //    }
    
    def genererCA(boolean AP, String type) { 
        session = RequestContextHolder.currentRequestAttributes().getSession()
        
        // AP : recherche par partenaire 
        // type : VENTE ou ACHAT
        
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport chiffre d'affaire "
        
        Date dateDebut 
        Date dateFin 
        
        String partenaire
        
        def document
                      
        if (AP == true && type == "ACHAT" ) { 
           
            document  = paiementService.getPaiementsByPartenaireBetwen(type,partenaireF,datedebutCAFournisseur,datefinCAFournisseur)
            dateDebut = datedebutCAFournisseur
            dateFin = datefinCAFournisseur
            partenaire = partenaireF
            
        }
        else if (AP == true && type == "VENTE" ) {             
            document  = paiementService.getPaiementsByPartenaireBetwen(type,partenaireC,datedebutCAClient,datefinCAClient)
            dateDebut = datedebutCAClient
            dateFin = datefinCAClient
            partenaire = partenaireC
        }
        else if (AP == false && type == "ACHAT" ) { 
            document  = paiementService.getPaiementsBetwen(type,datedebutCAFournisseurs,datefinCAFournisseurs)
            dateDebut = datedebutCAFournisseurs
            dateFin = datefinCAFournisseurs
            partenaire = "Liste_Fournisseurs"
        }
        else if (AP == false && type == "VENTE" ) { 
            document  = paiementService.getPaiementsBetwen(type,datedebutCAClients,datefinCAClients)
            dateDebut = datedebutCAClients
            dateFin = datefinCAClients
            partenaire = "Liste_Clients"
        }        
         
        def reportDef = new JasperReportDef(name:'rapport_des_Chiffresaffaires.jasper',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : document,
            parameters : ['nomsociete' : nomsociete,'titrerapport' : titrerapport, 'dateDebut' : dateDebut, 'dateFin' : datefinCAClients]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_CA_" + partenaire + "_" + dateDebut + "_" + dateFin +".pdf"
        //Filedownload.save(bit, "application/file", nom_fichier);
        Media amedia = new AMedia(nom_fichier, "pdf", "application/pdf", bit);
        Map map = new HashMap();
        map.put("content", amedia);
        Executions.createComponents("/zul/util/pdfviewer.zul", null, map).doModal();
    }
 
    def genererVentesAchats(AP,type) { 
        session = RequestContextHolder.currentRequestAttributes().getSession()
        // AP : recherche par partenaire 
        // type : VENTE ou ACHAT
     
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport "
        def SubReportBonCommandesventesachatsDetails = Utilitaire.getSubReportBonCommandesventesachatsDetails(getClass())
        def ligneProduits 
        def document 
        
        if (AP == true && type == "ACHAT" ) {  
            document =  bonCommandeService.getBonCommandeByPartenaireBetwen(type,partenaireF,datedebutVAFournisseur,datefinVAFournisseur)
        }
        else if (AP == true && type == "VENTE" ) {  
            document =  bonCommandeService.getBonCommandeByPartenaireBetwen(type,partenaireC,datedebutVAClient,datefinVAClient)  
        }
        else if (AP == false && type == "ACHAT" ) {  
            document =  bonCommandeService.getBonCommandeBetwen(type,datedebutVAFournisseurs,datedebutVAFournisseurs)  
        }
        else if (AP == false && type == "VENTE" ) {  
            document =  bonCommandeService.getBonCommandeBetwen(type,datedebutVAClients,datedebutVAClients)  
        }
        
        ligneProduits = document.ligneProduits
        
        def reportDef = new JasperReportDef(name:'rapport_des_BonCommandes_ventesachats.jasper',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : document,
            parameters : ['nomsociete' : nomsociete,'titrerapport' : titrerapport,'SubReportBonCommandesventesachatsDetails' : SubReportBonCommandesventesachatsDetails, 'ligneProduits' : ligneProduits]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_VA_"+".pdf"
        //Filedownload.save(bit, "application/file", nom_fichier);
        Media amedia = new AMedia(nom_fichier, "pdf", "application/pdf", bit);
        Map map = new HashMap();
        map.put("content", amedia);
        Executions.createComponents("/zul/util/pdfviewer.zul", null, map).doModal();
    }
    
    def genererPaiementFacture(){
        
        session = RequestContextHolder.currentRequestAttributes().getSession()        
        
        String nomsociete = session.societe.raisonSociale             
        
        String partenaire
        
        String statutPaiementPar = "1" 
        String statutPaiementTitre = "Tout"
        
        if (typePaiementSelected){
            statutPaiementTitre = typePaiementSelected
            
            if(typePaiementSelected == "Tout" ){
                statutPaiementPar = "1"
       
            }
            else if (typePaiementSelected == "Paiements reglés" ){
                statutPaiementPar = "Non Réglé"
            
            }
            else if (typePaiementSelected == "Paiements non reglés" ) {
                statutPaiementPar = "Réglé"
                
            }
        }
            
        String titrerapport = "Paiement par facture type ["   +statutPaiementTitre + "]"
        
        def reportDef = new JasperReportDef(name:'rapport_des_Factures_Paiements.jasper',
            fileFormat:JasperExportFormat.PDF_FORMAT,            
            parameters : ['nomsociete' : nomsociete,'titrerapport' : titrerapport, 'datedebut' : datedebutFacture, 'datefin' : datefinFacture, 'statutPaiement' : statutPaiementPar]
        )
        
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_factures_du" + datedebutFacture.format('dd_MM_yyyy')+ "_au_" + datefinFacture.format('dd_MM_yyyy')+ ".pdf"
        //Filedownload.save(bit, "application/file", nom_fichier);
        Media amedia = new AMedia(nom_fichier, "pdf", "application/pdf", bit);
        Map map = new HashMap();
        map.put("content", amedia);
        Executions.createComponents("/zul/util/pdfviewer.zul", null, map).doModal();
    }
    
    def genererPaiementFactureEtPartenaire(){
        
        if (partenaireFacture){
            
            session = RequestContextHolder.currentRequestAttributes().getSession()        
        
            String nomsociete = session.societe.raisonSociale
        
               
        
            String statutPaiementPar = "1" 
            String statutPaiementTitre = "Tout"
        
            if (typePaiementSelected){
                statutPaiementTitre = typePaiementSelected
            
                if(typePaiementSelected == "Tout" ){
                    statutPaiementPar = "1"
       
                }
                else if (typePaiementSelected == "Paiements reglés" ){
                    statutPaiementPar = "Non Réglé"
            
                }
                else if (typePaiementSelected == "Paiements non reglés" ) {
                    statutPaiementPar = "Réglé"
                
                }
            }
            
            String titrerapport = "Paiement par facture- type paiement ["   + statutPaiementTitre + "]"
                       
            def reportDef = new JasperReportDef(name:'rapport_des_FacturesPartenaire_Paiements.jasper',
                fileFormat:JasperExportFormat.PDF_FORMAT,            
                parameters : ['nomsociete' : nomsociete,'titrerapport' : titrerapport, 'datedebut' : datedebutFacturePartenaire, 'datefin' : datefinFacturePartenaire, 'partenaire' : partenaireFacture.id, 'statutPaiement' : statutPaiementPar]
            )
        
            def bit = jasperService.generateReport(reportDef).toByteArray()
            def nom_fichier = "rapport_factures_" + partenaireFacture + "_du_" + datedebutFacturePartenaire.format('dd_MM_yyyy')+ "_au_" + datefinFacturePartenaire.format('dd_MM_yyyy') + ".pdf"
            //Filedownload.save(bit, "application/file", nom_fichier);
            Media amedia = new AMedia(nom_fichier, "pdf", "application/pdf", bit);
            Map map = new HashMap();
            map.put("content", amedia);
            Executions.createComponents("/zul/util/pdfviewer.zul", null, map).doModal();
        }
    }

}