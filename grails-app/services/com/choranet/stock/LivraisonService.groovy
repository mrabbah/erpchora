
package com.choranet.stock;
import com.choranet.commun.SuperService
import com.choranet.gesticom.BonCommande
/**
 * LivraisonService Service pour la gestion des opérations
 * transactionnelles pour l'objet Livraison
 */
class LivraisonService extends SuperService {

    static transactional = true

    def paternCompteurService
    def bonCommandeService
    def mouvementStockService
    def sessionFactory
    
    def list() throws Exception {
        return super.list(Livraison.class)
    }
    def save(objet) throws Exception {
        objet.numExpedition = paternCompteurService.getProchainNumLivraisonEtIncrementer()
        for(bl in objet.bonLivraisons) {
            objet.addToBonLivraisons(bl)
        }
        def result = super.save(objet)
        for(bl in result.bonLivraisons) {
            mettreAjourBc(bl)
            mettreAjourStock(bl)
        }
        return result
    }
    def sauverEtAjouterBl(objet, bl) throws Exception {
        objet.addToBonLivraisons(bl)
        return save(objet)
    }
    def supprimerSiLieeAvecUnSeulBl(codelivraison) {
        def c = Livraison.createCriteria()
        def l = c.get {
            eq("numExpedition", codelivraison)
        }
        if(l.bonLivraisons.size() == 0) {
            delete(l)
        }
    }
    private void mettreAjourBc(bl) {
        def bc = BonCommande.findById(bl.bonCommande.id)
        if(bl.estBonRetour) {            
            for(lpBc in bc.ligneProduits) {
                for(lpBl in bl.ligneProduits) {
                    if(lpBc.produit.code.equals(lpBl.produit.code)) {
                        lpBc.quantiteRetournee += lpBl.quantiteRetournee
                        // si les produits perissables est non gerés
                        if(lpBl.date_preremption == null){
                            break
                        }
                    }
                }
            }
            bc.retournee = bc.trans_retournee
        } else {
            for(lpBc in bc.ligneProduits) {
                for(lpBl in bl.ligneProduits) {
                    if(lpBc.produit.code.equals(lpBl.produit.code)) {
                        lpBc.quantiteLivree += lpBl.quantiteLivree
                        // si les produits perissables est non gerés
                        if(lpBl.date_preremption == null){
                            break
                        }
                    }
                }
            }
            bc.livree = bc.trans_livree
        }
        bonCommandeService.update(bc)
    }
    
    private void mettreAjourStock(bl) {
        mouvementStockService.ajouterMouvementBlApresLivraison(bl)
    }
    def produitsLesPlusVendusAchetesQte (date1,date2,typeTran){
        def session = sessionFactory.getCurrentSession()
        session.clear()  
        //where livraison.date between '"+date1+"' and '"+date2+ "'
        //         and bl.date between :date1 and :date2
        def Qry = "select concat(concat(produit.designation,'-'), produit.code) as produit, sum(ligneProduit.quantiteLivree) as qte from Livraison livraison join livraison.bonLivraisons bl join bl.ligneProduits ligneProduit join ligneProduit.produit produit where bl.type = :typeTran and bl.date between :date1 and :date2 group by concat(concat(produit.designation,'-'), produit.code) order by sum(ligneProduit.quantiteLivree) desc"
        def q = session.createQuery(Qry)
        q.setString("typeTran",typeTran)
        q.setDate("date1",date1)
        q.setDate("date2",date2)
        q.setMaxResults(5)
        def results = q.list()
        
        return results

    }
    def produitsLesPlusVendusAchetesValeur (date1,date2,typeTran){
        def session = sessionFactory.getCurrentSession()
        session.clear()  
        //where livraison.date between '"+date1+"' and '"+date2+ "'
         
        def Qry = "select concat(concat(produit.designation,'-'), produit.code) as produit,sum(ligneProduit.quantiteLivree * ligneProduit.prixDeduit) as montant from Livraison livraison join livraison.bonLivraisons bl join bl.ligneProduits ligneProduit join ligneProduit.produit produit where bl.type = :typeTran and bl.date between :date1 and :date2  group by concat(concat(produit.designation,'-'), produit.code) order by sum(ligneProduit.quantiteLivree * ligneProduit.prixDeduit) desc"
        def q = session.createQuery(Qry)
        q.setString("typeTran",typeTran)
        q.setDate("date1",date1)
        q.setDate("date2",date2)
        q.setMaxResults(5)
        def results = q.list()
        
        return results

    }
    def getproduitsVendusAchetesTotal (date1,date2,typeTran){
        def session = sessionFactory.getCurrentSession()
        session.clear()  
        //where livraison.date between '"+date1+"' and '"+date2+ "'
        def Qry = "select sum(ligneProduit.quantiteLivree) as qte,sum(ligneProduit.quantiteLivree * ligneProduit.prixDeduit) as montant from Livraison livraison join livraison.bonLivraisons bl join bl.ligneProduits ligneProduit join ligneProduit.produit produit where bl.type = :typeTran and bl.date between :date1 and :date2 "
        def q = session.createQuery(Qry)
        q.setString("typeTran",typeTran)
        q.setDate("date1",date1)
        q.setDate("date2",date2)
        def results = q.uniqueResult()
        return results
    }  
    def exercice (annee){
        def session = sessionFactory.getCurrentSession()
        session.clear()
        def Qry = "select bl.type,month(bl.date),sum(ligneProduit.quantiteLivree) from Livraison livraison join livraison.bonLivraisons bl join bl.ligneProduits ligneProduit join ligneProduit.produit produit where year(bl.date)= :annee group by bl.type,month(bl.date) order by bl.type,month(bl.date)"
        def q = session.createQuery(Qry)
        q.setString("annee",annee.toString())
        def results = q.list()
        return results
    }
    def getlisteAnnees(){
        def session = sessionFactory.getCurrentSession()
        session.clear()  
        //where livraison.date between '"+date1+"' and '"+date2+ "'
        def Qry = "select distinct year(bl.date) from Livraison livraison join livraison.bonLivraisons bl order by year(bl.date)"
        def q = session.createQuery(Qry)
        def results = q.list()
        return results
    }
    def getlistePrixFournisseur (typeTran,produitcode){
        def session = sessionFactory.getCurrentSession()
        session.clear()  
        def Qry = "select partenaire.raisonSociale as fournisseur,min(ligneProduit.prixDeduit) as prix from Livraison livraison join livraison.partenaire partenaire join livraison.bonLivraisons bl join bl.ligneProduits ligneProduit join ligneProduit.produit produit where bl.type = :typeTran and produit.code = :code group by partenaire.raisonSociale order by min(ligneProduit.prixDeduit)"
        def q = session.createQuery(Qry)
        q.setString("typeTran",typeTran)
        q.setString("code",produitcode)
        q.setMaxResults(5)
        def results = q.list()
        return results
    }    
    def evolutionPrixVentesAchats(codeproduit) {
        def session = sessionFactory.getCurrentSession()
        session.clear()  
        def Qry = ""
        def q = session.createQuery(Qry)
        q.setString("code",codeproduit)
        def results = q.list()
        return results
    }
    
    def getLpsBLEnDetails(lignesProduitsBL){
        return mouvementStockService.getLignesProduitMvmEnDetails(lignesProduitsBL)
    }
}