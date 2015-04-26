package toulousemusee

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(FavorisController)
@Mock([Adresse, Gestionnaire, Musee])
class FavorisControllerSpec extends Specification {

    Adresse uneAdresse
    Gestionnaire unGestionnaire
    List<Musee> museesFavoris

    def setup() {
        controller.favorisService = new FavorisService()
        unGestionnaire = new Gestionnaire(nom: "Mairie de Toulouse - DGA Culture")
        uneAdresse = new Adresse(numero: "2",
                rue: "RUE DES ARCHIVES UN",
                codePostal: 31500,
                ville: "Toulouse")
        museesFavoris = new ArrayList<>()
    }

    def populateValidParams(params) {
        assert params != null
        params["id"] = '1'
        params["nom"] = 'ARCHIVES MUNICIPALES TOULOUSE'
        params["horairesOuverture"] = 'Ouvert du mardi au' +
                ' samedi de 13h à 19hfermé les dimanches, jours fériés et du 1er au 15 août'
        params["telephone"] = '05 61 61 63 33'
        params["accessBus"] = '36, 38'
        params["accessMetro"] = 'Roseraie (A)'
        params["adresse"] = uneAdresse
        params["gestionnaire"] = unGestionnaire
    }

    void "test ajout dans la liste des favoris"() {
        when: "on tente d'ajouter le musée dans la liste de favoris"
        controller.ajouterFavoris()

        then: "la liste est initialisée à un élément"
        museesFavoris.size() == 1

        and:"le musée dans la liste est bien celui qui a été crée dans le setup"
        museesFavoris.get(0) == Musee.findById(1)
    }

    void "test enlever un musee de la liste des favoris"() {
        and: "la liste initialisé avec l'élément unMusee"
        controller.ajouterFavoris()

        when: "on tente d'enlever le musée de la liste de favoris"
        controller.enleverFavoris()

        then: "la liste est vide"
        museesFavoris.size() == 0
    }
}
