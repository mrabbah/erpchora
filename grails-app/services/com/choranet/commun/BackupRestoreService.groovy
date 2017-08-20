
package com.choranet.commun
/*
import grails.converters.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.choranet.gesticom.util.BackupDirectoryFilter;
import org.hibernate.ReplicationMode;
import com.choranet.gesticom.*
import com.choranet.compta.*
import com.choranet.projet.*
import com.choranet.securite.*
import com.choranet.exception.*
import com.choranet.rh.*
import com.choranet.stock.*
*/
class BackupRestoreService {
/*
    def sessionFactory
    
    static transactional = true

    def cleanDataBase() throws Exception {
        try {
            def session = sessionFactory.getCurrentSession()
            session.clear()  

            Pays.executeUpdate("delete Pays pays")
            AgenceBancaire.executeUpdate("delete AgenceBancaire agenceBancaire")
            CategoriePartenaire.executeUpdate("delete CategoriePartenaire categoriePartenaire")
            Entrepot.executeUpdate("delete Entrepot entrepot")
            Devis.executeUpdate("delete Devis devis")
            Nature.executeUpdate("delete Nature nature")
            AlertsSecuriteStock.executeUpdate("delete AlertsSecuriteStock alertsSecuriteStock")
            Partenaire.executeUpdate("delete Partenaire partenaire")
            Depenses.executeUpdate("delete Depenses depenses")
            PaternCompteur.executeUpdate("delete PaternCompteur paternCompteur")
            Paiement.executeUpdate("delete Paiement paiement")
            BonLivraison.executeUpdate("delete BonLivraison bonLivraison")
            Livraison.executeUpdate("delete Livraison livraison")
            Contact.executeUpdate("delete Contact contact")
            CategorieProduit.executeUpdate("delete CategorieProduit categorieProduit")
            Ville.executeUpdate("delete Ville ville")
            DeclarationTva.executeUpdate("delete DeclarationTva declarationTva")
            LigneProduit.executeUpdate("delete LigneProduit ligneProduit")
            UniteMesure.executeUpdate("delete UniteMesure uniteMesure")
            Echeance.executeUpdate("delete Echeance echeance")
            MessageDocument.executeUpdate("delete MessageDocument messageDocument")
            MouvementStock.executeUpdate("delete MouvementStock mouvementStock")
            FormeJuridique.executeUpdate("delete FormeJuridique formeJuridique")
            RegimeDeclarationTva.executeUpdate("delete RegimeDeclarationTva regimeDeclarationTva")
            CompteBancaire.executeUpdate("delete CompteBancaire compteBancaire")
            RegleFacturationAutomatique.executeUpdate("delete RegleFacturationAutomatique regleFacturationAutomatique")
            Produit.executeUpdate("delete Produit produit")
            LigneInventaire.executeUpdate("delete LigneInventaire ligneInventaire")
            ModeLivraison.executeUpdate("delete ModeLivraison modeLivraison")
            RegimeTVA.executeUpdate("delete RegimeTVA regimeTVA")
            Securitestock.executeUpdate("delete Securitestock securitestock")
            BonCommande.executeUpdate("delete BonCommande bonCommande")
            Inventaire.executeUpdate("delete Inventaire inventaire")
            ReglePrix.executeUpdate("delete ReglePrix reglePrix")
            BonPret.executeUpdate("delete BonPret bonPret")
            Facture.executeUpdate("delete Facture facture")
            ModeRelancePaiement.executeUpdate("delete ModeRelancePaiement modeRelancePaiement")
            ModeReglement.executeUpdate("delete ModeReglement modeReglement")
            NatureDeCharge.executeUpdate("delete NatureDeCharge natureDeCharge")
            DroitUtilisateur.executeUpdate("delete DroitUtilisateur d") 
            session.createSQLQuery("delete from utilisateur_authorities").executeUpdate();
            Utilisateur.executeUpdate("delete Utilisateur uts")
            GroupeUtilisateur.executeUpdate("delete GroupeUtilisateur gu")
            ChoraClientInfo.executeUpdate("delete ChoraClientInfo ci")
            ChoraBarrage.executeUpdate("delete ChoraBarrage chbar")
            session.flush()
            session.clear()
        } catch(Exception ex) {
            throw new Exception("Impossible de supprimer la base de donnée courante", ex)
        }
    }
    
    def bulkInsert(objets) throws Exception {

        def session = sessionFactory.getCurrentSession()

        try {
            def batch = []
            objets.each { objet ->
                batch.add(objet)
                if(batch.size() > 1000) {
                    objet.getClass().withTransaction {
                        for(o in batch) {
                            session.replicate(o, ReplicationMode.OVERWRITE);
                        }
                    }
                    batch.clear()
                    session.flush()
                    session.clear()        
                }
            }
            if(batch.size() > 0) {
                batch[0].getClass().withTransaction {
                    for(o in batch) {
                        session.replicate(o, ReplicationMode.OVERWRITE);
                    }
                }
                batch.clear()
                session.flush()
                session.clear()  
            }
        } catch(Exception ex) {
            throw new Exception("Impossible d'insérer dans la base de données les objets : " + objets, ex)
        }
    }
    
    def xmlStringToObjets(xmls) throws Exception {
        def objets = null
        try {
            objets = XML.parse(xmls)
        } catch (Exception ex) {
            throw new Exception("Problème lors de la convesion de la chaine " + xmls + " en objets", ex)
        }
        return objets
    }
    
    def xmlFileToObjets(File file) throws Exception {
        def objets = null
        try {
            FileInputStream fis = new FileInputStream(file)
            objets = XML.parse(fis, "UTF-8")
        } catch (Exception ex) {
            throw new Exception("Le fichier " + file.getAbsolutePath() + " est corrompu", ex)
        }
        return objets
    }
    
    def sauveguarderBdD() throws Exception {
        def info = session.societe
        if(info == null) {
            throw new Exception("Aucune informations sur le client trouvé au niveau de la Base")
        } else if(info.repertoirBackup == null || info.repertoirBackup.equals("")) {
            throw new Exception("Vous devez indiquer un répértoir pour la sauveguarde de vos données")
        } else {
            File f;
            try {
                f = new File(info.repertoirBackup)
            } catch(Exception e) {
                throw new Exception("Le chemin indiqué n'est pas une répértoir valide : " + info.repertoirBackup, e)
            }
            if(!f.exists()) {
                throw new Exception("Le chemin indiqué n'est pas une répértoir valide : " + info.repertoirBackup)
            }
            if(!f.isDirectory()) {
                throw new Exception("Le chemin indiqué n'est pas un répértoire : " + info.repertoirBackup)
            }
            if(!f.canWrite()) {
                throw new Exception("Vous n'avez pas le droit d'écriture sur le répértoire : " + info.repertoirBackup)
            }
         
        }
        String FS = System.getProperty("file.separator");
        Date d = new Date()
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        String fullPathToBackupDirectory = info.repertoirBackup + FS + "BACKUP" + dateFormat.format(d);
        File directoryBackup = new File(fullPathToBackupDirectory);
        directoryBackup.mkdirs();
        
        def choraBarrage = ChoraBarrage.list()
        def cci = ChoraClientInfo.list()
        def du = DroitUtilisateur.list()
        def gu = GroupeUtilisateur.list()
        def utilisateurs = Utilisateur.list()

        def pays = Pays.list()

        def agenceBancaire = AgenceBancaire.list()

        def categoriePartenaire = CategoriePartenaire.list()

        def entrepot = Entrepot.list()

        def devis = Devis.list()

        def nature = Nature.list()

        def bonRetour = BonRetour.list()

        def alertsSecuriteStock = AlertsSecuriteStock.list()

        def partenaire = Partenaire.list()

        def depenses = Depenses.list()

        def paternCompteur = PaternCompteur.list()

        def paiement = Paiement.list()

        def bonLivraison = BonLivraison.list()

        def livraison = Livraison.list()

        def contact = Contact.list()

        def categorieProduit = CategorieProduit.list()

        def ville = Ville.list()

        def declarationTva = DeclarationTva.list()

        def ligneProduit = LigneProduit.list()

        def uniteMesure = UniteMesure.list()

        def echeance = Echeance.list()

        def messageDocument = MessageDocument.list()

        def mouvementStock = MouvementStock.list()

        def formeJuridique = FormeJuridique.list()

        def regimeDeclarationTva = RegimeDeclarationTva.list()

        def compteBancaire = CompteBancaire.list()

        def regleFacturationAutomatique = RegleFacturationAutomatique.list()

        def produit = Produit.list()

        def ligneInventaire = LigneInventaire.list()

        def modeLivraison = ModeLivraison.list()

        def regimeTVA = RegimeTVA.list()

        def fonction = Fonction.list()

        def securitestock = Securitestock.list()

        def bonCommande = BonCommande.list()

        def inventaire = Inventaire.list()

        def reglePrix = ReglePrix.list()

        def bonPret = BonPret.list()

        def facture = Facture.list()

        def modeRelancePaiement = ModeRelancePaiement.list()

        def modeReglement = ModeReglement.list()

        def natureDeCharge = NatureDeCharge.list()

        def collaborateur = Collaborateur.list()
        
        
        File backupFile = new File(fullPathToBackupDirectory + FS + "chorainformatiquebackup.backup" );
        backupFile.createNewFile();
        backupFile.withObjectOutputStream { out ->
            out << choraBarrage
            out << cci
            out << gu
            out << utilisateurs
            out << du

        out << pays

        out << agenceBancaire

        out << categoriePartenaire

        out << entrepot

        out << devis

        out << nature

        out << bonRetour

        out << alertsSecuriteStock

        out << partenaire

        out << depenses

        out << paternCompteur

        out << paiement

        out << bonLivraison

        out << livraison

        out << contact

        out << categorieProduit

        out << ville

        out << declarationTva

        out << ligneProduit

        out << uniteMesure

        out << echeance

        out << messageDocument

        out << mouvementStock

        out << formeJuridique

        out << regimeDeclarationTva

        out << compteBancaire

        out << regleFacturationAutomatique

        out << produit

        out << ligneInventaire

        out << avoir

        out << modeLivraison

        out << regimeTVA

        out << fonction

        out << securitestock

        out << bonCommande

        out << inventaire

        out << reglePrix

        out << bonPret

        out << facture

        out << modeRelancePaiement

        out << modeReglement

        out << natureDeCharge

        out << collaborateur
 
        }
    }
    
    def getListeDesSauveguardes() {
        def info = session.societe
        def result = []
        if(info != null && info.repertoirBackup != null && !info.repertoirBackup.equals("")) {
            File f = new File(info.repertoirBackup);
            if(f.exists() && f.isDirectory() && f.canRead()) {
                result = f.list(new BackupDirectoryFilter());
            } 
        } 
        return result;
    }
    
    def restaurerDb(backupname) throws Exception {
        def info = session.societe
        if(info != null && info.repertoirBackup != null && !info.repertoirBackup.equals("")) {
            File f = new File(info.repertoirBackup);
            if(f.exists() && f.isDirectory() && f.canRead()) {
                String FS = System.getProperty("file.separator");
                String fullPathToBackupDirectory = info.repertoirBackup + FS + backupname;
                File directoryBackup = new File(fullPathToBackupDirectory);
                if(directoryBackup.exists() && directoryBackup.isDirectory() && directoryBackup.canRead()) {
                    File backupFile = new File(fullPathToBackupDirectory + FS + "chorainformatiquebackup.backup" );
                    if(!(backupFile.exists() && backupFile.canRead())) {
                        throw new Exception("Le fichier chorainformatiquebackup.backup n'existe pas ou n'est pas en lecture" )
                    } 
                    //Vérification du fichier de sauveguarde
                    //TODO
                    //                    int compteur = 0;
                    //                    try {
                    //                        backupFile.withObjectInputStream(getClass().classLoader) { instream ->
                    //                            instream.eachObject { 
                    //                                compteur++;
                    //                            }
                    //                        }
                    //                        if(compteur != 20) {
                    //                            throw new Exception("Le fichier chorainformatiquebackup.backup est invalide");
                    //                        }
                    //                    } catch(Exception ex) {
                    //                        throw ex;
                    //                    }
                    
                    //Apres verification supression de la base actuelle
                    cleanDataBase()
                    //lecture des fichiers
                    try {
                        backupFile.withObjectInputStream(getClass().classLoader) { instream ->
                            instream.eachObject { 
                                bulkInsert(it)
                            }
                        }
                    } catch(Exception ex) {
                        ex.printStackTrace()
                        throw ex
                    }
                    
                } else {
                    throw new Exception("Le répertoire de backup : " + fullPathToBackupDirectory + " n'existe pas ou n'est pas en lecture") 
                }
            }else {
                throw new Exception("Le répertoire de backup : " + info.repertoirBackup + " n'existe pas ou n'est pas en lecture") 
            }
        } else {
            throw new Exception("Vous devez indiquer un répértoir pour la sauveguarde de vos données")
        }
    }
    */
}
