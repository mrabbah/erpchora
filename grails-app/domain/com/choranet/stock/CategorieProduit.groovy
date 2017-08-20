

package com.choranet.stock


import org.apache.commons.lang.builder.*
/**
 * CategorieProduit Domain Object 
 */
class CategorieProduit {	
    
    String code	
    	
    String intitule	
       		   
    
    static belongsTo = [categorieParente : CategorieProduit]
    
    static mapping = { 
        //categorieParente lazy : false
        categorieParente cascade:"save-update"
        cache usage : 'nonstrict-read-write'
        batchSize: 14
        sort "intitule"
    }
    static constraints = {
    
        code(blank : false, nullable : false)
    
        intitule(blank : false, nullable : false)
        
        categorieParente(nullable : true)
    
    }
    
    CategorieProduit(){
        
    }
    
    CategorieProduit(code, intitule, categorieParente){
        this.code = code
        this.intitule = intitule
        this.categorieParente = null
    }
	
    @Override 
    boolean equals(final Object that) {         
        if(that == null || !(that instanceof CategorieProduit)) {
            return false
        }      
        return that.id == id            
    }   
    
    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["id"]) 
    } 
    
    static def remove(listCtgrProduit, ctgrProdASupprime) {
        def newcategorieList = []
        
        if (listCtgrProduit == null)
        return null
            
        if (ctgrProdASupprime == null) 
        return listCtgrProduit
            
        listCtgrProduit.each { obj ->
            if ( obj != null && obj.code != ctgrProdASupprime.code)
            newcategorieList.add(obj)
        }
        return newcategorieList
        
    }
    
    static def removeAllSon(listCtgrProduit, ctgrProdASupprime) {
        
        if (listCtgrProduit == null)
        return null
            
        if (ctgrProdASupprime == null) 
        return listCtgrProduit
        
        def newList = listCtgrProduit
        listCtgrProduit.each { ctgrProd ->
            def fullname = []
            getCategorieFullName(ctgrProd, fullname)
            if (fullname.contains(ctgrProdASupprime.intitule)) {
                newList = remove(newList, ctgrProd)
            }
        }
        return newList
    }
    
    // function pour retourner la difference entre deux listes d'objets categorieProduit
    static def removeAll(listResult, listObject) {
        if (listResult == null || listResult.size()<1 || listObject == null || listObject.size()<1 )
        return false
            
        def listResultfinal = listResult.toList()
        listResultfinal.each { ctgrProdBasic ->
            listObject.each {
                if (ctgrProdBasic.code == it.code) {
                    listResult.remove(ctgrProdBasic)
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
            fullName.add(objet.intitule)
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
        return intitule
    }
    
}

