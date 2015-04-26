package toulousemusee
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Musee)
class MuseeSpec extends Specification {

    @Unroll
    void "teste la validite d'un Musee valide"(String unNom, String unHoraireOuverture, String unTelephone, String unAccessMetro, String unAccessBus,
                                               Gestionnaire unGestionnaire, Adresse uneAdresse, Set<DemandeVisiteMusee> desDemandeVisiteMusee) {

        given: "une Musee initialise avec ses parametres valide"
        Musee musee = new Musee(nom: unNom, horairesOuverture: unHoraireOuverture, telephone: unTelephone, accessMetro: unAccessMetro, accessBus: unAccessBus,
                gestionnaire: unGestionnaire, adresse: uneAdresse, demandeVisiteMusees: desDemandeVisiteMusee)

        expect: "le Musee est valide"
        musee.validate() == true

        where:
        unNom       | unHoraireOuverture    | unTelephone   | unAccessMetro | unAccessBus   | unGestionnaire        | uneAdresse    | desDemandeVisiteMusee
        "Gravin"    | "7h00"                | "0615208172"  | "oui"         | "oui"         | new Gestionnaire()    | new Adresse() | new HashSet<DemandeVisiteMusee>()
        "Gravin"    | "7h00"                | "0615208172"  | null          | "oui"         | new Gestionnaire()    | new Adresse() | new HashSet<DemandeVisiteMusee>()
        "Gravin"    | "7h00"                | "0615208172"  | "oui"         | null          | new Gestionnaire()    | new Adresse() | new HashSet<DemandeVisiteMusee>()



    }

    @Unroll
    void "test l'invalidite d'un Musee non valide"(String unNom, String unHoraireOuverture, String unTelephone, String unAccessMetro, String unAccessBus,
                                                   Gestionnaire unGestionnaire, Adresse uneAdresse, Set<DemandeVisiteMusee> desDemandeVisiteMusee) {

        given: "un Musee initialisee avec un de ses parametres invalide"
        Musee musee = new Musee(nom: unNom, horairesOuverture: unHoraireOuverture, telephone: unTelephone, accessMetro: unAccessMetro,
                gestionnaire: unGestionnaire, adresse: uneAdresse, demandeVisiteMusees: desDemandeVisiteMusee)

        expect: "le Musee est invalide"
        musee.validate() == false

        where:
        unNom       | unHoraireOuverture    | unTelephone   | unAccessMetro | unAccessBus   | unGestionnaire        | uneAdresse    | desDemandeVisiteMusee
        ""          | "7h00"                | "0615208172"  | "oui"         | "non"         | new Gestionnaire()    | new Adresse() | new HashSet<DemandeVisiteMusee>()
        null        | "7h00"                | "0615208172"  | "oui"         | "oui"         | new Gestionnaire()    | new Adresse() | new HashSet<DemandeVisiteMusee>()
        "Gravin"    | ""                    | "0615208172"  | "oui"         | "oui"         | new Gestionnaire()    | new Adresse() | new HashSet<DemandeVisiteMusee>()
        "Gravin"    | null                  | "0615208172"  | "oui"         | "non"         | new Gestionnaire()    | new Adresse() | new HashSet<DemandeVisiteMusee>()
        "Gravin"    | "7h00"                | ""            | "oui"         | "oui"         | new Gestionnaire()    | new Adresse() | new HashSet<DemandeVisiteMusee>()
        "Gravin"    | "7h00"                | null          | "oui"         | "non"         | new Gestionnaire()    | new Adresse() | new HashSet<DemandeVisiteMusee>()
        //"Gravin"    | "7h00"                | "0615208172"  | null          | "oui"         | new Gestionnaire()    | new Adresse() | new HashSet<DemandeVisiteMusee>()
        //"Gravin"    | "7h00"                | "0615208172"  | "oui"         | null          | new Gestionnaire()    | new Adresse() | new HashSet<DemandeVisiteMusee>()

    }
}