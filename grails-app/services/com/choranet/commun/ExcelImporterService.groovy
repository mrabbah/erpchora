package com.choranet.commun

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import java.text.SimpleDateFormat;

import com.choranet.gesticom.*
import com.choranet.compta.*
import com.choranet.projet.*
import com.choranet.securite.*
import com.choranet.exception.*
import com.choranet.rh.*
import com.choranet.stock.*


class ExcelImporterService {

    static transactional = true   
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
    
    String fs = System.getProperty('file.separator');
    String ls = System.getProperty('line.separator');
    
    def categorieProduitService
    def produitService
    def regimeTVAService
    def entrepotService
    def uniteMesureService
    def categoriePartenaireService
    def partenaireService
    
    def creerFichierLogImport(String nomFichier) {
        File filelog = new File(System.getProperty('user.home')+fs+nomFichier+dateFormat.format(new Date()))
        filelog.createNewFile();
        loggerInformations(filelog, System.getProperty('os.name'))
        loggerInformations(filelog, System.getProperty('os.arch'))
        loggerInformations(filelog, System.getProperty('user.dir'))
        loggerInformations(filelog, System.getProperty('user.home'))
        loggerInformations(filelog, System.getProperty('user.language'))
        loggerInformations(filelog, System.getProperty('java.version'))
        loggerInformations(filelog, System.getProperty('java.vendor'))
        return filelog;
    }
    
    
    def loggerInformations(File filelog, Object info) {
        filelog << info
        filelog << ls
    }
    
    def chargerDonneesLigne(Row row, types) {
        int taille = types.size()
        Map map = [:]        
        def cles = types.keySet().toArray();
        for(int i = 0 ; i < taille ; i++ )  {
            Cell cell = row.getCell(i)
            def cle = cles[i]
            if(cell != null) {
                if(types[cle].equals("String")) {
                    switch(cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING : 
                         map[cle] = cell.getStringCellValue(); 
                        break;
                        case Cell.CELL_TYPE_NUMERIC : 
                         map[cle] = String.valueOf(cell.getNumericCellValue());
                        break;
                        case Cell.CELL_TYPE_BOOLEAN : 
                         map[cle] = String.valueOf(cell.getBooleanCellValue());
                        break;
                    }
                    if(map[cle] != null) {
                        map[cle] = map[cle].trim()
                        if(map[cle].equals("")) {
                            map[cle] = null
                        }
                    }
                } else if (types[cle].equals("Numeric")) {
                    map[cle] = cell.getNumericCellValue();
                } else if (types[cle].equals("Date")) {
                    map[cle] = cell.getDateCellValue();
                } else if (types[cle].equals("Boolean")) {
                    map[cle] = cell.getBooleanCellValue();
                } else {
                    map[cle] = null
                }
            } else {
                map[cle] = null
            }
        }
        return map
    }
    
    def chargerDonneesParDefaut(Map objet, Map valeursParDefaut) {
        valeursParDefaut.entrySet().each {
            if(objet[it.key] == null) {
                objet[it.key] = it.value
            }
        }
    } 
    
