package toulousemusee

import grails.transaction.Transactional

@Transactional
class FavorisService {

    boolean transactional = true
    static scope = "session"

    List<Musee> museesFavoris

    def ajouterFavoris(Musee unMusee) {
        if (museesFavoris*.nom.contains(unMusee.nom))
            museesFavoris << unMusee
    }


    def enleverFavoris(Musee unMusee) {
        for (Musee m : museesFavoris)
            if (unMusee.nom == m.nom)
                museesFavoris.remove(m)
    }
}
