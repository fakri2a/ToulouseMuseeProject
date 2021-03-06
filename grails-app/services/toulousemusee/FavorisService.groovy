package toulousemusee

import grails.transaction.Transactional

@Transactional
class FavorisService {

    boolean transactional = true

    List<Musee> museesFavoris

    def ajouterFavoris(Musee unMusee) {
            museesFavoris << unMusee
    }


    def enleverFavoris(Musee unMusee) {
        for (Musee m : museesFavoris)
            if (unMusee.nom == m.nom)
                museesFavoris.remove(m)
    }
}
