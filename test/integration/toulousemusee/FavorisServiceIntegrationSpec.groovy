package toulousemusee


import spock.lang.*

/**
 *
 */
class FavorisServiceIntegrationSpec extends Specification {

    FavorisService favorisService
    Musee unMusee
    Adresse uneAdresse
    Gestionnaire unGestionnaire
    List<Musee> museesFavoris

    def setup() {
        unGestionnaire = new Gestionnaire(nom: "Mairie de Toulouse - DGA Culture")
        uneAdresse = new Adresse(numero: "2",
                rue: "RUE DES ARCHIVES UN",
                codePostal: 31500,
                ville: "Toulouse")
        museesFavoris = new ArrayList<>()
    }

    void "test ajout dans la liste des favoris"() {
        given: "un musée"
        unMusee = new Musee(nom: "ARCHIVES MUNICIPALES TOULOUSE",
                gestionnaire: unGestionnaire,
                horairesOuverture: "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches, jours fériés et du 1er au 15 août",
                adresse: uneAdresse,
                telephone: "05 61 61 63 33",
                accessMetro: "Roseraie (A)",
                accessBus: "36, 38")

        when: "on tente d'ajouter le musée dans la liste de favoris"
        favorisService.ajouterFavoris(unMusee)

        then: "la liste est initialisée à un élément"
        museesFavoris.size() == 1

        and:"le musée dans la liste est bien celui qui a été crée dans le setup"
        museesFavoris.get(0) == unMusee

        when: "on tente d'ajouter deux fois le même musée dans la liste de favoris"
        favorisService.ajouterFavoris(unMusee)
        favorisService.ajouterFavoris(unMusee)

        then: "la liste est bien initialisée à un élément"
        museesFavoris.size() == 1

    }

    void "test enlever un musee de la liste des favoris"() {
        given: "un musée"
        unMusee = new Musee(nom: "ARCHIVES MUNICIPALES TOULOUSE",
                gestionnaire: unGestionnaire,
                horairesOuverture: "Ouvert du mardi au samedi de 13h à 19hfermé les dimanches, jours fériés et du 1er au 15 août",
                adresse: uneAdresse,
                telephone: "05 61 61 63 33",
                accessMetro: "Roseraie (A)",
                accessBus: "36, 38")

        and: "la liste initialisé avec l'élément unMusee"
        favorisService.ajouterFavoris(unMusee)

        when: "on tente d'enlever le musée de la liste de favoris"
        favorisService.enleverFavoris(unMusee)

        then: "la liste est vide"
        museesFavoris.size() == 0
    }
}
