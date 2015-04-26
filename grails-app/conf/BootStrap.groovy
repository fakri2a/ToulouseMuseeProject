import toulousemusee.ParseurCsvService

class BootStrap {

    ParseurCsvService parseurCsvService
    def init = { servletContext ->
        parseurCsvService.parse("grails-app/ressources/Musee.csv")
    }
    def destroy = {
    }
}