    def validerChampsRequis(Map objet, champsrequis) {
        for(champs in champsrequis) {
            if(objet[champs] == null) {
                return champs
            }
        }
        return null
    }
    /**
     *  Import des des produits
    Le fichier doit être sous la forme :
    code  codeCommun profil   designation        prixAchat       prixRevient        prixVente        Emplacement         UniteMesure     RegimeTVA       Categorie       codeBarre           poids           Nature    perissable      Produit parent        prixMoyenpendre      fournisseurPref
    001     cc1       p1       produit 1          120.3           150                160              Default             Kg              20%             C1              4556455545          200             Fragile    false
    .
    .
    .
     **/
    def importerProduits(Object media) {
        File fileLog = creerFichierLogImport("log_import_produits")
        try {
            Workbook bk = new HSSFWorkbook(media.getStreamData())
            Sheet sheet = bk.getSheetAt(0)
            def first = true
            def compteur = 1;
            def nbImport = 0;
            def nbEchec = 0;
            
            def types = ["code": "String", "codecommun" : "String" , "profil" : "String", "designation" : "String", "prixAchat" : "Numeric", "prixRevient" : "Numeric"
                , "prixVenteStandard" : "Numeric", "empReception" : "String", "uniteMesure" : "String", "regimeTVA" : "String", "categorieProduit" : "String"
                , "codebarre" : "String", "poids" : "Numeric", "nature" : "String", "perissable" : "Boolean" ,"produitParent" : "String", "prixMoyenPendere" : "Numeric", "fournisseurPref" : "String"];
        
            def defaultCategorie = categorieProduitService.getDefaultCategorie();
            def defaultRegimeTva = regimeTVAService.getDefaultRegime();
            def empReceptionDefaut = entrepotService.getDefaultEntrepot()
            def uniteMesureDefaut = uniteMesureService.getUnitePardefaut()
            
            def valeursParDefaut = ["categorieProduit" : defaultCategorie, "regimeTVA" : defaultRegimeTva, "empReception" : empReceptionDefaut, 
                                    "uniteMesure" : uniteMesureDefaut, "perissable" : false]
            
            def valeursObligatoire = ["code", "designation", "prixAchat", "prixRevient", "prixVenteStandard", "empReception",
            "uniteMesure", "regimeTVA", "categorieProduit"]
            
            loggerInformations(fileLog, "Début import des produits : " + new Date())
            for (Row row : sheet) {
                try {
                    if(first) {
                        first = false
                        compteur++;
                        continue
                    }
                    
                    def map = chargerDonneesLigne(row, types);
                    
                    if(map.categorieProduit != null && !map.categorieProduit.equals("")) {
                        map.categorieProduit = categorieProduitService.getCategorieByLibelle(map.categorieProduit)
                        if(map.categorieProduit == null) {
                            loggerInformations(fileLog, "Echec import ligne " + compteur + " categorie parente inexistante")
                            nbEchec++
                            compteur++
                            continue;
                        }
                    }
                    if(map.regimeTVA != null && !map.regimeTVA.equals("")) {
                        map.regimeTVA = regimeTVAService.getRegimeByLibelle(map.regimeTVA)
                    }
                    
                    if(map.empReception != null && !map.empReception.equals("")) {
                        map.empReception = entrepotService.getEntrepotByTitre(map.empReception)
                    }
                    
                    if(map.uniteMesure != null && !map.uniteMesure.equals("")) {
                        def um = map.uniteMesure
                        map.uniteMesure = uniteMesureService.getUniteMesureByLibelle(um)
                        if(map.uniteMesure == null) {
                            map.uniteMesure = uniteMesureService.save(new  UniteMesure(libelle : um))
                        }
                    }
            
                    if(map.prixAchat != null && map.prixRevient == null) {
                        map.prixRevient = map.prixAchat
                    }
                    
                    if(map.fournisseurPref != null && !map.fournisseurPref.equals("")) {
                        map.fournisseurPref = partenaireService.getPartenaireParRaisonSociale(map.fournisseurPref)
                        if(map.fournisseurPref == null) {
                            loggerInformations(fileLog, "Echec import ligne " + compteur + " categorie parente inexistante")
                            nbEchec++
                            compteur++
                            continue;
                        }
                    }
                    
                    chargerDonneesParDefaut(map, valeursParDefaut)
                    
                    def champ = validerChampsRequis(map, valeursObligatoire)
                    
                    if(champ != null) {
                        loggerInformations(fileLog, "Echec import ligne " + compteur + " le champ : " + champ + " est requis")
                        nbEchec++
                    } else {
                        def oldPdt1 = produitService.getProduitByCode(map.code);
                        def oldPdt2 = produitService.getProduitByDesignation(map.designation);
                        if(oldPdt1 != null || oldPdt2 != null) {
                            loggerInformations(fileLog, "Echec import ligne " + compteur + " le produit : " + map.designation + " existe déjà")
                            nbEchec++
                        } else {
                            def newPdt = new Produit(code: map.code, codecommun : map.categorieProduit.code, profil : map.profil, designation : map.designation, prixAchat : map.prixAchat, prixRevient : map.prixRevient
                                , prixVenteStandard : map.prixVenteStandard, empReception : map.empReception, uniteMesure : map.uniteMesure, regimeTVA : map.regimeTVA, categorieProduit : map.categorieProduit
                                , codebarre : map.codebarre, poids : map.poids, nature : map.nature, perissable : map.perissable, produitParent : map.produitParent, prixMoyenPendere : map.prixMoyenPendere, fournisseurPref : map.fournisseurPref);
                                    
                            produitService.save(newPdt)
                            nbImport++
                        }
                    }
                    
                    compteur++;
                } catch(Exception ex) {
                    loggerInformations(fileLog, "Echec import ligne " + compteur + " " + ex)
                    nbEchec++
                    compteur++;
                }
                
            }
            loggerInformations(fileLog, "Fin import catégorie produit : " + new Date())
            loggerInformations(fileLog, "Nombre de lignes importées : " + nbImport)
            loggerInformations(fileLog, "Nombre de lignes échouées : " + nbEchec)
        
            
        } catch(Exception ex) {
            loggerInformations(fileLog, "Fichier invalide : " + ls + ex)
        }
        return "Import terminé voir le fichier de log : " + fileLog.getAbsolutePath();
    }
    
