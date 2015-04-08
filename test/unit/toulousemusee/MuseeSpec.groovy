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
    void "test la validite d'un musee valide"(String unNom, String unHoraire, int unNum, String unAccesMetro, String unAccesBus, Adresse uneAdresse) {

        given: "un musee initialise avec ses parametres non vide"
        Musee musee = new Musee(nom: unNom, unHoraire: unHoraire, numeroTel: unNum, accesMetro: unAccesMetro, accesBus: unAccesBus, adresse: uneAdresse)

        expect: "le musee est valide"
        musee.validate() == true

        where:
        unNom    | unHoraire    |    unNum          |  unAccesMetro             |  unAccesBus       | uneAdresse
        "un nom" | "12h00"      |    "051502568"    |  "station jean jaures"    |   "saint agne"    |   "1 rue jean colbert"
        "un nom" | "13h05"      |    "051502568"    |  "station carme"          |   "saint agne"    |   "1 rue jean colbert"
        "un nom" | " "          |    " "            |  " "                      |   " "             |    null

    }

    @Unroll
    void "test l'invalidite d'un musee non valide"(String unNom, String _) {

        given: "un musee initialise avec un nom vide"
        Musee musee = new Musee(nom: unNom)

        expect: "le musee est invalide"
        musee.validate() == false

        where:
        unNom   | _
        null    | _
        ""      | _

    }
}