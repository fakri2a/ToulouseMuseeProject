package toulousemusee


import spock.lang.*

/**
 *
 */
class ParseurCsvServiceIntegrationSpec extends Specification {
    def parseurCsvService

    String path

    void "test parseur du csv"() {
        given: "le path du fichier csv"
        path = "grails-app/ressources/Musee.csv"

        when:"on tente de parser le document"
        parseurCsvService.parse(path)

        then: "il y a pile 12 musées"
        Musee.list().size() == 12

        and: "le 1er musee est en base"
        Musee.findById(1) != null

        and: "le dernier musee est en base"
        !Musee.findById(12) != null
    }
}
