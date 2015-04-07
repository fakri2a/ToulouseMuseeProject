package toulousemusee

class DemandeVisite {
        int codeVisite
        Date debutPeriode
        Date finPeriode
        int nbPersonne
        String statut

    static constraints = {

        debutPeriode nullable: true
        finPeriode nullable: true

    }
}
