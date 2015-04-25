package toulousemusee


import grails.gorm.PagedResultList;
import grails.test.mixin.*
import spock.lang.*

@TestFor(MuseeController)
@Mock([Musee, Gestionnaire, Adresse])
class MuseeControllerSpec extends Specification {

    Gestionnaire unGestionnaire
    Adresse uneAdresse

    def setup() {
        unGestionnaire = new Gestionnaire(name: "gestionnaire").save()
        uneAdresse = new Adresse(numero: 1, rue: "rue jean jaures", codePostale: 31000, ville: "Toulouse")
    }

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params["nom"] = 'Grevin'
        params["horairesOuverture"] = '17h'
        params["telephone"] = "0615208172"
        params["accesBus"] = "34"
        params["accesMetro"] = "metro jean jaures"
        params["adresse"] = uneAdresse
        params["gestionnaire"] = unGestionnaire
    }

    void "Test the index action returns the correct model"() {

        given:
        def resultList = mockFor(PagedResultList)
        resultList.demand.getTotalCount { -> return 0 }
        def museeService = mockFor(MuseeService)
        PagedResultList resultMock = resultList.createMock()
        museeService.demand.postalCode {  -> return [] }
        museeService.demand.search { -> return resultList}
        controller.museeService = museeService.createMock()

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.museeInstanceList
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def musee = new Musee(params)
        controller.show(musee)

        then: "A model is populated containing the domain instance"
        model.museeInstance == musee
    }
}