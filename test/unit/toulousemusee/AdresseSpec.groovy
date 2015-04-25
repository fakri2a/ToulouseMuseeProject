package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Adresse)
class AdresseSpec extends Specification {

    @Unroll
    void "test la validite d'une adresse valide"(String unNumero, String uneRue, String unCodePostal , String uneVille) {

        given: "adresse initialise avec des paramètres correct"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue,  codePostal: unCodePostal, ville: uneVille)

        expect: "l'adresse est valide"
        adresse.validate() == true

        where:
        unNumero| uneRue               | unCodePostal      | uneVille
        "17"    | "jean jaures"        | "31200"           | "Toulouse"
         ""     | "rue victor hugo"    | "31400"           | "Toulouse"
        null    | "rue marchand"       | "31000"           | "Toulouse"

    }

    @Unroll
    void "test l'invalidite d'une adresse non valide"(String unNumero, String uneRue, String unCodePostal, String uneVille) {

        given: "adresse initialise avec des paramètres incorrect"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille )

        expect: "l'adresse est invalide"
        adresse.validate() == false

        where:
        unNumero | uneRue               | unCodePostal    | uneVille
        "15"     |"rue"                 | ""              | "Toulouse"
        "20"     |""                    | "31200"         | "Toulouse"
        "14"     | null                 | "31100"         | "Toulouse"
        "15"     |"rue"                 | null            | "Toulouse"
        "4"      |"rue"                 | "31100"         |  null
        "1"      |"rue"                 | "31200"         | ""
    }
}