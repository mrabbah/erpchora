

package com.choranet.gesticom


import org.apache.commons.lang.builder.*

/**
 * CategoriePartenaire Domain Object 
 */
class CategoriePartenaire {	
    
    String libelle	
       		   
    
    static belongsTo = [categorieParente : CategoriePartenaire]
    
    static mapping = { 
        //categorieParente lazy : false
        categorieParente cascade:"save-update"
        cache usage : 'nonstrict-read-write'
        //batchSize: 14 
        sort "libelle"
    }
    static constraints = {
    
        libelle(blank : false, nullable : false, unique : true)
        categorieParente(nullable : true)
    }
	
//    @Override
//    public boolean equals(Object o) {
//        return this.libelle == o.libelle
//    }
//    
    
    CategoriePartenaire(){}
    
    
    CategoriePartenaire(libelle, categorieParente){
        this.libelle = libelle
        this.categorieParente = null
    }
    
    static def remove(listCtgrPartenaire, ctgrPrtASupprime) {
        def newcategorieList = []
        
        if (listCtgrPartenaire == null)
        return null
            
        if (ctgrPrtASupprime == null) 
        return listCtgrPartenaire
            
        listCtgrPartenaire.each { obj ->
            if ( obj != null && obj.libelle != ctgrPrtASupprime.libelle)
            newcategorieList.add(obj)
        }
        return newcategorieList
        
    }
    
    static def removeAllSon(listCtgrPartenaire, ctgrPrtASupprime) {
        
        if (listCtgrPartenaire == null)
        return null
            
        if (ctgrPrtASupprime == null) 
        return listCtgrPartenaire

        def newList = listCtgrPartenaire
        listCtgrPartenaire.each { ctgrPrt ->
            def fullname = []
            getCategorieFullName(ctgrPrt, fullname)
            if (fullname.contains(ctgrPrtASupprime.libelle)) {
                newList = remove(newList, ctgrPrt)
            }
        }
        return newList
    }
    
    // function pour retourner la difference entre deux listes d'objets categoriePartenaire
    static def removeAll(listResult, listObject) {
        if (listResult == null || listResult.size()<1 || listObject == null || listObject.size()<1 )
            return false
            
        def listResultfinal = listResult.toList()
        listResultfinal.each { ctgrPrtBasic ->
            listObject.each {
                if (ctgrPrtBasic.libelle == it.libelle) {
                    listResult.remove(ctgrPrtBasic)
                }
            }
        }
        if (listResultfinal.size() > listResult.size()) {
            listResult = listResultfinal
            return true
        }
    }
    
    static def getCategorieFullName(objet, fullName){
        if (objet != null){
            fullName.add(objet.libelle)
            getCategorieFullName(objet.categorieParente, fullName)
        }
    }
        
    
    def getCategorieFullName(){
        def fullNameList = []
        
        getCategorieFullName(this, fullNameList)
        fullNameList = fullNameList.reverse()
        def fullName = ""
        fullNameList.each {
            fullName += "/" + it
        }
        return fullName
    }
    
    String toString() {
        return (getCategorieFullName())
    }
        
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof CategoriePartenaire)) {
            return false
        }      
        return that.libelle == libelle            
    } 
    

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["libelle"]) 
    } 
}

