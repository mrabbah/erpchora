package com.choranet.commun


class ParametrageService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(Parametrage.class)
    }
        
    def getConfig() throws Exception {
        def config = this.list()
        def objet
        if (config == null || config == []){
            objet = new Parametrage()            
            objet = this.save(objet)
        }else {
            objet = config[0]
        }
        return objet
    }
        
    def isProduitPerissableManaged() throws Exception {
        return this.getConfig().isProduitPerissableManaged()
    }
        
    def isMultiEntrepotEnabled() throws Exception {
        return this.getConfig().isMultiEntrepotEnabled()
    }
}