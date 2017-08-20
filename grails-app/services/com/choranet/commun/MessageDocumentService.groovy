
package com.choranet.commun;


/**
 * MessageDocumentService Service pour la gestion des opérations
 * transactionnelles pour l'objet MessageDocument
 */
class MessageDocumentService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(MessageDocument.class)
        }
        
        def getMessageParType (typedocument, typeMvt) { 
             def criteria = MessageDocument.createCriteria()
            def result = criteria.list {
                eq('type', typedocument)
                eq("typeMouvement", typeMvt)
            }
            return result
        }
        }
        
        
