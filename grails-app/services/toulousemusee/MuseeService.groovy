package toulousemusee

import grails.transaction.Transactional

@Transactional
class MuseeService {

    boolean transactional = true

    Musee insertOrUpdateMusee(Musee unMusee, Adresse uneAdresse, Gestionnaire unGestionnaire) {
        unMusee.setAdresse(uneAdresse)
        unMusee.save()

        unMusee
    }

    def deleteMusee(Musee unMusee) {
        unMusee.adresse.delete()
        unMusee.delete()
    }

    Musee searchMusee(String nom, String unCodePostal, String uneAdresse) {
        def criteria = Musee.createCriteria()
        def Musee result


        result
    }
}
