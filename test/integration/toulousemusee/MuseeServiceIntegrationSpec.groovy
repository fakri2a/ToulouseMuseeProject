package toulousemusee



import spock.lang.*

/**
 *
 */
class MuseeServiceIntegrationSpec extends Specification {
    Adresse uneAdresse
    Gestionnaire unGestionnaire

    MuseeService museeService

    def setup() {
    }

    def cleanup() {
    }

    void "test insertion ou mise à jour d'un musee avec une adresse et un gestionnaire"() {

        given: "une adresse"
        uneAdresse = new Adresse(numero: "2",
                rue: "RUE DES ARCHIVES",
                codePostal: 31500,
                ville: "Toulouse")

        and: "un gestionnaire"
        unGestionnaire = new Gestionnaire(nom: "Mairie de Toulouse - DGA Culture")

        and: "un musée"
        Musee unMusee = new Musee(nom: "ARCHIVES MUNICIPALES TOULOUSE",
                gestionnaire: unGestionnaire,
                horairesOuverture: "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches, jours fériés et du 1er au 15 août",
                adresse: uneAdresse,
                numeroTel: "05 61 61 63 33",
                accesMetro: "Roseraie (A)",
                accesBus: "36, 38")

        when: "on tente de répercuter en base la création ou la modification du musée"
        Musee resultMusee = museeService.insertOrUpdateMusee(unMusee, uneAdresse, unGestionnaire)

        then: "le musée résultant pointe sur le musée initial"
        resultMusee == unMusee

        and:"le musée résultant n'a pas d'erreur"
        !resultMusee.hasErrors()

        and:"le musée résultant a un id"
        resultMusee.id

        and:"le musée est bien presente en base"
        Musee.findById(resultMusee.id) != null

        and: "l'activite a pour responsable le responsable passé en paramètre"
        resultActivite.responsable == unResponsable

        and:"le responsable a dans sa liste d'activité l'activité passé en paramètre"
        println ">>>>>>> classe pour activites : ${unResponsable.activites.class.name}"
        unResponsable.activites.contains(resultActivite)
    }

}
