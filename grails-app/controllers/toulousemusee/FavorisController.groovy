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
    def ajouterFavoris() {
        favorisService.ajouterFavoris(Musee.findById(params["id"] as long))
        redirect(controller: "musee", action: "search")
    }

    /**
     *
     * @param unMusee
     * @return
     */
    def enleverFavoris() {
        favorisService.enleverFavoris(Musee.findById(params["id"] as long))
        redirect(controller: "musee", action: "search")
    }

}
