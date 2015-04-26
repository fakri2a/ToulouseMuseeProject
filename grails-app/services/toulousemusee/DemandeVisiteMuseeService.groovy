package toulousemusee

import grails.transaction.Transactional

@Transactional
class DemandeVisiteMuseeService {


    DemandeVisiteMusee insertOrUpdateDemandeVisiteMusee(Musee musee, DemandeVisite visite, Date date) {
        DemandeVisiteMusee demandeVisiteMusee = new DemandeVisiteMusee(dateDemande : date)
        demandeVisiteMusee.setDemandeVisite(visite)
        demandeVisiteMusee.setMusee(musee)

        demandeVisiteMusee.save(flush: true)
        demandeVisiteMusee
    }



}
