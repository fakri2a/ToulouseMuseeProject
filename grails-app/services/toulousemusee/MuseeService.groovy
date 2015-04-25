package toulousemusee

import grails.transaction.Transactional

@Transactional
class MuseeService {

    boolean transactional = true

    /**
     * Insere ou met à jour unMusee dans la base de donnée.
     * @param unMusee
     * @param uneAdresse
     * @param unGestionnaire
     * @return
     */
    Musee insertOrUpdateMusee(Musee unMusee, Adresse uneAdresse, Gestionnaire unGestionnaire) {
        unMusee.setAdresse(uneAdresse)
        uneAdresse.setMusee(unMusee)
        unMusee.setGestionnaire(unGestionnaire)
        unGestionnaire.addToMusees(unMusee)

        uneAdresse.save(flush: true)
        unGestionnaire.save(flush: true)
        unMusee.save(flush: true)

        return unMusee
    }

    def deleteMusee(Musee unMusee) {
        unMusee.gestionnaire.removeFromMusees(unMusee)
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
