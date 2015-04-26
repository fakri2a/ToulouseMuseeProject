package toulousemusee

import grails.gorm.PagedResultList

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MuseeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    MuseeService museeService


    /**
     *
     * @return
     */
    def doSearchMusee(Integer max) {

        params.max = Math.min(max ?: 5, 100)
        def museeList = museeService.searchMusees(params.nom,params.codePostal, params.rue)
        render(view: 'index', model: [museeInstanceList: museeList, museeInstanceCount: Musee.count()])
    }
    /**
     *
     * @return
     */
    def index(Integer max) {
        params.max = Math.min(max ?: 5, 100)
        respond Musee.list(params), model:[museeInstanceCount: Musee.count()]

    }
    /**
     *
     * @return
     */
    def ajouterFavoris() {

        ArrayList<String> listeFavoris = session.getAttribute("mesFavoris")
        listeFavoris? null : (listeFavoris = new ArrayList<String>())
        listeFavoris.contains(params.nom) ? false : listeFavoris.add(params.nom)
        session.setAttribute("mesFavoris",listeFavoris)
        render(view: 'index', model: [museeList: session.getAttribute("museeList")])
    }
    /**
     *
     * @return
     */
    def supprimerFavoris() {
        ArrayList<String> listeFavoris = session.getAttribute("mesFavoris")
        listeFavoris ? null : (listeFavoris = new ArrayList<String>())
        listeFavoris.remove(params.nom)
        session.setAttribute("mesFavoris", listeFavoris)
        render(view: ("/" + params.from), model: [museeList: session.getAttribute("museeList")])
    }

    /**
     *
     * @return
     */

    def formFav() {
        def musee = Musee.findById(params.id as long)
        render template: "formAddFavoris", model: [id: params.id, nom: musee.nom]
    }
    /**
     *
     * @param museeInstance
     * @return
     */
    def show(Musee museeInstance) {
        respond museeInstance
    }





    /**
     *
     */
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'musee.label', default: 'Musee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}