    /**
     *  Import des catégories de produit
    Le fichier doit être sous la forme :
    code     intitule           categorieParent
    001      Catégorie 1                
    011      Catégorie 2        Catégorie 1
    002      Catégorie 3        Catégorie 1
    .
    .
    .
     **/
    def importerCategoriesProduits(Object media) {
       
        File fileLog = creerFichierLogImport("log_import_categorie_produit")
        try {
            Workbook bk = new HSSFWorkbook(media.getStreamData())
            Sheet sheet = bk.getSheetAt(0)
            def first = true
            def compteur = 1;
            def nbImport = 0;
            def nbEchec = 0;
            def types = ["code" : "String", "intitule" : "String", "categorieParente" : "String"]
            
            def defaultCategorie = categorieProduitService.getDefaultCategorie();
            def valeursParDefaut = ["categorieParente" : defaultCategorie]
            def valeursObligatoire = ["code", "intitule"]
            
            loggerInformations(fileLog, "Début import catégorie produit : " + new Date())
            for (Row row : sheet) {
                try {
                    if(first) {
                        first = false
                        compteur++;
                        continue
                    }
                    
                    def map = chargerDonneesLigne(row, types);
                    
                    if(map.categorieParente != null && !map.categorieParente.equals("")) {
                        map.categorieParente = categorieProduitService.getCategorieByLibelle(map.categorieParente)
                    }
                    
                    chargerDonneesParDefaut(map, valeursParDefaut)

                    def champ = validerChampsRequis(map, valeursObligatoire)
                    
                    if(champ != null) {
                        loggerInformations(fileLog, "Echec import ligne " + compteur + " le champ : " + champ + " est requis")
                        nbEchec++
                    } else {
                        def oldCat1 = categorieProduitService.getCategorieByCode(map.code)
                        def oldCat2 = categorieProduitService.getCategorieByLibelle(map.intitule)
                        if(oldCat1 != null || oldCat2 != null) {
                            loggerInformations(fileLog, "Echec import ligne " + compteur + " la catégorie : " + map.intitule + " exsite déjà")
                            nbEchec++
                        } else {
                            def newCp = new CategorieProduit(code : map.code, intitule : map.intitule, categorieParente : map.categorieParente)
                            categorieProduitService.save(newCp)
                            nbImport++      
                        }                        
                    }
                    compteur++;
                } catch(Exception ex) {
                    loggerInformations(fileLog, "Echec import ligne " + compteur + " " + ex)
                    nbEchec++
                    compteur++;
                }
                
            }
            loggerInformations(fileLog, "Fin import catégorie produit : " + new Date())
            loggerInformations(fileLog, "Nombre de lignes importées : " + nbImport)
            loggerInformations(fileLog, "Nombre de lignes échouées : " + nbEchec)
        
            
        } catch(Exception ex) {
            loggerInformations(fileLog, "Fichier invalide : " + ls + ex)
            log.error(ex)
        }
        return "Import terminé voir le fichier de log : " + fileLog.getAbsolutePath();
    }
    /**
     *  Import des catégories partenaire
    Le fichier doit être sous la forme :
    libelle           categorieParente
    Catégorie 1                
    Catégorie 2        Catégorie 1
    Catégorie 3        Catégorie 1
    .
    .
    .
     **/
    def importerCategoriesPartenaires(Object media) {
       
        File fileLog = creerFichierLogImport("log_import_categorie_partenaire")
        try {
            Workbook bk = new HSSFWorkbook(media.getStreamData())
            Sheet sheet = bk.getSheetAt(0)
            def first = true
            def compteur = 1;
            def nbImport = 0;
            def nbEchec = 0;
            def types = ["libelle" : "String", "categorieParente" : "String"]
            
            def defaultCategorie = categoriePartenaireService.getDefaultCategorie();
            def valeursParDefaut = ["categorieParente" : defaultCategorie]
            def valeursObligatoire = ["libelle"]
            
            loggerInformations(fileLog, "Début import catégories partenaires : " + new Date())
            for (Row row : sheet) {
                try {
                    if(first) {
                        first = false
                        compteur++;
                        continue
                    }
                    
                    def map = chargerDonneesLigne(row, types);
                    
                    if(map.categorieParente != null && !map.categorieParente.equals("")) {
                        map.categorieParente = categoriePartenaireService.getCategorieByLibelle(map.categorieParente)
                    }
                    
                    chargerDonneesParDefaut(map, valeursParDefaut)

                    def champ = validerChampsRequis(map, valeursObligatoire)
                    
                    if(champ != null) {
                        loggerInformations(fileLog, "Echec import ligne " + compteur + " le champ : " + champ + " est requis")
                        nbEchec++
                    } else {
                        def oldCat2 = categoriePartenaireService.getCategorieByLibelle(map.libelle)
                        if(oldCat2 != null) {
                            loggerInformations(fileLog, "Echec import ligne " + compteur + " la catégorie : " + map.libelle + " exsite déjà")
                            nbEchec++
                        } else {
                            def newCp = new CategoriePartenaire(libelle : map.libelle, categorieParente : map.categorieParente)
                            categoriePartenaireService.save(newCp)
                            nbImport++      
                        }                        
                    }
                    compteur++;
                } catch(Exception ex) {
                    loggerInformations(fileLog, "Echec import ligne " + compteur + " " + ex)
                    nbEchec++
                    compteur++;
                }
                
            }
            loggerInformations(fileLog, "Fin import catégorie partenaire : " + new Date())
            loggerInformations(fileLog, "Nombre de lignes importées : " + nbImport)
            loggerInformations(fileLog, "Nombre de lignes échouées : " + nbEchec)
        
            
        } catch(Exception ex) {
            loggerInformations(fileLog, "Fichier invalide : " + ls + ex)
            log.error(ex)
        }
        return "Import terminé voir le fichier de log : " + fileLog.getAbsolutePath();
    }
    
