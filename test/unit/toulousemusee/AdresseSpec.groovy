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
    void "test la validite d'une adresse valide"(String unNumero, String uneRue, int unCodePostal , String uneVille, Musee unMusee) {

        given: "adresse initialise avec des paramètres correct"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue,  codePostal: unCodePostal, ville: uneVille, musee: unMusee)

        expect: "l'adresse est valide"
        adresse.validate() == true

        where:
        unNumero| uneRue               | unCodePostal    | uneVille     | unMusee
        "17"    | "jean jaures"        | 31200           | "Toulouse"   |new Musee()
         ""     | "rue victor hugo"    | 31400           | "Toulouse"   |new Musee()
        null    | "rue marchand"       | 31000           | "Toulouse"   |new Musee()

    }

    @Unroll
    void "test l'invalidite d'une adresse non valide"(String unNumero, String uneRue, int unCodePostal, String uneVille, Musee unMusee) {

        given: "adresse initialise avec des paramètres incorrect"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille, musee: unMusee )

        expect: "l'adresse est invalide"
        adresse.validate() == false

        where:
        unNumero | uneRue               | unCodePostal  | uneVille      | unMusee
        "15"     |"rue"                 | 75000         | "Toulouse"    | new Musee()
        "20"     |""                    | 31000         | "Toulouse"    | new Musee()
        "14"     | null                 | 31000         | "Toulouse"    | new Musee()
        "15"     |"rue"                 | 0             | "Toulouse"    | new Musee()
        "4"      |"rue"                 | 31100         |  null         | new Musee()
        "1"      |"rue"                 | 31200         | ""            | new Musee()
    }
}