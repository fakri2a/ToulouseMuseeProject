package toulousemusee

class DemandeVisite {
        String codeVisite
        Date debutPeriode
        Date finPeriode
        int nbPersonne
        String statut

    static constraints = {

        codeViste nullable: false, blank: false, unique: true
        debutPeriode nullable: false
        finPeriode nullable: false
        nbPersonne min: 1
        statut inList: ["En cours de traitement","Confirmée","Refusée"]


    }
}
