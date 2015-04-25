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

    List<Musee> searchMusees(String unNom, String unCodePostal, String uneVille, String unAccesBus, String unAccesMetro) {
        def criteria = Musee.createCriteria()
        List<Musee> result = criteria.list {
            if (unNom) {
                ilike("nom", "%${unNom}%")
            }


            if (unCodePostal) {
                int code = Integer.parseInt(unCodePostal)
                adresse {
                    eq ("codePostal", code)
                }
            }

            if (uneVille) {
                adresse {
                    like("ville", "%${uneVille}%")
                }
            }

            if (unAccesBus) {
                like("accessBus", "%${unAccesBus}%")
            }
            if (unAccesMetro) {
                like("accessMetro", "%${unAccesMetro}%")
            }
        }
        result
    }
}
