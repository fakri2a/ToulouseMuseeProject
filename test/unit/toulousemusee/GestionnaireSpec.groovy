package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Gestionnaire)
class GestionnaireSpec extends Specification {

    @Unroll
    void "test la validite d'un gestionnaire valide"(String unNom) {

        given: "un gestionnaire initialise avec ses parametres non vide"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le gestionnaire est valide"
        gestionnaire.validate() == true

        where:
        unNom
        "un nom"


    }

    @Unroll
    void "test l'invalidite d'un gestionnaire non valide"(String unNom) {

        given: "un gestionnaire initialise avec un nom vide"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le gestionnaire est invalide"
        gestionnaire.validate() == false

        where:
        unNom   | _
        null    | _
        ""      | _

    }
}