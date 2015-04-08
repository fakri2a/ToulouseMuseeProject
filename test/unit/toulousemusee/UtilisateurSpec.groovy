package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Utilisateur)
class UtilisateurSpec extends Specification {

    @Unroll
    void "test la validite d'un utilisateur valide"(String unNom, String unPrenom, String unEmail, String unSexe, Date uneDateNaissance) {

        given: "un utilisateur initialise correctement"
        Utilisateur utilisateur = new Utilisateur(nom: unNom, prenom: unPrenom, email: unEmail, sexe: unSexe, dateNaissance: uneDateNaissance)

        expect: "l'utilisateur est valide"
        utilisateur.validate() == true

        and: "il n'est proprietaire d'aucune activite"
        !utilisateur.activites

        where:
        unNom    | unPrenom  | unEmail     | unSexe | uneDateNaissance
        "Dupont" | "Jeanne"  | "jd@jd.com" | "F"    | new Date(1972, 6, 17)
        "Durand" | "Jacques" | "jd@jd.com" | "M"    | new Date(1972, 6, 17)
        "Durand" | "Jacques" | "jd@jd.com" | "M"    | null

    }

    @Unroll
    void "test l'invalidite d'un utilisateur non valide"(String unNom, String unPrenom, String unEmail, String unSexe) {

        given: "un utilisateur initialise de maniere non valide"
        Utilisateur utilisateur = new Utilisateur(nom: unNom, prenom: unPrenom, email: unEmail, sexe: unSexe)

        expect: "l'utilisateur est invalide"
        utilisateur.validate() == false

        where:
        unNom    | unPrenom  | unEmail     | unSexe
        null     | "Jeanne"  | "jd@jd.com" | "F"
        ''       | "Jeanne"  | "jd@jd.com" | "F"
        "Dupont" | null      | "jd@jd.com" | "F"
        "Durand" | ""        | "jd@jd.com" | "M"
        "Durand" | "Jacques" | "jdjd.com"  | "M"
        "Durand" | "Jacques" | null        | "M"
        "Durand" | "Jacques" | "jd@jd.com" | "Z"
        "Durand" | "Jacques" | "jd@jd.com" | null


    }
}
