package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisite)
class DemandeVisiteSpec extends Specification {

    @Unroll
    void "test la validite d'une demande de visite d'un musee valide"(String unCode, Date uneDateDebut, Date uneDateFin, int unNombre, String unStatut ) {

        given: "une demande initialise avec ses parametres non vide"
        DemandeVisite demandeVisite = new DemandeVisite(codeVisite: unCode, debutPeriode: uneDateDebut, finPeriode: uneDateFin, nbPersonne: unNombre, statut: unStatut)

        expect: "demande de visite est valide"
        demandeVisite.validate() == true


        where:
        unCode    | uneDateDebut                |    uneDateFin              |  unNombre                 |  unStatut
        "50"      | new Date(2015,12,05)        |    new Date(2015,12,15)    |  4                       |   "En cours de traitement"
        "50"      | new Date(2015,12,15)        |    new Date(2015,12,30)    |  1                        |   "Confirmée"
        "50"      | new Date(2015,12,15)        |    new Date(2015,12,30)    |  5                      |   "Refusée"



    }

    @Unroll
    void "test l'invalidite d'une demande de visite non valide"(String unCode, Date uneDateDebut, Date uneDateFin, int unNombre, String unStatut) {

        given: "une demande de visite initialise avec ses paramatres  vide"
        DemandeVisite demandeVisite = new DemandeVisite(codeVisite: unCode, debutPeriode: uneDateDebut, finPeriode: uneDateFin, nbPersonne: unNombre, statut: unStatut)

        expect: "la demande de musee est invalide"
        demandeVisite.validate() == false

        where:
        unCode      | uneDateDebut               |    uneDateFin              |  unNombre                 |  unStatut
        "5"         | new Date(2015,12,05)       |    null                    |  4                       |   "En cours de traitement"
        "5"         | null                       |    new Date(2013,12,30)    |  5                        |   "Confirmée"
        "5"         | new Date(2015,12,15)       |    new Date(2012,12,15)    |  0                        |   "Refusée "
        ""          | new Date(2015,12,15)       |    new Date(2012,12,15)    |  0                        |   "Refusée "
        null        | new Date(2015,12,15)       |    new Date(2012,12,15)    |  0                        |   "Refusée "
        null        | new Date(2015,12,15)       |    new Date(2012,12,15)    |  0                        |    null
    }
}