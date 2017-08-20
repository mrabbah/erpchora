
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
import java.util.*;
import org.zkoss.zk.ui.Executions
import com.choranet.commun.SuperWindow
import com.choranet.commun.SuperService
import com.choranet.commun.ChoraClientInfo
import com.choranet.gesticom.BonLivraison
import com.choranet.gesticom.BonCommande
import com.choranet.gesticom.Partenaire

/**
 * Livraison Window Object
 **/
class LivraisonWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Livraison
     **/
    def bonlivraisons =new ArrayList<BonLivraison>()
    def bonlivraisons1 =[]
    def bonlivraisons2=[]
    def boncommandes=new ArrayList<BonCommande>()
    def partenaire=new ArrayList<Partenaire>()
    def bonlivraisonpartenaire
    
    def partenaireSelected
    def veri=false
    
    def codes=[]
    //    def bonLivraisonService
    
    /**
     * Service jasper pour la generation des rapports
     **/
    def jasperService
    /**
     * Logger de la class LivraisonWindow
     **/
    private Log logger = LogFactory.getLog(LivraisonWindow.class)
   
    /**
     * liste de bonLivraisons
     **/
    def bonLivraisons	
    /**
     * bonLivraisons  selectionn�
     **/
    def bonLivraisonsSelected
                
    /**
     * liste de modeLivraison
     **/	
    def modeLivraisons	
    /**
     * modeLivraison  selectionn�
     **/
    def modeLivraisonSelected
    
    def ligneProduits = []
    def ligneProduitsglobal=[]
    
    def partenaires
    
    def partenaireService
    def bonLivraisonService
    def livraisonService
    def paternCompteurService
    
    def bonCommandeService
    /**
     * Constructeur
     **/
    public LivraisonWindow (livraisonService, paternCompteurService, partenaireService, bonLivraisonService, bonCommandeService) {
        super(Livraison.class, true, false)
        this.livraisonService = livraisonService
        this.paternCompteurService = paternCompteurService
        this.partenaireService = partenaireService
        this.bonLivraisonService = bonLivraisonService
        this.bonCommandeService = bonCommandeService
        specialeInitialisation()
    }  

    def specialeInitialisation() {
        partenaires = Partenaire.list()
        objet.numExpedition = paternCompteurService.getProchainNumLivraison()
        modeLivraisons = ModeLivraison.list()		
        if(modeLivraisons.size() > 0) {
            modeLivraisonSelected = modeLivraisons.get(0)            
        }
        
        filtre.nbrColis = null
        filtre.poidTotal = null
        filtre.frais = null
        filtre.date = null
        def map
        if(attributsAFiltrer == null) {
            map = livraisonService.filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, maxNb)
        } else {
            map = livraisonService.filtrer(clazz, filtre, attributsAFiltrer, sortedHeader, sortedDirection, ofs, maxNb)
        }
        tailleListe = map["tailleListe"]
        listeObjets = map["listeObjets"]
    }
    def blSelectionner(event) {
        def binderligneproduits = new AnnotateDataBinder(this.getFellow("lstligneProduits"))
        def item = event.getReference()   
        def numbl = item.getValue().toString()
       


        def bl = BonLivraison.findByNumBL(numbl)
        
        def ligneproduitsafficher=[]
        //        ligneProduits=ligneproduitsafficher
        def ligneproduitbakup=[]
       
        def t=false
        if(veri==true)
        {
            t=true
            bonlivraisons1=objet.bonLivraisons
            bonlivraisons2=objet.bonLivraisons
            
            
            def interrr
            
            if(bonlivraisons2.size()> 1)
            {
                Iterator it=bonlivraisons1.iterator()
                
                while(it.hasNext())
                {
                    def str=it.next()  
                    def res=str.toString()
                    def bl1 = BonLivraison.findByNumBL(res)
                      
                    ligneProduits.addAll(bl1.ligneProduits) 
                      
                }
                //                    ligneProduits.addAll(bonlivraisons1.get(i).ligneProduits)
                   
       
                binderligneproduits.loadAll()
               
                
            }
            else
            {
                ligneProduits.addAll(bonlivraisons1.ligneProduits) 
                binderligneproduits.loadAll()
            }
            ligneProduitsglobal=ligneProduits
            

            binderligneproduits.loadAll()   
            
            veri=false 
            
        }
       
        
        
        
        
        def ligneProduits2=[]
        
        
        if(item.isSelected() && t==false) { 
            def ligneProduits6=[]
            ligneProduits=ligneProduits6
            
            def bonlivinter=bonlivraisons2
            println()
           
            bonlivraisons2.add(bl)
           
            
            
            Iterator it1=bonlivraisons2.iterator()
               
            def ligneProduits1=[]
            ligneProduits=ligneProduits1
            while(it1.hasNext())
            {
                def str=it1.next()  
                def res=str.toString()
                def bl1 = BonLivraison.findByNumBL(res)
                      
                ligneProduits.addAll(bl1.ligneProduits) 
            }
            binderligneproduits.loadAll() 
        } 
        
        else 
        {
            def blinter=bonlivraisons2
            
            
            if(t==false)
            {
          
                def bonlivbak=[]    
                Iterator it=blinter.iterator()
               
                while(it.hasNext())
                {
                       
                    def str=it.next()  
                    def res=str.toString()
                    def bl1 = BonLivraison.findByNumBL(res)
                    println(bl1)
                    println(bl)
                    if(bl.equals(bl1))
                    {
                      
                        println('le rsultat du bonlivraison'+bl1+'sa liste de produits'+bl)
                    }
                    else
                    {
                          
                        bonlivbak.add(bl1)
                         
                    }
                      
                }
                
                bonlivraisons2=bonlivbak
                
                

                Iterator it1=bonlivbak.iterator()
               
                
                def ligneProduits4=[]
                
                ligneProduits=ligneProduits4
                while(it1.hasNext())
                {
                    def str=it1.next()  
                    def res=str.toString()
                    def bl1 = BonLivraison.findByNumBL(res)
                    def ligneProduits1=[]
                    ligneProduits.addAll(bl1.ligneProduits) 

                }
                binderligneproduits.loadAll()   
            }
        } 
    }
    def blSelectionner2(event) {
        def binderligneproduits = new AnnotateDataBinder(this.getFellow("lstligneProduits"))
        def item = event.getReference()   
        def numbl = item.getValue().toString()
       


        def bl = BonLivraison.findByNumBL(numbl)
        println("le bon livraison"+bl+'sa liste de produit'+bl.ligneProduits)
        
        def ligneproduitsafficher=[]
        //        ligneProduits=ligneproduitsafficher
        def ligneproduitbakup=[]
       
        def t=false
        if(veri==true)
        {
            t=true
            bonlivraisons1=objet.bonLivraisons
            bonlivraisons2=objet.bonLivraisons
            def interrr
            println('le bonlivraison +'+bonlivraisons1.size()+'global'+bonlivraisons+'initial pour le select'+bonlivraisons1)
            if(bonlivraisons2.size()> 1)
            {
                Iterator it=bonlivraisons1.iterator()
                println('le total'+bonlivraisons1+'la liste produits '+ligneProduits)
                while(it.hasNext())
                {
                    def str=it.next()  
                    def res=str.toString()
                    def bl1 = BonLivraison.findByNumBL(res)
                    println('le rsultat du bonlivraison'+bl1+'sa liste de produits'+bl1.ligneProduits)
                    println('sa liste de produits'+bl1.ligneProduits.produit.code)  
                    ligneProduits.addAll(bl1.ligneProduits) 
                      
                }
                //                    ligneProduits.addAll(bonlivraisons1.get(i).ligneProduits)
                println('le total'+ligneProduits)
       
                binderligneproduits.loadAll()
               
                
            }
            else
            {
                ligneProduits.addAll(bonlivraisons1.ligneProduits) 
                binderligneproduits.loadAll()
            }
            ligneProduitsglobal=ligneProduits
            println('la liste bakup'+ligneProduitsglobal)
            veri=false 
            
        }
       
        
        
        
        
        if(item.isSelected() && t==false) {            
            ligneProduits.addAll(bl.ligneProduits)
            ligneProduitsglobal=ligneProduits
            println("la liste de produits dans la selection"+ligneProduits)
            binderligneproduits.loadAll()
        } else 
        {
            def ligneproduitslivraison=bl.ligneProduits
            
            
            
            if(t==false)
            {
                Iterator it=ligneProduits.iterator()
            
                ligneProduits.removeAll(bl.ligneProduits)
                println('nkjnkjnkjnkjn'+ligneProduits)
           
                def ver=false
                while(it.hasNext())
                {
                    ver=false
        
                    def r=it.next().toString()
                    def resultat
                    Iterator it1=ligneproduitslivraison.iterator()
                    while((it1.hasNext())&&(ver==false))
                    {
                        def ss=it1.next().toString()
                        println('le type des elements de objet'+it1.next())     
                    
                        if(ss.equals(r))
                        { 
                            ver=true
                        
                        
                        }
                        else
                        {
                            ver=false
                            resultat=r
                       
                        }
                   
              
                    }
                    if(ver==false)
                    {
                        //                    def  foo = Integer.parseInt(resultat)
                        //                    def ligne=LigneProduit.findBySequenceLigne(foo)


                        println('le nouveauuuuuuuuuuu'+resultat.getClass()+'resultat'+resultat)
                        ligneProduits.addAll(bl.ligneProduits)
                        ligneproduitbakup.add(resultat)
                        
                  
                
                    }
                }
            
                ligneProduits=ligneproduitbakup
                binderligneproduits.loadAll()
            } 

            println('passer par ici'+ligneProduits)
        }
       
       
        
        t=false
        println('les lignes produits'+ ligneProduits)
        //        println('produits'+ligneProduits.produit.code)
        
    }
   
    def bl1Selectionner(event) {
        println("la liste de produits"+ligneProduits)
        def item = event.getReference()   
        def numbl = item.getValue().toString()
        
        
        
        def bl = BonLivraison.findByNumBL(numbl)
        println("le bon livraison"+bl+'sa liste de produit'+bl.ligneProduits)
        
        if(item.isSelected()) {            
            ligneProduits.addAll(bl.ligneProduits)
            println("la liste de produits"+ligneProduits)
        } else {
            ligneProduits.removeAll(bl.ligneProduits)
        }
        new AnnotateDataBinder(this.getFellow("lstligneProduits")).loadAll()
        
        
    }
    
    
    
    def partenaireSelectionner() {

        //       bonLivraisons = bonLivraisonService.getBonsLivraisonBonsRetourPartenaire(objet.partenaire)
        //       
        //        println('liste bonlivraison lie partenaire'+bonLivraisons)
        def part
        def partselect
        part=objet.partenaire
        objet.partenaire=partenaireSelected
        part=objet.partenaire
        if(veri==true)
        {
          
            bonLivraisons=objet.bonLivraisons
        }
        else
        {
           
            bonLivraisons = bonLivraisonService.getBonsLivraisonBonsRetourPartenaire(objet.partenaire)
        }
       

        new AnnotateDataBinder(this.getFellow("lstbonLivraisons")).loadAll()
        
        def modeLivraisonPartenaire = objet.partenaire.modeLivraison
        if(modeLivraisonPartenaire != null) {
            modeLivraisonSelected = modeLivraisons.find{ it.code == modeLivraisonPartenaire.code }
            new AnnotateDataBinder(this.getFellow("comodeLivraisons")).loadAll()
        }
        
    }
    
    def activerBoutons(visible) {
        this.getFellow("btnUpdate").visible = visible
        this.getFellow("btnDelete").visible = visible
        this.getFellow("btnPdf").visible = !visible
        this.getFellow("btnExcel").visible = !visible
        this.getFellow("btnSave").visible = !visible
        this.getFellow("btnNew").visible = !visible
        this.getFellow("westPanel").open = visible        
    }
    
    protected SuperService getService() {
        return this.livraisonService
    }
    
    /**
     * Generation du rapport pdf
     **/
    def genererRapportPdf() {
        String nomsociete = session.societe.raisonSociale
        String titrerapport = "Rapport des Livraisons"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Livraisons.jrxml',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Livraisons.pdf"
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
        String titrerapport = "Rapport des Livraisons"
        def listeObjetsExporter = getObjetsToExport()
        def reportDef = new JasperReportDef(name:'rapport_des_Livraisons.jrxml',
            fileFormat:JasperExportFormat.XLS_FORMAT,
            reportData : listeObjetsExporter,
            parameters : [  'nomsociete':nomsociete, 'titrerapport':titrerapport ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Livraisons.xls"
        Filedownload.save(bit, "application/file", nom_fichier);
        
    }
       
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
        
        if(del) {
            bonLivraisons = BonLivraison.list()
        }
        this.getFellow("lstbonLivraisons").clearSelection()
        bonLivraisonsSelected = null// = new ArrayList()
                    	
        if(del) {
            modeLivraisons = ModeLivraison.list()
        }	
        if(modeLivraisons.size() > 0)
        modeLivraisonSelected = modeLivraisons.get(0)
        else
        modeLivraisonSelected = null
                    
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
        
        
        objet.bonLivraisons = bonLivraisonsSelected
        this.getFellow("lstbonLivraisons").clearSelection()
        bonLivraisonsSelected = null// = new ArrayList()
                    		
        objet.modeLivraison = modeLivraisonSelected
        if(modeLivraisons.size() > 0) {
            def bindermodeLivraison = new AnnotateDataBinder(this.getFellow("comodeLivraisons"))
            modeLivraisonSelected = modeLivraisons.get(0)
            bindermodeLivraison.loadAll()
        }
        else
        modeLivraisonSelected = null
                    
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
        
        def partenairelivraison=[]
        
        partenairelivraison=partenaireService.getPartenairebyLivraison(objetSelected)
       

        //  if(partenairelivraison.size() > 1)
        //  {
        //      
        //  
        //         def listpartenaire =new ArrayList<Partenaire>()
        //        Iterator it=partenairelivraison.iterator()
        //        while(it.hasNext())
        //        {
        //            if(listpartenaire.contains(it.next()))
        //            {}
        //            else
        //            
        //            listpartenaire.add((Partenaire)it.next())
        //        }
        //        partenaires=listpartenaire
        //  }
        //  else
        //  partenaires=partenairelivraison
        //  
        //         if(partenaires.size() > 0) {
        //            def binderpartenaire = new AnnotateDataBinder(this.getFellow("comodePartenaires"))
        //            
        //            println('gxfxfvxfvxgfxgx'+PartenaireSelected)
        //            binderpartenaire.loadAll()
        //        }
        //        else
        //        PartenaireSelected = null

        partenaires=partenairelivraison
        if(partenaires)
        {
            def binderpartenaire = new AnnotateDataBinder(this.getFellow("comodePartenaires"))
            partenaireSelected=partenaires.get(0)
            objet.partenaire=partenaires.get(0)
            binderpartenaire.loadAll()
           
        } 
           
        

        if(objet.partenaire)
        {
            veri=true
            partenaireSelectionner()
            
        }

        def binderbonLivraisons = new AnnotateDataBinder(this.getFellow("lstbonLivraisons"))
        bonLivraisonsSelected = objetSelected.bonLivraisons
        
        binderbonLivraisons.loadAll()
                    		
        def bindermodeLivraison = new AnnotateDataBinder(this.getFellow("comodeLivraisons"))
        modeLivraisonSelected = modeLivraisons.find{ it.id == Livraison.findById(objet.id).modeLivraison.id }
        println('les partenaires selectionnes'+modeLivraisonSelected)
        bindermodeLivraison.loadAll()
        
                    
    }
}







