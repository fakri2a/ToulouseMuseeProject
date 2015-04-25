package toulousemusee

import grails.transaction.Transactional

@Transactional
class MuseeService {

    boolean transactional = true

    Musee insertOrUpdateMusee(Musee unMusee, Adresse uneAdresse, Gestionnaire unGestionnaire) {
        unMusee.setAdresse(uneAdresse)
        unMusee.setGestionnaire(unGestionnaire)
        unMusee.save()

        unMusee
    }

    def deleteMusee(Musee unMusee) {
        unMusee.adresse.delete()
        unMusee.delete()
    }

    List<Musee> searchMusees(String unNom, String unCodePostal, String uneAdresse) {
        def criteria = Musee.createCriteria()
        def List<Musee> result = criteria.list {
            if (unNom) {
                like("nom", "%${unNom}%")
            }
            if (unCodePostal) {
                like("codePostal", "%${unCodePostal}%")
            }
            if (uneAdresse) {
                like("adresse", "%${uneAdresse}%")
            }
        }
        result
    }
}
