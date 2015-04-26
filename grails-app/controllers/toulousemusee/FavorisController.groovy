package toulousemusee

import grails.transaction.Transactional

@Transactional
class FavorisController {

    /**
     *
     */
    FavorisService favorisService

    /**
     *
     * @param unMusee
     * @return
     */
    def ajouterAuxFavoris() {
        favorisService.ajouterFavoris(Musee.findByNom(params["nom"] as String))
        redirect(controller: "musee", action: "search")
    }

    /**
     *
     * @param unMusee
     * @return
     */
    def enleverFavoris() {
        favorisService.enleverFavoris(Musee.findByNom(params["nom"] as String))
        redirect(controller: "musee", action: "search")
    }

}