//
//def blSelectionner(event) {
//       def binderligneproduits = new AnnotateDataBinder(this.getFellow("lstligneProduits"))
//        def item = event.getReference()   
//        def numbl = item.getValue().toString()
//       
//
//
//        def bl = BonLivraison.findByNumBL(numbl)
//        println("le bon livraison"+bl+'sa liste de produit'+bl.ligneProduits)
//        
//        def ligneproduitsafficher=[]
////        ligneProduits=ligneproduitsafficher
//        def ligneproduitbakup=[]
//       
//        def t=false
//      if(veri==true)
//        {
//            t=true
//            bonlivraisons1=objet.bonLivraisons
//            bonlivraisons2=objet.bonLivraisons
//            def interrr
//            println('le bonlivraison +'+bonlivraisons1.size()+'global'+bonlivraisons+'initial pour le select'+bonlivraisons1)
//            if(bonlivraisons2.size()> 1)
//            {
//                Iterator it=bonlivraisons1.iterator()
//                println('le total'+bonlivraisons1+'la liste produits '+ligneProduits)
//                while(it.hasNext())
//                  {
//                      def str=it.next()  
//                      def res=str.toString()
//                      def bl1 = BonLivraison.findByNumBL(res)
//                      println('le rsultat du bonlivraison'+bl1+'sa liste de produits'+bl1.ligneProduits)
//                      ligneProduits.addAll(bl1.ligneProduits) 
//                      
//                  }
////                    ligneProduits.addAll(bonlivraisons1.get(i).ligneProduits)
//                    println('le total'+ligneProduits)
//       
//                 binderligneproduits.loadAll()
//               
//                
//            }
//            else
//            {
//               ligneProduits.addAll(bonlivraisons1.ligneProduits) 
//               binderligneproduits.loadAll()
//            }
//            
//          veri=false 
//            
//        }
//       
//        
//        
//        
//        
//        if(item.isSelected() && t==false) {            
//            ligneProduits.addAll(bl.ligneProduits)
//            println("la liste de produits dans la selection"+ligneProduits)
//             binderligneproduits.loadAll()
//        } else 
//        {
//            def ligneproduitslivraison=bl.ligneProduits
//             println("entrer dans la boucle de non selection"+ligneProduits+'la valeur t'+t)
//             if(t==false)
//             {
//            Iterator it=ligneProduits.iterator()
//            def ver=false
//            while(it.hasNext())
//            {
//                ver=false
//             
//                 def r=it.next().toString()
//                 def resultat
//                Iterator it1=ligneproduitslivraison.iterator()
//                    while((it1.hasNext())&&(ver==false))
//                    {
//                    def ss=it1.next().toString()
//                    
//                    if(ss.equals(r))
//                        { 
//                            ver=true
//                        
//                        
//                        }
//                   else
//                        {
//                       ver=false
//                       resultat=r
//                       
//                        }
//                   
//              
//                     }
//                if(ver==false)
//                {
////                    def  foo = Integer.parseInt(resultat)
////                    def ligne=LigneProduit.findBySequenceLigne(foo)
//println('le nouveauuuuuuuuuuu'+resultat.getClass()+'resultat'+resultat)
//                        ligneproduitbakup.add(resultat)
//                  
//                
//                }
//            }
//            
//            ligneProduits=ligneproduitbakup
//            binderligneproduits.loadAll()
//             } 
//
//println('passer par ici'+ligneProduits)
//        }
//       
//       
//        
//        t=false
//        println('les lignes produits'+ ligneProduits)
////        println('produits'+ligneProduits.produit.code)
//        
//    }