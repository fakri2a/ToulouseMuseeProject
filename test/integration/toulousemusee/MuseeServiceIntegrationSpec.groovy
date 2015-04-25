package toulousemusee



import spock.lang.*

/**
 *
 */
class MuseeServiceIntegrationSpec extends Specification {
    Adresse uneAdresse
    Adresse uneAdresseBis
    Adresse uneAdresseTierce
    Gestionnaire unGestionnaire

    MuseeService museeService

    def setup() {
        unGestionnaire = new Gestionnaire(nom: "Mairie de Toulouse - DGA Culture")
        uneAdresse = new Adresse(numero: "2",
                rue: "RUE DES ARCHIVES UN",
                codePostal: 31500,
                ville: "Toulouse")
        uneAdresseBis = new Adresse(numero: "3",
                rue: "RUE DES ARCHIVES BIS",
                codePostal: 31500,
                ville: "Toulouse")
        uneAdresseTierce = new Adresse(numero: "4",
                rue: "RUE DES ARCHIVES TIERCE",
                codePostal: 31500,
                ville: "Toulouse")
    }

    void "test insertion ou mise à jour d'un musee avec une adresse et un gestionnaire"() {

        given: "un musée"
        Musee unMusee = new Musee(nom: "ARCHIVES MUNICIPALES TOULOUSE",
                gestionnaire: unGestionnaire,
                horairesOuverture: "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches, jours fériés et du 1er au 15 août",
                adresse: uneAdresse,
                telephone: "05 61 61 63 33",
                accessMetro: "Roseraie (A)",
                accessBus: "36, 38")

        when: "on tente de répercuter en base la création ou la modification du musée"

        Musee resultMusee = museeService.insertOrUpdateMusee(unMusee, uneAdresse, unGestionnaire)

        then: "le musée résultant pointe sur le musée initial"
        resultMusee == unMusee

        and:"le musée résultant est valide"
        resultMusee.validate()

        and:"le musée résultant n'a pas d'erreur"
        !resultMusee.hasErrors()

        and:"le musée résultant a un id"
        resultMusee.id

        and:"le musée est bien presente en base"
        Musee.findById(resultMusee.id) != null
    }

    void "test suppression d'un musée"() {
        given: "un musée existant en base"
        Musee unMusee = new Musee(nom: "ARCHIVES MUNICIPALES TOULOUSE",
                gestionnaire: unGestionnaire,
                horairesOuverture: "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches, jours fériés et du 1er au 15 août",
                adresse: uneAdresse,
                telephone: "05 61 61 63 33",
                accessMetro: "Roseraie (A)",
                accessBus: "36, 38")

        when:"on tente de supprimer le musee"
        museeService.deleteMusee(unMusee)

        then:"le musee n'existe plus en base"
        Musee.findById(unMusee.id) == null
    }

    void "test recherche de musée(s)"() {
        given: "un jeu de test avec plusieurs musées"
        Musee unMusee = new Musee(nom: "ARCHIVES MUNICIPALES TOULOUSE",
                gestionnaire: unGestionnaire,
                horairesOuverture: "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches, jours fériés et du 1er au 15 août",
                adresse: uneAdresse,
                telephone: "05 61 61 63 33",
                accessMetro: "Roseraie (A)",
                accessBus: "36, 38")
        Musee unMuseeBis = new Musee(nom: "LE MEGA MUSEE DE TOULOUSE",
                gestionnaire: unGestionnaire,
                horairesOuverture: "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches, jours fériés et du 1er au 15 août",
                adresse: uneAdresseBis,
                telephone: "05 61 61 63 34",
                accessMetro: "Roseraie (A)",
                accessBus: "36, 38")
        Musee unMuseeTierce = new Musee(nom: "UN MUSEE PERDU DANS TOULOUSE",
                gestionnaire: unGestionnaire,
                horairesOuverture: "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches, jours fériés et du 1er au 15 août",
                adresse: uneAdresseTierce,
                telephone: "05 61 61 63 35",
                accessMetro: "Roseraie (A)",
                accessBus: "36, 38")

        museeService.insertOrUpdateMusee(unMusee, uneAdresse, unGestionnaire)
        museeService.insertOrUpdateMusee(unMuseeBis, uneAdresseBis, unGestionnaire)
        museeService.insertOrUpdateMusee(unMuseeTierce, uneAdresseTierce, unGestionnaire)

        when:"on cherche les musées dont le nom est ARCHIVES MUNICIPALES TOULOUSE TIERCE"
        List<Musee> resultatRechercheMusees = museeService.searchMusees(
                "ARCHIVES MUNICIPALES TOULOUSE", "", "", "", "")

        then:"on trouve le bon musée"
        resultatRechercheMusees.size() == 1
        resultatRechercheMusees.contains(unMusee)
        resultatRechercheMusees.clear()


        when:"on cherche les musées dont l'adresse est RUE DES ARCHIVES UN"
        resultatRechercheMusees = museeService.searchMusees(
                "", "", "RUE DES ARCHIVES UN", "", "")

        then:"on trouve le bon musée"
        resultatRechercheMusees.size() == 1
        resultatRechercheMusees.contains(unMusee)
        resultatRechercheMusees.clear()

        when:"on cherche les musées dans le 31500"
        resultatRechercheMusees = museeService.searchMusees(
                "", "31500", "", "", "")

        then:"on trouve les bons musées"
        resultatRechercheMusees.size() == 3
        resultatRechercheMusees.contains(unMusee)
        resultatRechercheMusees.contains(unMuseeBis)
        resultatRechercheMusees.contains(unMuseeTierce)
        resultatRechercheMusees.clear()


        when:"on cherche les musées dont l'acces au metro est Roseraie (A)"
        resultatRechercheMusees = museeService.searchMusees(
                "", "", "", "", "Roseraie (A)")

        then:"on trouve les bon musées"
        resultatRechercheMusees.size() == 3
        resultatRechercheMusees.contains(unMusee)
        resultatRechercheMusees.contains(unMuseeBis)
        resultatRechercheMusees.contains(unMuseeTierce)
        resultatRechercheMusees.clear()

        when:"on cherche les musées dont l'acces au bus est 36, 38"
        resultatRechercheMusees = museeService.searchMusees(
                "", "", "", "36, 38", "")

        then:"on trouve les bon musées"
        resultatRechercheMusees.size() == 3
        resultatRechercheMusees.contains(unMusee)
        resultatRechercheMusees.contains(unMuseeBis)
        resultatRechercheMusees.contains(unMuseeTierce)
        resultatRechercheMusees.clear()
    }

}
