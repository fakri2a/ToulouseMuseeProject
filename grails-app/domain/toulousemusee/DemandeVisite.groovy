package toulousemusee

class DemandeVisite {
        String codeVisite
        Date debutPeriode
        Date finPeriode
        int nbPersonne
        String statut

    static constraints = {
        codeVisite nullable: false, blank: false, unique: true
        debutPeriode nullable: false
        finPeriode nullable: false
        nbPersonne min: 1, max:6
        statut nullable: false
    }


}