    /**
     *  Import des partenaire
    Le fichier doit être sous la forme :
    code    raisonSociale  idFiscal   patente   registreCommerce   cnss    adresse   codePostal   telephone   fax   email   siteWeb    adresseFacturation    adresseLivraison   estClient  estFournisseur  estParticulier   ville  FormeJuridique  categorie    
    .
     **/
    def importerPartenaires(Object media) {
       
        File fileLog = creerFichierLogImport("log_import_partenaire")
        try {
            Workbook bk = new HSSFWorkbook(media.getStreamData())
            Sheet sheet = bk.getSheetAt(0)
            def first = true
            def compteur = 1;
            def nbImport = 0;
            def nbEchec = 0;
            def types = ["code" : "String","raisonSociale" : "String", "idFiscal" : "String",
            "patente" : "String",  "registreCommerce"  : "String", "cnss"  : "String",  "adresse" : "String",  
            "codePostal" : "String",  "telephone"  : "String", "fax"  : "String", "email"  : "String",
             "siteWeb"  : "String",  "adresseFacturation"  : "String",  "adresseLivraison" : "String",  
            "estClient" : "Boolean", "estFournisseur" : "Boolean", "estParticulier"  : "Boolean", 
            "ville" : "String", "formeJuridique" : "String", "categoriePartenaires" : "String"]
            
            def defaultCategorie = categoriePartenaireService.getDefaultCategorie();
            def categorieClient = categoriePartenaireService.getCategorieClients();
            def categorieFournisseur = categoriePartenaireService.getCategorieFournisseurs();
            
            //def valeursParDefaut = ["categoriePartenaires", [defaultCategorie]]
            def valeursObligatoire = ["code", "raisonSociale"]
            
            loggerInformations(fileLog, "Début import partenaires : " + new Date())
            for (Row row : sheet) {
                try {
                    if(first) {
                        first = false
                        compteur++;
                        continue
                    }
                    
                    def map = chargerDonneesLigne(row, types);
                    
                    if(map.ville != null && !map.ville.equals("")) {
                        map.ville = Ville.findByIntituleIlike(map.ville)
                    }
                    
                    if(map.formeJuridique != null && !map.formeJuridique.equals("")) {
                        map.formeJuridique = FormeJuridique.findByLibelleIlike(map.formeJuridique)
                    }
                    
                    if(map.estClient != null && map.estClient) {
                        //valeursParDefaut["categoriePartenaires"] = [categorieClient]
                        defaultCategorie = categorieClient
                    }
                    
                    if(map.estParticulier != null && map.estParticulier) {
                        //                        valeursParDefaut["categoriePartenaires"] = [categorieClient]
                        defaultCategorie = categorieClient
                    }
                    
                    if(map.estFournisseur != null && map.estFournisseur) {
                        //                        valeursParDefaut["categoriePartenaires"] = [categorieFournisseur]
                        defaultCategorie = categorieFournisseur
                    }
                    
                    //chargerDonneesParDefaut(map, valeursParDefaut)

                    def champ = validerChampsRequis(map, valeursObligatoire)
                    
                    if(champ != null) {
                        loggerInformations(fileLog, "Echec import ligne " + compteur + " le champ : " + champ + " est requis")
                        nbEchec++
                    } else {
                        def oldPar1 = Partenaire.findByCodeIlike(map.code)
                        def oldPar2 = Partenaire.findByRaisonSocialeIlike(map.raisonSociale)
                        if(oldPar2 != null || oldPar1 != null) {
                            loggerInformations(fileLog, "Echec import ligne " + compteur + " le partenaire : " + map.raisonSociale + " exsite déjà")
                            nbEchec++
                        } else {
                            new Partenaire(code : map.code ,   raisonSociale : map.raisonSociale , idFiscal  : map.idFiscal ,
                                patente : map.patente ,  registreCommerce : map.registreCommerce ,  cnss  : map.cnss ,  
                                adresse : map.adresse ,  codePostal : map.codePostal ,  telephone  : map.telephone , fax  : map.fax , email  : map.email , 
                                siteWeb : map.siteWeb ,   adresseFacturation  : map.adresseFacturation ,  adresseLivraison  : map.adresseLivraison , estClient : map.estClient , 
                                estFournisseur : map.estFournisseur , estParticulier : map.estParticulier , estBlacklist : false,  ville : map.ville , FormeJuridique : map.FormeJuridique , 
                                modeRelancePaiement : null, modeReglementDefaut : null, echeance : null, modeLivraison : null).addToCategoriePartenaires(defaultCategorie).save(flush:true)
                            //                            partenaireService.save(newPartenaire)
                            nbImport++      
                        }                        
                    }
                    compteur++;
                } catch(Exception ex) {
                    loggerInformations(fileLog, "Echec import ligne " + compteur + " " + ex)
                    nbEchec++
                    compteur++;
                }
                
            }
            loggerInformations(fileLog, "Fin import partenaires : " + new Date())
            loggerInformations(fileLog, "Nombre de lignes importées : " + nbImport)
            loggerInformations(fileLog, "Nombre de lignes échouées : " + nbEchec)
        
            
        } catch(Exception ex) {
            loggerInformations(fileLog, "Fichier invalide : " + ls + ex)
            log.error(ex)
        }
        return "Import terminé voir le fichier de log : " + fileLog.getAbsolutePath();
    }
   
}
