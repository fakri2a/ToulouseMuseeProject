package toulousemusee



import spock.lang.*

/**
 *
 */
class DemandeVisiteMuseeServiceIntegrationSpec extends Specification {

    DemandeVisiteMuseeService demandeVisiteMuseeService
    MuseeService museeService

    DemandeVisite demandeVisite
    Musee unMusee
    Adresse uneAdresse
    Gestionnaire unGestionnaire

    def setup() {
        demandeVisite = new DemandeVisite(codeVisite: 1, debutPeriode: new Date(2015, 5, 5),
            finPeriode: new Date(2015, 5, 7), nbPersonne: 20, statut: "en cours")

        unGestionnaire = new Gestionnaire(nom: "Mairie de Toulouse - DGA Culture")
        uneAdresse = new Adresse(numero: "2",
                rue: "RUE DES ARCHIVES",
                codePostal: 31500,
                ville: "Toulouse")

        demandeVisite.save(flush: true)
    }

    void "test insertion ou mise à jour d'une demande de visite d'un musée"() {

        given: "un musée"
            unMusee = new Musee(nom: "ARCHIVES MUNICIPALES TOULOUSE",
                gestionnaire: unGestionnaire,
                horairesOuverture: "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches, jours fériés et du 1er au 15 août",
                adresse: uneAdresse,
                telephone: "05 61 61 63 33",
                accessMetro: "Roseraie (A)",
                accessBus: "36, 38")
        museeService.insertOrUpdateMusee(unMusee, uneAdresse, unGestionnaire)

        when: "on tente de répercuter en base la création ou la modification d'une demande de visite d'un musée"
        DemandeVisiteMusee resultDemande = demandeVisiteMuseeService.
                insertOrUpdateDemandeVisiteMusee(unMusee, demandeVisite, new Date())

        then: "la demande a bien été ajoutée"
        DemandeVisiteMusee.count() == 1

        and: "la demande resultante pointe sur celle en base"
        DemandeVisiteMusee.findByMusee(unMusee) == resultDemande

        and:"la demande résultante est valide"
        resultDemande.validate()

        and:"la demande résultante n'a pas d'erreur"
        !resultDemande.hasErrors()

        and:"la demande résultante a un id"
        resultDemande.id
    }
}
