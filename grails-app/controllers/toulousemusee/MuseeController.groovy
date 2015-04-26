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
        render(view: 'index', model: [museeInstanceList: museeList, museeInstanceCount: museeList.size()])
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
    def addToFavoris() {
        Map favorisList = session.getAttribute('favoris')
        if (request.post) {
            if (!favorisList) {
                favorisList = new HashMap()
            }
            favorisList.put(params.museeId as long, params.museeNom)
            favorisList.sort {it.value}
            session.setAttribute('favoris', favorisList)
        }
        render template: "favorislist"
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
     * @return
     */
    def deleteFromFavoris() {
        Map favorisList = session.getAttribute('favoris')
        if (request.post) {
            favorisList.remove(params.museeId as long)
            session.setAttribute('favoris', favorisList)
        }
        render template: "favorislist"
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